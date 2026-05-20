package com.grameen.light.admin.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000V\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\u001a(\u0010\u0003\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0016\b\u0002\u0010\u0007\u001a\u0010\u0012\u0006\u0012\u0004\u0018\u00010\t\u0012\u0004\u0012\u00020\u00040\bH\u0007\u001a8\u0010\n\u001a\u00020\u00042\u0006\u0010\u000b\u001a\u00020\t2\u0006\u0010\f\u001a\u00020\t2\u000e\b\u0002\u0010\r\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u000e\b\u0002\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00040\u000eH\u0007\u001a\b\u0010\u0010\u001a\u00020\u0004H\u0003\u001a8\u0010\u0011\u001a\u00020\u00042\u0006\u0010\u0012\u001a\u00020\u00132\f\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00040\u000e2\u0018\u0010\u0015\u001a\u0014\u0012\u0004\u0012\u00020\t\u0012\u0004\u0012\u00020\u0017\u0012\u0004\u0012\u00020\u00040\u0016H\u0003\u001a:\u0010\u0018\u001a\u00020\u00042\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001c2\u0006\u0010\u001d\u001a\u00020\t2\u0006\u0010\u001e\u001a\u00020\t2\u0006\u0010\u001f\u001a\u00020 H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b!\u0010\"\"\u0014\u0010\u0000\u001a\b\u0012\u0004\u0012\u00020\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006#"}, d2 = {"mockAlerts", "", "Lcom/grameen/light/admin/screens/MockAlert;", "AdminDashboardScreen", "", "viewModel", "Lcom/grameen/light/core/viewmodel/AdminDashboardViewModel;", "onOpenMap", "Lkotlin/Function1;", "", "AlertCard", "poleId", "location", "onNavigate", "Lkotlin/Function0;", "onDispatch", "DashboardHeader", "DispatchBottomSheet", "complaint", "Lcom/grameen/light/core/model/Complaint;", "onDismiss", "onUpdate", "Lkotlin/Function2;", "Lcom/grameen/light/core/model/ComplaintStatus;", "StatCard", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "iconTint", "Landroidx/compose/ui/graphics/Color;", "label", "value", "modifier", "Landroidx/compose/ui/Modifier;", "StatCard-iJQMabo", "(Landroidx/compose/ui/graphics/vector/ImageVector;JLjava/lang/String;Ljava/lang/String;Landroidx/compose/ui/Modifier;)V", "app_debug"})
public final class AdminDashboardScreenKt {
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<com.grameen.light.admin.screens.MockAlert> mockAlerts = null;
    
    @androidx.compose.runtime.Composable
    public static final void AdminDashboardScreen(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.viewmodel.AdminDashboardViewModel viewModel, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onOpenMap) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void DashboardHeader() {
    }
    
    @androidx.compose.runtime.Composable
    public static final void AlertCard(@org.jetbrains.annotations.NotNull
    java.lang.String poleId, @org.jetbrains.annotations.NotNull
    java.lang.String location, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigate, @org.jetbrains.annotations.NotNull
    kotlin.jvm.functions.Function0<kotlin.Unit> onDispatch) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void DispatchBottomSheet(com.grameen.light.core.model.Complaint complaint, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function2<? super java.lang.String, ? super com.grameen.light.core.model.ComplaintStatus, kotlin.Unit> onUpdate) {
    }
}