package com.gmail.catdog_puga24.trackplane.ui.flightBook.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.FlightDao;
import com.gmail.catdog_puga24.trackplane.data.database.entity.Flight;
import com.gmail.catdog_puga24.trackplane.ui.flightBook.view.FlightBookView;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

@InjectViewState
public class FlightBookPresenterImpl extends MvpPresenter<FlightBookView> implements FlightBookPresenter {

    @Inject
    FlightDao flightDao;
    private List<Flight> flightList;

    public FlightBookPresenterImpl() {
        App.getApp().getAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        flightList = new ArrayList<>();
        fillFlightList();
    }

    public void addFlight() {
        getViewState().openNewFlightScreen();
    }

    public List<Flight> getFlightList() {
        return flightList;
    }

    private void fillFlightList() {
        Disposable disposable = flightDao.getAll().subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread())
                .subscribe(flights -> {
                    flightList = flights;
                    //getViewState().updateRecyclerView();
                    Log.d("Flight7", "Данные загружены из БД");
                }, throwable -> Log.e("Flight7", "Ошибка загрузки из БД: " + throwable));
    }
}
