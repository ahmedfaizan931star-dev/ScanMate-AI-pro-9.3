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
public final class CameraViewModel_Factory implements Factory<CameraViewModel> {
  private final Provider<DocDao> daoProvider;

  private final Provider<Context> contextProvider;

  public CameraViewModel_Factory(Provider<DocDao> daoProvider, Provider<Context> contextProvider) {
    this.daoProvider = daoProvider;
    this.contextProvider = contextProvider;
  }

  @Override
  public CameraViewModel get() {
    return newInstance(daoProvider.get(), contextProvider.get());
  }

  public static CameraViewModel_Factory create(Provider<DocDao> daoProvider,
      Provider<Context> contextProvider) {
    return new CameraViewModel_Factory(daoProvider, contextProvider);
  }

  public static CameraViewModel newInstance(DocDao dao, Context context) {
    return new CameraViewModel(dao, context);
  }
}
