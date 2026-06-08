package com.synthbyte.scanmate.domain;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0010\b\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001a\u0010\u0005\u001a\u00020\u00032\u0006\u0010\u0006\u001a\u00020\u00072\b\u0010\b\u001a\u0004\u0018\u00010\u0003H\u0002J \u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\u00032\b\b\u0002\u0010\f\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0002\u0010\rJ\u0018\u0010\u000e\u001a\u0004\u0018\u00010\u000f2\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00120\u0011H\u0002J\u0016\u0010\u0013\u001a\u00020\n2\u0006\u0010\f\u001a\u00020\u0003H\u0086@\u00a2\u0006\u0002\u0010\u0014R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0015"}, d2 = {"Lcom/synthbyte/scanmate/domain/GeminiHelper;", "", "apiKey", "", "(Ljava/lang/String;)V", "friendlyGeminiError", "statusCode", "", "rawMessage", "generateContent", "Lcom/synthbyte/scanmate/domain/GeminiTextResult;", "prompt", "modelId", "(Ljava/lang/String;Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "parseError", "Lcom/synthbyte/scanmate/domain/GeminiError;", "response", "Lretrofit2/Response;", "Lcom/synthbyte/scanmate/domain/GenerateContentResponse;", "testConnection", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public final class GeminiHelper {
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String apiKey = null;
    
    public GeminiHelper(@org.jetbrains.annotations.NotNull()
    java.lang.String apiKey) {
        super();
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object generateContent(@org.jetbrains.annotations.NotNull()
    java.lang.String prompt, @org.jetbrains.annotations.NotNull()
    java.lang.String modelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.synthbyte.scanmate.domain.GeminiTextResult> $completion) {
        return null;
    }
    
    @org.jetbrains.annotations.Nullable()
    public final java.lang.Object testConnection(@org.jetbrains.annotations.NotNull()
    java.lang.String modelId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.synthbyte.scanmate.domain.GeminiTextResult> $completion) {
        return null;
    }
    
    private final com.synthbyte.scanmate.domain.GeminiError parseError(retrofit2.Response<com.synthbyte.scanmate.domain.GenerateContentResponse> response) {
        return null;
    }
    
    private final java.lang.String friendlyGeminiError(int statusCode, java.lang.String rawMessage) {
        return null;
    }
}