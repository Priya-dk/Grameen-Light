package com.grameen.light.data.repository

import com.grameen.light.data.model.Pole
import com.grameen.light.data.model.PoleStatus
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class PoleRepository {
    
    private val poles = mutableListOf<Pole>()
    
    init {
        // Sample data
        poles.addAll(
            listOf(
                Pole("Pole-001", "Main Road, Sector 1", PoleStatus.WORKING, 28.6139, 77.2090),
                Pole("Pole-002", "Main Road, Sector 1", PoleStatus.FUSED, 28.6140, 77.2091),
                Pole("Pole-003", "Market Street", PoleStatus.WORKING, 28.6141, 77.2092),
                Pole("Pole-004", "School Road", PoleStatus.DAYTIME_ON, 28.6142, 77.2093),
                Pole("Pole-005", "Hospital Road", PoleStatus.WORKING, 28.6143, 77.2094),
                Pole("Pole-006", "Temple Street", PoleStatus.FUSED, 28.6144, 77.2095),
                Pole("Pole-007", "Park Area", PoleStatus.WORKING, 28.6145, 77.2096),
                Pole("Pole-008", "Community Center", PoleStatus.WORKING, 28.6146, 77.2097),
                Pole("Pole-009", "Bus Stand", PoleStatus.FUSED, 28.6147, 77.2098),
                Pole("Pole-010", "Railway Station", PoleStatus.WORKING, 28.6148, 77.2099)
            )
        )
    }
    
    fun getAllPoles(): Flow<List<Pole>> = flowOf(poles)
    
    fun getPolesByStatus(status: PoleStatus): Flow<List<Pole>> {
        return flowOf(poles.filter { it.status == status })
    }
    
    fun getPoleById(id: String): Pole? {
        return poles.find { it.id == id }
    }
    
    fun updatePoleStatus(id: String, status: PoleStatus) {
        val index = poles.indexOfFirst { it.id == id }
        if (index != -1) {
            poles[index] = poles[index].copy(status = status)
        }
    }
}
