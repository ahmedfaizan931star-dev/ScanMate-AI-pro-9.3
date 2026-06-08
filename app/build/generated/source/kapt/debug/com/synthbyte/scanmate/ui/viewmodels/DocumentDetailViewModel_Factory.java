package com.synthbyte.scanmate.ui.viewmodels;

import android.content.Context;
import androidx.lifecycle.SavedStateHandle;
import com.synthbyte.scanmate.data.DocDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DocumentDetailViewModel_Factory implements Factory<DocumentDetailViewModel> {
  private final Provider<DocDao> daoProvider;

  private final Provider<Context> contextProvider;

  private final Provider<SavedStateHandle> savedStateHandleProvider;

  private final Provider<AppErrorReporter> appViewModelProvider;

  public DocumentDetailViewModel_Factory(Provider<DocDao> daoProvider,
      Provider<Context> contextProvider, Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<AppErrorReporter> appViewModelProvider) {
    this.daoProvider = daoProvider;
    this.contextProvider = contextProvider;
    this.savedStateHandleProvider = savedStateHandleProvider;
    this.appViewModelProvider = appViewModelProvider;
  }

  @Override
  public DocumentDetailViewModel get() {
    return newInstance(daoProvider.get(), contextProvider.get(), savedStateHandleProvider.get(), appViewModelProvider.get());
  }

  public static DocumentDetailViewModel_Factory create(Provider<DocDao> daoProvider,
      Provider<Context> contextProvider, Provider<SavedStateHandle> savedStateHandleProvider,
      Provider<AppErrorReporter> appViewModelProvider) {
    return new DocumentDetailViewModel_Factory(daoProvider, contextProvider, savedStateHandleProvider, appViewModelProvider);
  }

  public static DocumentDetailViewModel newInstance(DocDao dao, Context context,
      SavedStateHandle savedStateHandle, AppErrorReporter appViewModel) {
    return new DocumentDetailViewModel(dao, context, savedStateHandle, appViewModel);
  }
}
