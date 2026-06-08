package com.synthbyte.scanmate.utils;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0084\u0001\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u000b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0006\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u0007\n\u0002\b\n\n\u0002\u0010\u0015\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u0001IB\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\"\u0010\b\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u000b\u0012\u0004\u0012\u00020\u00060\n0\t2\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u000e\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000bJ\u001f\u0010\f\u001a\u00020\r2\u0006\u0010\u000e\u001a\u00020\u000b2\b\u0010\u000f\u001a\u0004\u0018\u00010\u0010H\u0002\u00a2\u0006\u0002\u0010\u0011J\u0006\u0010\u0012\u001a\u00020\u0013J\u0010\u0010\u0014\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J0\u0010\u0015\u001a\u0014\u0012\u0010\u0012\u000e\u0012\u0004\u0012\u00020\u0016\u0012\u0004\u0012\u00020\u000b0\n0\t2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ \u0010\u001c\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001fJ \u0010 \u001a\u00020\u000b2\u0006\u0010\u001d\u001a\u00020\u00062\b\b\u0002\u0010\u001e\u001a\u00020\u0010H\u0086@\u00a2\u0006\u0002\u0010\u001fJ\u001e\u0010!\u001a\u00020\u000b2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u001e\u0010\"\u001a\u00020\r2\u0006\u0010\u0017\u001a\u00020\u00182\u0006\u0010\u0019\u001a\u00020\u001aH\u0086@\u00a2\u0006\u0002\u0010\u001bJ\u0018\u0010#\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u0019\u001a\u00020\u001aH\u0002J\b\u0010$\u001a\u00020\u0004H\u0002J*\u0010%\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020&0\t0\t2\f\u0010\'\u001a\b\u0012\u0004\u0012\u00020&0\t2\u0006\u0010(\u001a\u00020\u0010H\u0002J\u0010\u0010)\u001a\u00020*2\u0006\u0010\u001d\u001a\u00020\u0006H\u0002J\u0010\u0010+\u001a\u00020*2\u0006\u0010,\u001a\u00020\rH\u0002J\u001c\u0010-\u001a\b\u0012\u0004\u0012\u00020&0\t2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\tH\u0002J\u0010\u00100\u001a\u00020\u000b2\u0006\u00101\u001a\u00020\u000bH\u0002J\u0010\u00102\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0016\u00103\u001a\u00020\u000b2\f\u0010.\u001a\b\u0012\u0004\u0012\u00020/0\tH\u0002J\u0018\u00104\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u00105\u001a\u000206H\u0002J\u0018\u00107\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u00062\u0006\u00108\u001a\u000206H\u0002J\u0016\u00109\u001a\u00020\r2\u0006\u0010\u0007\u001a\u00020\u0006H\u0082@\u00a2\u0006\u0002\u0010:J\u001e\u0010;\u001a\u00020\r2\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u0010H\u0082@\u00a2\u0006\u0002\u0010\u001fJ\u0010\u0010<\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\u0006H\u0002J\u0018\u0010=\u001a\u00020\u00162\u0006\u0010>\u001a\u00020\u00162\u0006\u0010?\u001a\u00020\u0016H\u0002J\u0014\u0010@\u001a\u00020\u0010*\u00020A2\u0006\u0010B\u001a\u000206H\u0002J\u0014\u0010C\u001a\u00020\u0006*\u00020\u00062\u0006\u0010D\u001a\u00020\u0010H\u0002J\u0013\u0010E\u001a\u0004\u0018\u00010\u0010*\u00020FH\u0002\u00a2\u0006\u0002\u0010GJ\f\u0010H\u001a\u00020\u000b*\u00020FH\u0002R\u0010\u0010\u0003\u001a\u0004\u0018\u00010\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006J"}, d2 = {"Lcom/synthbyte/scanmate/utils/OcrHelper;", "", "()V", "recognizer", "Lcom/google/mlkit/vision/text/TextRecognizer;", "adaptiveBinarizeBitmap", "Landroid/graphics/Bitmap;", "source", "buildOcrCandidates", "", "Lkotlin/Pair;", "", "buildStats", "Lcom/synthbyte/scanmate/utils/OcrExtractionResult;", "text", "mlKitConfidence", "", "(Ljava/lang/String;Ljava/lang/Integer;)Lcom/synthbyte/scanmate/utils/OcrExtractionResult;", "closeRecognizer", "", "estimateSkewAndRotate", "extractBlocksFromFile", "Landroid/graphics/Rect;", "context", "Landroid/content/Context;", "file", "Ljava/io/File;", "(Landroid/content/Context;Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractStatsFromBitmap", "bitmap", "rotationDegrees", "(Landroid/graphics/Bitmap;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "extractTextFromBitmap", "extractTextFromFile", "extractTextWithStatsFromFile", "fixExifRotation", "getRecognizer", "groupLinesIntoRows", "Lcom/synthbyte/scanmate/utils/OcrHelper$OcrLine;", "lines", "medianHeight", "horizontalProjectionVariance", "", "ocrQualityScore", "result", "orderedOcrLines", "blocks", "Lcom/google/mlkit/vision/text/Text$TextBlock;", "postProcessOcrText", "value", "preprocessForOcr", "reconstructParagraphs", "rotate", "degrees", "", "rotateForSkewScore", "angle", "runBestTextRecognition", "(Landroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "runTextRecognition", "toGrayscaleBitmap", "union", "a", "b", "percentile", "", "fraction", "scaleDownToMax", "maxSide", "symbolConfidencePercent", "Lcom/google/mlkit/vision/text/Text;", "(Lcom/google/mlkit/vision/text/Text;)Ljava/lang/Integer;", "toSortedText", "OcrLine", "app_debug"})
public final class OcrHelper {
    @kotlin.jvm.Volatile()
    @org.jetbrains.annotations.Nullable()
    private static volatile com.google.mlkit.vision.text.TextRecognizer recognizer;
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.utils.OcrHelper INSTANCE = null;
    
