package com.manmohan.cards.data;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import com.manmohan.cards.data.database.DatabaseManager;
import com.manmohan.cards.data.model.User;
import com.manmohan.cards.data.network.AppApiManager;
import io.reactivex.Observable;
import java.util.List;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by manmohan.
 */
@Singleton
public class AppDataManager implements DataManager {

  private final AppApiManager mApiManager;
  private final DatabaseManager mDatabaseManager;
  private final ConnectivityManager mConnectivityManager;


  @Inject
  public AppDataManager(AppApiManager apiManager, DatabaseManager databaseManager, ConnectivityManager connectivityManager) {
    mApiManager = apiManager;
    mDatabaseManager = databaseManager;
    mConnectivityManager = connectivityManager;
  }

  @Override public Observable<List<User>> getUserList() {
    if(!isNetworkConnected(mConnectivityManager))
      return mDatabaseManager.getAllUser();
    else
      return mApiManager.getUserList()
        .map(response -> response.results)
        .doOnNext(mDatabaseManager::saveUser);
  }

  @Override public void saveUserAction(final User user, final boolean isAccept) {
    mDatabaseManager.saveUserAction(user, isAccept);
  }

  public static boolean isNetworkConnected(final ConnectivityManager cm) {
    final NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
    return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
  }
}
