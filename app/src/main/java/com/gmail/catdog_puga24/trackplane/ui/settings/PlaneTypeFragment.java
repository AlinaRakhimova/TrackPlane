package com.gmail.catdog_puga24.trackplane.ui.settings;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;
import com.gmail.catdog_puga24.trackplane.ui.settings.adapter.PlaneTypeAdapter;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

public class PlaneTypeFragment extends MvpAppCompatFragment implements PlaneTypeView {

    @BindView(R.id.btnAddPlaneType)
    FloatingActionButton addPlaneType;
    @BindView(R.id.planeTypeRecycler)
    RecyclerView recyclerView;
    @InjectPresenter
    PlaneTypePresenterImpl presenter;
    private PlaneTypeFragment.OnPlaneTypeListFragmentInteractionListener mListener;
    private Context context;
    private PlaneTypeAdapter planeTypeAdapter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_plane_types_list, container, false);
        ButterKnife.bind(this, view);
        App.getApp().getAppComponent().inject(presenter);
        context = view.getContext();
        getTypePlaneList();
        addPlaneType.setOnClickListener(v -> addPlaneType());
        return view;
    }

    private void addPlaneType() {
        LayoutInflater factory = LayoutInflater.from(getActivity());
        @SuppressLint("InflateParams") final View alertView = factory.inflate(R.layout.layout_add_plane_type, null);
        if (getActivity() == null) return;
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setView(alertView);
        builder.setTitle(R.string.plane_type_add);
        builder.setNegativeButton(R.string.cancel, null);
        builder.setPositiveButton(R.string.add, (dialog, id) -> {
            EditText newPlaneType = alertView.findViewById(R.id.editTextPlaneType);
            String planeType = newPlaneType.getText().toString();
            presenter.addPlaneType(planeType);
        });
        builder.show();
    }

    public void getTypePlaneList() { //TODO Пересмотреть перенос участка кода в presenter
        presenter.getPlaneTypeList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<TypePlane>>() {
                    @Override
                    public void onSuccess(List<TypePlane> typePlanes) {
                        showPlaneTypeRecycler(typePlanes);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Log.d(TAG, "Ошибка получения типов ВС: " + e.getMessage());
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "onComplete: getPlaneTypeList");
                    }
                });
    }


    private void showPlaneTypeRecycler(List<TypePlane> typePlanes) {
        recyclerView.setLayoutManager(new LinearLayoutManager(context));
        planeTypeAdapter = new PlaneTypeAdapter(typePlanes, mListener);
        recyclerView.setAdapter(planeTypeAdapter);
    }

    @Override
    public void refreshRecycler() {
        planeTypeAdapter.notifyDataSetChanged();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnPlaneTypeListFragmentInteractionListener) {
            mListener = (OnPlaneTypeListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectTypeFlightListener");
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        Log.d("Flight7", "onDestroy");
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    public interface OnPlaneTypeListFragmentInteractionListener {
        void onListFragmentInteraction(final String item);
    }
}
