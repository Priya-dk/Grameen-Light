package com.grameen.light.user.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000f\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\u001a.\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\b\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0003\u001a&\u0010\f\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\t2\f\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00030\u000bH\u0003\u001a(\u0010\u000e\u001a\u00020\u00032\u0006\u0010\u000f\u001a\u00020\u00102\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00030\u000b2\b\b\u0002\u0010\u0012\u001a\u00020\u0013H\u0003\u001a\u0010\u0010\u0014\u001a\u00020\u00032\u0006\u0010\u0015\u001a\u00020\u0016H\u0007\u001a\u001a\u0010\u0017\u001a\u0004\u0018\u00010\u00182\u0006\u0010\u0019\u001a\u00020\u001a2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002\u001a\u0010\u0010\u001d\u001a\u00020\u001e2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002\u001a\u000e\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u00100 H\u0002\u001a\u001c\u0010!\u001a\u000e\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020#0\"2\u0006\u0010\u001b\u001a\u00020\u001cH\u0002\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"OSM_HTTPS_TILE_SOURCE", "Lorg/osmdroid/tileprovider/tilesource/XYTileSource;", "MapBottomNavItem", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "label", "", "isSelected", "", "onClick", "Lkotlin/Function0;", "MapFilterChip", "active", "PoleDetailsSheet", "pole", "Lcom/grameen/light/core/model/Pole;", "onReport", "modifier", "Landroidx/compose/ui/Modifier;", "PoleMapScreen", "navController", "Landroidx/navigation/NavController;", "markerDrawable", "Landroid/graphics/drawable/Drawable;", "context", "Landroid/content/Context;", "status", "Lcom/grameen/light/core/model/PoleStatus;", "markerRes", "", "sampleDummyPoles", "", "statusBadgeColors", "Lkotlin/Pair;", "Landroidx/compose/ui/graphics/Color;", "app_debug"})
public final class PoleMapScreenKt {
    @org.jetbrains.annotations.NotNull
    private static final org.osmdroid.tileprovider.tilesource.XYTileSource OSM_HTTPS_TILE_SOURCE = null;
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.ui.ExperimentalComposeUiApi.class})
    @androidx.compose.runtime.Composable
    public static final void PoleMapScreen(@org.jetbrains.annotations.NotNull
    androidx.navigation.NavController navController) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void MapBottomNavItem(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String label, boolean isSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void MapFilterChip(java.lang.String label, boolean active, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable
    private static final void PoleDetailsSheet(com.grameen.light.core.model.Pole pole, kotlin.jvm.functions.Function0<kotlin.Unit> onReport, androidx.compose.ui.Modifier modifier) {
    }
    
    private static final int markerRes(com.grameen.light.core.model.PoleStatus status) {
        return 0;
    }
    
    private static final android.graphics.drawable.Drawable markerDrawable(android.content.Context context, com.grameen.light.core.model.PoleStatus status) {
        return null;
    }
    
    private static final kotlin.Pair<androidx.compose.ui.graphics.Color, androidx.compose.ui.graphics.Color> statusBadgeColors(com.grameen.light.core.model.PoleStatus status) {
        return null;
    }
    
    private static final java.util.List<com.grameen.light.core.model.Pole> sampleDummyPoles() {
        return null;
    }
}