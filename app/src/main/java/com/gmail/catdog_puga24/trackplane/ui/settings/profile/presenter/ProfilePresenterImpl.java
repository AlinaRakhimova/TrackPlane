package com.gmail.catdog_puga24.trackplane.ui.settings.profile.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.RoomHelper;
import com.gmail.catdog_puga24.trackplane.data.database.entity.User;
import com.gmail.catdog_puga24.trackplane.ui.settings.profile.view.ProfileFragmentView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.INDEX_FIRST_USER;

@InjectViewState
public class ProfilePresenterImpl extends MvpPresenter<ProfileFragmentView> implements ProfilePresenter {

    @Inject
    RoomHelper roomHelper;

    public ProfilePresenterImpl() {
        App.getApp().getAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        getUser();
    }

    private void getUser() {
        roomHelper.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        if (users.size() == 0) {
                            getViewState().showToast("Данные пользователя не заполнены");
                        } else getViewState().initProfile(users.get(INDEX_FIRST_USER));
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast("Ошибка проверки пользователя " + e.getMessage());
                    }
                });
    }

}
