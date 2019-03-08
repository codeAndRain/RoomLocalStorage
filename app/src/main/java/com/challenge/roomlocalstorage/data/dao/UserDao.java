package com.challenge.roomlocalstorage.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.challenge.roomlocalstorage.data.entities.User;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    List<User> getAllUsers();

    @Query("SELECT COUNT(*) FROM user_table")
    int getUserCount();

    @Insert
    void addUser(User user);

    @Delete
    void removeUser(User user);
}
