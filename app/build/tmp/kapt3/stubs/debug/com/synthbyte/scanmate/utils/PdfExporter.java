package com.synthbyte.scanmate.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010$\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0018\u0002\n\u0002\b\t\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J$\u0010\u0005\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u00062\u0006\u0010\u0007\u001a\u00020\b2\b\b\u0002\u0010\t\u001a\u00020\nJ\u0018\u0010\u000b\u001a\u00020\b2\u0006\u0010\f\u001a\u00020\b2\u0006\u0010\r\u001a\u00020\u000eH\u0002JJ\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\u0017\u001a\u00020\u00152\b\b\u0002\u0010\u0018\u001a\u00020\u00192\b\b\u0002\u0010\u001a\u001a\u00020\u0019H\u0086@\u00a2\u0006\u0002\u0010\u001bJ.\u0010\u001c\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001eJZ\u0010\u001f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#\u0018\u00010\"H\u0086@\u00a2\u0006\u0002\u0010$J\u0082\u0001\u0010%\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010 \u001a\u00020\u000e2\b\b\u0002\u0010\t\u001a\u00020\n2\u0016\b\u0002\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#\u0018\u00010\"2&\b\u0002\u0010&\u001a \u0012\u0004\u0012\u00020\u0015\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00150\u00060\u00140\'H\u0086@\u00a2\u0006\u0002\u0010)J2\u0010*\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010+\u001a\u00020\u00152\u0006\u0010\u0016\u001a\u00020\u00152\b\b\u0002\u0010\t\u001a\u00020\nH\u0086@\u00a2\u0006\u0002\u0010,JN\u0010-\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\n2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#\u0018\u00010\"2\u0006\u0010 \u001a\u00020\u000eH\u0002J|\u0010.\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010/\u001a\u00020\u00042\u0006\u0010\u0016\u001a\u00020\u00152\u0006\u0010\t\u001a\u00020\n2\u0014\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0015\u0012\u0004\u0012\u00020#\u0018\u00010\"2$\u0010&\u001a \u0012\u0004\u0012\u00020\u0015\u0012\u0016\u0012\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00150\u00060\u00140\'2\u0006\u0010 \u001a\u00020\u000eH\u0002J2\u00100\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u00101\u001a\u0002022\b\b\u0002\u00103\u001a\u00020\u00042\n\b\u0002\u00104\u001a\u0004\u0018\u000105J.\u00106\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\f\u00107\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001eJ2\u00108\u001a\b\u0012\u0004\u0012\u00020\u00100\u00142\u0006\u0010\u0011\u001a\u00020\u00122\f\u00107\u001a\b\u0012\u0004\u0012\u00020\b0\u00142\u0006\u0010\u0016\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010\u001eJ&\u00109\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010:\u001a\u00020\u00152\u0006\u0010;\u001a\u00020<2\u0006\u00103\u001a\u00020=H\u0002J>\u0010>\u001a\u00020#*\u00020?2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010@\u001a\u00020\u00042\u0006\u0010A\u001a\u00020\u00042\u0018\u0010B\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020(\u0012\u0004\u0012\u00020\u00150\u00060\u0014H\u0002J$\u0010C\u001a\u00020#*\u00020?2\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010D\u001a\u00020\u00042\u0006\u0010E\u001a\u00020\u0004H\u0002J\u0016\u0010F\u001a\u0004\u0018\u00010\b*\u00020\b2\u0006\u0010G\u001a\u00020\u0004H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082D\u00a2\u0006\u0002\n\u0000\u00a8\u0006H"}, d2 = {"Lcom/synthbyte/scanmate/utils/PdfExporter;", "", "()V", "PASSWORD_PDF_ALL_PERMISSIONS", "", "buildPdfPageDimensions", "Lkotlin/Pair;", "bitmap", "Landroid/graphics/Bitmap;", "pageSize", "Lcom/synthbyte/scanmate/utils/PdfPageSize;", "compressBitmap", "bmp", "q", "Lcom/synthbyte/scanmate/utils/PdfExportQuality;", "generatePasswordProtectedPdf", "Ljava/io/File;", "context", "Landroid/content/Context;", "imagePaths", "", "", "filename", "userPassword", "allowPrinting", "", "allowCopy", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Ljava/lang/String;ZZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdf", "images", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdfFromBitmaps", "quality", "onProgress", "Lkotlin/Function1;", "", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Lcom/synthbyte/scanmate/utils/PdfExportQuality;Lcom/synthbyte/scanmate/utils/PdfPageSize;Lkotlin/jvm/functions/Function1;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdfFromPaths", "ocrRectsByPath", "", "Landroid/graphics/Rect;", "(Landroid/content/Context;Ljava/util/List;Ljava/lang/String;Lcom/synthbyte/scanmate/utils/PdfExportQuality;Lcom/synthbyte/scanmate/utils/PdfPageSize;Lkotlin/jvm/functions/Function1;Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdfFromText", "text", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lcom/synthbyte/scanmate/utils/PdfPageSize;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "generatePdfInternal", "generatePdfInternalFromPaths", "targetSize", "renderPdfUriToBitmaps", "uri", "Landroid/net/Uri;", "maxWidth", "pageRange", "Lkotlin/ranges/IntRange;", "saveLongImageFromBitmaps", "pages", "saveRenderedPdfPagesAsImages", "wrapPdfLine", "line", "paint", "Landroid/graphics/Paint;", "", "drawInvisibleOcrLayer", "Landroid/graphics/Canvas;", "pageWidthPx", "pageHeightPx", "ocrBlocks", "letterbox", "pageWidth", "pageHeight", "scaleDownToMax", "maxSide", "app_debug"})
public final class PdfExporter {
    private static final int PASSWORD_PDF_ALL_PERMISSIONS = 2068;
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.utils.PdfExporter INSTANCE = null;
    
