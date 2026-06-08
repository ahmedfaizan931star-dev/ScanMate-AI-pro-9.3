package com.synthbyte.scanmate;

@dagger.hilt.android.AndroidEntryPoint()
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000B\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0007\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0003\n\u0002\b\u0002\b\u0007\u0018\u0000 \u001c2\u00020\u0001:\u0001\u001cB\u0005\u00a2\u0006\u0002\u0010\u0002J(\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u00072\u0006\u0010\u000b\u001a\u00020\u00072\u0006\u0010\f\u001a\u00020\u00072\u0006\u0010\r\u001a\u00020\u0007H\u0002J\u0012\u0010\u000e\u001a\u00020\u00072\b\u0010\u000f\u001a\u0004\u0018\u00010\u0007H\u0002J\u0012\u0010\u0010\u001a\u00020\u00112\b\u0010\u0012\u001a\u0004\u0018\u00010\u0013H\u0014J\b\u0010\u0014\u001a\u00020\u0011H\u0014J\u0010\u0010\u0015\u001a\u00020\u00112\u0006\u0010\u0016\u001a\u00020\u0017H\u0014J\b\u0010\u0018\u001a\u00020\u0011H\u0002J\u0010\u0010\u0019\u001a\u00020\u00112\u0006\u0010\u001a\u001a\u00020\u001bH\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082.\u00a2\u0006\u0002\n\u0000R\u0016\u0010\u0005\u001a\n\u0012\u0004\u0012\u00020\u0007\u0018\u00010\u0006X\u0082\u000e\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001d"}, d2 = {"Lcom/synthbyte/scanmate/MainActivity;", "Landroidx/fragment/app/FragmentActivity;", "()V", "settingsRepository", "Lcom/synthbyte/scanmate/data/SettingsRepository;", "shortcutRouteState", "Landroidx/compose/runtime/MutableState;", "", "buildShortcut", "Landroidx/core/content/pm/ShortcutInfoCompat;", "id", "label", "longLabel", "route", "normalizeShortcutRoute", "raw", "onCreate", "", "savedInstanceState", "Landroid/os/Bundle;", "onDestroy", "onNewIntent", "intent", "Landroid/content/Intent;", "publishLauncherShortcuts", "writeCrashLog", "throwable", "", "Companion", "app_debug"})
public final class MainActivity extends androidx.fragment.app.FragmentActivity {
    @org.jetbrains.annotations.Nullable()
    private androidx.compose.runtime.MutableState<java.lang.String> shortcutRouteState;
    private com.synthbyte.scanmate.data.SettingsRepository settingsRepository;
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String EXTRA_SHORTCUT_ROUTE = "shortcut_route";
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.MainActivity.Companion Companion = null;
    
    public MainActivity() {
        super();
    }
    
    @java.lang.Override()
    protected void onCreate(@org.jetbrains.annotations.Nullable()
    android.os.Bundle savedInstanceState) {
    }
    
    private final void writeCrashLog(java.lang.Throwable throwable) {
    }
    
    @java.lang.Override()
    protected void onNewIntent(@org.jetbrains.annotations.NotNull()
    android.content.Intent intent) {
    }
    
    @java.lang.Override()
    protected void onDestroy() {
    }
    
    private final void publishLauncherShortcuts() {
    }
    
    private final androidx.core.content.pm.ShortcutInfoCompat buildShortcut(java.lang.String id, java.lang.String label, java.lang.String longLabel, java.lang.String route) {
        return null;
    }
    
    private final java.lang.String normalizeShortcutRoute(java.lang.String raw) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0005"}, d2 = {"Lcom/synthbyte/scanmate/MainActivity$Companion;", "", "()V", "EXTRA_SHORTCUT_ROUTE", "", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}