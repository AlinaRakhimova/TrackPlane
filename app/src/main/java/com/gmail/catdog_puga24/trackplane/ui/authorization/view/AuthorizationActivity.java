package com.gmail.catdog_puga24.trackplane.ui.authorization.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.Button;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.ui.authorization.presenter.AuthPresenterImpl;
import com.gmail.catdog_puga24.trackplane.ui.mainPage.view.TotalInformationActivity;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AuthorizationActivity extends MvpAppCompatActivity implements AuthorizationView {

    @InjectPresenter
    AuthPresenterImpl presenter;
    @BindView(R.id.flightSpecialty)
    EditText fieldFlightSpecialty;
    @BindView(R.id.rank)
    EditText fieldRank;
    @BindView(R.id.firstName)
    EditText fieldFirstName;
    @BindView(R.id.lastName)
    EditText fieldLastName;
    @BindView(R.id.patronymic)
    EditText fieldPatronymic;
    @BindView(R.id.category)
    EditText fieldCategory;
    @BindView(R.id.open)
    Button open;
    private Bundle intentBundle;
    private String firstName;
    private String lastName;
    private String rank;
    private String flightSpecialty;
    private String category;
    private String patronymic;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auth);
        ButterKnife.bind(this);
        intentBundle = getIntent().getExtras();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.d("Flight7", "onStart");
        if (isChangingUser()) {
            open.setOnClickListener(v -> updateUser());
        } else {
            presenter.onStart();
            open.setOnClickListener(v -> addUser());
        }
    }

    private boolean isChangingUser() {
        return intentBundle != null;
    }

    private void updateUser() {
        getTextFields();
        presenter.updateUser(firstName, lastName, patronymic, rank, flightSpecialty, category);
    }

    private void addUser() {
        getTextFields();
        presenter.addUser(firstName, lastName, patronymic, rank, flightSpecialty, category);
    }

    private void getTextFields() {
        firstName = String.valueOf(fieldFirstName.getText());
        lastName = String.valueOf(fieldLastName.getText());
        patronymic = String.valueOf(fieldPatronymic.getText());
        rank = String.valueOf(fieldRank.getText());
        flightSpecialty = String.valueOf(fieldFlightSpecialty.getText());
        category = String.valueOf(fieldCategory.getText());
    }

    @Override
    public void openFlightBookScreen() {
        Log.d("Flight7", "openFlightBookScreen");
        Intent intent = new Intent(this, TotalInformationActivity.class);
        startActivity(intent);
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.d("Flight7", "onStop");
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.d("Flight7", "onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.onDestroy();
        Log.d("Flight7", "onDestroy");
    }

}


