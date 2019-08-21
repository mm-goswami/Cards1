package com.manmohan.cards.di.module;

import android.app.Application;
import android.content.Context;
import android.net.ConnectivityManager;
import androidx.lifecycle.ViewModelProvider;
import com.google.gson.Gson;
import com.manmohan.cards.data.AppDataManager;
import com.manmohan.cards.data.DataManager;
import com.manmohan.cards.data.database.AppDatabaseManager;
import com.manmohan.cards.data.database.DatabaseManager;
import com.manmohan.cards.data.network.ApiInterface;
import com.manmohan.cards.di.ApplicationContext;
import com.manmohan.cards.di.ViewModelSubComponent;
import com.manmohan.cards.utils.ViewModelFactory;
import dagger.Module;
import dagger.Provides;
import java.io.File;
import java.util.concurrent.TimeUnit;
import javax.inject.Singleton;
import okhttp3.Cache;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.manmohan.cards.data.network.Constant.BASE_URL;

/**
 * Created by manmohan.
 */
@Module(subcomponents = ViewModelSubComponent.class)
public class ApplicationModule {
  private static final String TAG = "ApplicationModule";

  public static final int DISK_CACHE_SIZE = (int) (50 * 1024 * 1024);

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
    // Install an HTTP cache in the application cache directory.
    final File cacheDir = new File(mApplication.getCacheDir(), "http");
    Cache cache = new Cache(cacheDir, DISK_CACHE_SIZE);
    httpClient.cache(cache);
    return httpClient.build();
  }

  @Provides
  @Singleton
  ConnectivityManager provideConnectivityManager(final Application app) {
    return (ConnectivityManager) app.getSystemService(Context.CONNECTIVITY_SERVICE);
  }

  @Provides
  @Singleton
  Retrofit provideRetrofit(OkHttpClient httpClient) {
    return new Retrofit.Builder()
        .client(httpClient)
        .baseUrl(BASE_URL)
        .addConverterFactory(GsonConverterFactory.create(new Gson()))
        .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
        .build();
  }

  @Singleton
  @Provides
  ViewModelProvider.Factory provideViewModelFactory(
      ViewModelSubComponent.Builder viewModelSubComponent) {
    return new ViewModelFactory(viewModelSubComponent.build());
  }
}
