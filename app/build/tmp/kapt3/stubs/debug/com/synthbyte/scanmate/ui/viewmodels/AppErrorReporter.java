package com.synthbyte.scanmate.ui.viewmodels;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000\u0018\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0002\n\u0002\u0010\u000e\n\u0000\bf\u0018\u00002\u00020\u0001J\b\u0010\u0002\u001a\u00020\u0003H&J\u0010\u0010\u0004\u001a\u00020\u00032\u0006\u0010\u0005\u001a\u00020\u0006H&\u00f8\u0001\u0000\u0082\u0002\u0006\n\u0004\b!0\u0001\u00a8\u0006\u0007\u00c0\u0006\u0001"}, d2 = {"Lcom/synthbyte/scanmate/ui/viewmodels/AppErrorReporter;", "", "clearError", "", "reportError", "message", "", "app_debug"})
public abstract interface AppErrorReporter {
    
    public abstract void reportError(@org.jetbrains.annotations.NotNull()
    java.lang.String message);
    
    public abstract void clearError();
}