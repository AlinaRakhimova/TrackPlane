package com.gmail.catdog_puga24.trackplane.ui.mainPage.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.view.NewFlightActivity;
import com.gmail.catdog_puga24.trackplane.ui.settings.SettingsActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainPageFragment extends MvpAppCompatFragment implements MainPageView {

    @BindView(R.id.dailyFlightAccounting)
    TextView dailyFlightAccounting;

    @BindView(R.id.settings)
    ImageView settings;

    @InjectPresenter
    MainPagePresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             final Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main_page, container, false);
        ButterKnife.bind(this, view);
        App.getApp().getAppComponent().inject(presenter);
        dailyFlightAccounting.setOnClickListener(v -> presenter.onDailyFlightAccounting());
        settings.setOnClickListener(v -> presenter.onSettings());
        return view;
    }

    @Override
    public void openScreenSettings() {
        Intent intent = new Intent(getActivity(), SettingsActivity.class);
        startActivity(intent);
    }

    @Override
    public void openScreenDailyFlightAccounting() {
        Intent intent = new Intent(getActivity(), NewFlightActivity.class);
        startActivity(intent);
    }

}
