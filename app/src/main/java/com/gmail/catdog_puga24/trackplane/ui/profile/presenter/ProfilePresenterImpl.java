package com.gmail.catdog_puga24.trackplane.ui.profile.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.ui.profile.view.ProfileFragmentView;

@InjectViewState
public class ProfilePresenterImpl extends MvpPresenter<ProfileFragmentView> implements ProfilePresenter {

    @Override
    public void initProfile() {
        //   User user = getUser();
        // getViewState().initProfile(user);
    }

    @Override
    public void onStart() {
//        dataSource.open();
        // initProfile();
    }

//    private User getUser() {
//        return dataSource.getDataReader().getUser();
//    }

}
