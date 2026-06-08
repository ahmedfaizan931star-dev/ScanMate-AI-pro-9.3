package com.synthbyte.scanmate.utils;

/**
 * Lightweight offline document intelligence used as a graceful fallback when online AI is unavailable.
 * It never sends data anywhere and keeps ScanMate usable as an offline-first scanner.
 */
@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0012\n\u0002\u0018\u0002\n\u0002\u0010\u0010\n\u0000\n\u0002\u0010\u000e\n\u0002\b\u0010\b\u0086\u0081\u0002\u0018\u00002\b\u0012\u0004\u0012\u00020\u00000\u0001B\u001f\b\u0002\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u0012\u0006\u0010\u0004\u001a\u00020\u0003\u0012\u0006\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0006R\u0011\u0010\u0004\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\u0007\u0010\bR\u0011\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\t\u0010\bR\u0011\u0010\u0005\u001a\u00020\u0003\u00a2\u0006\b\n\u0000\u001a\u0004\b\n\u0010\bj\u0002\b\u000bj\u0002\b\fj\u0002\b\rj\u0002\b\u000ej\u0002\b\u000fj\u0002\b\u0010j\u0002\b\u0011j\u0002\b\u0012\u00a8\u0006\u0013"}, d2 = {"Lcom/synthbyte/scanmate/utils/AiWorkflow;", "", "label", "", "description", "promptPrefix", "(Ljava/lang/String;ILjava/lang/String;Ljava/lang/String;Ljava/lang/String;)V", "getDescription", "()Ljava/lang/String;", "getLabel", "getPromptPrefix", "SUMMARY", "HOMEWORK", "RECEIPT", "INVOICE", "OCR_CLEANUP", "OCR_TRANSLATE", "DOCUMENT_CHAT", "TITLE_KEYWORDS", "app_debug"})
public enum AiWorkflow {
    /*public static final*/ SUMMARY /* = new SUMMARY(null, null, null) */,
    /*public static final*/ HOMEWORK /* = new HOMEWORK(null, null, null) */,
    /*public static final*/ RECEIPT /* = new RECEIPT(null, null, null) */,
    /*public static final*/ INVOICE /* = new INVOICE(null, null, null) */,
    /*public static final*/ OCR_CLEANUP /* = new OCR_CLEANUP(null, null, null) */,
    /*public static final*/ OCR_TRANSLATE /* = new OCR_TRANSLATE(null, null, null) */,
    /*public static final*/ DOCUMENT_CHAT /* = new DOCUMENT_CHAT(null, null, null) */,
    /*public static final*/ TITLE_KEYWORDS /* = new TITLE_KEYWORDS(null, null, null) */;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String label = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String description = null;
    @org.jetbrains.annotations.NotNull()
    private final java.lang.String promptPrefix = null;
    
    AiWorkflow(java.lang.String label, java.lang.String description, java.lang.String promptPrefix) {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getLabel() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getDescription() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String getPromptPrefix() {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public static kotlin.enums.EnumEntries<com.synthbyte.scanmate.utils.AiWorkflow> getEntries() {
        return null;
    }
}