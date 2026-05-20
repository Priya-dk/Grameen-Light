package com.grameen.light.core.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00162\u00020\u0001:\u0001\u0016B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ\u001e\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0015R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0017"}, d2 = {"Lcom/grameen/light/core/viewmodel/AdminMapViewModel;", "Landroidx/lifecycle/ViewModel;", "poleRepository", "Lcom/grameen/light/core/repository/PoleRepository;", "complaintRepository", "Lcom/grameen/light/core/repository/ComplaintRepository;", "(Lcom/grameen/light/core/repository/PoleRepository;Lcom/grameen/light/core/repository/ComplaintRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/grameen/light/core/viewmodel/AdminMapUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "dispatchComplaint", "complaintId", "", "workerName", "status", "Lcom/grameen/light/core/model/ComplaintStatus;", "Companion", "app_debug"})
public final class AdminMapViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.PoleRepository poleRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.ComplaintRepository complaintRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.grameen.light.core.viewmodel.AdminMapUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.AdminMapUiState> uiState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.grameen.light.core.viewmodel.AdminMapViewModel.Companion Companion = null;
    
    public AdminMapViewModel(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.PoleRepository poleRepository, @org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.ComplaintRepository complaintRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.AdminMapUiState> getUiState() {
        return null;
    }
    
    public final void dispatchComplaint(@org.jetbrains.annotations.NotNull
    java.lang.String complaintId, @org.jetbrains.annotations.NotNull
    java.lang.String workerName, @org.jetbrains.annotations.NotNull
    com.grameen.light.core.model.ComplaintStatus status) {
    }
    
    public final void clearError() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/grameen/light/core/viewmodel/AdminMapViewModel$Companion;", "", "()V", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "poleRepository", "Lcom/grameen/light/core/repository/PoleRepository;", "complaintRepository", "Lcom/grameen/light/core/repository/ComplaintRepository;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.lifecycle.ViewModelProvider.Factory factory(@org.jetbrains.annotations.NotNull
        com.grameen.light.core.repository.PoleRepository poleRepository, @org.jetbrains.annotations.NotNull
        com.grameen.light.core.repository.ComplaintRepository complaintRepository) {
            return null;
        }
    }
}