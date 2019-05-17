package com.gmail.catdog_puga24.trackplane.ui.authorization.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.UserDao;
import com.gmail.catdog_puga24.trackplane.data.database.entity.User;
import com.gmail.catdog_puga24.trackplane.service.BaseSettings;
import com.gmail.catdog_puga24.trackplane.ui.authorization.view.AuthorizationView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

@InjectViewState
public class AuthPresenterImpl extends MvpPresenter<AuthorizationView> implements AuthPresenter {

    @Inject
    UserDao userDao;

    @Inject
    BaseSettings baseSettings;

    public AuthPresenterImpl() {
        App.getApp().getAppComponent().inject(this);
    }

    @Override
    public void onStart() {
        checkUser();
    }

    private void checkUser() {
        userDao.getAll()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        openFlightBookScreen();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Ошибка проверки пользователя " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        fillSettings();
                    }
                });
    }

    private Single<Long> addUserObservable(User user) {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            long id = userDao.insert(user);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    private Single<Long> updateUserObservable(User user) {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            long id = userDao.update(user);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    private void fillSettings() {
        baseSettings.start();
    }

    public void addUser(String firstName, String lastName, String patronymic, String rank, String flightSpecialty, String category) {
        User user = new User(firstName, lastName, patronymic, rank, flightSpecialty, category);
        addUserObservable(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(Long aLong) {
                        //TODO Вывод уведомления о добавлении данных пользователя
                        Log.d(TAG, "Пользователь добавлен");
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Ошибка добавления пользователя " + e.getMessage());
                    }
                });
    }

    @Override
    public void openFlightBookScreen() {
        getViewState().openFlightBookScreen();
    }

    public void updateUser(String firstName, String lastName, String patronymic, String rank, String flightSpecialty, String category) {
        User user = new User(firstName, lastName, patronymic, rank, flightSpecialty, category); //FIXME Проверить первый параметр
        updateUserObservable(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(Long aLong) {
                        //TODO Вывод уведомления об изменении данных
                        Log.d(TAG, "Данные пользователя изменены");
                        openFlightBookScreen();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Ошибка редактирования пользователя" + e.getMessage());
                    }
                });
    }

}
