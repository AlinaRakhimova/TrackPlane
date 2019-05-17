package com.gmail.catdog_puga24.trackplane.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TABLE_FLIGHT_BOOK;

@Entity(tableName = TABLE_FLIGHT_BOOK)
public class Flight {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private int day;
    private int month;
    private int year;
    private long idTypePlane;
    private boolean isDayTime;
    private long idTypeFlight;
    private int quantityFlights;
    private int totalTimeHour;
    private int totalTimeMin;
    private int closeCabinHour;
    private int closeCabinMin;
    private int totalSMUHour;
    private int totalSMUMin;
    private int inCloudHour;
    private int inCloudMin;
    private int quantityZahod;
    private int quantityPosad;
    private int quantityPosadMP;

    private String date;

    public Flight(int day, int month, int year,
                  long idTypePlane, boolean isDayTime,
                  long idTypeFlight, int quantityFlights,
                  int totalTimeHour, int totalTimeMin,
                  int closeCabinHour, int closeCabinMin,
                  int totalSMUHour, int totalSMUMin,
                  int inCloudHour, int inCloudMin,
                  int quantityZahod, int quantityPosad, int quantityPosadMP) {

        this.day = day;
        this.month = month;
        this.year = year;
        this.idTypePlane = idTypePlane;
        this.isDayTime = isDayTime;
        this.idTypeFlight = idTypeFlight;
        this.quantityFlights = quantityFlights;
        this.totalTimeHour = totalTimeHour;
        this.totalTimeMin = totalTimeMin;
        this.closeCabinHour = closeCabinHour;
        this.closeCabinMin = closeCabinMin;
        this.totalSMUHour = totalSMUHour;
        this.totalSMUMin = totalSMUMin;
        this.inCloudHour = inCloudHour;
        this.inCloudMin = inCloudMin;
        this.quantityZahod = quantityZahod;
        this.quantityPosad = quantityPosad;
        this.quantityPosadMP = quantityPosadMP;
        date = year + "-" + month + "-" + day;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public int getDay() {
        return day;
    }

    public int getMonth() {
        return month;
    }

    public int getYear() {
        return year;
    }

    public long getIdTypePlane() {
        return idTypePlane;
    }

    public boolean isDayTime() {
        return isDayTime;
    }

    public long getIdTypeFlight() {
        return idTypeFlight;
    }

    public int getQuantityFlights() {
        return quantityFlights;
    }

    public int getTotalTimeHour() {
        return totalTimeHour;
    }

    public int getTotalTimeMin() {
        return totalTimeMin;
    }

    public int getCloseCabinHour() {
        return closeCabinHour;
    }

    public int getCloseCabinMin() {
        return closeCabinMin;
    }

    public int getTotalSMUHour() {
        return totalSMUHour;
    }

    public int getTotalSMUMin() {
        return totalSMUMin;
    }

    public int getInCloudHour() {
        return inCloudHour;
    }

    public int getInCloudMin() {
        return inCloudMin;
    }

    public int getQuantityZahod() {
        return quantityZahod;
    }

    public int getQuantityPosad() {
        return quantityPosad;
    }

    public int getQuantityPosadMP() {
        return quantityPosadMP;
    }

    @Override
    public String toString() {
        return "Flight: id: " + id + " \n Date: " + date + " \n idTypePlane: " + idTypePlane + " \n idTypeFlight: " + idTypeFlight + "\n isDayTime: " + isDayTime
                + "\n Quantity flights: " + quantityFlights + "\n Quantity Zahod: " + quantityZahod;
    }
}

