package com.manmohan.cards.datamanagertest;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.manmohan.cards.data.AppDataManager;
import com.manmohan.cards.data.database.DatabaseManager;
import com.manmohan.cards.data.model.BaseResponse;
import com.manmohan.cards.data.model.User;
import com.manmohan.cards.data.network.AppApiManager;
import io.reactivex.Observable;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class DataManagerTest {

  @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  @Mock AppApiManager appApiManager;
  @Mock DatabaseManager databaseManager;
  @Mock ConnectivityManager connectivityManager;
  @Mock NetworkInfo networkInfo;

  AppDataManager dataManager;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    // creating presenter with mock objects
    dataManager = new AppDataManager(appApiManager, databaseManager, connectivityManager);
  }

  @Test
  public void testLoadUserList() {
    //Assemble
    BaseResponse<User> response = new BaseResponse<>();
    when(appApiManager.getUserList()).thenReturn(Observable.just(response));
    when(connectivityManager.getActiveNetworkInfo()).thenReturn(networkInfo);

    // for network connected
    when(networkInfo.isConnectedOrConnecting()).thenReturn(true);
    dataManager.getUserList();
    //Verify
    verify(appApiManager).getUserList();

    // for network not connected
    when(networkInfo.isConnectedOrConnecting()).thenReturn(false);
    dataManager.getUserList();
    //Verify
    verify(databaseManager).getAllUser();
  }

  @Test
  public void testSaveUserAction() {
    User user = new User();
    //call test method
    dataManager.saveUserAction(user, true);
    verify(databaseManager).saveUserAction(user, true);

    dataManager.saveUserAction(user, false);
    verify(databaseManager).saveUserAction(user, false);
  }

  @After
  public void tearDown(){
    dataManager = null;
  }
}
