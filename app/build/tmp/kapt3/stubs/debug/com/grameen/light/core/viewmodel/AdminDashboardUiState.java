package com.grameen.light.core.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b \b\u0086\b\u0018\u00002\u00020\u0001Bs\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0005\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0006\u001a\u00020\u0003\u0012\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u0012\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u0012\b\b\u0002\u0010\u000e\u001a\u00020\u000f\u0012\b\b\u0002\u0010\u0010\u001a\u00020\u000f\u0012\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\f\u00a2\u0006\u0002\u0010\u0012J\t\u0010!\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\"\u001a\u00020\u0003H\u00c6\u0003J\t\u0010#\u001a\u00020\u0003H\u00c6\u0003J\t\u0010$\u001a\u00020\u0003H\u00c6\u0003J\u000f\u0010%\u001a\b\u0012\u0004\u0012\u00020\t0\bH\u00c6\u0003J\u0015\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000bH\u00c6\u0003J\t\u0010\'\u001a\u00020\u000fH\u00c6\u0003J\t\u0010(\u001a\u00020\u000fH\u00c6\u0003J\u000b\u0010)\u001a\u0004\u0018\u00010\fH\u00c6\u0003Jw\u0010*\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u00032\b\b\u0002\u0010\u0006\u001a\u00020\u00032\u000e\b\u0002\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b2\u0014\b\u0002\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b2\b\b\u0002\u0010\u000e\u001a\u00020\u000f2\b\b\u0002\u0010\u0010\u001a\u00020\u000f2\n\b\u0002\u0010\u0011\u001a\u0004\u0018\u00010\fH\u00c6\u0001J\u0013\u0010+\u001a\u00020\u000f2\b\u0010,\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010-\u001a\u00020\u0003H\u00d6\u0001J\t\u0010.\u001a\u00020\fH\u00d6\u0001R\u0011\u0010\u0010\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0011\u0010\u0006\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0013\u0010\u0011\u001a\u0004\u0018\u00010\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018R\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u0016R\u0011\u0010\u000e\u001a\u00020\u000f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001a\u0010\u0014R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u0016R\u001d\u0010\n\u001a\u000e\u0012\u0004\u0012\u00020\f\u0012\u0004\u0012\u00020\r0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u0017\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u001fR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0016\u00a8\u0006/"}, d2 = {"Lcom/grameen/light/core/viewmodel/AdminDashboardUiState;", "", "totalComplaints", "", "pendingRepairs", "fixedLights", "energySaved", "recentAlerts", "", "Lcom/grameen/light/core/model/Complaint;", "polesById", "", "", "Lcom/grameen/light/core/model/Pole;", "loading", "", "dispatchSuccess", "error", "(IIIILjava/util/List;Ljava/util/Map;ZZLjava/lang/String;)V", "getDispatchSuccess", "()Z", "getEnergySaved", "()I", "getError", "()Ljava/lang/String;", "getFixedLights", "getLoading", "getPendingRepairs", "getPolesById", "()Ljava/util/Map;", "getRecentAlerts", "()Ljava/util/List;", "getTotalComplaints", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "component8", "component9", "copy", "equals", "other", "hashCode", "toString", "app_debug"})
public final class AdminDashboardUiState {
    private final int totalComplaints = 0;
    private final int pendingRepairs = 0;
    private final int fixedLights = 0;
    private final int energySaved = 0;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.grameen.light.core.model.Complaint> recentAlerts = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> polesById = null;
    private final boolean loading = false;
    private final boolean dispatchSuccess = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String error = null;
    
    public AdminDashboardUiState(int totalComplaints, int pendingRepairs, int fixedLights, int energySaved, @org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> recentAlerts, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> polesById, boolean loading, boolean dispatchSuccess, @org.jetbrains.annotations.Nullable
    java.lang.String error) {
        super();
    }
    
    public final int getTotalComplaints() {
        return 0;
    }
    
    public final int getPendingRepairs() {
        return 0;
    }
    
    public final int getFixedLights() {
        return 0;
    }
    
    public final int getEnergySaved() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> getRecentAlerts() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> getPolesById() {
        return null;
    }
    
    public final boolean getLoading() {
        return false;
    }
    
    public final boolean getDispatchSuccess() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String getError() {
        return null;
    }
    
    public AdminDashboardUiState() {
        super();
    }
    
    public final int component1() {
        return 0;
    }
    
    public final int component2() {
        return 0;
    }
    
    public final int component3() {
        return 0;
    }
    
    public final int component4() {
        return 0;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> component5() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> component6() {
        return null;
    }
    
    public final boolean component7() {
        return false;
    }
    
    public final boolean component8() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component9() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.grameen.light.core.viewmodel.AdminDashboardUiState copy(int totalComplaints, int pendingRepairs, int fixedLights, int energySaved, @org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> recentAlerts, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> polesById, boolean loading, boolean dispatchSuccess, @org.jetbrains.annotations.Nullable
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