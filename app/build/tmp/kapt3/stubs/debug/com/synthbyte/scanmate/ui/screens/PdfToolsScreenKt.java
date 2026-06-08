package com.synthbyte.scanmate.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000V\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a6\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\b2\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\b\u0010\u000b\u001a\u00020\u0001H\u0003\u001a\u001e\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\u0016\u0010\u0010\u001a\u00020\u00012\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a&\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00162\f\u0010\u0017\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0003\u001a\u0010\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0004\u001a\u00020\u0005H\u0003\u001a\u001c\u0010\u0019\u001a\u00020\u00052\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0082@\u00a2\u0006\u0002\u0010\u001d\u001a\u0016\u0010\u001e\u001a\u00020\u00012\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001bH\u0002\u001a$\u0010\u001f\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010 \u001a\u00020!2\f\u0010\"\u001a\b\u0012\u0004\u0012\u00020\u00160\u001bH\u0002\u001a,\u0010#\u001a\b\u0012\u0004\u0012\u00020\u001c0\u001b2\u0006\u0010 \u001a\u00020!2\f\u0010$\u001a\b\u0012\u0004\u0012\u00020\u00160\u001b2\u0006\u0010%\u001a\u00020&H\u0002\u001a\u0010\u0010\'\u001a\u00020\u00142\u0006\u0010%\u001a\u00020&H\u0002\u00a8\u0006("}, d2 = {"ConversionButton", "", "icon", "Landroidx/compose/ui/graphics/vector/ImageVector;", "title", "", "subtitle", "enabled", "", "onClick", "Lkotlin/Function0;", "EmptyPdfToolState", "ExportedFileActions", "file", "Ljava/io/File;", "onDismiss", "PdfToolsScreen", "onNavigateBack", "PdfUriRow", "index", "", "uri", "Landroid/net/Uri;", "onRemove", "ToolGroupTitle", "ocrPagesToText", "pages", "", "Landroid/graphics/Bitmap;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "recycleBitmaps", "renderImageUris", "context", "Landroid/content/Context;", "selectedImages", "renderSelectedPdfPages", "selectedPdfs", "quality", "Lcom/synthbyte/scanmate/utils/PdfExportQuality;", "widthFor", "app_debug"})
public final class PdfToolsScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void PdfToolsScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyPdfToolState() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PdfUriRow(int index, android.net.Uri uri, kotlin.jvm.functions.Function0<kotlin.Unit> onRemove) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ToolGroupTitle(java.lang.String title) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ConversionButton(androidx.compose.ui.graphics.vector.ImageVector icon, java.lang.String title, java.lang.String subtitle, boolean enabled, kotlin.jvm.functions.Function0<kotlin.Unit> onClick) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ExportedFileActions(java.io.File file, kotlin.jvm.functions.Function0<kotlin.Unit> onDismiss) {
    }
    
    private static final java.util.List<android.graphics.Bitmap> renderImageUris(android.content.Context context, java.util.List<? extends android.net.Uri> selectedImages) {
        return null;
    }
    
    private static final java.util.List<android.graphics.Bitmap> renderSelectedPdfPages(android.content.Context context, java.util.List<? extends android.net.Uri> selectedPdfs, com.synthbyte.scanmate.utils.PdfExportQuality quality) {
        return null;
    }
    
    private static final java.lang.Object ocrPagesToText(java.util.List<android.graphics.Bitmap> pages, kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private static final void recycleBitmaps(java.util.List<android.graphics.Bitmap> pages) {
    }
    
    private static final int widthFor(com.synthbyte.scanmate.utils.PdfExportQuality quality) {
        return 0;
    }
}