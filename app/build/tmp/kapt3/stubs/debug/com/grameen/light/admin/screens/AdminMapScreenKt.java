package com.grameen.light.admin.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000X\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\u001a\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bH\u0007\u001a8\u0010\t\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000b2\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\u0018\u0010\u000e\u001a\u0014\u0012\u0004\u0012\u00020\u0010\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00060\u000fH\u0003\u001a<\u0010\u0012\u001a\u00020\u00062\u0006\u0010\u0013\u001a\u00020\u00102\u0006\u0010\u0014\u001a\u00020\u00102\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0017\u001a\u00020\u00162\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0003\u00f8\u0001\u0000\u00a2\u0006\u0004\b\u001a\u0010\u001b\u001a\u0016\u0010\u001c\u001a\u00020\u00062\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0003\u001a6\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00042\f\u0010 \u001a\b\u0012\u0004\u0012\u00020\u00060\r2\f\u0010!\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\b\b\u0002\u0010\u0018\u001a\u00020\u0019H\u0003\u001a\u0010\u0010\"\u001a\u00020#2\u0006\u0010$\u001a\u00020%H\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\"\u0014\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006&"}, d2 = {"OSM_HTTPS_TILE_SOURCE", "Lorg/osmdroid/tileprovider/tilesource/XYTileSource;", "mockPoles", "", "Lcom/grameen/light/core/model/Pole;", "AdminMapScreen", "", "viewModel", "Lcom/grameen/light/core/viewmodel/AdminMapViewModel;", "MapDispatchDialog", "complaint", "Lcom/grameen/light/core/model/Complaint;", "onDismiss", "Lkotlin/Function0;", "onUpdate", "Lkotlin/Function2;", "", "Lcom/grameen/light/core/model/ComplaintStatus;", "MonitoringStatCard", "value", "label", "bgColor", "Landroidx/compose/ui/graphics/Color;", "textColor", "modifier", "Landroidx/compose/ui/Modifier;", "MonitoringStatCard-OoHUuok", "(Ljava/lang/String;Ljava/lang/String;JJLandroidx/compose/ui/Modifier;)V", "PoleMonitoringStats", "poles", "PoleOverlayCard", "pole", "onDispatch", "onClose", "markerRes", "", "status", "Lcom/grameen/light/core/model/PoleStatus;", "app_debug"})
public final class AdminMapScreenKt {
    @org.jetbrains.annotations.NotNull
    private static final java.util.List<com.grameen.light.core.model.Pole> mockPoles = null;
    @org.jetbrains.annotations.NotNull
    private static final org.osmdroid.tileprovider.tilesource.XYTileSource OSM_HTTPS_TILE_SOURCE = null;
    
    @androidx.compose.runtime.Composable
    public static final void AdminMapScreen(@org.jetbrains.annotations.NotNull
    com.grameen.light.core.viewmodel.AdminMapViewModel viewModel) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void PoleOverlayCard(com.grameen.light.core.model.Pole pole, kotlin.jvm.functions.Function0<kotlin.Unit> onDispatch, kotlin.jvm.functions.Function0<kotlin.Unit> onClose, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void PoleMonitoringStats(java.util.List<com.grameen.light.core.model.Pole> poles) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void MapDispatchDialog(com.grameen.light.core.model.Complaint complaint, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss, kotlin.jvm.functions.Function2<? super java.lang.String, ? super com.grameen.light.core.model.ComplaintStatus, kotlin.Unit> onUpdate) {
    }
    
    private static final int markerRes(com.grameen.light.core.model.PoleStatus status) {
        return 0;
    }
}