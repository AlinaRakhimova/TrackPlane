package com.gmail.catdog_puga24.trackplane.ui.settings.profile.view;

import com.arellomobile.mvp.MvpView;
import com.arellomobile.mvp.viewstate.strategy.AddToEndSingleStrategy;
import com.arellomobile.mvp.viewstate.strategy.StateStrategyType;
import com.gmail.catdog_puga24.trackplane.data.database.entity.User;

@StateStrategyType(AddToEndSingleStrategy.class)
public interface ProfileFragmentView extends MvpView {

    void initProfile(User user);

    void showToast(String message);
}
