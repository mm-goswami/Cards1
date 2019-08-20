package com.manmohan.cards.di.module;

import android.app.Application;
import android.content.Context;
import com.google.gson.Gson;
import com.manmohan.cards.data.AppDataManager;
import com.manmohan.cards.data.DataManager;
import com.manmohan.cards.data.database.AppDatabaseManager;
import com.manmohan.cards.data.database.DatabaseManager;
import com.manmohan.cards.data.network.ApiInterface;
import com.manmohan.cards.data.network.AppApiManager;
import com.manmohan.cards.di.ApplicationContext;
import dagger.Module;
import dagger.Provides;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by manmohan.
 */
@Module
public class ApplicationModule {

  private final Application mApplication;

  public ApplicationModule(Application application) {
    mApplication = application;
  }

  @Provides
  @Singleton
  DatabaseManager provideApiDataManager(AppDatabaseManager appDatabaseManager) {
    return appDatabaseManager;
  }

  @Provides
  @Singleton
  ApiInterface provideApiInterface(Retrofit retrofit) {
    return retrofit.create(ApiInterface.class);
  }

  @Provides
  Application provideApplication() {
    return mApplication;
  }

  @Provides
  @ApplicationContext
  Context provideContext() {
    return mApplication;
  }

  @Provides
  @Singleton
  DataManager provideDataManager(AppDataManager appDataManager) {
    return appDataManager;
  }

  @Provides
  @Singleton
  OkHttpClient provideOkHttpClient() {
    OkHttpClient.Builder httpClient = new OkHttpClient.Builder();
    httpClient.readTimeout(60, TimeUnit.SECONDS);
    httpClient.connectTimeout(60, TimeUnit.SECONDS);
    return httpClient.build();
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(OkHttpClient httpClient) {
    return new Retrofit.Builder()
        .client(httpClient)
        .baseUrl("BASE_URL")
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }
}
