package com.manmohan.cards.data.network;

import com.manmohan.cards.utils.RxSchedulers;
import io.reactivex.ObservableTransformer;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by manmohan.
 */
@Singleton
public class AppApiManager {

  ApiInterface apiInterface;

  @Inject
  public AppApiManager(ApiInterface apiInterface) {
    this.apiInterface = apiInterface;
  }

  /**
   * Performs global operators on all rx chains that compose with this transformer
   */
  private <T> ObservableTransformer<T, T> applyGlobalTransformer() {
    return RxSchedulers.DEFAULT.applySchedulers();
  }
}
