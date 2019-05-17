package com.gmail.catdog_puga24.trackplane.ui.settings;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.ui.ViewPagerAdapter;
import com.gmail.catdog_puga24.trackplane.ui.profile.view.ProfileFragment;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SettingsActivity extends AppCompatActivity implements PlaneTypeFragment.OnPlaneTypeListFragmentInteractionListener {

    @BindView(R.id.tablayout)
    TabLayout tabLayout;

    @BindView(R.id.viewpager)
    ViewPager viewPager;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        ButterKnife.bind(this);
        initUI();
    }

    private void initUI() {
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) actionBar.setDisplayHomeAsUpEnabled(true);
        setupViewPager(viewPager);
        tabLayout.setupWithViewPager(viewPager);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFragment(new ProfileFragment(), "Личные данные");
        adapter.addFragment(new PlaneTypeFragment(), "Виды ВС");
        viewPager.setAdapter(adapter);
    }

    @Override
    public void onListFragmentInteraction(String item) {
        //TODO Сделать функию изменения типа ЛС
        Log.d("Flight7", "Изменение типа ВС");
    }
}
