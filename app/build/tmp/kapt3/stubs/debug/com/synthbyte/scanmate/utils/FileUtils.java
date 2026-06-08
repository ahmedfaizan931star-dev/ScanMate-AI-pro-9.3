package com.synthbyte.scanmate.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u009c\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0012\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0007\n\u0002\b\r\n\u0002\u0010\u000b\n\u0002\b\u000f\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\u0018\n\u0002\u0018\u0002\n\u0002\b\u0017\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0003\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\bJ\u0016\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\rJ.\u0010\u000e\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u000f\u001a\u00020\u00042\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u00112\u0006\u0010\u0013\u001a\u00020\u00142\u0006\u0010\u0015\u001a\u00020\u0014J\u0018\u0010\u0016\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\bJ\u000e\u0010\u0019\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nJ$\u0010\u001a\u001a\u000e\u0012\u0004\u0012\u00020\u0014\u0012\u0004\u0012\u00020\u00140\u001b2\u0006\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010\u001d\u001a\u00020\u001eJ\"\u0010\u001f\u001a\u00020 2\u0006\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010!\u001a\u00020\u00142\b\b\u0002\u0010\"\u001a\u00020#J\u0018\u0010$\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&J\u000e\u0010\'\u001a\u00020\u00042\u0006\u0010\u0005\u001a\u00020\u0006J.\u0010(\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010)\u001a\u00020*2\u0006\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020*2\u0006\u0010-\u001a\u00020*J$\u0010.\u001a\u0004\u0018\u00010\n2\u0006\u0010/\u001a\u00020\b2\b\b\u0002\u00100\u001a\u00020\u00142\b\b\u0002\u00101\u001a\u00020\u0014J\u000e\u00102\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nJ\u0016\u00103\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u0018\u001a\u00020\bJ \u00104\u001a\u00020\n2\u0006\u00105\u001a\u00020\n2\u0006\u00106\u001a\u00020\n2\b\b\u0002\u00107\u001a\u000208J\u0018\u00109\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010\u0018\u001a\u00020\bJ\u0018\u0010:\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010;\u001a\u00020\bJJ\u0010<\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010>\u001a\u00020\b2\u0006\u0010?\u001a\u00020\b2\b\b\u0002\u0010@\u001a\u0002082\b\b\u0002\u0010A\u001a\u000208H\u0086@\u00a2\u0006\u0002\u0010BJ.\u0010C\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010D\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010EJZ\u0010F\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010G\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010>\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020H2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0016\b\u0002\u0010I\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020K\u0018\u00010JH\u0086@\u00a2\u0006\u0002\u0010LJ\u0082\u0001\u0010M\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010=\u001a\b\u0012\u0004\u0012\u00020\b0\u00112\u0006\u0010>\u001a\u00020\b2\b\b\u0002\u0010!\u001a\u00020H2\b\b\u0002\u0010\u001d\u001a\u00020\u001e2\u0016\b\u0002\u0010I\u001a\u0010\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020K\u0018\u00010J2&\b\u0002\u0010N\u001a \u0012\u0004\u0012\u00020\b\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020P\u0012\u0004\u0012\u00020\b0\u001b0\u00110OH\u0086@\u00a2\u0006\u0002\u0010QJ2\u0010R\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010>\u001a\u00020\b2\b\b\u0002\u0010\u001d\u001a\u00020\u001eH\u0086@\u00a2\u0006\u0002\u0010SJ\u0016\u0010T\u001a\u00020\b2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&J\u0014\u0010U\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\u0005\u001a\u00020\u0006J\u000e\u0010V\u001a\u00020\b2\u0006\u0010\u000f\u001a\u00020\u0004J\u0016\u0010W\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010\u000f\u001a\u00020\u0004J \u0010X\u001a\u00020K2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010Y\u001a\u00020\bJN\u0010Z\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010[\u001a\u00020*2\u0006\u0010\\\u001a\u00020*2\u0006\u0010]\u001a\u00020*2\u0006\u0010^\u001a\u00020*2\u0006\u0010_\u001a\u00020*2\u0006\u0010`\u001a\u00020*2\u0006\u0010a\u001a\u00020*2\u0006\u0010b\u001a\u00020*J\u0018\u0010c\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\b\b\u0002\u0010d\u001a\u00020*J\u000e\u0010e\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\nJ2\u0010f\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010%\u001a\u00020&2\b\b\u0002\u0010g\u001a\u00020\u00142\n\b\u0002\u0010h\u001a\u0004\u0018\u00010iJ\u0016\u0010j\u001a\u00020\n2\u0006\u0010\u0017\u001a\u00020\n2\u0006\u0010k\u001a\u00020*J\u000e\u0010l\u001a\u00020\b2\u0006\u0010m\u001a\u00020\bJ(\u0010n\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010oJD\u0010p\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\n2\u0006\u0010q\u001a\u00020\b2\u0006\u0010>\u001a\u00020\b2\b\b\u0002\u0010\"\u001a\u00020#2\b\b\u0002\u0010!\u001a\u00020\u0014H\u0086@\u00a2\u0006\u0002\u0010rJ(\u0010s\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010tJ*\u0010u\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\n2\b\b\u0002\u0010v\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010oJ.\u0010w\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010x\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010EJ.\u0010y\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010x\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010EJ2\u0010z\u001a\b\u0012\u0004\u0012\u00020\u00040\u00112\u0006\u0010\u0005\u001a\u00020\u00062\f\u0010x\u001a\b\u0012\u0004\u0012\u00020\n0\u00112\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010EJ(\u0010{\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010tJ(\u0010|\u001a\u0004\u0018\u00010\u00042\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\b2\u0006\u0010>\u001a\u00020\bH\u0086@\u00a2\u0006\u0002\u0010tJ \u0010}\u001a\u00020K2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u000f\u001a\u00020\u00042\b\b\u0002\u0010Y\u001a\u00020\bJ \u0010~\u001a\u00020K2\u0006\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0018\u001a\u00020\b2\b\b\u0002\u0010\u007f\u001a\u00020\b\u00a8\u0006\u0080\u0001"}, d2 = {"Lcom/synthbyte/scanmate/utils/FileUtils;", "", "()V", "appFolder", "Ljava/io/File;", "context", "Landroid/content/Context;", "name", "", "applyFilter", "Landroid/graphics/Bitmap;", "original", "type", "Lcom/synthbyte/scanmate/utils/FilterType;", "applyPerspectiveCorrection", "file", "corners", "", "Landroidx/compose/ui/geometry/Offset;", "previewWidth", "", "previewHeight", "applyWatermark", "source", "text", "autoCropDocument", "buildPdfPageDimensions", "Lkotlin/Pair;", "bitmap", "pageSize", "Lcom/synthbyte/scanmate/utils/PdfPageSize;", "compressBitmap", "", "quality", "format", "Landroid/graphics/Bitmap$CompressFormat;", "copyUriToImageFile", "uri", "Landroid/net/Uri;", "createUniqueImageFile", "cropBitmapNormalized", "leftPercent", "", "topPercent", "rightPercent", "bottomPercent", "decodeSampledBitmap", "path", "reqWidth", "reqHeight", "deskewBitmap", "drawNoteStampOnBitmap", "drawSignatureOnBitmap", "pageBitmap", "signatureBitmap", "alignRight", "", "drawWatermarkOnBitmap", "duplicateImageFile", "sourcePath", "generatePasswordProtectedPdf", "imagePaths", "filename", "userPassword", "allowPrinting", "allowCopy", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdf", "images", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdfFromBitmaps", "bitmaps", "Lcom/synthbyte/scanmate/utils/PdfExportQuality;", "onProgress", "Lkotlin/Function1;", "", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Lcom/synthbyte/scanmate/utils/PdfExportQuality;Lcom/synthbyte/scanmate/utils/PdfPageSize;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdfFromPaths", "ocrRectsByPath", "", "Landroid/graphics/Rect;", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Lcom/synthbyte/scanmate/utils/PdfExportQuality;Lcom/synthbyte/scanmate/utils/PdfPageSize;Lkotlin/jvm/functions/Function1;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdfFromText", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/synthbyte/scanmate/utils/PdfPageSize;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getDisplayName", "listManagedFiles", "mimeTypeFor", "normalizeBitmapOrientation", "openFile", "mimeType", "perspectiveCorrectBitmapNormalized", "topLeftX", "topLeftY", "topRightX", "topRightY", "bottomRightX", "bottomRightY", "bottomLeftX", "bottomLeftY", "removeMarksFromBitmap", "sensitivity", "removeShadowFromBitmap", "renderPdfUriToBitmaps", "maxWidth", "pageRange", "Lkotlin/ranges/IntRange;", "rotateBitmap", "degrees", "sanitizeFileBaseName", "value", "saveBitmapAsPng", "(Landroid/content/Context;Landroid/graphics/Bitmap;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveBitmapToFolder", "folderName", "(Landroid/content/Context;Landroid/graphics/Bitmap;Ljava/lang/String;Ljava/lang/String;Landroid/graphics/Bitmap$CompressFormat;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDocxText", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEditedBitmap", "sourceName", "saveLongImageFromBitmaps", "pages", "savePptxFromBitmaps", "saveRenderedPdfPagesAsImages", "saveTextFile", "saveXlsxFromText", "shareFile", "shareText", "title", "app_debug"})
public final class FileUtils {
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.utils.FileUtils INSTANCE = null;
    
    private FileUtils() {
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
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveEditedBitmap(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    java.lang.String sourceName, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File duplicateImageFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String sourcePath) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final android.graphics.Bitmap decodeSampledBitmap(@org.jetbrains.annotations.NotNull()
    java.lang.String path, int reqWidth, int reqHeight) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap normalizeBitmapOrientation(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, @org.jetbrains.annotations.NotNull()
    java.io.File file) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.io.File applyPerspectiveCorrection(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    java.util.List<androidx.compose.ui.geometry.Offset> corners, int previewWidth, int previewHeight) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap rotateBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, float degrees) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap cropBitmapNormalized(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, float leftPercent, float topPercent, float rightPercent, float bottomPercent) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap autoCropDocument(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap perspectiveCorrectBitmapNormalized(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, float topLeftX, float topLeftY, float topRightX, float topRightY, float bottomRightX, float bottomRightY, float bottomLeftX, float bottomLeftY) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap applyFilter(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap original, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.FilterType type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap drawSignatureOnBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap pageBitmap, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap signatureBitmap, boolean alignRight) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap drawWatermarkOnBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap drawNoteStampOnBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final byte[] compressBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, int quality, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap.CompressFormat format) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap applyWatermark(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, @org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap removeMarksFromBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source, float sensitivity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap removeShadowFromBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.graphics.Bitmap deskewBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap source) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generatePdf(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<android.graphics.Bitmap> images, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generatePdfFromBitmaps(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<android.graphics.Bitmap> bitmaps, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfExportQuality quality, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfPageSize pageSize, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onProgress, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generatePdfFromPaths(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> imagePaths, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfExportQuality quality, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfPageSize pageSize, @org.jetbrains.annotations.Nullable()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onProgress, @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, ? extends java.util.List<kotlin.Pair<android.graphics.Rect, java.lang.String>>> ocrRectsByPath, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generatePasswordProtectedPdf(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> imagePaths, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    java.lang.String userPassword, boolean allowPrinting, boolean allowCopy, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<android.graphics.Bitmap> renderPdfUriToBitmaps(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, int maxWidth, @org.jetbrains.annotations.Nullable()
    kotlin.ranges.IntRange pageRange) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generatePdfFromText(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfPageSize pageSize, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveRenderedPdfPagesAsImages(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<android.graphics.Bitmap> pages, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.io.File>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveLongImageFromBitmaps(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<android.graphics.Bitmap> pages, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.Integer, java.lang.Integer> buildPdfPageDimensions(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfPageSize pageSize) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveXlsxFromText(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object savePptxFromBitmaps(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.util.List<android.graphics.Bitmap> pages, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveDocxText(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
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