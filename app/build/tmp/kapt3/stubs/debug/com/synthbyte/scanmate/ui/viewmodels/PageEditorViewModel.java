package com.synthbyte.scanmate.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u00a0\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u000b\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0010\u0003\n\u0002\b\u0005\n\u0002\u0010\u0007\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\b\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u000e\u0010!\u001a\u00020\"2\u0006\u0010#\u001a\u00020$J\u000e\u0010!\u001a\u00020\"2\u0006\u0010%\u001a\u00020\rJ\u0006\u0010&\u001a\u00020\'J\u001e\u0010(\u001a\u00020\"2\u0006\u0010\u001e\u001a\u00020\u00132\u000e\b\u0002\u0010)\u001a\b\u0012\u0004\u0012\u00020\'0*J\u0006\u0010+\u001a\u00020\"J$\u0010,\u001a\b\u0012\u0004\u0012\u00020\'0-2\u0006\u0010\u0019\u001a\u00020\u001bH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b.\u0010/J\u000e\u00100\u001a\u00020\"2\u0006\u00101\u001a\u00020\rJ,\u00102\u001a\b\u0012\u0004\u0012\u00020\'0-2\u0006\u0010\u0019\u001a\u00020\u001b2\u0006\u00103\u001a\u000204H\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\b5\u00106J\u001a\u00107\u001a\u00020\'2\u0006\u00108\u001a\u0002092\b\b\u0002\u0010:\u001a\u00020\rH\u0002J\u0010\u0010;\u001a\u00020\'2\b\u0010<\u001a\u0004\u0018\u00010\u0011J\u0010\u0010=\u001a\u00020\"2\b\b\u0002\u0010>\u001a\u00020?J\u0006\u0010@\u001a\u00020\"J\u000e\u0010A\u001a\u00020\'H\u0082@\u00a2\u0006\u0002\u0010BJ \u0010C\u001a\u0004\u0018\u00010D2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010E\u001a\u00020FH\u0086@\u00a2\u0006\u0002\u0010GJ \u0010H\u001a\u0004\u0018\u00010D2\u0006\u0010\u001e\u001a\u00020\u00132\u0006\u0010<\u001a\u00020\u0011H\u0086@\u00a2\u0006\u0002\u0010IJ,\u0010J\u001a\b\u0012\u0004\u0012\u00020\'0-2\u0006\u0010\u0019\u001a\u00020\u001b2\u0006\u0010K\u001a\u00020\rH\u0086@\u00f8\u0001\u0000\u00f8\u0001\u0001\u00a2\u0006\u0004\bL\u0010MR\u0016\u0010\u000b\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0014\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0014\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\r0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\u0017R\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u000f0\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0018\u0010\u0017R\u0019\u0010\u0019\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u001b0\u001a\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001c\u0010\u001dR\u000e\u0010\u001e\u001a\u00020\u0013X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u001f\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00110\u0015\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u0017\u0082\u0002\u000b\n\u0002\b!\n\u0005\b\u00a1\u001e0\u0001\u00a8\u0006N"}, d2 = {"Lcom/synthbyte/scanmate/ui/viewmodels/PageEditorViewModel;", "Landroidx/lifecycle/ViewModel;", "dao", "Lcom/synthbyte/scanmate/data/DocDao;", "context", "Landroid/content/Context;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "appViewModel", "Lcom/synthbyte/scanmate/ui/viewmodels/AppErrorReporter;", "(Lcom/synthbyte/scanmate/data/DocDao;Landroid/content/Context;Landroidx/lifecycle/SavedStateHandle;Lcom/synthbyte/scanmate/ui/viewmodels/AppErrorReporter;)V", "_errorState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "_isProcessing", "", "_workingBitmap", "Landroid/graphics/Bitmap;", "docId", "", "errorState", "Lkotlinx/coroutines/flow/StateFlow;", "getErrorState", "()Lkotlinx/coroutines/flow/StateFlow;", "isProcessing", "page", "Lkotlinx/coroutines/flow/Flow;", "Lcom/synthbyte/scanmate/data/Page;", "getPage", "()Lkotlinx/coroutines/flow/Flow;", "pageId", "workingBitmap", "getWorkingBitmap", "applyFilter", "Lkotlinx/coroutines/Job;", "filter", "Lcom/synthbyte/scanmate/utils/FilterType;", "filterId", "clearError", "", "deleteCurrentPage", "onDone", "Lkotlin/Function0;", "deskew", "duplicatePage", "Lkotlin/Result;", "duplicatePage-gIAlu-s", "(Lcom/synthbyte/scanmate/data/Page;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "loadPageBitmap", "path", "movePage", "direction", "", "movePage-0E7RQCE", "(Lcom/synthbyte/scanmate/data/Page;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "publishError", "throwable", "", "fallback", "pushBitmap", "bitmap", "removeMarks", "sensitivity", "", "removeShadow", "renumberPagesInternal", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "replacePageImage", "Ljava/io/File;", "uri", "Landroid/net/Uri;", "(JLandroid/net/Uri;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEditedPage", "(JLandroid/graphics/Bitmap;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "savePageOcr", "text", "savePageOcr-0E7RQCE", "(Lcom/synthbyte/scanmate/data/Page;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class PageEditorViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.synthbyte.scanmate.data.DocDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.synthbyte.scanmate.ui.viewmodels.AppErrorReporter appViewModel = null;
    private final long docId = 0L;
    private final long pageId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.synthbyte.scanmate.data.Page> page = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _errorState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> errorState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.Boolean> _isProcessing = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isProcessing = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<android.graphics.Bitmap> _workingBitmap = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<android.graphics.Bitmap> workingBitmap = null;
    
    @javax.inject.Inject()
    public PageEditorViewModel(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.DocDao dao, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.ui.viewmodels.AppErrorReporter appViewModel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.synthbyte.scanmate.data.Page> getPage() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getErrorState() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.Boolean> isProcessing() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<android.graphics.Bitmap> getWorkingBitmap() {
        return null;
    }
    
    public final void clearError() {
    }
    
    private final void publishError(java.lang.Throwable throwable, java.lang.String fallback) {
    }
    
    public final void pushBitmap(@org.jetbrains.annotations.Nullable()
    android.graphics.Bitmap bitmap) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job loadPageBitmap(@org.jetbrains.annotations.NotNull()
    java.lang.String path) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job applyFilter(@org.jetbrains.annotations.NotNull()
    java.lang.String filterId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job applyFilter(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.FilterType filter) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job removeMarks(float sensitivity) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job removeShadow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deskew() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveEditedPage(long pageId, @org.jetbrains.annotations.NotNull()
    android.graphics.Bitmap bitmap, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object replacePageImage(long pageId, @org.jetbrains.annotations.NotNull()
    android.net.Uri uri, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deleteCurrentPage(long pageId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
    
    private final java.lang.Object renumberPagesInternal(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
}