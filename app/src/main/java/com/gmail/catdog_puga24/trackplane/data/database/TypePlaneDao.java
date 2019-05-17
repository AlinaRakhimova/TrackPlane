package com.gmail.catdog_puga24.trackplane.data.database;

import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;

import java.util.List;

import io.reactivex.Maybe;

@Dao
public interface TypePlaneDao {

    @Query("SELECT * FROM plane_type")
    Maybe<List<TypePlane>> getAll();

    @Insert
    long insert(TypePlane typePlane);

    @Delete
    int delete(TypePlane typePlane);

    @Update
    int update(TypePlane typePlane);

}
