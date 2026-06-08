package com.synthbyte.scanmate.utils;

/**
 * Local-only custom OCR dictionary storage.
 * No account, backend, Firebase, or cloud dependency.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\u000b\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\"\n\u0002\b\u0004\b\u0007\u0018\u0000 \u00122\u00020\u0001:\u0001\u0012B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\b\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bJ\u0006\u0010\f\u001a\u00020\rJ\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000b0\u000fJ\u0012\u0010\u0010\u001a\u0004\u0018\u00010\u000b2\u0006\u0010\n\u001a\u00020\u000bH\u0002J\u000e\u0010\u0011\u001a\u00020\t2\u0006\u0010\n\u001a\u00020\u000bR\u0016\u0010\u0005\u001a\n \u0007*\u0004\u0018\u00010\u00060\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0013"}, d2 = {"Lcom/synthbyte/scanmate/utils/OcrCustomDictionaryStore;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "prefs", "Landroid/content/SharedPreferences;", "kotlin.jvm.PlatformType", "addWord", "", "word", "", "clear", "", "getWords", "", "normalizeWord", "removeWord", "Companion", "app_debug"})
public final class OcrCustomDictionaryStore {
    private final android.content.SharedPreferences prefs = null;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String PREFS_NAME = "scanmate_ocr_dictionary";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_WORDS = "custom_words";
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.utils.OcrCustomDictionaryStore.Companion Companion = null;
    
    public OcrCustomDictionaryStore(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.Set<java.lang.String> getWords() {
        return null;
    }
    
    public final boolean addWord(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
        return false;
    }
    
    public final boolean removeWord(@org.jetbrains.annotations.NotNull()
    java.lang.String word) {
        return false;
    }
    
    public final void clear() {
    }
    
    private final java.lang.String normalizeWord(java.lang.String word) {
        return null;
    }
    
    @kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0014\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0002\b\u0086\u0003\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0006"}, d2 = {"Lcom/synthbyte/scanmate/utils/OcrCustomDictionaryStore$Companion;", "", "()V", "KEY_WORDS", "", "PREFS_NAME", "app_debug"})
    public static final class Companion {
        
        private Companion() {
            super();
        }
    }
}