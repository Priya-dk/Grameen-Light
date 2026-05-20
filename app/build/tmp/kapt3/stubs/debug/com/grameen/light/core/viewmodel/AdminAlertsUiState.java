package com.grameen.light.core.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000:\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u001a\n\u0002\u0010\b\n\u0002\b\u0002\b\u0086\b\u0018\u00002\u00020\u0001Bg\u0012\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u0012\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b\u0012\b\b\u0002\u0010\f\u001a\u00020\r\u0012\b\b\u0002\u0010\u000e\u001a\u00020\r\u0012\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\b\u00a2\u0006\u0002\u0010\u0010J\u000f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u000f\u0010\u001e\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\u0015\u0010\u001f\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007H\u00c6\u0003J\u000b\u0010 \u001a\u0004\u0018\u00010\u000bH\u00c6\u0003J\t\u0010!\u001a\u00020\rH\u00c6\u0003J\t\u0010\"\u001a\u00020\rH\u00c6\u0003J\u000b\u0010#\u001a\u0004\u0018\u00010\bH\u00c6\u0003Jk\u0010$\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u000e\b\u0002\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\u0014\b\u0002\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u00072\n\b\u0002\u0010\n\u001a\u0004\u0018\u00010\u000b2\b\b\u0002\u0010\f\u001a\u00020\r2\b\b\u0002\u0010\u000e\u001a\u00020\r2\n\b\u0002\u0010\u000f\u001a\u0004\u0018\u00010\bH\u00c6\u0001J\u0013\u0010%\u001a\u00020\r2\b\u0010&\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\'\u001a\u00020(H\u00d6\u0001J\t\u0010)\u001a\u00020\bH\u00d6\u0001R\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\u0012R\u0011\u0010\u000e\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0013\u0010\u000f\u001a\u0004\u0018\u00010\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0015\u0010\u0016R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0012R\u0011\u0010\f\u001a\u00020\r\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0014R\u001d\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\t0\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u001aR\u0013\u0010\n\u001a\u0004\u0018\u00010\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u001c\u00a8\u0006*"}, d2 = {"Lcom/grameen/light/core/viewmodel/AdminAlertsUiState;", "", "complaints", "", "Lcom/grameen/light/core/model/Complaint;", "filteredComplaints", "polesById", "", "", "Lcom/grameen/light/core/model/Pole;", "selectedFilter", "Lcom/grameen/light/core/model/ComplaintStatus;", "loading", "", "dispatchSuccess", "error", "(Ljava/util/List;Ljava/util/List;Ljava/util/Map;Lcom/grameen/light/core/model/ComplaintStatus;ZZLjava/lang/String;)V", "getComplaints", "()Ljava/util/List;", "getDispatchSuccess", "()Z", "getError", "()Ljava/lang/String;", "getFilteredComplaints", "getLoading", "getPolesById", "()Ljava/util/Map;", "getSelectedFilter", "()Lcom/grameen/light/core/model/ComplaintStatus;", "component1", "component2", "component3", "component4", "component5", "component6", "component7", "copy", "equals", "other", "hashCode", "", "toString", "app_debug"})
public final class AdminAlertsUiState {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.grameen.light.core.model.Complaint> complaints = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.grameen.light.core.model.Complaint> filteredComplaints = null;
    @org.jetbrains.annotations.NotNull
    private final java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> polesById = null;
    @org.jetbrains.annotations.Nullable
    private final com.grameen.light.core.model.ComplaintStatus selectedFilter = null;
    private final boolean loading = false;
    private final boolean dispatchSuccess = false;
    @org.jetbrains.annotations.Nullable
    private final java.lang.String error = null;
    
    public AdminAlertsUiState(@org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> complaints, @org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> filteredComplaints, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> polesById, @org.jetbrains.annotations.Nullable
    com.grameen.light.core.model.ComplaintStatus selectedFilter, boolean loading, boolean dispatchSuccess, @org.jetbrains.annotations.Nullable
    java.lang.String error) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> getComplaints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> getFilteredComplaints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> getPolesById() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.grameen.light.core.model.ComplaintStatus getSelectedFilter() {
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
    
    public AdminAlertsUiState() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> component1() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.List<com.grameen.light.core.model.Complaint> component2() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> component3() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.grameen.light.core.model.ComplaintStatus component4() {
        return null;
    }
    
    public final boolean component5() {
        return false;
    }
    
    public final boolean component6() {
        return false;
    }
    
    @org.jetbrains.annotations.Nullable
    public final java.lang.String component7() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final com.grameen.light.core.viewmodel.AdminAlertsUiState copy(@org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> complaints, @org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.model.Complaint> filteredComplaints, @org.jetbrains.annotations.NotNull
    java.util.Map<java.lang.String, com.grameen.light.core.model.Pole> polesById, @org.jetbrains.annotations.Nullable
    com.grameen.light.core.model.ComplaintStatus selectedFilter, boolean loading, boolean dispatchSuccess, @org.jetbrains.annotations.Nullable
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