package com.synthbyte.scanmate.utils;

/**
 * Minimal Android Keystore backed local vault for sensitive OCR text.
 * Files are stored locally in app-managed storage and encrypted with AES-GCM.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0002J\u0018\u0010\f\u001a\u0004\u0018\u00010\u00042\u0006\u0010\r\u001a\u00020\u000eH\u0086@\u00a2\u0006\u0002\u0010\u000fJ(\u0010\u0010\u001a\u0004\u0018\u00010\u000e2\u0006\u0010\u0011\u001a\u00020\u00122\u0006\u0010\u0013\u001a\u00020\u00042\u0006\u0010\u0014\u001a\u00020\u0004H\u0086@\u00a2\u0006\u0002\u0010\u0015R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0006X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/synthbyte/scanmate/utils/EncryptedVaultUtils;", "", "()V", "ANDROID_KEYSTORE", "", "GCM_TAG_BITS", "", "KEY_ALIAS", "TRANSFORMATION", "VERSION", "getOrCreateKey", "Ljavax/crypto/SecretKey;", "readEncryptedText", "file", "Ljava/io/File;", "(Ljava/io/File;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "saveEncryptedText", "context", "Landroid/content/Context;", "text", "filename", "(Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class EncryptedVaultUtils {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String ANDROID_KEYSTORE = "AndroidKeyStore";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String KEY_ALIAS = "scanmate_ai_pro_local_vault";
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String TRANSFORMATION = "AES/GCM/NoPadding";
    private static final int GCM_TAG_BITS = 128;
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String VERSION = "SCANMATE_VAULT_V1";
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.utils.EncryptedVaultUtils INSTANCE = null;
    
    private EncryptedVaultUtils() {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object saveEncryptedText(@org.jetbrains.annotations.NotNull()
    android.content.Context context, @org.jetbrains.annotations.NotNull()
    java.lang.String text, @org.jetbrains.annotations.NotNull()
    java.lang.String filename, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.io.File> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object readEncryptedText(@org.jetbrains.annotations.NotNull()
    java.io.File file, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.String> $completion) {
        return null;
    }
    
    private final javax.crypto.SecretKey getOrCreateKey() {
        return null;
    }
}