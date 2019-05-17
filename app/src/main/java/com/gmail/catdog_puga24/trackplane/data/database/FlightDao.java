package com.gmail.catdog_puga24.trackplane.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gmail.catdog_puga24.trackplane.data.database.entity.Flight;

import java.util.List;

import io.reactivex.Single;

@Dao
public interface FlightDao {

    @Query("SELECT * FROM flightbook")
    Single<List<Flight>> getAll();

    @Insert
    long insert(Flight flight);

    @Delete
    int delete(Flight hit);

    @Update
    int update(Flight hit);

}
