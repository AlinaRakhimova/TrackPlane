package com.gmail.catdog_puga24.trackplane.ui.flightBook.view;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.data.database.entity.Flight;
import com.gmail.catdog_puga24.trackplane.ui.flightBook.FlightAdapter;
import com.gmail.catdog_puga24.trackplane.ui.flightBook.presenter.FlightBookPresenterImpl;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.view.NewFlightActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class FlightBookFragment extends Fragment implements FlightBookView {

    @BindView(R.id.btnAddFlight)
    FloatingActionButton addFlight;

    @BindView(R.id.flightRecycler)
    RecyclerView recyclerView;

    @InjectPresenter
    FlightBookPresenterImpl presenter;

    private OnListFragmentInteractionListener mListener;
    private Context context;
    private int columnCount = 1;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_flight_list, container, false);
        ButterKnife.bind(this, view);
        context = view.getContext();
        App.getApp().getAppComponent().inject(presenter);
        initUI();
        showFlightRecycler();
        return view;
    }

    private void initUI() {
        addFlight.setOnClickListener(view -> presenter.addFlight());
    }

    private void showFlightRecycler() {
        if (columnCount <= 1) {
            recyclerView.setLayoutManager(new LinearLayoutManager(context));
        } else {
            recyclerView.setLayoutManager(new GridLayoutManager(context, columnCount));
        }
        recyclerView.setAdapter(new FlightAdapter(presenter.getFlightList(), mListener));
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnListFragmentInteractionListener) {
            mListener = (OnListFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement SelectTypeFlightListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }

    @Override
    public void openNewFlightScreen() {
        Intent intent = new Intent(getContext(), NewFlightActivity.class);
        startActivity(intent);
    }

    public interface OnListFragmentInteractionListener {
        void onListFragmentInteraction(final Flight item);
    }

}
