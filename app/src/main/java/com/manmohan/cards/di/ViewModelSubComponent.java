package com.manmohan.cards.di;

import com.manmohan.cards.ui.main.MainViewModel;
import dagger.Subcomponent;

/**
 * A sub component to create ViewModels. It is called by the
 */
@Subcomponent
public interface ViewModelSubComponent {
    @Subcomponent.Builder
    interface Builder {
        ViewModelSubComponent build();
    }

    MainViewModel mainViewModel();
}
