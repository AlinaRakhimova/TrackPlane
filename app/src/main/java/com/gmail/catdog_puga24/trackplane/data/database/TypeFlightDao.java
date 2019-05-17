package com.gmail.catdog_puga24.trackplane.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gmail.catdog_puga24.trackplane.data.database.entity.TypeFlight;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface TypeFlightDao {

    @Query("SELECT * FROM flight_type")
    Maybe<List<TypeFlight>> getAll();

    @Insert
    long insert(TypeFlight typeFlight);

    @Insert
    List<Long> insertList(List<TypeFlight> typeFlights);

    @Delete
    int delete(TypeFlight typeFlight);

    @Update
    int update(TypeFlight typeFlight);

}
