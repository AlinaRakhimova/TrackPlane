package com.gmail.catdog_puga24.trackplane.data.database;

import android.arch.persistence.room.Database;
import android.arch.persistence.room.RoomDatabase;

import com.gmail.catdog_puga24.trackplane.data.database.entity.Flight;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypeFlight;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;
import com.gmail.catdog_puga24.trackplane.data.database.entity.User;

@Database(entities = {Flight.class, TypePlane.class, TypeFlight.class, User.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract FlightDao flightDao();

    public abstract TypePlaneDao typePlaneDao();

    public abstract TypeFlightDao typeFlightDao();

    public abstract UserDao userDao();
}