    private OcrHelper() {
        super();
    }
    
    private final com.google.mlkit.vision.text.TextRecognizer getRecognizer() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object extractTextFromBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, int rotationDegrees, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object extractTextFromFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    @kotlin.Suppress(names = {"UNUSED_PARAMETER"})
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object extractBlocksFromFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<kotlin.Pair<android.graphics.Rect, java.lang.String>>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object extractTextWithStatsFromFile(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.synthbyte.scanmate.utils.OcrExtractionResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object extractStatsFromBitmap(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, int rotationDegrees, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.synthbyte.scanmate.utils.OcrExtractionResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.synthbyte.scanmate.utils.OcrExtractionResult buildStats(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    private final com.synthbyte.scanmate.utils.OcrExtractionResult buildStats(java.lang.String text, java.lang.Integer mlKitConfidence) {
        return null;
    }
    
    public final void closeRecognizer() {
    }
    
    private final java.lang.Object runTextRecognition(android.graphics.Bitmap bitmap, int rotationDegrees, kotlin.coroutines.Continuation<? super com.synthbyte.scanmate.utils.OcrExtractionResult> $completion) {
        return null;
    }
    
    private final java.lang.String reconstructParagraphs(java.util.List<? extends com.google.mlkit.vision.text.Text.TextBlock> blocks) {
        return null;
    }
    
    private final java.lang.String toSortedText(com.google.mlkit.vision.text.Text $this$toSortedText) {
        return null;
    }
    
    private final java.util.List<com.synthbyte.scanmate.utils.OcrHelper.OcrLine> orderedOcrLines(java.util.List<? extends com.google.mlkit.vision.text.Text.TextBlock> blocks) {
        return null;
    }
    
    private final java.util.List<java.util.List<com.synthbyte.scanmate.utils.OcrHelper.OcrLine>> groupLinesIntoRows(java.util.List<com.synthbyte.scanmate.utils.OcrHelper.OcrLine> lines, int medianHeight) {
        return null;
    }
    
    private final android.graphics.Rect union(android.graphics.Rect a, android.graphics.Rect b) {
        return null;
    }
    
    private final java.lang.String postProcessOcrText(java.lang.String value) {
        return null;
    }
    
    private final java.lang.Integer symbolConfidencePercent(com.google.mlkit.vision.text.Text $this$symbolConfidencePercent) {
        return null;
    }
    
    private final java.lang.Object runBestTextRecognition(android.graphics.Bitmap source, kotlin.coroutines.Continuation<? super com.synthbyte.scanmate.utils.OcrExtractionResult> $completion) {
        return null;
    }
    
    @kotlin.Suppress(names = {"unused"})
    private final android.graphics.Bitmap preprocessForOcr(android.graphics.Bitmap source) {
        return null;
    }
    
    private final java.util.List<kotlin.Pair<java.lang.String, android.graphics.Bitmap>> buildOcrCandidates(android.graphics.Bitmap source) {
        return null;
    }
    
    private final double ocrQualityScore(com.synthbyte.scanmate.utils.OcrExtractionResult result) {
        return 0.0;
    }
    
    private final android.graphics.Bitmap toGrayscaleBitmap(android.graphics.Bitmap source) {
        return null;
    }
    
    private final android.graphics.Bitmap adaptiveBinarizeBitmap(android.graphics.Bitmap source) {
        return null;
    }
    
    private final int percentile(int[] $this$percentile, float fraction) {
        return 0;
    }
    
    private final android.graphics.Bitmap estimateSkewAndRotate(android.graphics.Bitmap source) {
        return null;
    }
    
    private final android.graphics.Bitmap rotateForSkewScore(android.graphics.Bitmap source, float angle) {
        return null;
    }
    
    private final double horizontalProjectionVariance(android.graphics.Bitmap bitmap) {
        return 0.0;
    }
    
    private final android.graphics.Bitmap fixExifRotation(android.graphics.Bitmap bitmap, java.io.File file) {
        return null;
    }
    
    private final android.graphics.Bitmap rotate(android.graphics.Bitmap source, float degrees) {
        return null;
    }
    
    private final android.graphics.Bitmap scaleDownToMax(android.graphics.Bitmap $this$scaleDownToMax, int maxSide) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\f\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0082\b\u0018\u00002\u00020\u0001B\u001d\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\u0002\u0010\bJ\t\u0010\u000f\u001a\u00020\u0003H\u00c6\u0003J\t\u0010\u0010\u001a\u00020\u0005H\u00c6\u0003J\t\u0010\u0011\u001a\u00020\u0007H\u00c6\u0003J\'\u0010\u0012\u001a\u00020\u00002\b\b\u0002\u0010\u0002\u001a\u00020\u00032\b\b\u0002\u0010\u0004\u001a\u00020\u00052\b\b\u0002\u0010\u0006\u001a\u00020\u0007H\u00c6\u0001J\u0013\u0010\u0013\u001a\u00020\u00142\b\u0010\u0015\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0016\u001a\u00020\u0007H\u00d6\u0001J\t\u0010\u0017\u001a\u00020\u0005H\u00d6\u0001R\u0011\u0010\u0006\u001a\u00020\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\nR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u0011\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\b\n\u0000\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0018"}, d2 = {"Lcom/synthbyte/scanmate/utils/OcrHelper$OcrLine;", "", "rect", "Landroid/graphics/Rect;", "text", "", "blockIndex", "", "(Landroid/graphics/Rect;Ljava/lang/String;I)V", "getBlockIndex", "()I", "getRect", "()Landroid/graphics/Rect;", "getText", "()Ljava/lang/String;", "component1", "component2", "component3", "copy", "equals", "", "other", "hashCode", "toString", "app_debug"})
    static final class OcrLine {
        @org.jetbrains.annotations.NotNull()
        private final android.graphics.Rect rect = null;
        @org.jetbrains.annotations.NotNull()
        private final java.lang.String text = null;
        private final int blockIndex = 0;
        
        public OcrLine(@org.jetbrains.annotations.NotNull()
        android.graphics.Rect rect, @org.jetbrains.annotations.NotNull()
        java.lang.String text, int blockIndex) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.graphics.Rect getRect() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String getText() {
            return null;
        }
        
        public final int getBlockIndex() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final android.graphics.Rect component1() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.lang.String component2() {
            return null;
        }
        
        public final int component3() {
            return 0;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.synthbyte.scanmate.utils.OcrHelper.OcrLine copy(@org.jetbrains.annotations.NotNull()
        android.graphics.Rect rect, @org.jetbrains.annotations.NotNull()
        java.lang.String text, int blockIndex) {
            return null;
        }
        
        @java.lang.Override()
        public boolean equals(@org.jetbrains.annotations.Nullable()
        java.lang.Object other) {
            return false;
        }
        
        @java.lang.Override()
        public int hashCode() {
            return 0;
        }
        
        @java.lang.Override()
        @org.jetbrains.annotations.NotNull()
        public java.lang.String toString() {
            return null;
        }
    }
}