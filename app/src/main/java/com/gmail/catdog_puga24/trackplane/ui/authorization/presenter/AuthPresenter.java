package com.gmail.catdog_puga24.trackplane.ui.authorization.presenter;

public interface AuthPresenter {

    void onStart();

    void onDestroy();

    void addUser(String firstName, String lastName, String patronymic, String rank, String flightSpecialty, String category);

    void openFlightBookScreen();
}
