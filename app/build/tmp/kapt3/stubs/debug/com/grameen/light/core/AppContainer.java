package com.grameen.light.core;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000<\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\b\u00c6\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u0019R\u001e\u0010\u0005\u001a\u00020\u00042\u0006\u0010\u0003\u001a\u00020\u0004@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u001e\u0010\t\u001a\u00020\b2\u0006\u0010\u0003\u001a\u00020\b@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000bR\u000e\u0010\f\u001a\u00020\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u001e\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0003\u001a\u00020\u000e@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u001e\u0010\u0013\u001a\u00020\u00122\u0006\u0010\u0003\u001a\u00020\u0012@BX\u0086.\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015\u00a8\u0006\u001a"}, d2 = {"Lcom/grameen/light/core/AppContainer;", "", "()V", "<set-?>", "Lcom/grameen/light/core/repository/AuthRepository;", "authRepository", "getAuthRepository", "()Lcom/grameen/light/core/repository/AuthRepository;", "Lcom/grameen/light/core/repository/ComplaintRepository;", "complaintRepository", "getComplaintRepository", "()Lcom/grameen/light/core/repository/ComplaintRepository;", "initialized", "", "Lcom/grameen/light/core/data/NetworkMonitor;", "networkMonitor", "getNetworkMonitor", "()Lcom/grameen/light/core/data/NetworkMonitor;", "Lcom/grameen/light/core/repository/PoleRepository;", "poleRepository", "getPoleRepository", "()Lcom/grameen/light/core/repository/PoleRepository;", "init", "", "context", "Landroid/content/Context;", "app_debug"})
public final class AppContainer {
    @kotlin.jvm.Volatile
    private static volatile boolean initialized = false;
    private static com.grameen.light.core.repository.AuthRepository authRepository;
    private static com.grameen.light.core.repository.PoleRepository poleRepository;
    private static com.grameen.light.core.repository.ComplaintRepository complaintRepository;
    private static com.grameen.light.core.data.NetworkMonitor networkMonitor;
    @org.jetbrains.annotations.NotNull
    public static final com.grameen.light.core.AppContainer INSTANCE = null;
    
    private AppContainer() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.grameen.light.core.repository.AuthRepository getAuthRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.grameen.light.core.repository.PoleRepository getPoleRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.grameen.light.core.repository.ComplaintRepository getComplaintRepository() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.grameen.light.core.data.NetworkMonitor getNetworkMonitor() {
        return null;
    }
    
    public final void init(@org.jetbrains.annotations.NotNull
    android.content.Context context) {
    }
}