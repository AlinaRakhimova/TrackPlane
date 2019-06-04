package com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter;

import android.util.Log;

import com.arellomobile.mvp.InjectViewState;
import com.arellomobile.mvp.MvpPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.data.database.TypeFlightDao;
import com.gmail.catdog_puga24.trackplane.data.database.TypePlaneDao;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypeFlight;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.IViewHolder;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.view.NewFlightView;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Maybe;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

@InjectViewState
public class NewFlightPresenterImpl extends MvpPresenter<NewFlightView> implements NewFlightPresenter {

    @Inject
    TypePlaneDao typePlaneDao;

    @Inject
    TypeFlightDao typeFlightDao;
    private RecyclerTypeFlightPresenter recyclerTypeFlightPresenter;

    public NewFlightPresenterImpl() {
        App.getApp().getAppComponent().inject(this);
        recyclerTypeFlightPresenter = new RecyclerTypeFlightPresenter();
    }

    @Override
    public void onStart() {
        getPlaneTypeList();
    }

    @Override
    public void openFlightBookScreen() {
        getViewState().openFlightBookScreen();
    }

    public Maybe<List<TypePlane>> getPlaneTypeList() {
        return typePlaneDao.getAll();
    }

    public RecyclerTypeFlightPresenter getRecyclerMainPresenter() {
        return recyclerTypeFlightPresenter;
    }

    private class RecyclerTypeFlightPresenter implements IRecyclerTypeFlightPresenter {

        private List<TypeFlight> typeFlightList;

        private RecyclerTypeFlightPresenter() {
            getTypeFlightList();
        }

        private void getTypeFlightList() {
            typeFlightDao.getAll()
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new DisposableMaybeObserver<List<TypeFlight>>() {
                        @Override
                        public void onSuccess(List<TypeFlight> typeFlights) {
                            typeFlightList = typeFlights;
                        }

                        @Override
                        public void onError(Throwable e) {
                            Log.d(TAG, "Ошибка получения типов полетов: " + e.getMessage());
                        }

                        @Override
                        public void onComplete() {
                            Log.d(TAG, "onComplete: RecyclerTypeFlightPresenter");
                        }
                    });
        }

        @Override
        public void bindView(IViewHolder holder) {
            holder.setTypeFlight(typeFlightList.get(holder.getPos()));
        }

        @Override
        public int getItemCount() {
            if (typeFlightList == null) return 0;
            else return typeFlightList.size();
        }

        @Override
        public void onSelectedItem(IViewHolder holder) {
            TypeFlight typeFlight = typeFlightList.get(holder.getPos());
            getViewState().openNewFlightToTypeScreen(typeFlight.getId());
        }
    }
}
