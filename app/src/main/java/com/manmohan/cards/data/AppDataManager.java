package com.manmohan.cards.data;

import com.manmohan.cards.data.database.DatabaseManager;
import com.manmohan.cards.data.network.AppApiManager;
import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by manmohan.
 */
@Singleton
public class AppDataManager implements DataManager {

  private final AppApiManager mApiManager;
  private final DatabaseManager mDatabaseManager;

  @Inject
  public AppDataManager(AppApiManager apiManager, DatabaseManager databaseManager) {
    mApiManager = apiManager;
    mDatabaseManager = databaseManager;
  }
}
