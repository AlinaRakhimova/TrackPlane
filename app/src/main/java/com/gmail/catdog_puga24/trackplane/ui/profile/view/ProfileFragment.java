package com.gmail.catdog_puga24.trackplane.ui.profile.view;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.arellomobile.mvp.MvpAppCompatFragment;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.catdog_puga24.trackplane.App;
import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.data.database.entity.User;
import com.gmail.catdog_puga24.trackplane.ui.authorization.view.AuthorizationActivity;
import com.gmail.catdog_puga24.trackplane.ui.profile.presenter.ProfilePresenterImpl;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.CHANGE_PROFILE;

public class ProfileFragment extends MvpAppCompatFragment implements ProfileFragmentView {

    @BindView(R.id.changeInfo)
    Button changeInfo;

    @BindView(R.id.firstName)
    TextView firstName;

    @BindView(R.id.lastName)
    TextView lastName;

    @BindView(R.id.rank)
    TextView rank;

    @BindView(R.id.flightSpecialty)
    TextView flightSpecialty;

    @InjectPresenter
    ProfilePresenterImpl presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);
        ButterKnife.bind(this, view);
        App.getApp().getAppComponent().inject(presenter);
        changeInfo.setOnClickListener(v -> openAuthorizationScreen());
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
    }

    private void openAuthorizationScreen() {
        Intent intent = new Intent(getContext(), AuthorizationActivity.class);
        intent.putExtra(CHANGE_PROFILE, true);
        startActivity(intent);
    }

    @Override
    public void initProfile(User user) {
        firstName.setText(user.getFirstName());
        lastName.setText(user.getLastName());
        rank.setText(user.getRank());
        flightSpecialty.setText(user.getFlightSpecialty());
    }
}
