package com.gmail.catdog_puga24.trackplane.ui.mainPage.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.gmail.catdog_puga24.trackplane.R;

import butterknife.ButterKnife;

public class TotalInformationActivity extends AppCompatActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);
        ButterKnife.bind(this);
    }

}

