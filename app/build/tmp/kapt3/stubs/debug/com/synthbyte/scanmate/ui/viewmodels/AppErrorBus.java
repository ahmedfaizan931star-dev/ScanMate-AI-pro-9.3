package com.synthbyte.scanmate.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000&\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0002\u0010\u000e\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0010\u0002\n\u0002\b\u0003\b\u0007\u0018\u00002\u00020\u0001B\u0007\b\u0007\u00a2\u0006\u0002\u0010\u0002J\b\u0010\n\u001a\u00020\u000bH\u0016J\u0010\u0010\f\u001a\u00020\u000b2\u0006\u0010\r\u001a\u00020\u0005H\u0016R\u0016\u0010\u0003\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0004X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u0019\u0010\u0006\u001a\n\u0012\u0006\u0012\u0004\u0018\u00010\u00050\u0007\u00a2\u0006\b\n\u0000\u001a\u0004\b\b\u0010\t\u00a8\u0006\u000e"}, d2 = {"Lcom/synthbyte/scanmate/ui/viewmodels/AppErrorBus;", "Lcom/synthbyte/scanmate/ui/viewmodels/AppErrorReporter;", "()V", "_globalError", "Lkotlinx/coroutines/flow/MutableStateFlow;", "", "globalError", "Lkotlinx/coroutines/flow/StateFlow;", "getGlobalError", "()Lkotlinx/coroutines/flow/StateFlow;", "clearError", "", "reportError", "message", "app_debug"})
@dagger.hilt.android.scopes.ActivityRetainedScoped()
public final class AppErrorBus implements com.synthbyte.scanmate.ui.viewmodels.AppErrorReporter {
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.MutableStateFlow<java.lang.String> _globalError = null;
    @org.jetbrains.annotations.NotNull()
    private final kotlinx.coroutines.flow.StateFlow<java.lang.String> globalError = null;
    
    @javax.inject.Inject()
    public AppErrorBus() {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final kotlinx.coroutines.flow.StateFlow<java.lang.String> getGlobalError() {
        return null;
    }
    
    @java.lang.Override()
    public void reportError(@org.jetbrains.annotations.NotNull()
    java.lang.String message) {
    }
    
    @java.lang.Override()
    public void clearError() {
    }
}