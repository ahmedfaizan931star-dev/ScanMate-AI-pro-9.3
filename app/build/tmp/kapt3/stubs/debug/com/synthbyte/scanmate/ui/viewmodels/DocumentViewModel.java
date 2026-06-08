package com.synthbyte.scanmate.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0086\u0001\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0010\b\n\u0002\b\u0006\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\b\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\t\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u000e\n\u0002\u0010\u000b\n\u0002\b\n\b\u0007\u0018\u00002\u00020\u0001B\u0019\b\u0007\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\b\b\u0001\u0010\u0004\u001a\u00020\u0005\u00a2\u0006\u0002\u0010\u0006J\u000e\u0010*\u001a\u00020+2\u0006\u0010,\u001a\u00020\nJ\u0006\u0010-\u001a\u00020.JH\u0010/\u001a\u00020+2\f\u00100\u001a\b\u0012\u0004\u0012\u0002010\t2\b\b\u0002\u00102\u001a\u00020\n2\u0012\u00103\u001a\u000e\u0012\u0004\u0012\u000205\u0012\u0004\u0012\u00020+042\u0014\b\u0002\u00106\u001a\u000e\u0012\u0004\u0012\u00020\n\u0012\u0004\u0012\u00020+04J\u001e\u00107\u001a\u00020+2\u0006\u00108\u001a\u0002052\u000e\b\u0002\u00109\u001a\b\u0012\u0004\u0012\u00020+0:J$\u0010;\u001a\u00020+2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002050\t2\u000e\b\u0002\u00109\u001a\b\u0012\u0004\u0012\u00020+0:J\u0016\u0010=\u001a\u00020.2\u0006\u0010>\u001a\u00020\n2\u0006\u0010?\u001a\u00020\nJ\b\u0010@\u001a\u00020+H\u0014J\u0016\u0010A\u001a\u00020+2\u0006\u00108\u001a\u0002052\u0006\u0010B\u001a\u00020\nJ,\u0010C\u001a\u00020+2\u0006\u0010D\u001a\u00020\r2\f\u0010E\u001a\b\u0012\u0004\u0012\u00020\u00110\t2\u000e\b\u0002\u0010F\u001a\b\u0012\u0004\u0012\u00020+0:J,\u0010G\u001a\u00020+2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002050\t2\u0006\u0010H\u001a\u00020I2\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020+0:J,\u0010K\u001a\u00020+2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002050\t2\u0006\u0010L\u001a\u00020I2\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020+0:J\u000e\u0010M\u001a\u00020+2\u0006\u0010N\u001a\u00020\nJ,\u0010O\u001a\u00020+2\f\u0010<\u001a\b\u0012\u0004\u0012\u0002050\t2\u0006\u0010P\u001a\u00020\n2\u000e\b\u0002\u0010J\u001a\b\u0012\u0004\u0012\u00020+0:J\u000e\u0010Q\u001a\u00020+2\u0006\u0010D\u001a\u00020\rJ\u000e\u0010R\u001a\u00020+2\u0006\u0010D\u001a\u00020\rR\u001a\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\bX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u000b\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u000fR\u001d\u0010\u0010\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u00110\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0012\u0010\u000fR\u000e\u0010\u0004\u001a\u00020\u0005X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0013\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0014\u0010\u000fR\u0017\u0010\u0015\u001a\b\u0012\u0004\u0012\u00020\u00160\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0017\u0010\u000fR\u0017\u0010\u0018\u001a\b\u0012\u0004\u0012\u00020\u00160\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0019\u0010\u000fR\u001d\u0010\u001a\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001b\u0010\u000fR\u001d\u0010\u001c\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\u001d0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\u000fR\u001d\u0010\u001f\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\f\u00a2\u0006\b\n\u0000\u001a\u0004\b \u0010\u000fR\u001d\u0010!\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b#\u0010$R\u0017\u0010%\u001a\b\u0012\u0004\u0012\u00020\n0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b&\u0010\'R\u001d\u0010(\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\r0\t0\"\u00a2\u0006\b\n\u0000\u001a\u0004\b)\u0010$\u00a8\u0006S"}, d2 = {"Lcom/synthbyte/scanmate/ui/viewmodels/DocumentViewModel;", "Landroidx/lifecycle/ViewModel;", "dao", "Lcom/synthbyte/scanmate/data/DocDao;", "context", "Landroid/content/Context;", "(Lcom/synthbyte/scanmate/data/DocDao;Landroid/content/Context;)V", "_searchHistory", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "", "allDocuments", "Lkotlinx/coroutines/flow/Flow;", "Lcom/synthbyte/scanmate/data/Document;", "getAllDocuments", "()Lkotlinx/coroutines/flow/Flow;", "allPages", "Lcom/synthbyte/scanmate/data/Page;", "getAllPages", "favoriteDocuments", "getFavoriteDocuments", "pageCount", "", "getPageCount", "pdfCount", "getPdfCount", "pinnedDocuments", "getPinnedDocuments", "qrHistory", "Lcom/synthbyte/scanmate/data/QrHistory;", "getQrHistory", "recentDocuments", "getRecentDocuments", "searchHistory", "Lkotlinx/coroutines/flow/StateFlow;", "getSearchHistory", "()Lkotlinx/coroutines/flow/StateFlow;", "searchQuery", "getSearchQuery", "()Lkotlinx/coroutines/flow/MutableStateFlow;", "searchResults", "getSearchResults", "addToSearchHistory", "", "query", "clearQrHistory", "Lkotlinx/coroutines/Job;", "createDocumentFromUris", "uris", "Landroid/net/Uri;", "defaultWorkspace", "onCreated", "Lkotlin/Function1;", "", "onError", "deleteDocument", "id", "onDeleted", "Lkotlin/Function0;", "deleteDocuments", "documentIds", "insertQrHistory", "value", "type", "onCleared", "renameDocument", "title", "restoreDocument", "document", "pages", "onRestored", "setFavoriteBulk", "favorite", "", "onDone", "setPinnedBulk", "pinned", "setSearchQuery", "q", "setWorkspace", "workspace", "toggleFavorite", "togglePinned", "app_debug"})
@dagger.hilt.android.lifecycle.HiltViewModel()
public final class DocumentViewModel extends androidx.lifecycle.ViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.synthbyte.scanmate.data.DocDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> allDocuments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> favoriteDocuments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> pinnedDocuments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> recentDocuments = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Page>> allPages = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> pageCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Integer> pdfCount = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.QrHistory>> qrHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> searchQuery = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.util.List<java.lang.String>> _searchHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> searchHistory = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.util.List<com.synthbyte.scanmate.data.Document>> searchResults = null;
    
    @javax.inject.Inject()
    public DocumentViewModel(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.DocDao dao, @dagger.hilt.android.qualifiers.ApplicationContext()
    @org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> getAllDocuments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> getFavoriteDocuments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> getPinnedDocuments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Document>> getRecentDocuments() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.Page>> getAllPages() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getPageCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Integer> getPdfCount() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.util.List<com.synthbyte.scanmate.data.QrHistory>> getQrHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> getSearchQuery() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<java.lang.String>> getSearchHistory() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.util.List<com.synthbyte.scanmate.data.Document>> getSearchResults() {
        return null;
    }
    
    public final void setSearchQuery(@org.jetbrains.annotations.NotNull()
    java.lang.String q) {
    }
    
    public final void addToSearchHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
    }
    
    public final void createDocumentFromUris(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends android.net.Uri> uris, @org.jetbrains.annotations.NotNull()
    java.lang.String defaultWorkspace, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.Long, kotlin.Unit> onCreated, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onError) {
    }
    
    public final void renameDocument(long id, @org.jetbrains.annotations.NotNull()
    java.lang.String title) {
    }
    
    public final void toggleFavorite(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.Document document) {
    }
    
    public final void togglePinned(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.Document document) {
    }
    
    public final void deleteDocument(long id, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeleted) {
    }
    
    public final void setWorkspace(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> documentIds, @org.jetbrains.annotations.NotNull()
    java.lang.String workspace, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    public final void setFavoriteBulk(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> documentIds, boolean favorite, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    public final void setPinnedBulk(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> documentIds, boolean pinned, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDone) {
    }
    
    public final void deleteDocuments(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> documentIds, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onDeleted) {
    }
    
    public final void restoreDocument(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.Document document, @org.jetbrains.annotations.NotNull()
    java.util.List<com.synthbyte.scanmate.data.Page> pages, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onRestored) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job insertQrHistory(@org.jetbrains.annotations.NotNull()
    java.lang.String value, @org.jetbrains.annotations.NotNull()
    java.lang.String type) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.Job clearQrHistory() {
        return null;
    }
    
    @java.lang.Override()
    protected void onCleared() {
    }
}