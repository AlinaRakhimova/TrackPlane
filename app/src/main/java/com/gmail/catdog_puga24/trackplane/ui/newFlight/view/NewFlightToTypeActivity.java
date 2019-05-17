package com.gmail.catdog_puga24.trackplane.ui.newFlight.view;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;

import com.arellomobile.mvp.MvpAppCompatActivity;
import com.arellomobile.mvp.presenter.InjectPresenter;
import com.gmail.catdog_puga24.trackplane.R;
import com.gmail.catdog_puga24.trackplane.constants.Constant;
import com.gmail.catdog_puga24.trackplane.data.database.entity.Flight;
import com.gmail.catdog_puga24.trackplane.ui.mainPage.view.TotalInformationActivity;
import com.gmail.catdog_puga24.trackplane.ui.newFlight.presenter.NewFlightToTypePresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import io.reactivex.Single;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.observers.DisposableSingleObserver;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_DAY;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_DAY_TIME;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_MONTH;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.COLUMN_YEAR;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.TABLE_FLIGHT_TYPE;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.TABLE_PLANE_TYPE;
import static com.gmail.catdog_puga24.trackplane.constants.Constant.TAG;

public class NewFlightToTypeActivity extends MvpAppCompatActivity implements NewFlightToTypeView {

    @BindView(R.id.quantityFlightsET)
    EditText quantityFlightsET;

    @BindView(R.id.totalTimeHour)
    EditText totalTimeHourET;

    @BindView(R.id.totalTimeMin)
    EditText totalTimeMinET;

    @BindView(R.id.closeCabinHour)
    EditText closeCabinHourET;

    @BindView(R.id.closeCabinMin)
    EditText closeCabinMinET;

    @BindView(R.id.totalSmuHour)
    EditText totalSmuHourET;

    @BindView(R.id.totalSmuMin)
    EditText totalSmuMinET;

    @BindView(R.id.cloudHour)
    EditText cloudHourET;

    @BindView(R.id.cloudMin)
    EditText cloudSmuMinET;

    @BindView(R.id.quantityZahodET)
    EditText quantityZahodET;

    @BindView(R.id.quantityPosadET)
    EditText quantityPosadET;

    @BindView(R.id.quantityPosadMpET)
    EditText quantityPosadMpET;

    @InjectPresenter
    NewFlightToTypePresenter presenter;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_flight_to_type);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.save)
    public void addFlightToDatabase() {
        Flight newFlight = createFlight();
        Log.d(Constant.TAG, newFlight.toString());
        Single<Long> id = presenter.addFlightToDatabase(newFlight);
        id.observeOn(AndroidSchedulers.mainThread()).subscribe(new DisposableSingleObserver<Long>() {
            @Override
            public void onSuccess(Long aLong) {
                Log.d(TAG, "Полет добавлен под номером: " + aLong);
                openFlightBookScreen(); //FIXME Уточнить механизм закрытия окна после добавления данных
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "Ошибка добавления полета: " + e.getMessage() + " - " + e.getMessage());
            }
        });
    }

    private Flight createFlight() {
        int day = Integer.parseInt(getIntent().getStringExtra(COLUMN_DAY));
        int month = Integer.parseInt(getIntent().getStringExtra(COLUMN_MONTH));
        int year = Integer.parseInt(getIntent().getStringExtra(COLUMN_YEAR));
        long typePlane = getIntent().getLongExtra(TABLE_PLANE_TYPE, 0);
        boolean isDayTime = getIntent().getBooleanExtra(COLUMN_DAY_TIME, true);
        long typeFlight = getIntent().getLongExtra(TABLE_FLIGHT_TYPE, 0);
        int quantityFlights = Integer.parseInt(quantityFlightsET.getText().toString());
        int totalTimeHour = Integer.parseInt(totalTimeHourET.getText().toString());
        int totalTimeMin = Integer.parseInt(totalTimeMinET.getText().toString());
        int closeCabinHour = Integer.parseInt(closeCabinHourET.getText().toString());
        int closeCabinMin = Integer.parseInt(closeCabinMinET.getText().toString());
        int totalSMUHour = Integer.parseInt(totalSmuHourET.getText().toString());
        int totalSMUMin = Integer.parseInt(totalSmuMinET.getText().toString());
        int inCloudHour = Integer.parseInt(cloudHourET.getText().toString());
        int inCloudMin = Integer.parseInt(cloudSmuMinET.getText().toString());
        int quantityZahod = Integer.parseInt(quantityZahodET.getText().toString());
        int quantityPosad = Integer.parseInt(quantityPosadET.getText().toString());
        int quantityPosadMP = Integer.parseInt(quantityPosadMpET.getText().toString());

        return new Flight(day, month, year,
                typePlane, isDayTime, typeFlight, quantityFlights,
                totalTimeHour, totalTimeMin,
                closeCabinHour, closeCabinMin,
                totalSMUHour, totalSMUMin,
                inCloudHour, inCloudMin, quantityZahod, quantityPosad, quantityPosadMP);
    }

    private void openFlightBookScreen() {
        Log.d("Flight7", "openFlightBookScreen");
        Intent intent = new Intent(this, TotalInformationActivity.class);
        startActivity(intent);
    }

}
