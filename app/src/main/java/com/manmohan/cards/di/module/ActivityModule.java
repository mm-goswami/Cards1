package com.manmohan.cards.di.module;

import android.content.Context;
import androidx.appcompat.app.AppCompatActivity;
import com.manmohan.cards.di.ActivityContext;
import com.yuyakaido.android.cardstackview.CardStackLayoutManager;
import dagger.Module;
import dagger.Provides;

/**
 * Created by manmohan .
 */
@Module
public class ActivityModule {

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

  @Provides
  CardStackLayoutManager provideCardStackLayoutManager(){
    return new CardStackLayoutManager(mActivity);
  }

}
