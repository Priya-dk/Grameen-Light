package com.grameen.light.core.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000H\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\n\u0018\u0000  2\u00020\u0001:\u0001 B\u001b\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005\u00a2\u0006\u0002\u0010\u0007J.\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00100\u000f2\u0006\u0010\u0012\u001a\u00020\u00132\b\u0010\u0014\u001a\u0004\u0018\u00010\u0015H\u0002J\u0006\u0010\u0016\u001a\u00020\u0017J\u0006\u0010\u0018\u001a\u00020\u0017J\u0006\u0010\u0019\u001a\u00020\u0017J\u000e\u0010\u001a\u001a\u00020\u00172\u0006\u0010\u001b\u001a\u00020\u0010J\u0010\u0010\u001c\u001a\u00020\u00172\b\u0010\u001d\u001a\u0004\u0018\u00010\u0015J\u000e\u0010\u001e\u001a\u00020\u00172\u0006\u0010\u001f\u001a\u00020\u0013R\u0014\u0010\b\u001a\b\u0012\u0004\u0012\u00020\n0\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\n0\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006!"}, d2 = {"Lcom/grameen/light/core/viewmodel/MapViewModel;", "Landroidx/lifecycle/ViewModel;", "poleRepository", "Lcom/grameen/light/core/repository/PoleRepository;", "connectivity", "Lkotlinx/coroutines/flow/StateFlow;", "", "(Lcom/grameen/light/core/repository/PoleRepository;Lkotlinx/coroutines/flow/StateFlow;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/grameen/light/core/viewmodel/MapUiState;", "uiState", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "applyFilter", "", "Lcom/grameen/light/core/model/Pole;", "poles", "query", "", "status", "Lcom/grameen/light/core/model/PoleStatus;", "clearSelectedPole", "", "refreshPoles", "seedIfNeeded", "selectPole", "pole", "setFilter", "filter", "setSearch", "text", "Companion", "app_debug"})
public final class MapViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.PoleRepository poleRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> connectivity = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.grameen.light.core.viewmodel.MapUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.MapUiState> uiState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.grameen.light.core.viewmodel.MapViewModel.Companion Companion = null;
    
    public MapViewModel(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.PoleRepository poleRepository, @org.jetbrains.annotations.NotNull
    kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> connectivity) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.MapUiState> getUiState() {
        return null;
    }
    
    public final void seedIfNeeded() {
    }
    
    public final void setSearch(@org.jetbrains.annotations.NotNull
    java.lang.String text) {
    }
    
    public final void setFilter(@org.jetbrains.annotations.Nullable
    com.grameen.light.core.model.PoleStatus filter) {
    }
    
    public final void selectPole(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.model.Pole pole) {
    }
    
    public final void clearSelectedPole() {
    }
    
    public final void refreshPoles() {
    }
    
    private final java.util.List<com.grameen.light.core.model.Pole> applyFilter(java.util.List<com.grameen.light.core.model.Pole> poles, java.lang.String query, com.grameen.light.core.model.PoleStatus status) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\"\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000b\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u001c\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a8\u0006\n"}, d2 = {"Lcom/grameen/light/core/viewmodel/MapViewModel$Companion;", "", "()V", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "repository", "Lcom/grameen/light/core/repository/PoleRepository;", "connectivity", "Lkotlinx/coroutines/flow/StateFlow;", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.lifecycle.ViewModelProvider.Factory factory(@org.jetbrains.annotations.NotNull
        com.grameen.light.core.repository.PoleRepository repository, @org.jetbrains.annotations.NotNull
        kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> connectivity) {
            return null;
        }
    }
}