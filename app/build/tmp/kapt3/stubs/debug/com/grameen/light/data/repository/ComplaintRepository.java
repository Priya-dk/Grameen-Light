package com.grameen.light.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\u0005J\u0006\u0010\t\u001a\u00020\nJ\u0012\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\r0\fJ\u0010\u0010\u000e\u001a\u0004\u0018\u00010\u00052\u0006\u0010\u000f\u001a\u00020\nJ\u0016\u0010\u0010\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\n2\u0006\u0010\u0011\u001a\u00020\u0012R\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/grameen/light/data/repository/ComplaintRepository;", "", "()V", "complaints", "", "Lcom/grameen/light/data/model/Complaint;", "addComplaint", "", "complaint", "generateComplaintId", "", "getAllComplaints", "Lkotlinx/coroutines/flow/Flow;", "", "getComplaintById", "id", "updateComplaintStatus", "status", "Lcom/grameen/light/data/model/ComplaintStatus;", "app_debug"})
public final class ComplaintRepository {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.grameen.light.data.model.Complaint> complaints = null;
    
    public ComplaintRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.data.model.Complaint>> getAllComplaints() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.grameen.light.data.model.Complaint getComplaintById(@org.jetbrains.annotations.NotNull
    java.lang.String id) {
        return null;
    }
    
    public final void addComplaint(@org.jetbrains.annotations.NotNull
    com.grameen.light.data.model.Complaint complaint) {
    }
    
    public final void updateComplaintStatus(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    com.grameen.light.data.model.ComplaintStatus status) {
    }
    
    @org.jetbrains.annotations.NotNull
    public final java.lang.String generateComplaintId() {
        return null;
    }
}