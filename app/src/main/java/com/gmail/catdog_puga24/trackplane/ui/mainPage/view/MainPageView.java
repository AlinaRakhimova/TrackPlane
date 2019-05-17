package com.gmail.catdog_puga24.trackplane.ui.mainPage.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface MainPageView extends MvpView {

    void openScreenSettings();

    void openScreenDailyFlightAccounting();

}
