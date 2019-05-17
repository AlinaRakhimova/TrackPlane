package com.gmail.catdog_puga24.trackplane.service;

import android.util.Log;

import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.TypeFlightDao;
import com.gmail.catdog_puga24.trackplane.data.database.TypeFlightListCreator;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypeFlight;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

public class BaseSettings {

    @Inject
    TypeFlightListCreator typeFlightListCreator;

    @Inject
    TypeFlightDao typeFlightDao;

    private List<TypeFlight> typeFlightList;

    public void start() {
        App.getApp().getAppComponent().inject(this);
        typeFlightList = typeFlightListCreator.getTypeFlightsList();
        putTypeFlightsList();
    }

    private void putTypeFlightsList() {
        getTypeFlightsObservable()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(id -> Log.d(TAG, "put type flights list: " + id), throwable -> Log.d(TAG, "put type flights list: " + throwable));
    }

    private Single<Long> getTypeFlightsObservable() {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            List<Long> longList = typeFlightDao.insertList(typeFlightList);
            emitter.onSuccess(longList.get(0));
        }).subscribeOn(Schedulers.io());
    }

}
