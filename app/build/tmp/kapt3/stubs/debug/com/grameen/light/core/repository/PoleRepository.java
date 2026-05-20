package com.grameen.light.core.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\u0018\u00002\u00020\u0001B\u0017\u0012\b\b\u0002\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0012\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bJ\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\f0\bJ\u001c\u0010\r\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u000f\u0010\u0010J\u001c\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u000eH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0012\u0010\u0010J,\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u000e2\u0006\u0010\u0014\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u0017H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b\u0018\u0010\u0019R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u001a"}, d2 = {"Lcom/grameen/light/core/repository/PoleRepository;", "", "firestore", "Lcom/google/firebase/firestore/FirebaseFirestore;", "poleDao", "Lcom/grameen/light/core/local/dao/PoleDao;", "(Lcom/google/firebase/firestore/FirebaseFirestore;Lcom/grameen/light/core/local/dao/PoleDao;)V", "observePoles", "Lkotlinx/coroutines/flow/Flow;", "", "Lcom/grameen/light/core/model/Pole;", "observePolesRealtime", "", "seedPolesIfEmpty", "Lkotlin/Result;", "seedPolesIfEmpty-IoAF18A", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "syncPoles", "syncPoles-IoAF18A", "updatePoleStatus", "poleId", "", "status", "Lcom/grameen/light/core/model/PoleStatus;", "updatePoleStatus-0E7RQCE", "(Ljava/lang/String;Lcom/grameen/light/core/model/PoleStatus;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class PoleRepository {
    @org.jetbrains.annotations.NotNull
    private final com.google.firebase.firestore.FirebaseFirestore firestore = null;
    @org.jetbrains.annotations.NotNull
    private final com.grameen.light.core.local.dao.PoleDao poleDao = null;
    
    public PoleRepository(@org.jetbrains.annotations.NotNull
    com.google.firebase.firestore.FirebaseFirestore firestore, @org.jetbrains.annotations.NotNull
    com.grameen.light.core.local.dao.PoleDao poleDao) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.core.model.Pole>> observePoles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<kotlin.Unit> observePolesRealtime() {
        return null;
    }
}