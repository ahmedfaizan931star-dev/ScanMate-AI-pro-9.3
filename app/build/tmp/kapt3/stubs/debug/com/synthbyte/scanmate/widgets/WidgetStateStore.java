package com.synthbyte.scanmate.widgets;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000.\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u0018\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\b\u0010\u000b\u001a\u0004\u0018\u00010\fJ\u001a\u0010\r\u001a\u000e\u0012\u0004\u0012\u00020\u0004\u0012\u0004\u0012\u00020\u00040\u000e2\u0006\u0010\t\u001a\u00020\nJ\u0010\u0010\u000f\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\nH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0010"}, d2 = {"Lcom/synthbyte/scanmate/widgets/WidgetStateStore;", "", "()V", "KEY_SUBTITLE", "", "KEY_TITLE", "PREFS_NAME", "publishRecentDocument", "", "context", "Landroid/content/Context;", "document", "Lcom/synthbyte/scanmate/data/Document;", "recentDocumentText", "Lkotlin/Pair;", "requestRecentWidgetRefresh", "app_debug"})
public final class WidgetStateStore {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "scanmate_widget_state";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_TITLE = "recent_title";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_SUBTITLE = "recent_subtitle";
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.widgets.WidgetStateStore INSTANCE = null;
    
    private WidgetStateStore() {
        super();
    }
    
    public final void publishRecentDocument(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.Nullable()
    com.synthbyte.scanmate.data.Document document) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlin.Pair<java.lang.String, java.lang.String> recentDocumentText(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        return null;
    }
    
    private final void requestRecentWidgetRefresh(android.content.Context context) {
    }
}