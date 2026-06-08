package com.synthbyte.scanmate.data;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000F\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\t\n\u0002\u0010\u000b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u0002\n\u0002\b\u0012\b\u0007\u0018\u0000 12\u00020\u0001:\u00011B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u001f\u001a\u00020 H\u0086@\u00a2\u0006\u0002\u0010!J\n\u0010\"\u001a\u0004\u0018\u00010\u0007H\u0002J\u0016\u0010#\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u0010&\u001a\u00020 2\u0006\u0010\'\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u0010(\u001a\u00020 2\u0006\u0010)\u001a\u00020\u0007H\u0086@\u00a2\u0006\u0002\u0010%J\u0016\u0010*\u001a\u00020 2\u0006\u0010+\u001a\u00020\u001dH\u0086@\u00a2\u0006\u0002\u0010,J\u0018\u0010-\u001a\u00020 2\b\b\u0002\u0010.\u001a\u00020\u0015H\u0086@\u00a2\u0006\u0002\u0010/J\u0010\u00100\u001a\u00020 2\u0006\u0010$\u001a\u00020\u0007H\u0002R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0017\u0010\u0005\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\tR\u001b\u0010\n\u001a\u00020\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000e\u0010\u000f\u001a\u0004\b\f\u0010\rR\u0019\u0010\u0010\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0011\u0010\tR\u0017\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00070\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0013\u0010\tR\u0017\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\u00150\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0016\u0010\tR\u000e\u0010\u0017\u001a\u00020\u0018X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0019\u001a\u0004\u0018\u00010\u000b8BX\u0082\u0084\u0002\u00a2\u0006\f\n\u0004\b\u001b\u0010\u000f\u001a\u0004\b\u001a\u0010\rR\u0017\u0010\u001c\u001a\b\u0012\u0004\u0012\u00020\u001d0\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u001e\u0010\t\u00a8\u00062"}, d2 = {"Lcom/synthbyte/scanmate/data/SettingsRepository;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "defaultWorkspaceFlow", "Lkotlinx/coroutines/flow/Flow;", "", "getDefaultWorkspaceFlow", "()Lkotlinx/coroutines/flow/Flow;", "fallbackPrefs", "Landroid/content/SharedPreferences;", "getFallbackPrefs", "()Landroid/content/SharedPreferences;", "fallbackPrefs$delegate", "Lkotlin/Lazy;", "geminiApiKeyFlow", "getGeminiApiKeyFlow", "geminiModelIdFlow", "getGeminiModelIdFlow", "onboardingCompleteFlow", "", "getOnboardingCompleteFlow", "repositoryScope", "Lkotlinx/coroutines/CoroutineScope;", "securePrefs", "getSecurePrefs", "securePrefs$delegate", "themeModeFlow", "Lcom/synthbyte/scanmate/data/ThemeMode;", "getThemeModeFlow", "clearApiKey", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "readApiKey", "saveApiKey", "apiKey", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveDefaultWorkspace", "workspace", "saveGeminiModel", "modelId", "saveThemeMode", "themeMode", "(Lcom/synthbyte/scanmate/data/ThemeMode;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "setOnboardingComplete", "complete", "(ZLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "writeApiKey", "Companion", "app_debug"})
@kotlin.Suppress(names = {"DEPRECATION"})
public final class SettingsRepository {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.CoroutineScope repositoryScope = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy fallbackPrefs$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlin.Lazy securePrefs$delegate = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> GEMINI_API_KEY = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> GEMINI_MODEL_ID = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> THEME_MODE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> ONBOARDING_COMPLETE = null;
    @org.jetbrains.annotations.NotNull()
    private static final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> DEFAULT_WORKSPACE = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> geminiApiKeyFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<com.synthbyte.scanmate.data.ThemeMode> themeModeFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> geminiModelIdFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.Boolean> onboardingCompleteFlow = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.Flow<java.lang.String> defaultWorkspaceFlow = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.data.SettingsRepository.Companion Companion = null;
    
    public SettingsRepository(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    private final android.content.SharedPreferences getFallbackPrefs() {
        return null;
    }
    
    private final android.content.SharedPreferences getSecurePrefs() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGeminiApiKeyFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<com.synthbyte.scanmate.data.ThemeMode> getThemeModeFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getGeminiModelIdFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.Boolean> getOnboardingCompleteFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.Flow<java.lang.String> getDefaultWorkspaceFlow() {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveApiKey(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object clearApiKey(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveGeminiModel(@org.jetbrains.annotations.NotNull()
    java.lang.String modelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveThemeMode(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.ThemeMode themeMode, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object setOnboardingComplete(boolean complete, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveDefaultWorkspace(@org.jetbrains.annotations.NotNull()
    java.lang.String workspace, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion) {
        return null;
    }
    
    private final java.lang.String readApiKey() {
        return null;
    }
    
    private final void writeApiKey(java.lang.String apiKey) {
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000 \n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0002\b\u0007\n\u0002\u0010\u000b\n\u0002\b\u0004\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u0017\u0010\u0003\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0006\u0010\u0007R\u0017\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\u0007R\u0017\u0010\n\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\u0007R\u0017\u0010\f\u001a\b\u0012\u0004\u0012\u00020\r0\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000e\u0010\u0007R\u0017\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u00050\u0004\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0010\u0010\u0007\u00a8\u0006\u0011"}, d2 = {"Lcom/synthbyte/scanmate/data/SettingsRepository$Companion;", "", "()V", "DEFAULT_WORKSPACE", "Landroidx/datastore/preferences/core/Preferences$Key;", "", "getDEFAULT_WORKSPACE", "()Landroidx/datastore/preferences/core/Preferences$Key;", "GEMINI_API_KEY", "getGEMINI_API_KEY", "GEMINI_MODEL_ID", "getGEMINI_MODEL_ID", "ONBOARDING_COMPLETE", "", "getONBOARDING_COMPLETE", "THEME_MODE", "getTHEME_MODE", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getGEMINI_API_KEY() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getGEMINI_MODEL_ID() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getTHEME_MODE() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.Boolean> getONBOARDING_COMPLETE() {
            return null;
        }
        
        @org.jetbrains.annotations.NotNull()
        public final androidx.datastore.preferences.core.Preferences.Key<java.lang.String> getDEFAULT_WORKSPACE() {
            return null;
        }
    }
}