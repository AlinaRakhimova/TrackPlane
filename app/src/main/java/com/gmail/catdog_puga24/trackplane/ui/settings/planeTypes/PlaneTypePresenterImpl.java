package com.gmail.catdog_puga24.trackplane.ui.settings.planeTypes;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.data.database.TypePlaneDao;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.Single;
import io.reactivex.SingleOnSubscribe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

@InjectViewState
public class PlaneTypePresenterImpl extends MvpPresenter<PlaneTypeView> implements PlaneTypePresenter {

    @Inject
    TypePlaneDao typePlaneDao;

    @Override
    public void addPlaneType(String planeTypeName) {
        TypePlane planeType = new TypePlane();
        planeType.setName(planeTypeName);
        addPlaneType(planeType).observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                getViewState().showToast("Тип самолета добавлен");
                getViewState().refreshRecycler(); //FIXME Не происходит обновление списка - создать пассивный адаптер
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Ошибка добавления типа самолета: " + e.getMessage());
            }
        });
    }

    private Single<Long> addPlaneType(TypePlane planeType) {
        return Single.create((SingleOnSubscribe<Long>) emitter -> {
            long id = typePlaneDao.insert(planeType);
            emitter.onSuccess(id);
        }).subscribeOn(Schedulers.io());
    }

    @Override
    public Maybe<List<TypePlane>> getPlaneTypeList() {
        return typePlaneDao.getAll();
    }

}
