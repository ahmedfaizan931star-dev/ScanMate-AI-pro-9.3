package com.synthbyte.scanmate.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000Z\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\t\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0005\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u0016\u0010\u000e\u001a\u00020\u000f2\u0006\u0010\u0010\u001a\u00020\t2\u0006\u0010\u0011\u001a\u00020\u0012J\"\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\f\u0010\u0016\u001a\b\u0012\u0004\u0012\u00020\u00170\u0014H\u0086@\u00a2\u0006\u0002\u0010\u0018J$\u0010\u0019\u001a\u00020\u001a2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00150\u00142\u0006\u0010\u001c\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010\u001eJ\u0016\u0010\u001f\u001a\u00020\u00152\u0006\u0010 \u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010!R\u0016\u0010\u0007\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\n\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\t0\u000b\u00a2\u0006\b\n\u0000\u001a\u0004\b\f\u0010\r\u00a8\u0006\""}, d2 = {"Lcom/synthbyte/scanmate/ui/viewmodels/CameraViewModel;", "Landroidx/lifecycle/ViewModel;", "dao", "Lcom/synthbyte/scanmate/data/DocDao;", "context", "Landroid/content/Context;", "(Lcom/synthbyte/scanmate/data/DocDao;Landroid/content/Context;)V", "_previewBitmap", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Landroid/graphics/Bitmap;", "previewBitmap", "Lkotlinx/coroutines/flow/StateFlow;", "getPreviewBitmap", "()Lkotlinx/coroutines/flow/StateFlow;", "applyPreviewFilter", "Lkotlinx/coroutines/Job;", "bitmap", "filter", "Lcom/synthbyte/scanmate/utils/FilterType;", "importUris", "", "Ljava/io/File;", "uris", "Landroid/net/Uri;", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveScannedDocument", "", "files", "defaultWorkspace", "", "(Ljava/util/List;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "sharpenCapturedImageFile", "photoFile", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class CameraViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.synthbyte.scanmate.data.DocDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<android.graphics.Bitmap> _previewBitmap = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<android.graphics.Bitmap> previewBitmap = null;
    
    @javax.inject.Inject()
    public CameraViewModel(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.DocDao dao, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<android.graphics.Bitmap> getPreviewBitmap() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job applyPreviewFilter(@org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.FilterType filter) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object sharpenCapturedImageFile(@org.jetbrains.annotations.NotNull()
    java.io.File photoFile, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object importUris(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<? extends java.io.File>> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveScannedDocument(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends java.io.File> files, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultWorkspace, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Long> $completion) {
        return null;
    }
}