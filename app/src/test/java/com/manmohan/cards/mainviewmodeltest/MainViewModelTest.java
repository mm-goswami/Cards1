package com.manmohan.cards.mainviewmodeltest;

import androidx.arch.core.executor.testing.InstantTaskExecutorRule;
import com.jraska.livedata.TestObserver;
import com.manmohan.cards.data.DataManager;
import com.manmohan.cards.data.model.User;
import com.manmohan.cards.ui.main.MainViewModel;
import io.reactivex.Observable;
import java.util.ArrayList;
import java.util.List;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class MainViewModelTest {

  @Rule public InstantTaskExecutorRule instantTaskExecutorRule = new InstantTaskExecutorRule();

  @Mock DataManager dataManager;
  @Mock Observable<List<User>> observable;

  MainViewModel viewModel;

  @Before
  public void setUp() throws Exception {
    MockitoAnnotations.initMocks(this);
    //Assemble
    List<User> users = new ArrayList<>();
    when(dataManager.getUserList()).thenReturn(Observable.just(users));
    // creating presenter with mock objects
    viewModel = new MainViewModel(dataManager);
  }

  @Test
  public void testLoadUserList() {
    verify(dataManager).getUserList();
    //Assemble
    List<User> users = new ArrayList<>();
    when(dataManager.getUserList()).thenReturn(Observable.just(users));

    TestObserver<List<User>> dataObserver =
        TestObserver.test(viewModel.getUserList());
    TestObserver<Boolean> loadingObserver = TestObserver.test(viewModel.getIsLoading());
    List<Boolean> loadingStates = loadingObserver.valueHistory();
    //call test method
    viewModel.reloadUserList();

    //Verify
    loadingObserver.assertHistorySize(3);
    assertEquals(true, loadingStates.get(1));
    assertEquals(false, loadingStates.get(2));
    assertEquals(users, dataObserver.value());
  }

  @Test
  public void testUserAction() {
    User user = new User();
    //call test method
    viewModel.acceptUser(user);
    verify(dataManager).saveUserAction(user, true);

    viewModel.declineUser(user);
    verify(dataManager).saveUserAction(user, false);
  }

  @After
  public void tearDown(){
    viewModel = null;
  }
}
