package com.challenge.roomlocalstorage.data.dao;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;

import com.challenge.roomlocalstorage.data.entities.User;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface UserDao {

    @Query("SELECT * FROM user_table")
    Single<List<User>> getAllUsers();

    @Query("SELECT COUNT(*) FROM user_table")
    int getUserCount();

    @Insert
    void addUser(User user);

    @Delete
    void removeUser(User user);
}
