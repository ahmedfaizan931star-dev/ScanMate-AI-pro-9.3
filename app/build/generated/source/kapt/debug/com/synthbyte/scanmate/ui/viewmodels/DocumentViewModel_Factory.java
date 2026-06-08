package com.synthbyte.scanmate.ui.viewmodels;

import android.content.Context;
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
public final class DocumentViewModel_Factory implements Factory<DocumentViewModel> {
  private final Provider<DocDao> daoProvider;

  private final Provider<Context> contextProvider;

  public DocumentViewModel_Factory(Provider<DocDao> daoProvider,
      Provider<Context> contextProvider) {
    this.daoProvider = daoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public DocumentViewModel get() {
    return newInstance(daoProvider.get(), contextProvider.get());
  }

  public static DocumentViewModel_Factory create(Provider<DocDao> daoProvider,
      Provider<Context> contextProvider) {
    return new DocumentViewModel_Factory(daoProvider, contextProvider);
  }

  public static DocumentViewModel newInstance(DocDao dao, Context context) {
    return new DocumentViewModel(dao, context);
  }
}
