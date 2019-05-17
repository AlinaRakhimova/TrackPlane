package com.gmail.catdog_puga24.trackplane.ui.mainPage.view;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;

@InjectViewState
public class MainPagePresenterImpl extends MvpPresenter<MainPageView> implements MainPagePresenter {

    @Override
    public void onDailyFlightAccounting() {
        getViewState().openScreenDailyFlightAccounting();
    }

    @Override
    public void onSettings() {
        getViewState().openScreenSettings();
    }
}
