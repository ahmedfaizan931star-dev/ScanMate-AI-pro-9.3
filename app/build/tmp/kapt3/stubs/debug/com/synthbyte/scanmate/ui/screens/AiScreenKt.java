package com.synthbyte.scanmate.ui.screens;

@kotlin.Metadata(mv = {1, 9, 0}, k = 2, xi = 48, d1 = {"\u0000B\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010\u000b\n\u0002\b\u0004\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\f\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\u001a \u0010\u0002\u001a\u00020\u00032\u0006\u0010\u0004\u001a\u00020\u00052\u0006\u0010\u0006\u001a\u00020\u00052\u0006\u0010\u0007\u001a\u00020\u0001H\u0003\u001a\u001e\u0010\b\u001a\u00020\u00032\f\u0010\t\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\u0006\u0010\u000b\u001a\u00020\fH\u0007\u001a8\u0010\r\u001a\u00020\u00032\u0006\u0010\u000e\u001a\u00020\u000f2\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u00112\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0003\u001a4\u0010\u0013\u001a\u00020\u00032\u0006\u0010\u0014\u001a\u00020\u00012\b\u0010\u0015\u001a\u0004\u0018\u00010\u00012\u0018\u0010\u0010\u001a\u0014\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u0001\u0012\u0004\u0012\u00020\u00030\u0011H\u0003\u001aD\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\u00012\u0006\u0010\u0018\u001a\u00020\u00012\u0006\u0010\u0019\u001a\u00020\u00052\u0006\u0010\u001a\u001a\u00020\u00052\f\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00030\n2\f\u0010\u001b\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0003\u001aD\u0010\u001c\u001a\u00020\u00032\b\u0010\u001d\u001a\u0004\u0018\u00010\u001e2\u0006\u0010\u001f\u001a\u00020\u00052\u0006\u0010 \u001a\u00020\u00052\u0012\u0010!\u001a\u000e\u0012\u0004\u0012\u00020\u001e\u0012\u0004\u0012\u00020\u00030\"2\f\u0010#\u001a\b\u0012\u0004\u0012\u00020\u00030\nH\u0003\"\u000e\u0010\u0000\u001a\u00020\u0001X\u0082T\u00a2\u0006\u0002\n\u0000\u00a8\u0006$"}, d2 = {"BUSINESS_CARD", "", "AiHeroCard", "", "canUseOnlineAi", "", "isOnline", "selectedModel", "AiScreen", "onNavigateBack", "Lkotlin/Function0;", "settingsRepository", "Lcom/synthbyte/scanmate/data/SettingsRepository;", "BusinessCardResultCard", "card", "Lcom/synthbyte/scanmate/utils/DocumentIntelligence$BusinessCardResult;", "onCopy", "Lkotlin/Function2;", "onSaveVCard", "BusinessCardRow", "label", "value", "ResponseCard", "title", "response", "isError", "isOfflineFallback", "onShare", "WorkflowChipGrid", "selectedWorkflow", "Lcom/synthbyte/scanmate/utils/AiWorkflow;", "selectedBusinessCard", "enabled", "onWorkflowSelected", "Lkotlin/Function1;", "onBusinessCardSelected", "app_debug"})
public final class AiScreenKt {
    @org.jetbrains.annotations.NotNull()
    private static final java.lang.String BUSINESS_CARD = "BUSINESS_CARD";
    
    @kotlin.OptIn(markerClass = {androidx.compose.material3.ExperimentalMaterial3Api.class, androidx.compose.foundation.layout.ExperimentalLayoutApi.class})
    @androidx.compose.runtime.Composable()
    public static final void AiScreen(@org.jetbrains.annotations.NotNull()
    kotlin.jvm.functions.Function0<kotlin.Unit> onNavigateBack, @org.jetbrains.annotations.NotNull()
    com.synthbyte.scanmate.data.SettingsRepository settingsRepository) {
    }
    
    @kotlin.OptIn(markerClass = {androidx.compose.foundation.layout.ExperimentalLayoutApi.class})
    @androidx.compose.runtime.Composable()
    private static final void WorkflowChipGrid(com.synthbyte.scanmate.utils.AiWorkflow selectedWorkflow, boolean selectedBusinessCard, boolean enabled, kotlin.jvm.functions.Function1<? super com.synthbyte.scanmate.utils.AiWorkflow, kotlin.Unit> onWorkflowSelected, kotlin.jvm.functions.Function0<kotlin.Unit> onBusinessCardSelected) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void AiHeroCard(boolean canUseOnlineAi, boolean isOnline, java.lang.String selectedModel) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BusinessCardResultCard(com.synthbyte.scanmate.utils.DocumentIntelligence.BusinessCardResult card, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onCopy, kotlin.jvm.functions.Function0<kotlin.Unit> onSaveVCard) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void BusinessCardRow(java.lang.String label, java.lang.String value, kotlin.jvm.functions.Function2<? super java.lang.String, ? super java.lang.String, kotlin.Unit> onCopy) {
    }
    
    @androidx.compose.runtime.Composable()
    private static final void ResponseCard(java.lang.String title, java.lang.String response, boolean isError, boolean isOfflineFallback, kotlin.jvm.functions.Function0<kotlin.Unit> onCopy, kotlin.jvm.functions.Function0<kotlin.Unit> onShare) {
    }
}