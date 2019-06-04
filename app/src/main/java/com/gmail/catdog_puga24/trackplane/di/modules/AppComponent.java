package com.gmail.catdog_puga24.trackplane.di.modules;

import com.gmail.catdog_puga24.trackplane.data.database.RoomHelper;
import com.gmail.catdog_puga24.trackplane.service.BaseSettings;
import com.gmail.catdog_puga24.trackplane.ui.authorization.presenter.AuthPresenterImpl;
import com.gmail.catdog_puga24.trackplane.ui.flightBook.presenter.FlightBookPresenterImpl;
import com.gmail.catdog_puga24.trackplane.ui.mainPage.view.MainPagePresenterImpl;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter.NewFlightPresenterImpl;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter.NewFlightToTypePresenter;
import com.gmail.catdog_puga24.trackplane.ui.settings.planeTypes.PlaneTypePresenterImpl;
import com.gmail.catdog_puga24.trackplane.ui.settings.profile.presenter.ProfilePresenterImpl;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class})
public interface AppComponent {

    void inject(AuthPresenterImpl authPresenter);

    void inject(PlaneTypePresenterImpl planeTypePresenter);

    void inject(MainPagePresenterImpl mainPagePresenter);

    void inject(ProfilePresenterImpl profilePresenter);

    void inject(FlightBookPresenterImpl flightBookPresenter);

    void inject(BaseSettings baseSettings);

    void inject(NewFlightPresenterImpl newFlightPresenter);

    void inject(NewFlightToTypePresenter newFlightToTypePresenter);

    void inject(RoomHelper roomHelper);
}
