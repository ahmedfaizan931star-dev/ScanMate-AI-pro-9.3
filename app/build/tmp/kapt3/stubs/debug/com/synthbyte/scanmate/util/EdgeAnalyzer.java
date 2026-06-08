package com.synthbyte.scanmate.util;

/**
 * Offline document edge detector for the CameraX preview analyzer.
 *
 * This implementation intentionally avoids OpenCV/native dependencies so release APK/AAB builds stay
 * small and stable. It samples the Y plane, builds adaptive brightness + edge projections, estimates
 * skew-aware corners, then smooths consecutive detections to avoid jumpy scanner overlays.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000P\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u0007\n\u0000\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\u0018\n\u0000\n\u0002\u0010\u0015\n\u0002\b\b\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\n\b\u00c7\u0002\u0018\u00002\u00020\u0001:\u0001/B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0010\u0010\u000f\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u0012J\u001a\u0010\u0013\u001a\u0004\u0018\u00010\u00102\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0014\u001a\u00020\u0006H\u0002JX\u0010\u0015\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\r2\u0006\u0010\u0016\u001a\u00020\u00172\u0006\u0010\u0018\u001a\u00020\u00192\u0006\u0010\u001a\u001a\u00020\u00062\u0006\u0010\u001b\u001a\u00020\u00062\u0006\u0010\u001c\u001a\u00020\u00062\u0006\u0010\u001d\u001a\u00020\u00062\u0006\u0010\u001e\u001a\u00020\u00062\u0006\u0010\u001f\u001a\u00020\u00062\u0006\u0010 \u001a\u00020\u0006H\u0002J&\u0010!\u001a\u0010\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u0006\u0018\u00010\"2\u0006\u0010#\u001a\u00020\u00192\u0006\u0010$\u001a\u00020\u0004H\u0002J\b\u0010%\u001a\u00020&H\u0002J\"\u0010\'\u001a\u0004\u0018\u00010\u00192\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010(\u001a\u00020\u00062\u0006\u0010)\u001a\u00020\u0006H\u0002J\u0014\u0010*\u001a\u00020\u0006*\u00020\u00192\u0006\u0010+\u001a\u00020\u0004H\u0002J \u0010,\u001a\b\u0012\u0004\u0012\u00020\u000e0\r*\b\u0012\u0004\u0012\u00020\u000e0\r2\u0006\u0010-\u001a\u00020\u0006H\u0002J\u0012\u0010.\u001a\u00020\u0006*\b\u0012\u0004\u0012\u00020\u00060\rH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0082\u000e\u00a2\u0006\u0002\n\u0000R\u0016\u0010\f\u001a\n\u0012\u0004\u0012\u00020\u000e\u0018\u00010\rX\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u00060"}, d2 = {"Lcom/synthbyte/scanmate/util/EdgeAnalyzer;", "", "()V", "MIN_CONFIDENCE", "", "SAMPLE_HEIGHT", "", "SAMPLE_WIDTH", "SMOOTHING_ALPHA", "lastRotation", "missedFrames", "smoothedConfidence", "smoothedCorners", "", "Landroidx/compose/ui/geometry/Offset;", "detect", "Lcom/synthbyte/scanmate/util/EdgeAnalyzer$Result;", "image", "Landroidx/camera/core/ImageProxy;", "detectRaw", "rotation", "estimateQuadrilateral", "mask", "", "edgeMap", "", "edgeThreshold", "width", "height", "left", "top", "right", "bottom", "findProjectionBounds", "Lkotlin/Pair;", "values", "minLengthRatio", "resetSmoothing", "", "sampleLuma", "targetWidth", "targetHeight", "percentile", "percent", "rotateNormalized", "rotationDegrees", "trimmedMedian", "Result", "app_debug"})
public final class EdgeAnalyzer {
    private static final int SAMPLE_WIDTH = 320;
    private static final int SAMPLE_HEIGHT = 240;
    private static final float MIN_CONFIDENCE = 0.3F;
    private static final float SMOOTHING_ALPHA = 0.42F;
    @org.jetbrains.annotations.Nullable()
    private static java.util.List<androidx.compose.ui.geometry.Offset> smoothedCorners;
    private static float smoothedConfidence = 0.0F;
    private static int missedFrames = 0;
    private static int lastRotation = -2147483648;
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.util.EdgeAnalyzer INSTANCE = null;
    
    private EdgeAnalyzer() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final com.synthbyte.scanmate.util.EdgeAnalyzer.Result detect(@org.jetbrains.annotations.NotNull()
    androidx.camera.core.ImageProxy image) {
        return null;
    }
    
    private final com.synthbyte.scanmate.util.EdgeAnalyzer.Result detectRaw(androidx.camera.core.ImageProxy image, int rotation) {
        return null;
    }
    
    private final int[] sampleLuma(androidx.camera.core.ImageProxy image, int targetWidth, int targetHeight) {
        return null;
    }
    
    private final kotlin.Pair<java.lang.Integer, java.lang.Integer> findProjectionBounds(int[] values, float minLengthRatio) {
        return null;
    }
    
    private final java.util.List<androidx.compose.ui.geometry.Offset> estimateQuadrilateral(boolean[] mask, int[] edgeMap, int edgeThreshold, int width, int height, int left, int top, int right, int bottom) {
        return null;
    }
    
    private final int percentile(int[] $this$percentile, float percent) {
        return 0;
    }
    
    private final int trimmedMedian(java.util.List<java.lang.Integer> $this$trimmedMedian) {
        return 0;
    }
    
    private final java.util.List<androidx.compose.ui.geometry.Offset> rotateNormalized(java.util.List<androidx.compose.ui.geometry.Offset> $this$rotateNormalized, int rotationDegrees) {
        return null;
    }
    
    private final void resetSmoothing() {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00000\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0010\b\n\u0000\n\u0002\u0010\u000e\n\u0000\b\u0087\b\u0018\u00002\u00020\u0001B\u001b\u0012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\u0002\u0010\u0007J\u000f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u00c6\u0003J\t\u0010\r\u001a\u00020\u0006H\u00c6\u0003J#\u0010\u000e\u001a\u00020\u00002\u000e\b\u0002\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u00c6\u0001J\u0013\u0010\u000f\u001a\u00020\u00102\b\u0010\u0011\u001a\u0004\u0018\u00010\u0001H\u00d6\u0003J\t\u0010\u0012\u001a\u00020\u0013H\u00d6\u0001J\t\u0010\u0014\u001a\u00020\u0015H\u00d6\u0001R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u0017\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\u000b\u00a8\u0006\u0016"}, d2 = {"Lcom/synthbyte/scanmate/util/EdgeAnalyzer$Result;", "", "corners", "", "Landroidx/compose/ui/geometry/Offset;", "confidence", "", "(Ljava/util/List;F)V", "getConfidence", "()F", "getCorners", "()Ljava/util/List;", "component1", "component2", "copy", "equals", "", "other", "hashCode", "", "toString", "", "app_debug"})
    public static final class Result {
        @org.jetbrains.annotations.NotNull()
        private final java.util.List<androidx.compose.ui.geometry.Offset> corners = null;
        private final float confidence = 0.0F;
        
        public Result(@org.jetbrains.annotations.NotNull()
        java.util.List<androidx.compose.ui.geometry.Offset> corners, float confidence) {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<androidx.compose.ui.geometry.Offset> getCorners() {
            return null;
        }
        
        public final float getConfidence() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final java.util.List<androidx.compose.ui.geometry.Offset> component1() {
            return null;
        }
        
        public final float component2() {
            return 0.0F;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final com.synthbyte.scanmate.util.EdgeAnalyzer.Result copy(@org.jetbrains.annotations.NotNull()
        java.util.List<androidx.compose.ui.geometry.Offset> corners, float confidence) {
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