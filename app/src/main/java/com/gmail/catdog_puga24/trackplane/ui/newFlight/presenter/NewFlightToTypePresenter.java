package com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.FlightDao;
import com.gmail.catdog_puga24.trackplane.data.database.entity.Flight;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.view.NewFlightToTypeView;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

@InjectViewState
public class NewFlightToTypePresenter extends MvpPresenter<NewFlightToTypeView> {

    @Inject
    FlightDao flightDao;

    public NewFlightToTypePresenter() {
        App.getApp().getAppComponent().inject(this);
    }

    public Single<Long> addFlightToDatabase(Flight newFlight) {
        Log.d(TAG, "addFlightToDatabase");
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            long id = flightDao.insert(newFlight);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

}
