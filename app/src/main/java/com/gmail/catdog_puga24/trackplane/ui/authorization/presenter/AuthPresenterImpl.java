package com.gmail.catdog_puga24.trackplane.ui.authorization.presenter;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.RoomHelper;
import com.gmail.catdog_puga24.trackplane.data.database.entity.User;
import com.gmail.catdog_puga24.trackplane.service.BaseSettings;
import com.gmail.catdog_puga24.trackplane.ui.authorization.view.AuthorizationView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.INDEX_FIRST_USER;

@InjectViewState
public class AuthPresenterImpl extends MvpPresenter<AuthorizationView> implements AuthPresenter {

    @Inject
    RoomHelper roomHelper;

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
        roomHelper.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        if (users.size() == 0) {
                            fillSettings();
                        } else openFlightBookScreen();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast("Ошибка проверки пользователя " + e.getMessage());
                    }
                });
    }

    private void fillSettings() {
        baseSettings.start();
        getViewState().showToast("Базовые настройки произведены");
    }

    public void addUser(String firstName, String lastName, String patronymic, String rank, String flightSpecialty, String category) {
        User user = new User(firstName, lastName, patronymic, rank, flightSpecialty, category);
        addUserObservable(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(Long aLong) {
                        getViewState().showToast("Пользователь добавлен");
                        openFlightBookScreen();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast("Ошибка добавления пользователя " + e.getMessage()); //FIXME Убрать или форматировать вывод текста ошибки
                    }
                });
    }

    private Single<Long> addUserObservable(User user) {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            long id = roomHelper.insertUser(user);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public void openFlightBookScreen() {
        getViewState().openFlightBookScreen();
    }

    public void updateUser(String firstName, String lastName, String patronymic, String rank, String flightSpecialty, String category) {
        User user = new User(firstName, lastName, patronymic, rank, flightSpecialty, category);
        updateUserObservable(user)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<Long>() {
                    @Override
                    public void onSuccess(Long aLong) {
                        getViewState().showToast("Данные пользователя изменены");
                        openFlightBookScreen();
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast("Ошибка редактирования пользователя" + e.getMessage()); //FIXME Убрать или форматировать вывод текста ошибки
                    }
                });
    }

    private Single<Long> updateUserObservable(User user) {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            long id = roomHelper.updateUser(user);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    public void getUser() {
        roomHelper.getUsers()
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableSingleObserver<List<User>>() {
                    @Override
                    public void onSuccess(List<User> users) {
                        if (users.size() != 0) {
                            User user = users.get(INDEX_FIRST_USER);
                            fillUserFields(user);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        getViewState().showToast("Ошибка получения пользователя " + e.getMessage());
                    }
                });
    }

    private void fillUserFields(User user) {
        String firstName = user.getFirstName();
        String lastName = user.getLastName();
        String patronymic = user.getPatronymic();
        String rank = user.getRank();
        String flightSpecialty = user.getFlightSpecialty();
        String category = user.getCategory();
        getViewState().fillUserFields(firstName, lastName, patronymic, rank, flightSpecialty, category);
    }

}
