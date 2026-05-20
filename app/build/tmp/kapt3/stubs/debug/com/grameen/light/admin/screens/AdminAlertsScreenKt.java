package com.grameen.light.admin.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000<\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0002\u001a(\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0016\b\u0002\u0010\u0004\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\u0006\u0012\u0004\u0012\u00020\u00010\u0005H\u0007\u001a,\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u001a8\u0010\r\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\t2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00010\u000b2\u0018\u0010\u000f\u001a\u0014\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\u0010H\u0003\u001a&\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00062\u0006\u0010\u0014\u001a\u00020\u00152\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00010\u000bH\u0003\u00a8\u0006\u0017"}, d2 = {"AdminAlertsScreen", "", "viewModel", "Lcom/grameen/light/core/viewmodel/AdminAlertsViewModel;", "onOpenMap", "Lkotlin/Function1;", "", "ComplaintCard", "complaint", "Lcom/grameen/light/core/model/Complaint;", "onNavigate", "Lkotlin/Function0;", "onDispatch", "DispatchDialog", "onDismiss", "onUpdate", "Lkotlin/Function2;", "Lcom/grameen/light/core/model/ComplaintStatus;", "FilterChip", "label", "selected", "", "onClick", "app_debug"})
public final class AdminAlertsScreenKt {
    
    @androidx.compose.runtime.Composable
    public static final void AdminAlertsScreen(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.viewmodel.AdminAlertsViewModel viewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onOpenMap) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void FilterChip(java.lang.String label, boolean selected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @kotlin.OptIn(markerClass = {com.bumptech.glide.integration.compose.ExperimentalGlideComposeApi.class})
    @androidx.compose.runtime.Composable
    private static final void ComplaintCard(com.grameen.light.core.model.Complaint complaint, kotlin.jvm.functions.Function0<kotlin.Unit> onNavigate, kotlin.jvm.functions.Function0<kotlin.Unit> onDispatch) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void DispatchDialog(com.grameen.light.core.model.Complaint complaint, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function2<? super java.lang.String, ? super com.grameen.light.core.model.ComplaintStatus, kotlin.Unit> onUpdate) {
    }
}