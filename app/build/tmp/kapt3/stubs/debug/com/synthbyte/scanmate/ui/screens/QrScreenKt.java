package com.synthbyte.scanmate.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000,\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0003\u001a \u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0003\u001a$\u0010\b\u001a\u00020\u00012\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\n2\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\nH\u0007\u001a(\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\u0006\u0010\u000f\u001a\u00020\u000e2\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u0007H\u0003\u001a\u0018\u0010\u0010\u001a\u00020\u00012\u0006\u0010\u0006\u001a\u00020\u00072\u0006\u0010\u000f\u001a\u00020\u000eH\u0002\u00a8\u0006\u0011"}, d2 = {"HistoryRow", "", "item", "Lcom/synthbyte/scanmate/data/QrHistory;", "clipboardManager", "Landroid/content/ClipboardManager;", "context", "Landroid/content/Context;", "QrScreen", "onNavigateBack", "Lkotlin/Function0;", "onOpenCameraScanner", "ResultCard", "title", "", "value", "openSafeLink", "app_debug"})
public final class QrScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void QrScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onOpenCameraScanner) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ResultCard(java.lang.String title, java.lang.String value, android.content.ClipboardManager clipboardManager, android.content.Context context) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void HistoryRow(com.synthbyte.scanmate.data.QrHistory item, android.content.ClipboardManager clipboardManager, android.content.Context context) {
    }
    
    private static final void openSafeLink(android.content.Context context, java.lang.String value) {
    }
}