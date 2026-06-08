package com.synthbyte.scanmate.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0098\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\t\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0005\n\u0002\u0010\u0003\n\u0002\b\n\n\u0002\u0010 \n\u0002\b\u0011\b\u0007\u0018\u00002\u00020\u0001B)\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u0012\u0006\u0010\u0006\u001a\u00020\u0007\u0012\u0006\u0010\b\u001a\u00020\t\u00a2\u0006\u0002\u0010\nJ\u0006\u0010\u0019\u001a\u00020\u001aJ\u0014\u0010\u001b\u001a\u00020\u001c2\f\u0010\u001d\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eJ\u001e\u0010\u001f\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u000f2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eJ\u0016\u0010\"\u001a\u00020\u001a2\u0006\u0010 \u001a\u00020\u000fH\u0082@\u00a2\u0006\u0002\u0010#J\u001e\u0010$\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eJ\u0016\u0010\'\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&H\u0082@\u00a2\u0006\u0002\u0010(J\u0010\u0010)\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u0012J*\u0010+\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u00122\u0006\u0010,\u001a\u00020-2\u0006\u0010.\u001a\u00020/2\b\b\u0002\u00100\u001a\u000201J4\u00102\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u00122\u0006\u00103\u001a\u00020/2\u0006\u0010.\u001a\u00020/2\b\b\u0002\u00104\u001a\u0002052\b\b\u0002\u00106\u001a\u000205J\u0010\u00107\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u0012J&\u00108\u001a\u00020\u001c2\u0006\u0010%\u001a\u00020&2\u0006\u00109\u001a\u00020:2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eJ\u001e\u0010;\u001a\u00020\u001a2\u0006\u0010%\u001a\u00020&2\u0006\u00109\u001a\u00020:H\u0082@\u00a2\u0006\u0002\u0010<J\b\u0010=\u001a\u00020\u001aH\u0014J\u0010\u0010>\u001a\u00020\u001a2\u0006\u0010?\u001a\u00020@H\u0002J\u0010\u0010A\u001a\u00020\u001a2\u0006\u0010B\u001a\u00020/H\u0002J\u000e\u0010C\u001a\u00020\u001c2\u0006\u0010D\u001a\u00020/J\u000e\u0010E\u001a\u00020\u001aH\u0082@\u00a2\u0006\u0002\u0010FJ\u0016\u0010G\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010H\u001a\u00020:J$\u0010I\u001a\u00020\u001c2\f\u0010J\u001a\b\u0012\u0004\u0012\u00020&0K2\u000e\b\u0002\u0010!\u001a\b\u0012\u0004\u0012\u00020\u001a0\u001eJ\u0010\u0010L\u001a\u00020\u001c2\b\u0010*\u001a\u0004\u0018\u00010\u0012J\u000e\u0010M\u001a\u00020\u001c2\u0006\u0010N\u001a\u000205J\u000e\u0010O\u001a\u00020\u001c2\u0006\u0010P\u001a\u000205J\u001e\u0010Q\u001a\u00020\u001c2\u0006\u0010R\u001a\u00020/2\u0006\u0010S\u001a\u00020/2\u0006\u0010T\u001a\u00020/J\u000e\u0010U\u001a\u00020\u001c2\u0006\u0010V\u001a\u00020/J\u001e\u0010W\u001a\u00020\u001a2\u0006\u0010V\u001a\u00020/2\u0006\u0010X\u001a\u00020/H\u0086@\u00a2\u0006\u0002\u0010YJ\u0016\u0010Z\u001a\u00020\u001c2\u0006\u0010 \u001a\u00020\u000f2\u0006\u0010[\u001a\u00020/R\u0014\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\r0\fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\tX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u000fX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00120\u0011\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\u0014R\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\r0\u0016\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u0018\u00a8\u0006\\"}, d2 = {"Lcom/synthbyte/scanmate/ui/viewmodels/DocumentDetailViewModel;", "Landroidx/lifecycle/ViewModel;", "dao", "Lcom/synthbyte/scanmate/data/DocDao;", "context", "Landroid/content/Context;", "savedStateHandle", "Landroidx/lifecycle/SavedStateHandle;", "appViewModel", "Lcom/synthbyte/scanmate/ui/viewmodels/AppErrorReporter;", "(Lcom/synthbyte/scanmate/data/DocDao;Landroid/content/Context;Landroidx/lifecycle/SavedStateHandle;Lcom/synthbyte/scanmate/ui/viewmodels/AppErrorReporter;)V", "_exportState", "Lkotlinx/coroutines/flow/MutableStateFlow;", "Lcom/synthbyte/scanmate/ui/viewmodels/ExportState;", "docId", "", "documentWithPages", "Lkotlinx/coroutines/flow/Flow;", "Lcom/synthbyte/scanmate/data/DocumentWithPages;", "getDocumentWithPages", "()Lkotlinx/coroutines/flow/Flow;", "exportState", "Lkotlinx/coroutines/flow/StateFlow;", "getExportState", "()Lkotlinx/coroutines/flow/StateFlow;", "clearExportState", "", "delete", "Lkotlinx/coroutines/Job;", "onDeleted", "Lkotlin/Function0;", "deletePage", "pageId", "onDone", "deletePageInternal", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "duplicatePage", "page", "Lcom/synthbyte/scanmate/data/Page;", "duplicatePageInternal", "(Lcom/synthbyte/scanmate/data/Page;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "exportDocx", "dwp", "exportPdf", "quality", "Lcom/synthbyte/scanmate/utils/PdfExportQuality;", "filename", "", "pageSize", "Lcom/synthbyte/scanmate/utils/PdfPageSize;", "exportProtectedPdf", "password", "allowPrinting", "", "allowCopy", "extractOcr", "movePage", "direction", "", "movePageInternal", "(Lcom/synthbyte/scanmate/data/Page;ILkotlin/coroutines/Continuation;)Ljava/lang/Object;", "onCleared", "publishError", "throwable", "", "publishErrorMessage", "message", "rename", "title", "renumberPages", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "reorderPage", "newOrder", "reorderPages", "pages", "", "scoreQuality", "setFavorite", "favorite", "setPinned", "pinned", "updateMeta", "category", "tags", "workspace", "updateOcr", "text", "updateOcrAndMetadata", "currentWorkspace", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "updatePageImage", "imagePath", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DocumentDetailViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.synthbyte.scanmate.data.DocDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final com.synthbyte.scanmate.ui.viewmodels.AppErrorReporter appViewModel = null;
    private final long docId = 0L;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.synthbyte.scanmate.data.DocumentWithPages> documentWithPages = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<com.synthbyte.scanmate.ui.viewmodels.ExportState> _exportState = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<com.synthbyte.scanmate.ui.viewmodels.ExportState> exportState = null;
    
    @javax.inject.Inject()
    public DocumentDetailViewModel(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.DocDao dao, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    androidx.lifecycle.SavedStateHandle savedStateHandle, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.ui.viewmodels.AppErrorReporter appViewModel) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.synthbyte.scanmate.data.DocumentWithPages> getDocumentWithPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<com.synthbyte.scanmate.ui.viewmodels.ExportState> getExportState() {
        return null;
    }
    
    public final void clearExportState() {
    }
    
    private final void publishError(java.lang.Throwable throwable) {
    }
    
    private final void publishErrorMessage(java.lang.String message) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job exportDocx(@org.jetbrains.annotations.Nullable()
    com.synthbyte.scanmate.data.DocumentWithPages dwp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job exportPdf(@org.jetbrains.annotations.Nullable()
    com.synthbyte.scanmate.data.DocumentWithPages dwp, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfExportQuality quality, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.utils.PdfPageSize pageSize) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job exportProtectedPdf(@org.jetbrains.annotations.Nullable()
    com.synthbyte.scanmate.data.DocumentWithPages dwp, @org.jetbrains.annotations.NotNull()
    java.lang.String password, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, boolean allowPrinting, boolean allowCopy) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job scoreQuality(@org.jetbrains.annotations.Nullable()
    com.synthbyte.scanmate.data.DocumentWithPages dwp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job extractOcr(@org.jetbrains.annotations.Nullable()
    com.synthbyte.scanmate.data.DocumentWithPages dwp) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setFavorite(boolean favorite) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job setPinned(boolean pinned) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job rename(@org.jetbrains.annotations.NotNull()
    java.lang.String title) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateMeta(@org.jetbrains.annotations.NotNull()
    java.lang.String category, @org.jetbrains.annotations.NotNull()
    java.lang.String tags, @org.jetbrains.annotations.NotNull()
    java.lang.String workspace) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job delete(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeleted) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updateOcr(@org.jetbrains.annotations.NotNull()
    java.lang.String text) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object updateOcrAndMetadata(@org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String currentWorkspace, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job updatePageImage(long pageId, @org.jetbrains.annotations.NotNull()
    java.lang.String imagePath) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job deletePage(long pageId, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job duplicatePage(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.Page page, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job movePage(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.Page page, int direction, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job reorderPage(long pageId, int newOrder) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job reorderPages(@org.jetbrains.annotations.NotNull()
    java.util.List<com.synthbyte.scanmate.data.Page> pages, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
        return null;
    }
    
    private final java.lang.Object deletePageInternal(long pageId, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object duplicatePageInternal(com.synthbyte.scanmate.data.Page page, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object movePageInternal(com.synthbyte.scanmate.data.Page page, int direction, kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.Object renumberPages(kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}