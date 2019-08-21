package com.manmohan.cards.data.database;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;
import com.manmohan.cards.data.model.User;
import io.reactivex.Observable;
import java.util.List;

@Dao
public interface DaoAccess {
  @Insert
  void insertAllUser(List<User> user);

  @Insert
  void insertUser(User user);

  @Query("SELECT * FROM user_table")
  Observable<List<User>> fetchAllUser();

  @Update
  void updateUser(User user);

  @Delete
  void deleteUser(User user);
}
