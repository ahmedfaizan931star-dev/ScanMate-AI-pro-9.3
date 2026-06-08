package com.synthbyte.scanmate.ui.navigation;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u001c\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0002\b\u0011\n\u0002\u0010\t\n\u0002\b\u0004\b\u00c7\u0002\u0018\u00002\u00020\u0001B\u0007\b\u0002\u00a2\u0006\u0002\u0010\u0002J\u000e\u0010\u0014\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016J\u0016\u0010\u0017\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u00162\u0006\u0010\u0018\u001a\u00020\u0016J\u000e\u0010\u0019\u001a\u00020\u00042\u0006\u0010\u0015\u001a\u00020\u0016R\u000e\u0010\u0003\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0005\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0006\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0007\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\t\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\n\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000b\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\r\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000e\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u000f\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0010\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0011\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0012\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000R\u000e\u0010\u0013\u001a\u00020\u0004X\u0086T\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/synthbyte/scanmate/ui/navigation/Routes;", "", "()V", "AI_ASSISTANT", "", "CAMERA_SCAN", "DOC_DETAIL", "FILE_MANAGER", "HOME", "OCR_TRANSLATE", "ONBOARDING", "PAGE_EDITOR", "PDF_TOOLS", "QR_SCANNER", "QR_TOOLS", "SETTINGS", "SIGNATURE", "TOOLS", "VAULT", "ZIP_TOOLS", "docDetail", "docId", "", "pageEditor", "pageId", "signature", "app_debug"})
public final class Routes {
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ONBOARDING = "onboarding";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String HOME = "home";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String CAMERA_SCAN = "camera_scan";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String DOC_DETAIL = "document_detail/{docId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PAGE_EDITOR = "page_editor/{docId}/{pageId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String PDF_TOOLS = "pdf_tools";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SIGNATURE = "signature/{docId}";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String QR_TOOLS = "qr_tools";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String QR_SCANNER = "qr_scanner";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String SETTINGS = "settings";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String TOOLS = "tools";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String ZIP_TOOLS = "zip_tools";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String AI_ASSISTANT = "ai_assistant";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String OCR_TRANSLATE = "ocr_translate";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String FILE_MANAGER = "file_manager";
    @org.jetbrains.annotations.NotNull()
    public static final java.lang.String VAULT = "vault";
    @org.jetbrains.annotations.NotNull()
    public static final com.synthbyte.scanmate.ui.navigation.Routes INSTANCE = null;
    
    private Routes() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String docDetail(long docId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String pageEditor(long docId, long pageId) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.lang.String signature(long docId) {
        return null;
    }
}