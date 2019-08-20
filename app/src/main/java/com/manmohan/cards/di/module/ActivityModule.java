package com.manmohan.cards.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.manmohan.cards.di.ActivityContext;
import dagger.Module;
import dagger.Provides;

/**
 * Created by manmohan .
 */
@Module
public class ActivityModule {

  private static final int KB = 1024;
  private static final int ONE_QUARTER = 4;

  private AppCompatActivity mActivity;

  public ActivityModule(AppCompatActivity activity) {
    this.mActivity = activity;
  }

  @Provides
  AppCompatActivity provideActivity() {
    return mActivity;
  }

  @Provides
  @ActivityContext
  Context provideContext() {
    return mActivity;
  }
}
