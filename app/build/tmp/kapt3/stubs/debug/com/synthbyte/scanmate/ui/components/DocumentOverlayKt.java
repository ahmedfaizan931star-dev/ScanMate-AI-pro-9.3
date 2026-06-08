package com.synthbyte.scanmate.ui.components;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000\u001a\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0007\n\u0002\b\u0005\u001a\"\u0010\u0000\u001a\u00020\u00012\u000e\u0010\u0002\u001a\n\u0012\u0004\u0012\u00020\u0004\u0018\u00010\u00032\b\b\u0002\u0010\u0005\u001a\u00020\u0006H\u0007\u001a\u000e\u0010\u0007\u001a\b\u0012\u0004\u0012\u00020\u00040\u0003H\u0002\u001a\u0016\u0010\b\u001a\u00020\u0004*\u00020\u0004H\u0002\u00f8\u0001\u0000\u00a2\u0006\u0004\b\t\u0010\n\u0082\u0002\u0007\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006\u000b"}, d2 = {"DocumentOverlay", "", "corners", "", "Landroidx/compose/ui/geometry/Offset;", "confidence", "", "rememberDefaultFrame", "coercedUnit", "coercedUnit-k-4lQ0M", "(J)J", "app_debug"})
public final class DocumentOverlayKt {
    
    /**
     * Draws the scanner guide/edge overlay.
     *
     * A default frame is always rendered when live edge detection has no result, so the signed APK never
     * shows a blank camera surface. When [corners] are provided, the same renderer animates the detected
     * document quadrilateral and confidence state.
     */
    @androidx.compose.runtime.Composable()
    public static final void DocumentOverlay(@org.jetbrains.annotations.Nullable()
    java.util.List<androidx.compose.ui.geometry.Offset> corners, float confidence) {
    }
    
    private static final java.util.List<androidx.compose.ui.geometry.Offset> rememberDefaultFrame() {
        return null;
    }
}