    private PdfExporter() {
        super();
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
    java.util.List<android.graphics.Bitmap> images, @org.jetbrains.annotations.NotNull()
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
    
    private final java.io.File generatePdfInternalFromPaths(android.content.Context context, java.util.List<java.lang.String> imagePaths, int targetSize, java.lang.String filename, com.synthbyte.scanmate.utils.PdfPageSize pageSize, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onProgress, java.util.Map<java.lang.String, ? extends java.util.List<kotlin.Pair<android.graphics.Rect, java.lang.String>>> ocrRectsByPath, com.synthbyte.scanmate.utils.PdfExportQuality quality) {
        return null;
    }
    
    private final java.io.File generatePdfInternal(android.content.Context context, java.util.List<android.graphics.Bitmap> images, java.lang.String filename, com.synthbyte.scanmate.utils.PdfPageSize pageSize, kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onProgress, com.synthbyte.scanmate.utils.PdfExportQuality quality) {
        return null;
    }
    
    private final android.graphics.Bitmap compressBitmap(android.graphics.Bitmap bmp, com.synthbyte.scanmate.utils.PdfExportQuality q) {
        return null;
    }
    
    private final void letterbox(android.graphics.Canvas $this$letterbox, android.graphics.Bitmap bitmap, int pageWidth, int pageHeight) {
    }
    
    private final void drawInvisibleOcrLayer(android.graphics.Canvas $this$drawInvisibleOcrLayer, android.graphics.Bitmap bitmap, int pageWidthPx, int pageHeightPx, java.util.List<kotlin.Pair<android.graphics.Rect, java.lang.String>> ocrBlocks) {
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
    
    private final java.util.List<java.lang.String> wrapPdfLine(java.lang.String line, android.graphics.Paint paint, float maxWidth) {
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
    
    private final android.graphics.Bitmap scaleDownToMax(android.graphics.Bitmap $this$scaleDownToMax, int maxSide) {
        return null;
    }
}