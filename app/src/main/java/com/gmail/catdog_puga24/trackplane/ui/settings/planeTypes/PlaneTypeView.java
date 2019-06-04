package com.gmail.catdog_puga24.trackplane.ui.settings.planeTypes;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
interface PlaneTypeView extends MvpView {

    void refreshRecycler();

    void showToast(String message);
}
