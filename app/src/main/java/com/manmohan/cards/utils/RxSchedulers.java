package com.manmohan.cards.utils;

import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

// CHECKSTYLE:OFF
public interface RxSchedulers {
  RxSchedulers DEFAULT = new RxSchedulers() {
    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
      return single -> single
          .subscribeOn(Schedulers.io())
          .observeOn(AndroidSchedulers.mainThread());
    }
  };

  RxSchedulers TEST = new RxSchedulers() {
    @Override
    public <T> ObservableTransformer<T, T> applySchedulers() {
      return single -> single;
    }
  };

  <T> ObservableTransformer<T, T> applySchedulers();
}

