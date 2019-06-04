package com.gmail.catdog_puga24.trackplane.data.database;

import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.entity.User;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.schedulers.Schedulers;

public class RoomHelper {

    @Inject
    UserDao userDao;

    public RoomHelper() {
        App.getApp().getAppComponent().inject(this);
    }

    public Single<List<User>> getUsers() {
        return userDao.getAll().subscribeOn(Schedulers.io());
    }

    public long insertUser(User user) {
        return userDao.insert(user);
    }

    public long updateUser(User user) {
        return userDao.update(user);
    }
}
