package com.manmohan.cards.di.component;

import android.app.Application;
import android.content.Context;
import com.manmohan.cards.MainApplication;
import com.manmohan.cards.data.DataManager;
import com.manmohan.cards.di.ApplicationContext;
import com.manmohan.cards.di.module.ApplicationModule;
import dagger.Component;
import javax.inject.Singleton;
import okhttp3.OkHttpClient;

/**
 * Created by manmohan.
 */
@Singleton
@Component(modules = ApplicationModule.class)
public interface ApplicationComponent {

  void inject(MainApplication app);

  Application application();

  @ApplicationContext
  Context context();

  DataManager getDataManager();

  OkHttpClient getOkHttpClient();

}
