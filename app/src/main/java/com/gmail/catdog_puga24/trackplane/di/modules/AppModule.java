package com.gmail.catdog_puga24.trackplane.di.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.AppDatabase;
import com.gmail.catdog_puga24.trackplane.data.database.FlightDao;
import com.gmail.catdog_puga24.trackplane.data.database.TypeFlightDao;
import com.gmail.catdog_puga24.trackplane.data.database.TypeFlightListCreator;
import com.gmail.catdog_puga24.trackplane.data.database.TypePlaneDao;
import com.gmail.catdog_puga24.trackplane.data.database.UserDao;
import com.gmail.catdog_puga24.trackplane.service.BaseSettings;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.DB_NAME;

@Module
public class AppModule {

    @Singleton
    @Provides
    public Context getContext() {
        return App.getApp();
    }

    @Singleton
    @Provides
    public TypeFlightDao getTypeFlightDao(AppDatabase appDatabase) {
        return appDatabase.typeFlightDao();
    }

    @Singleton
    @Provides
    public FlightDao getFlightDao(AppDatabase appDatabase) {
        return appDatabase.flightDao();
    }

    @Singleton
    @Provides
    public TypePlaneDao getTypePlaneDao(AppDatabase appDatabase) {
        return appDatabase.typePlaneDao();
    }

    @Singleton
    @Provides
    public UserDao getUserDao(AppDatabase appDatabase) {
        return appDatabase.userDao();
    }

    @Singleton
    @Provides
    public TypeFlightListCreator getTypeFlightListCreator() {
        return new TypeFlightListCreator();
    }

    @Singleton
    @Provides
    public AppDatabase getAppDatabase(Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class, DB_NAME).build();
    }

    @Singleton
    @Provides
    public BaseSettings getBaseSettings() {
        return new BaseSettings();
    }

}
