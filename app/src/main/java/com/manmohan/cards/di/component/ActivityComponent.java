package com.manmohan.cards.di.component;

import com.manmohan.cards.di.PerActivity;
import com.manmohan.cards.di.module.ActivityModule;
import com.manmohan.cards.ui.main.MainActivity;
import dagger.Component;

/**
 * Created by manmohan.
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = ActivityModule.class)
public interface ActivityComponent {

  void inject(MainActivity activity);
}
