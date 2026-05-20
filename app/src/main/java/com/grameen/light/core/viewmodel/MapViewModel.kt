package com.grameen.light.core.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.grameen.light.core.model.Pole
import com.grameen.light.core.model.PoleStatus
import com.grameen.light.core.repository.PoleRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.launch

data class MapUiState(
    val poles: List<Pole> = emptyList(),
    val filteredPoles: List<Pole> = emptyList(),
    val selectedPole: Pole? = null,
    val searchText: String = "",
    val statusFilter: PoleStatus? = null,
    val loading: Boolean = false,
    val offline: Boolean = false
)

class MapViewModel(
    private val poleRepository: PoleRepository,
    private val connectivity: StateFlow<Boolean>
) : ViewModel() {
    private val _uiState = MutableStateFlow(MapUiState(loading = true))
    val uiState: StateFlow<MapUiState> = _uiState.asStateFlow()

    init {
        viewModelScope.launch {
            combine(
                poleRepository.observePoles(),
                connectivity
            ) { poles, isOnline ->
                poles to isOnline
            }.collect { (poles, isOnline) ->
                val current = _uiState.value
                _uiState.value = current.copy(
                    poles = poles,
                    filteredPoles = applyFilter(poles, current.searchText, current.statusFilter),
                    loading = false,
                    offline = !isOnline
                )
            }
        }

        viewModelScope.launch {
            poleRepository.syncPoles()
        }

        viewModelScope.launch {
            poleRepository.observePolesRealtime().collect {
                poleRepository.syncPoles()
            }
        }
    }

    fun seedIfNeeded() {
        viewModelScope.launch {
            poleRepository.seedPolesIfEmpty()
            poleRepository.syncPoles()
        }
    }

    fun setSearch(text: String) {
        val current = _uiState.value
        _uiState.value = current.copy(
            searchText = text,
            filteredPoles = applyFilter(current.poles, text, current.statusFilter)
        )
    }

    fun setFilter(filter: PoleStatus?) {
        val current = _uiState.value
        _uiState.value = current.copy(
            statusFilter = filter,
            filteredPoles = applyFilter(current.poles, current.searchText, filter)
        )
    }

    fun selectPole(pole: Pole) {
        _uiState.value = _uiState.value.copy(selectedPole = pole)
    }

    fun clearSelectedPole() {
        _uiState.value = _uiState.value.copy(selectedPole = null)
    }

    fun refreshPoles() {
        viewModelScope.launch {
            poleRepository.syncPoles()
        }
    }

    private fun applyFilter(poles: List<Pole>, query: String, status: PoleStatus?): List<Pole> {
        return poles.filter { pole ->
            val statusMatch = status == null || pole.status == status
            val queryMatch = query.isBlank() ||
                pole.poleId.contains(query, ignoreCase = true) ||
                pole.sector.contains(query, ignoreCase = true)
            statusMatch && queryMatch
        }
    }

    companion object {
        fun factory(repository: PoleRepository, connectivity: StateFlow<Boolean>): ViewModelProvider.Factory = object : ViewModelProvider.Factory {
            @Suppress("UNCHECKED_CAST")
            override fun <T : ViewModel> create(modelClass: Class<T>): T = MapViewModel(repository, connectivity) as T
        }
    }
}
