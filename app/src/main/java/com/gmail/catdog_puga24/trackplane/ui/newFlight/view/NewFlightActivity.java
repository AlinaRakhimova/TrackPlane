package com.gmail.catdog_puga24.trackplane.ui.newFlight.view;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.ToggleButton;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;
import com.gmail.catdog_puga24.trackplane.ui.mainPage.view.TotalInformationActivity;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.TypeFlightAdapter;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter.NewFlightPresenterImpl;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableMaybeObserver;
import io.reactivex.schedulers.Schedulers;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_DAY;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_DAY_TIME;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_MONTH;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_YEAR;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.TABLE_FLIGHT_TYPE;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.TABLE_PLANE_TYPE;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

public class NewFlightActivity extends MvpAppCompatActivity implements NewFlightView {

    private static final String EMPTY_STRING = "";

    @InjectPresenter
    NewFlightPresenterImpl presenter;

    @BindView(R.id.etYear)
    TextInputLayout year;

    @BindView(R.id.etMonth)
    TextInputLayout month;

    @BindView(R.id.etDay)
    TextInputLayout day;

    @BindView(R.id.planeType)
    Spinner planeTypeSpinner;

    @BindView(R.id.flightTypeRecycler)
    RecyclerView flightTypeRecycler;

    @BindView(R.id.dayTime)
    ToggleButton dayTime;

    @BindView(R.id.nightTime)
    ToggleButton nightTime;

    private boolean isDayTime;
    private TypeFlightAdapter typeFlightAdapter;
    private long selectedIdTypePlane;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flight);
        ButterKnife.bind(this);
    }

    @Override
    public void onStart() {
        super.onStart();
        presenter.onStart();
        initUI();
    }

    private void initUI() {
        dayTime.setOnCheckedChangeListener((buttonView, isChecked) -> checkDayTime(isChecked));
        nightTime.setOnCheckedChangeListener((buttonView, isChecked) -> checkNightTime(isChecked));
        showTypeFlightsRecycler();
        getTypePlaneList();
    }

    private void showTypeFlightsRecycler() {
        typeFlightAdapter = new TypeFlightAdapter(presenter.getRecyclerMainPresenter());
        flightTypeRecycler.setLayoutManager(new LinearLayoutManager(this));
        flightTypeRecycler.setAdapter(typeFlightAdapter);
    }

    public void getTypePlaneList() { //TODO Пересмотреть перенос участка кода в presenter
        presenter.getPlaneTypeList().subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new DisposableMaybeObserver<List<TypePlane>>() {
                    @Override
                    public void onSuccess(List<TypePlane> typePlanes) {
                        List<String> planeTypesListNames = new ArrayList<>();
                        for (TypePlane type : typePlanes) {
                            planeTypesListNames.add(type.getName());
                        }
                        showTypePlanesSpinner(planeTypesListNames);
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

    private void showTypePlanesSpinner(List<String> planeTypesListNames) {
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, planeTypesListNames);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        planeTypeSpinner.setAdapter(adapter);

        planeTypeSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent,
                                       View itemSelected, int selectedItemPosition, long selectedId) {
                selectedIdTypePlane = selectedId + 1; //FIXME Пересмотреть определение id и переписать адаптер спиннера
            }

            public void onNothingSelected(AdapterView<?> parent) {
            }
        });
    }

    private void checkDayTime(boolean isChecked) {
        if (isChecked) nightTime.setChecked(false);
        isDayTime = true;
    }

    private void checkNightTime(boolean isChecked) {
        if (isChecked) dayTime.setChecked(false);
        isDayTime = false;
    }

    @Override
    public void openFlightBookScreen() {
        Log.d("Flight7", "open TotalInformationActivity");
        Intent intent = new Intent(this, TotalInformationActivity.class);
        startActivity(intent);
    }

    @Override
    public void updateRecyclerView() {
        typeFlightAdapter.notifyDataSetChanged();
    }

    @Override
    public void openNewFlightToTypeScreen(long selectedIdTypeFlight) {
        if (!hasDate()) return;
        Log.d("Flight7", "openNewFlightToTypeScreen");
        String dayText = String.valueOf(day.getEditText().getText()); //TODO Сделать проверки на null
        String monthText = String.valueOf(month.getEditText().getText());
        String yearText = String.valueOf(year.getEditText().getText());

        Intent intent = new Intent(this, NewFlightToTypeActivity.class);
        intent.putExtra(COLUMN_DAY, dayText);
        intent.putExtra(COLUMN_MONTH, monthText);
        intent.putExtra(COLUMN_YEAR, yearText);
        intent.putExtra(TABLE_PLANE_TYPE, selectedIdTypePlane);
        intent.putExtra(COLUMN_DAY_TIME, isDayTime);
        intent.putExtra(TABLE_FLIGHT_TYPE, selectedIdTypeFlight);
        startActivity(intent);
    }

    private boolean hasDate() {
        boolean hasDate = true;
        String dayText = String.valueOf(day.getEditText().getText());
        if (dayText.equals(EMPTY_STRING)) {
            day.getEditText().setError("Введите день");
            hasDate = false;
        } else {
            String monthText = String.valueOf(month.getEditText().getText());
            if (monthText.equals(EMPTY_STRING)) {
                month.getEditText().setError("Введите месяц");
                hasDate = false;
            } else {
                String yearText = String.valueOf(year.getEditText().getText());
                if (yearText.equals(EMPTY_STRING)) {
                    year.getEditText().setError("Введите год");
                    hasDate = false;
                }
            }
        }
        return hasDate;
    }

}
