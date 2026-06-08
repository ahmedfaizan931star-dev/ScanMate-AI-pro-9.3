package com.synthbyte.scanmate.di;

import com.synthbyte.scanmate.data.AppDatabase;
import com.synthbyte.scanmate.data.DocDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class AppModule_ProvideDocDaoFactory implements Factory<DocDao> {
  private final Provider<AppDatabase> databaseProvider;

  public AppModule_ProvideDocDaoFactory(Provider<AppDatabase> databaseProvider) {
    this.databaseProvider = databaseProvider;
  }

  @Override
  public DocDao get() {
    return provideDocDao(databaseProvider.get());
  }

  public static AppModule_ProvideDocDaoFactory create(Provider<AppDatabase> databaseProvider) {
    return new AppModule_ProvideDocDaoFactory(databaseProvider);
  }

  public static DocDao provideDocDao(AppDatabase database) {
    return Preconditions.checkNotNullFromProvides(AppModule.INSTANCE.provideDocDao(database));
  }
}
