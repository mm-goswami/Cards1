package com.manmohan.cards.data.database;

import com.manmohan.cards.data.model.User;
import io.reactivex.Observable;
import java.util.List;

/**
 * Created by manmohan.
 * For all other database dependencies
 */

public interface DatabaseManager {

  void saveUserAction(User user, boolean isAccept);

  Observable<List<User>> getAllUser();

  void saveUser(List<User> users);
}
