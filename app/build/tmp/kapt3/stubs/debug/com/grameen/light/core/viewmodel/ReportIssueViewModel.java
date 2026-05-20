package com.grameen.light.core.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u0018\u0000 \u00172\u00020\u0001:\u0001\u0017B\u0015\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0006\u0010\u000e\u001a\u00020\u000fJ(\u0010\u0010\u001a\u00020\u000f2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u00122\b\u0010\u0015\u001a\u0004\u0018\u00010\u0016R\u0014\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\u0018"}, d2 = {"Lcom/grameen/light/core/viewmodel/ReportIssueViewModel;", "Landroidx/lifecycle/ViewModel;", "authRepository", "Lcom/grameen/light/core/repository/AuthRepository;", "complaintRepository", "Lcom/grameen/light/core/repository/ComplaintRepository;", "(Lcom/grameen/light/core/repository/AuthRepository;Lcom/grameen/light/core/repository/ComplaintRepository;)V", "_uiState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/grameen/light/core/viewmodel/ReportIssueUiState;", "uiState", "Lkotlinx/coroutines/flow/StateFlow;", "getUiState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearMessage", "", "submit", "poleId", "", "issueType", "description", "imageUri", "Landroid/net/Uri;", "Companion", "app_debug"})
public final class ReportIssueViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.AuthRepository authRepository = null;
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.ComplaintRepository complaintRepository = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.MutableStateFlow<com.grameen.light.core.viewmodel.ReportIssueUiState> _uiState = null;
    @org.jetbrains.annotations.NotNull
    private final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.ReportIssueUiState> uiState = null;
    private static final long SUBMIT_TIMEOUT_MS = 60000L;
    @org.jetbrains.annotations.NotNull
    public static final com.grameen.light.core.viewmodel.ReportIssueViewModel.Companion Companion = null;
    
    public ReportIssueViewModel(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.AuthRepository authRepository, @org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.ComplaintRepository complaintRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.StateFlow<com.grameen.light.core.viewmodel.ReportIssueUiState> getUiState() {
        return null;
    }
    
    public final void submit(@org.jetbrains.annotations.NotNull
    java.lang.String poleId, @org.jetbrains.annotations.NotNull
    java.lang.String issueType, @org.jetbrains.annotations.NotNull
    java.lang.String description, @org.jetbrains.annotations.Nullable
    android.net.Uri imageUri) {
    }
    
    public final void clearMessage() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000$\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0016\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nR\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u000b"}, d2 = {"Lcom/grameen/light/core/viewmodel/ReportIssueViewModel$Companion;", "", "()V", "SUBMIT_TIMEOUT_MS", "", "factory", "Landroidx/lifecycle/ViewModelProvider$Factory;", "authRepository", "Lcom/grameen/light/core/repository/AuthRepository;", "complaintRepository", "Lcom/grameen/light/core/repository/ComplaintRepository;", "app_debug"})
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