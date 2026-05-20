package com.grameen.light.core.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\u0018\u0000 \u00102\u00020\u0001:\u0001\u0010B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\b\u0010\u000e\u001a\u00020\u000fH\u0002R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0011"}, d2 = {"Lcom/grameen/light/core/viewmodel/MyIssuesViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/grameen/light/core/repository/AuthRepository;", "complaintRepository", "Lcom/grameen/light/core/repository/ComplaintRepository;", "(Lcom/grameen/light/core/repository/AuthRepository;Lcom/grameen/light/core/repository/ComplaintRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/grameen/light/core/viewmodel/MyIssuesUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "observeComplaints", "", "Companion", "app_debug"})
public final class MyIssuesViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.ComplaintRepository complaintRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.grameen.light.core.viewmodel.MyIssuesUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.MyIssuesUiState> uiState = null;
    @org.jetbrains.annotations.NotNull
    public static final com.grameen.light.core.viewmodel.MyIssuesViewModel.Companion Companion = null;
    
    public MyIssuesViewModel(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.ComplaintRepository complaintRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.MyIssuesUiState> getUiState() {
        return null;
    }
    
    private final void observeComplaints() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001e\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b\u00a8\u0006\t"}, d2 = {"Lcom/grameen/light/core/viewmodel/MyIssuesViewModel$Companion;", "", "()V", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "authRepository", "Lcom/grameen/light/core/repository/AuthRepository;", "complaintRepository", "Lcom/grameen/light/core/repository/ComplaintRepository;", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull
        public final androidx.lifecycle.ViewModelProvider.Factory factory(@org.jetbrains.annotations.NotNull
        com.grameen.light.core.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull
        com.grameen.light.core.repository.ComplaintRepository complaintRepository) {
            return null;
        }
    }
}