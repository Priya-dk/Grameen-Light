package com.grameen.light.data.repository;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00006\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010!\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\u0018\u00002\u00020\u0001B\u0005\u00a2\u0006\u0002\u0010\u0002J\u0012\u0010\u0006\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u0007J\u0010\u0010\t\u001a\u0004\u0018\u00010\u00052\u0006\u0010\n\u001a\u00020\u000bJ\u001a\u0010\f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00050\b0\u00072\u0006\u0010\r\u001a\u00020\u000eJ\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\n\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u000eR\u0014\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0011"}, d2 = {"Lcom/grameen/light/data/repository/PoleRepository;", "", "()V", "poles", "", "Lcom/grameen/light/data/model/Pole;", "getAllPoles", "Lkotlinx/coroutines/flow/Flow;", "", "getPoleById", "id", "", "getPolesByStatus", "status", "Lcom/grameen/light/data/model/PoleStatus;", "updatePoleStatus", "", "app_debug"})
public final class PoleRepository {
    @org.jetbrains.annotations.NotNull
    private final java.util.List<com.grameen.light.data.model.Pole> poles = null;
    
    public PoleRepository() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.data.model.Pole>> getAllPoles() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.grameen.light.data.model.Pole>> getPolesByStatus(@org.jetbrains.annotations.NotNull
    com.grameen.light.data.model.PoleStatus status) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable
    public final com.grameen.light.data.model.Pole getPoleById(@org.jetbrains.annotations.NotNull
    java.lang.String id) {
        return null;
    }
    
    public final void updatePoleStatus(@org.jetbrains.annotations.NotNull
    java.lang.String id, @org.jetbrains.annotations.NotNull
    com.grameen.light.data.model.PoleStatus status) {
    }
}