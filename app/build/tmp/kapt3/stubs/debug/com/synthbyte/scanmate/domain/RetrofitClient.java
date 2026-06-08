package com.synthbyte.scanmate.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00002\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0005\n\u0002\u0010\u0002\n\u0002\u0018\u0002\n\u0000\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\f\u0010\u0011\u001a\u00020\u0012*\u00020\u0013H\u0002R\u000e\u0010\u0003\u001a\u00020\u0004X\u0082T\u00a2\u0006\u0002\n\u0000R\u0011\u0010\u0005\u001a\u00020\u0006\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u000e\u0010\t\u001a\u00020\nX\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001b\u0010\u000b\u001a\u00020\f8FX\u0086\u0084\u0002\u00a2\u0006\f\n\u0004\b\u000f\u0010\u0010\u001a\u0004\b\r\u0010\u000e\u00a8\u0006\u0014"}, d2 = {"Lcom/synthbyte/scanmate/domain/RetrofitClient;", "", "()V", "BASE_URL", "", "moshi", "Lcom/squareup/moshi/Moshi;", "getMoshi", "()Lcom/squareup/moshi/Moshi;", "okHttpClient", "Lokhttp3/OkHttpClient;", "service", "Lcom/synthbyte/scanmate/domain/GeminiApiService;", "getService", "()Lcom/synthbyte/scanmate/domain/GeminiApiService;", "service$delegate", "Lkotlin/Lazy;", "addDebugLoggingInterceptor", "", "Lokhttp3/OkHttpClient$Builder;", "app_debug"})
public final class RetrofitClient {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BASE_URL = "https://generativelanguage.googleapis.com/";
    @org.jetbrains.annotations.NotNull()
    private static final okhttp3.OkHttpClient okHttpClient = null;
    @org.jetbrains.annotations.NotNull()
    private static final com.squareup.moshi.Moshi moshi = null;
    @org.jetbrains.annotations.NotNull()
    private static final kotlin.Lazy service$delegate = null;
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.domain.RetrofitClient INSTANCE = null;
    
    private RetrofitClient() {
        super();
    }
    
    private final void addDebugLoggingInterceptor(okhttp3.OkHttpClient.Builder $this$addDebugLoggingInterceptor) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.squareup.moshi.Moshi getMoshi() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.synthbyte.scanmate.domain.GeminiApiService getService() {
        return null;
    }
}