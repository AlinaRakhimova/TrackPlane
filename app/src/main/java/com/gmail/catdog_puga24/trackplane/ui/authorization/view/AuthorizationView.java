package com.gmail.catdog_puga24.trackplane.ui.authorization.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface AuthorizationView extends MvpView {

    void fillUserFields(String firstName, String lastName, String patronymic, String rank, String flightSpecialty, String category);

    void openFlightBookScreen();

    void showToast(String message);
}
