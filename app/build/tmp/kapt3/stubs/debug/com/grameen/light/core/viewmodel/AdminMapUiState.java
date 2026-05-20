package com.grameen.light.core.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0015\b\u0086\b\u0018\u00002\u00020\u0001BE\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u0012\b\b\u0002\u0010\u0007\u001a\u00020\b\u0012\b\b\u0002\u0010\t\u001a\u00020\n\u0012\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\rJ\u000f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003H\u00c6\u0003J\t\u0010\u0019\u001a\u00020\bH\u00c6\u0003J\t\u0010\u001a\u001a\u00020\nH\u00c6\u0003J\u000b\u0010\u001b\u001a\u0004\u0018\u00010\fH\u00c6\u0003JI\u0010\u001c\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u00032\b\b\u0002\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\n2\n\b\u0002\u0010\u000b\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010\u001d\u001a\u00020\n2\b\u0010\u001e\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u001f\u001a\u00020\bH\u00d6\u0001J\t\u0010 \u001a\u00020\fH\u00d6\u0001R\u0013\u0010\u000b\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u0011\u0010\u0007\u001a\u00020\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0011R\u0011\u0010\t\u001a\u00020\n\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u0013R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u0015R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00060\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0015\u00a8\u0006!"}, d2 = {"Lcom/grameen/light/core/viewmodel/AdminMapUiState;", "", "poles", "", "Lcom/grameen/light/core/model/Pole;", "recentAlerts", "Lcom/grameen/light/core/model/Complaint;", "infrastructureLoadPercent", "", "loading", "", "error", "", "(Ljava/util/List;Ljava/util/List;IZLjava/lang/String;)V", "getError", "()Ljava/lang/String;", "getInfrastructureLoadPercent", "()I", "getLoading", "()Z", "getPoles", "()Ljava/util/List;", "getRecentAlerts", "component1", "component2", "component3", "component4", "component5", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class AdminMapUiState {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.grameen.light.core.model.Pole> poles = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.grameen.light.core.model.Complaint> recentAlerts = null;
    private final int infrastructureLoadPercent = 0;
    private final boolean loading = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String error = null;
    
    public AdminMapUiState(@org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Pole> poles, @org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> recentAlerts, int infrastructureLoadPercent, boolean loading, @org.jetbrains.annotations.Nullable
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Pole> getPoles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> getRecentAlerts() {
        return null;
    }
    
    public final int getInfrastructureLoadPercent() {
        return 0;
    }
    
    public final boolean getLoading() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getError() {
        return null;
    }
    
    public AdminMapUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Pole> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> component2() {
        return null;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final boolean component4() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.grameen.light.core.viewmodel.AdminMapUiState copy(@org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Pole> poles, @org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> recentAlerts, int infrastructureLoadPercent, boolean loading, @org.jetbrains.annotations.Nullable
    java.lang.String error) {
        return null;
    }
    
    @java.lang.Override
    public boolean equals(@org.jetbrains.annotations.Nullable
    java.lang.Object other) {
        return false;
    }
    
    @java.lang.Override
    public int hashCode() {
        return 0;
    }
    
    @java.lang.Override
    @org.jetbrains.annotations.NotNull
    public java.lang.String toString() {
        return null;
    }
}