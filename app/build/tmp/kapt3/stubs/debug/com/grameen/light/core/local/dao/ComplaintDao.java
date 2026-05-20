package com.grameen.light.core.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0006\bg\u0018\u00002\u00020\u0001J\u0010\u0010\u0002\u001a\u0004\u0018\u00010\u0003H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0004J\u0014\u0010\b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\tH\'J\u001c\u0010\n\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00070\u00060\t2\u0006\u0010\u000b\u001a\u00020\u0003H\'J\u0016\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u0007H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001c\u0010\u0010\u001a\u00020\r2\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/grameen/light/core/local/dao/ComplaintDao;", "", "getLatestComplaintId", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getUnsynced", "", "Lcom/grameen/light/core/local/entity/ComplaintEntity;", "observeAll", "Lkotlinx/coroutines/flow/Flow;", "observeByUser", "userId", "upsert", "", "item", "(Lcom/grameen/light/core/local/entity/ComplaintEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "upsertAll", "items", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@androidx.room.Dao
public abstract interface ComplaintDao {
    
    @androidx.room.Query(value = "SELECT * FROM complaints WHERE userId = :userId ORDER BY createdAtMillis DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.core.local.entity.ComplaintEntity>> observeByUser(@org.jetbrains.annotations.NotNull
    java.lang.String userId);
    
    @androidx.room.Query(value = "SELECT * FROM complaints ORDER BY createdAtMillis DESC")
    @org.jetbrains.annotations.NotNull
    public abstract kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.core.local.entity.ComplaintEntity>> observeAll();
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object upsert(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.local.entity.ComplaintEntity item, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object upsertAll(@org.jetbrains.annotations.NotNull
    java.util.List<com.grameen.light.core.local.entity.ComplaintEntity> items, @org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM complaints WHERE synced = 0")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getUnsynced(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.util.List<com.grameen.light.core.local.entity.ComplaintEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT complaintId FROM complaints ORDER BY complaintId DESC LIMIT 1")
    @org.jetbrains.annotations.Nullable
    public abstract java.lang.Object getLatestComplaintId(@org.jetbrains.annotations.NotNull
    kotlin.coroutines.Continuation<? super java.lang.String> $completion);
}