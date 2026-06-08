package com.synthbyte.scanmate.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00006\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\t\n\u0002\b\u0003\u001a\u0010\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u0003H\u0003\u001a\u0016\u0010\u0004\u001a\u00020\u00012\f\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0007\u001a\u0010\u0010\u0007\u001a\u00020\u00012\u0006\u0010\b\u001a\u00020\tH\u0003\u001a\u001a\u0010\n\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\b\b\u0002\u0010\r\u001a\u00020\u000eH\u0003\u001a:\u0010\u000f\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a:\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u000b\u001a\u00020\f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\u00010\u00062\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00010\u0006H\u0003\u001a\u0010\u0010\u0014\u001a\u00020\t2\u0006\u0010\u0015\u001a\u00020\u0016H\u0002\u001a\u0010\u0010\u0017\u001a\u00020\t2\u0006\u0010\u0018\u001a\u00020\u0016H\u0002\u00a8\u0006\u0019"}, d2 = {"EmptyFilesCard", "", "hasFiles", "", "FileManagerScreen", "onNavigateBack", "Lkotlin/Function0;", "FileSectionHeader", "folderName", "", "FileThumb", "file", "Ljava/io/File;", "modifier", "Landroidx/compose/ui/Modifier;", "ManagedFileGridCell", "onOpen", "onShare", "onDelete", "ManagedFileRow", "formatFileSize", "bytes", "", "formatShortDate", "timestamp", "app_debug"})
public final class FileManagerScreenKt {
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.foundation.ExperimentalFoundationApi.class})
    @androidx.compose.runtime.Composable()
    public static final void FileManagerScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FileSectionHeader(java.lang.String folderName) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void EmptyFilesCard(boolean hasFiles) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void FileThumb(java.io.File file, androidx.compose.ui.Modifier modifier) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ManagedFileRow(java.io.File file, kotlin.jvm.functions.Function0<kotlin.Unit> onOpen, kotlin.jvm.functions.Function0<kotlin.Unit> onShare, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ManagedFileGridCell(java.io.File file, kotlin.jvm.functions.Function0<kotlin.Unit> onOpen, kotlin.jvm.functions.Function0<kotlin.Unit> onShare, kotlin.jvm.functions.Function0<kotlin.Unit> onDelete) {
    }
    
    private static final java.lang.String formatFileSize(long bytes) {
        return null;
    }
    
    private static final java.lang.String formatShortDate(long timestamp) {
        return null;
    }
}