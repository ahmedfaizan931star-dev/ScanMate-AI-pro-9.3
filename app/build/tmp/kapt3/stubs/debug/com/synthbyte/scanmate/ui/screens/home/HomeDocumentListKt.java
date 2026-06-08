package com.synthbyte.scanmate.ui.screens.home;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u00000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0006\u001aD\u0010\u0000\u001a\u00020\u00012\u0006\u0010\u0002\u001a\u00020\u00032\b\u0010\u0004\u001a\u0004\u0018\u00010\u00052\f\u0010\u0006\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\b\u001a\b\u0012\u0004\u0012\u00020\u00010\u00072\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a\u0016\u0010\n\u001a\u00020\u00012\f\u0010\u000b\u001a\b\u0012\u0004\u0012\u00020\u00010\u0007H\u0007\u001a2\u0010\f\u001a\u00020\u00012\u0006\u0010\r\u001a\u00020\u000e2\f\u0010\u000f\u001a\b\u0012\u0004\u0012\u00020\u000e0\u00102\u0012\u0010\u0011\u001a\u000e\u0012\u0004\u0012\u00020\u000e\u0012\u0004\u0012\u00020\u00010\u0012H\u0007\u001a\u0018\u0010\u0013\u001a\u00020\u00012\u0006\u0010\u0014\u001a\u00020\u000e2\u0006\u0010\u0015\u001a\u00020\u000eH\u0007\u001a\u0010\u0010\u0016\u001a\u00020\u00012\u0006\u0010\u0017\u001a\u00020\u000eH\u0003\u00a8\u0006\u0018"}, d2 = {"DocumentRow", "", "document", "Lcom/synthbyte/scanmate/data/Document;", "page", "Lcom/synthbyte/scanmate/data/Page;", "onClick", "Lkotlin/Function0;", "onFavorite", "onPin", "HomeDocumentEmptyState", "onScanClick", "HomeDocumentFilterRow", "selectedFilter", "", "filters", "", "onFilterSelected", "Lkotlin/Function1;", "HomeDocumentSectionHeader", "title", "countLabel", "MiniBadge", "label", "app_debug"})
public final class HomeDocumentListKt {
    
    @androidx.compose.runtime.Composable()
    public static final void HomeDocumentEmptyState(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onScanClick) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HomeDocumentFilterRow(@org.jetbrains.annotations.NotNull()
    java.lang.String selectedFilter, @org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.String> filters, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function1<? super java.lang.String, kotlin.Unit> onFilterSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void HomeDocumentSectionHeader(@org.jetbrains.annotations.NotNull()
    java.lang.String title, @org.jetbrains.annotations.NotNull()
    java.lang.String countLabel) {
    }
    
    @androidx.compose.runtime.Composable()
    public static final void DocumentRow(@org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.Document document, @org.jetbrains.annotations.Nullable()
    com.synthbyte.scanmate.data.Page page, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onClick, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onFavorite, @org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onPin) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void MiniBadge(java.lang.String label) {
    }
}