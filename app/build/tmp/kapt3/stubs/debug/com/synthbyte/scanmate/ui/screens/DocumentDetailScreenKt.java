package com.synthbyte.scanmate.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000b\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u000b\u001aL\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\u0018\u0010\u0006\u001a\u0014\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\u00072\u0012\u0010\b\u001a\u000e\u0012\u0004\u0012\u00020\u0003\u0012\u0004\u0012\u00020\u00010\tH\u0007\u001a\u0010\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0003\u001a\u0010\u0010\r\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\fH\u0003\u001a\u0016\u0010\u000e\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u0010H\u0003\u001a\"\u0010\u0012\u001a\u00020\u00012\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u00142\b\b\u0002\u0010\u0016\u001a\u00020\u0017H\u0003\u001a\b\u0010\u0018\u001a\u00020\u0001H\u0003\u001a4\u0010\u0019\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\u0006\u0010\u001a\u001a\u00020\u001b2\u0006\u0010\u001c\u001a\u00020\u001d2\u0012\u0010\u001e\u001a\u000e\u0012\u0004\u0012\u00020\u001f\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a\u0086\u0001\u0010 \u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\t2\u0018\u0010\"\u001a\u0014\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020#\u0012\u0004\u0012\u00020\u00010\u00072\u0018\u0010$\u001a\u0014\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\u0010\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010%\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\t2\u0012\u0010&\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001a*\u0010\'\u001a\u00020\u00012\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00110\u00102\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u0011\u0012\u0004\u0012\u00020\u00010\tH\u0003\u001aV\u0010(\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010)\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010*\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010+\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010,\u001a\b\u0012\u0004\u0012\u00020\u00010\u00052\f\u0010-\u001a\b\u0012\u0004\u0012\u00020\u00010\u0005H\u0003\u00a8\u0006."}, d2 = {"DocumentDetailScreen", "", "docId", "", "onNavigateBack", "Lkotlin/Function0;", "onNavigateToPageEditor", "Lkotlin/Function2;", "onNavigateToSignature", "Lkotlin/Function1;", "DocumentInsightsCard", "dwp", "Lcom/synthbyte/scanmate/data/DocumentWithPages;", "DocumentMetaChips", "DocumentPreview", "pages", "", "Lcom/synthbyte/scanmate/data/Page;", "InsightPill", "label", "", "value", "modifier", "Landroidx/compose/ui/Modifier;", "LoadingDocumentState", "OcrCard", "clipboardManager", "Landroid/content/ClipboardManager;", "context", "Landroid/content/Context;", "onDocxReady", "Ljava/io/File;", "PageManagementList", "onEdit", "onMove", "", "onReorder", "onDuplicate", "onDelete", "PageThumbnails", "QuickActionRow", "onShareFirstImage", "onExport", "onExportDocx", "onSignature", "onMeta", "app_debug"})
public final class DocumentDetailScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void DocumentDetailScreen(long docId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function2<? super java.lang.Long, ? super java.lang.Long, kotlin.Unit> onNavigateToPageEditor, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onNavigateToSignature) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void LoadingDocumentState() {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DocumentPreview(java.util.List<com.synthbyte.scanmate.data.Page> pages) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void QuickActionRow(com.synthbyte.scanmate.data.DocumentWithPages dwp, kotlin.jvm.functions.Function0<kotlin.Unit> onShareFirstImage, kotlin.jvm.functions.Function0<kotlin.Unit> onExport, kotlin.jvm.functions.Function0<kotlin.Unit> onExportDocx, kotlin.jvm.functions.Function0<kotlin.Unit> onSignature, kotlin.jvm.functions.Function0<kotlin.Unit> onMeta) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DocumentMetaChips(com.synthbyte.scanmate.data.DocumentWithPages dwp) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void DocumentInsightsCard(com.synthbyte.scanmate.data.DocumentWithPages dwp) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void InsightPill(java.lang.String label, java.lang.String value, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void OcrCard(com.synthbyte.scanmate.data.DocumentWithPages dwp, android.content.ClipboardManager clipboardManager, android.content.Context context, kotlin.jvm.functions.Function1<? super java.io.File, kotlin.Unit> onDocxReady) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PageThumbnails(java.util.List<com.synthbyte.scanmate.data.Page> pages, kotlin.jvm.functions.Function1<? super com.synthbyte.scanmate.data.Page, kotlin.Unit> onEdit) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void PageManagementList(java.util.List<com.synthbyte.scanmate.data.Page> pages, kotlin.jvm.functions.Function1<? super com.synthbyte.scanmate.data.Page, kotlin.Unit> onEdit, kotlin.jvm.functions.Function2<? super com.synthbyte.scanmate.data.Page, ? super java.lang.Integer, kotlin.Unit> onMove, kotlin.jvm.functions.Function1<? super java.util.List<com.synthbyte.scanmate.data.Page>, kotlin.Unit> onReorder, kotlin.jvm.functions.Function1<? super com.synthbyte.scanmate.data.Page, kotlin.Unit> onDuplicate, kotlin.jvm.functions.Function1<? super com.synthbyte.scanmate.data.Page, kotlin.Unit> onDelete) {
    }
}