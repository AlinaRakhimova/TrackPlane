package com.gmail.catdog_puga24.trackplane.ui.settings;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface PlaneTypeView extends MvpView {

    void refreshRecycler();

}
