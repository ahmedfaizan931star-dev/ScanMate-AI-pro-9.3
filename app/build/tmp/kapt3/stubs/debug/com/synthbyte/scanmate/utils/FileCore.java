package com.synthbyte.scanmate.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000N\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\b\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0018\u0010\t\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u000e\u0010\f\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J\u0016\u0010\r\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\n\u001a\u00020\u000bJ\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u00040\u000f2\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010\u0010\u001a\u00020\b2\u0006\u0010\u0011\u001a\u00020\u0004J \u0010\u0012\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\bJ\u000e\u0010\u0015\u001a\u00020\b2\u0006\u0010\u0016\u001a\u00020\bJ(\u0010\u0017\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010\u001bJD\u0010\u001c\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001d\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\b2\b\b\u0002\u0010\u001e\u001a\u00020\u001f2\b\b\u0002\u0010 \u001a\u00020!H\u0086@\u00a2\u0006\u0002\u0010\"J(\u0010#\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\b2\u0006\u0010\u001a\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010%J \u0010&\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0011\u001a\u00020\u00042\b\b\u0002\u0010\u0014\u001a\u00020\bJ \u0010\'\u001a\u00020\u00132\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010$\u001a\u00020\b2\b\b\u0002\u0010(\u001a\u00020\b\u00a8\u0006)"}, d2 = {"Lcom/synthbyte/scanmate/utils/FileCore;", "", "()V", "appFolder", "Ljava/io/File;", "context", "Landroid/content/Context;", "name", "", "copyUriToImageFile", "uri", "Landroid/net/Uri;", "createUniqueImageFile", "getDisplayName", "listManagedFiles", "", "mimeTypeFor", "file", "openFile", "", "mimeType", "sanitizeFileBaseName", "value", "saveBitmapAsPng", "bitmap", "Landroid/graphics/Bitmap;", "filename", "(Landroid/content/Context;Landroid/graphics/Bitmap;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveBitmapToFolder", "folderName", "format", "Landroid/graphics/Bitmap$CompressFormat;", "quality", "", "(Landroid/content/Context;Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap$CompressFormat;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveTextFile", "text", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "shareFile", "shareText", "title", "app_debug"})
public final class FileCore {
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.utils.FileCore INSTANCE = null;
    
    private FileCore() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.io.File createUniqueImageFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File appFolder(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String name) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<java.io.File> listManagedFiles(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    public final void shareFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType) {
    }
    
    public final void openFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    java.lang.String mimeType) {
    }
    
    public final void shareText(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File copyUriToImageFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveTextFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveBitmapAsPng(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveBitmapToFolder(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    java.lang.String folderName, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap.CompressFormat format, int quality, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDisplayName(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String mimeTypeFor(@org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String sanitizeFileBaseName(@org.jetbrains.annotations.NotNull()
    java.lang.String value) {
        return null;
    }
}