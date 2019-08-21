package com.manmohan.cards.data.database;

import androidx.room.Database;
import androidx.room.RoomDatabase;
import com.manmohan.cards.data.model.User;

@Database(entities = {User.class}, version = 1, exportSchema = false)
public abstract class UserDatabase extends RoomDatabase {

  public abstract DaoAccess daoAccess();

}
