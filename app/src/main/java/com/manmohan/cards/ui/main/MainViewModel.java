package com.manmohan.cards.ui.main;

import android.app.Application;
import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import com.manmohan.cards.data.DataManager;
import com.manmohan.cards.data.model.User;
import io.reactivex.disposables.Disposable;
import java.util.List;
import javax.inject.Inject;

public class MainViewModel extends AndroidViewModel {

  private final MutableLiveData<Boolean> isLoadingData = new MutableLiveData<>();
  private final MutableLiveData<String> showError = new MutableLiveData<>();

  private final MutableLiveData<List<User>> userData = new MutableLiveData<>();

  final DataManager dataManager;
  Disposable disposable;

  @Inject
  public MainViewModel(@NonNull final Application application, DataManager dataManager) {
    super(application);
    this.dataManager = dataManager;
    reloadUserList();
  }

  public void acceptUser(final User user) {
    dataManager.saveUserAction(user, true);
  }

  public void declineUser(final User user) {
    dataManager.saveUserAction(user, false);
  }

  public void reloadUserList() {
    isLoadingData.setValue(true);
    disposable = dataManager.getUserList()
        .subscribe(list -> {
          userData.setValue(list);
          isLoadingData.setValue(false);
        }, e -> {
          e.printStackTrace();
          isLoadingData.setValue(false);
          showError.setValue("Error : " + e.getMessage());
        });
  }

  @Override protected void onCleared() {
    super.onCleared();
    if(disposable!=null)
      disposable.dispose();

  }

  LiveData<Boolean> getIsLoadingCartData() {
    return isLoadingData;
  }

  LiveData<List<User>> getUserList() {
    return userData;
  }
}
