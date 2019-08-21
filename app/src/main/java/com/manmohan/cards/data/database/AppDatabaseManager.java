package com.manmohan.cards.data.database;

import android.content.Context;
import android.util.Log;
import androidx.room.Room;
import com.manmohan.cards.data.model.User;
import com.manmohan.cards.di.ApplicationContext;
import com.manmohan.cards.utils.RxSchedulers;
import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Observable;
import io.reactivex.ObservableTransformer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by manmohan.
 */
@Singleton
public class AppDatabaseManager implements DatabaseManager {

  private static final String DB_NAME = "user_db";
  private static final String TAG = "AppDatabaseManager";

  private final UserDatabase userDatabase;

  @Inject
  public AppDatabaseManager(@ApplicationContext Context context) {
    userDatabase = Room.databaseBuilder(context, UserDatabase.class, DB_NAME).build();
  }

  @Override public void saveUserAction(final User user, final boolean isAccept) {
    Completable.fromAction(() -> {
      user.isAccept = isAccept;
      user.isDecline = !isAccept;
      userDatabase.daoAccess().updateUser(user);
    }).subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new CompletableObserver() {
      @Override
      public void onSubscribe(Disposable d) {

      }
      @Override
      public void onComplete() {
        Log.d(TAG, "User update success: " + user.email);
      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
        Log.e(TAG, "User update failed: " + user.email);
      }
    });
  }

  @Override public void saveUser(List<User> users) {
    Completable.fromAction(() -> {
      userDatabase.daoAccess().insertAllUser(users);
    }).subscribeOn(Schedulers.io())
      .observeOn(AndroidSchedulers.mainThread())
      .subscribe(new CompletableObserver() {
      @Override
      public void onSubscribe(Disposable d) {

      }
      @Override
      public void onComplete() {
        Log.d(TAG, "User list saved");
      }

      @Override
      public void onError(Throwable e) {
        e.printStackTrace();
        Log.e(TAG, "User list saved failed: " + e.getMessage());
      }
    });
  }

  @Override public Observable<List<User>> getAllUser() {
    return userDatabase.daoAccess().fetchAllUser().compose(applyGlobalTransformer());
  }

  /**
   * Performs global operators on all rx chains that compose with this transformer
   */
  private <T> ObservableTransformer<T, T> applyGlobalTransformer() {
    return RxSchedulers.DEFAULT.applySchedulers();
  }
}
