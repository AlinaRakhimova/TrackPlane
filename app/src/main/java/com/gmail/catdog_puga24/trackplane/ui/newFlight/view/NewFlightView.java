package com.gmail.catdog_puga24.trackplane.ui.newFlight.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface NewFlightView extends MvpView {

    void openFlightBookScreen();

    void updateRecyclerView();

    void openNewFlightToTypeScreen(long id);
}
