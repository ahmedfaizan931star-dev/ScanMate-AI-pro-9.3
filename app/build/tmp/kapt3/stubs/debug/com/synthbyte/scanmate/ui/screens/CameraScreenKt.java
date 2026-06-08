package com.synthbyte.scanmate.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000:\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a$\u0010\u0000\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00010\u0003H\u0003\u001a2\u0010\u0005\u001a\u00020\u00012\f\u0010\u0002\u001a\b\u0012\u0004\u0012\u00020\u00010\u00032\u0012\u0010\u0006\u001a\u000e\u0012\u0004\u0012\u00020\b\u0012\u0004\u0012\u00020\u00010\u00072\u0006\u0010\t\u001a\u00020\nH\u0007\u001a\u0016\u0010\u000b\u001a\u00020\u00012\f\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u000e0\rH\u0003\u001a\u0016\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\u0012H\u0082@\u00a2\u0006\u0002\u0010\u0013\u00a8\u0006\u0014"}, d2 = {"CameraPermissionState", "", "onNavigateBack", "Lkotlin/Function0;", "onRequestPermission", "CameraScreen", "onScanFinished", "Lkotlin/Function1;", "", "settingsRepository", "Lcom/synthbyte/scanmate/data/SettingsRepository;", "CapturedThumbnailStrip", "capturedImages", "", "Ljava/io/File;", "awaitCameraProvider", "Landroidx/camera/lifecycle/ProcessCameraProvider;", "context", "Landroid/content/Context;", "(Landroid/content/Context;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class CameraScreenKt {
    
    @kotlin.OptIn(markerClass = {com.google.accompanist.permissions.ExperimentalPermissionsApi.class, androidx.compose.material3.ExperimentalMaterial3Api.class})
    @androidx.compose.runtime.Composable()
    public static final void CameraScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onScanFinished, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.SettingsRepository settingsRepository) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CapturedThumbnailStrip(java.util.List<? extends java.io.File> capturedImages) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void CameraPermissionState(kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, kotlin.jvm.functions.Function0<kotlin.Unit> onRequestPermission) {
    }
    
    private static final java.lang.Object awaitCameraProvider(android.content.Context context, kotlin.coroutines.Continuation<? super androidx.camera.lifecycle.ProcessCameraProvider> $completion) {
        return null;
    }
}