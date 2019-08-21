package com.manmohan.cards.data;

import com.manmohan.cards.data.model.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by manmohan.
 */

public interface DataManager {

  Observable<List<User>> getUserList();

  void saveUserAction(User user, boolean isAccept);

}
