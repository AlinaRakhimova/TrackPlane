package com.gmail.catdog_puga24.trackplane.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gmail.catdog_puga24.trackplane.data.database.entity.User;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface UserDao {

    @Query("SELECT * FROM myprofile")
    Maybe<List<User>> getAll();

    @Insert
    long insert(User user);

    @Delete
    int delete(User user);

    @Update
    int update(User user);

}
