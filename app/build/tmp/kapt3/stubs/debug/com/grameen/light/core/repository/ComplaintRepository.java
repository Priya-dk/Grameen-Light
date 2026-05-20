package com.grameen.light.core.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000d\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\u0010\b\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0014\u0018\u0000 92\u00020\u0001:\u00019B)\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0002\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ(\u0010\u000b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u000f0\r0\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0010\u0010\u0011J\u000e\u0010\u0012\u001a\u00020\u0013H\u0082@\u00a2\u0006\u0002\u0010\u0011J\u0010\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0013H\u0002J\u0012\u0010\u0017\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u0018J\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u001c0\u0018J\u001a\u0010\u001d\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001a0\u00190\u00182\u0006\u0010\u001e\u001a\u00020\u0013J\u0014\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u00182\u0006\u0010\u001e\u001a\u00020\u0013J\u001e\u0010 \u001a\u0004\u0018\u00010\u001a2\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0013\u0012\u0004\u0012\u00020\u00010\rH\u0002JF\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00130\f2\u0006\u0010#\u001a\u00020\u00132\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010\u0016\u001a\u00020\u00132\u0006\u0010$\u001a\u00020\u00132\b\u0010%\u001a\u0004\u0018\u00010&H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\'\u0010(J\u001c\u0010)\u001a\b\u0012\u0004\u0012\u00020\u001c0\fH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b*\u0010\u0011J$\u0010+\u001a\b\u0012\u0004\u0012\u00020\u001c0\f2\u0006\u0010\u001e\u001a\u00020\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b,\u0010-J6\u0010.\u001a\b\u0012\u0004\u0012\u00020\u001c0\f2\u0006\u0010/\u001a\u00020\u00132\u0006\u00100\u001a\u00020\u000e2\b\u00101\u001a\u0004\u0018\u00010\u0013H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b2\u00103J,\u00104\u001a\b\u0012\u0004\u0012\u00020\u00130\f2\u0006\u0010/\u001a\u00020\u00132\u0006\u00105\u001a\u00020&H\u0082@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b6\u00107J\u0018\u00108\u001a\u00020\u001c2\u0006\u0010/\u001a\u00020\u00132\u0006\u00105\u001a\u00020&H\u0002R\u000e\u0010\u0006\u001a\u00020\u0007X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006:"}, d2 = {"Lcom/grameen/light/core/repository/ComplaintRepository;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "storage", "Lcom/google/firebase/storage/FirebaseStorage;", "complaintDao", "Lcom/grameen/light/core/local/dao/ComplaintDao;", "poleRepository", "Lcom/grameen/light/core/repository/PoleRepository;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/google/firebase/storage/FirebaseStorage;Lcom/grameen/light/core/local/dao/ComplaintDao;Lcom/grameen/light/core/repository/PoleRepository;)V", "complaintCountsByStatus", "Lkotlin/Result;", "", "Lcom/grameen/light/core/model/ComplaintStatus;", "", "complaintCountsByStatus-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generateComplaintId", "", "mapIssueTypeToPoleStatus", "Lcom/grameen/light/core/model/PoleStatus;", "issueType", "observeAllComplaints", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/grameen/light/core/model/Complaint;", "observeAllComplaintsRealtime", "", "observeUserComplaints", "userId", "observeUserComplaintsRealtime", "parseComplaint", "data", "submitComplaint", "poleId", "description", "imageUri", "Landroid/net/Uri;", "submitComplaint-hUnOzRk", "(Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncAllComplaints", "syncAllComplaints-IoAF18A", "syncUserComplaints", "syncUserComplaints-gIAlu-s", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updateComplaintStatus", "complaintId", "status", "assignedWorker", "updateComplaintStatus-BWLJW6A", "(Ljava/lang/String;Lcom/grameen/light/core/model/ComplaintStatus;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadPhoto", "uri", "uploadPhoto-0E7RQCE", "(Ljava/lang/String;Landroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "uploadPhotoAndAttachAsync", "Companion", "app_debug"})
public final class ComplaintRepository {
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.storage.FirebaseStorage storage = null;
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.local.dao.ComplaintDao complaintDao = null;
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.repository.PoleRepository poleRepository = null;
    @java.lang.Deprecated
    public static final long STORAGE_TIMEOUT_MS = 12000L;
    @org.jetbrains.annotations.NotNull
    private static final com.grameen.light.core.repository.ComplaintRepository.Companion Companion = null;
    
    public ComplaintRepository(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull
    com.google.firebase.storage.FirebaseStorage storage, @org.jetbrains.annotations.NotNull
    com.grameen.light.core.local.dao.ComplaintDao complaintDao, @org.jetbrains.annotations.NotNull
    com.grameen.light.core.repository.PoleRepository poleRepository) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.core.model.Complaint>> observeUserComplaints(@org.jetbrains.annotations.NotNull
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.core.model.Complaint>> observeAllComplaints() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<kotlin.Unit> observeUserComplaintsRealtime(@org.jetbrains.annotations.NotNull
    java.lang.String userId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<kotlin.Unit> observeAllComplaintsRealtime() {
        return null;
    }
    
    private final java.lang.Object generateComplaintId(kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final void uploadPhotoAndAttachAsync(java.lang.String complaintId, android.net.Uri uri) {
    }
    
    private final com.grameen.light.core.model.PoleStatus mapIssueTypeToPoleStatus(java.lang.String issueType) {
        return null;
    }
    
    private final com.grameen.light.core.model.Complaint parseComplaint(java.util.Map<java.lang.String, ? extends java.lang.Object> data) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\t\n\u0000\b\u0082\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/grameen/light/core/repository/ComplaintRepository$Companion;", "", "()V", "STORAGE_TIMEOUT_MS", "", "app_debug"})
    static final class Companion {
        
        private Companion() {
            super();
        }
    }
}