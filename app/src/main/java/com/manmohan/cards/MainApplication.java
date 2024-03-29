package com.manmohan.cards;

import android.app.Application;
import com.manmohan.cards.di.component.ApplicationComponent;
import com.manmohan.cards.di.component.DaggerApplicationComponent;
import com.manmohan.cards.di.module.ApplicationModule;

/**
 * Created by manmohan goswami
 */

public class MainApplication extends Application {

  private ApplicationComponent mApplicationComponent;

  @Override
  public void onCreate() {
    super.onCreate();

    mApplicationComponent = DaggerApplicationComponent.builder()
        .applicationModule(new ApplicationModule(this)).build();

    mApplicationComponent.inject(this);
  }

  public ApplicationComponent getComponent() {
    return mApplicationComponent;
  }

  // Needed to replace the component with a test specific one
  public void setComponent(ApplicationComponent applicationComponent) {
    mApplicationComponent = applicationComponent;
  }
}
