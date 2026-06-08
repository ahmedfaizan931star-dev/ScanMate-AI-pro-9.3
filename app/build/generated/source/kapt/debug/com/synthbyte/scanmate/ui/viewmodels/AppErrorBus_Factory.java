package com.synthbyte.scanmate.ui.viewmodels;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

@ScopeMetadata("dagger.hilt.android.scopes.ActivityRetainedScoped")
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
public final class AppErrorBus_Factory implements Factory<AppErrorBus> {
  @Override
  public AppErrorBus get() {
    return newInstance();
  }

  public static AppErrorBus_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static AppErrorBus newInstance() {
    return new AppErrorBus();
  }

  private static final class InstanceHolder {
    private static final AppErrorBus_Factory INSTANCE = new AppErrorBus_Factory();
  }
}
