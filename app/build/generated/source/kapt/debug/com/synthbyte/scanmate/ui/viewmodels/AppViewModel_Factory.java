package com.synthbyte.scanmate.ui.viewmodels;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
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
public final class AppViewModel_Factory implements Factory<AppViewModel> {
  private final Provider<AppErrorBus> appErrorBusProvider;

  public AppViewModel_Factory(Provider<AppErrorBus> appErrorBusProvider) {
    this.appErrorBusProvider = appErrorBusProvider;
  }

  @Override
  public AppViewModel get() {
    return newInstance(appErrorBusProvider.get());
  }

  public static AppViewModel_Factory create(Provider<AppErrorBus> appErrorBusProvider) {
    return new AppViewModel_Factory(appErrorBusProvider);
  }

  public static AppViewModel newInstance(AppErrorBus appErrorBus) {
    return new AppViewModel(appErrorBus);
  }
}
