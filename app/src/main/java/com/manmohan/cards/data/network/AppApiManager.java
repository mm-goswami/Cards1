package com.manmohan.cards.data.network;

import com.manmohan.cards.data.model.BaseResponse;
import com.manmohan.cards.data.model.User;
import com.manmohan.cards.utils.RxSchedulers;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by manmohan.
 */
@Singleton
public class AppApiManager implements ApiInterface{

  ApiInterface apiInterface;

  @Inject
  public AppApiManager(ApiInterface apiInterface) {
    this.apiInterface = apiInterface;
  }

  @Override public Observable<BaseResponse<User>> getUserList() {
    return apiInterface.getUserList().compose(applyGlobalTransformer());
  }

  /**
   * Performs global operators on all rx chains that compose with this transformer
   */
  private <T> ObservableTransformer<T, T> applyGlobalTransformer() {
    return RxSchedulers.DEFAULT.applySchedulers();
  }
}
