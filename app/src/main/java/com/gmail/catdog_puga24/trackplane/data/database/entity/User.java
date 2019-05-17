package com.gmail.catdog_puga24.trackplane.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TABLE_PROFILE;

/**
 * Created by Admin on 11.01.2018.
 */

@Entity(tableName = TABLE_PROFILE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private long id;
    private String firstName;
    private String lastName;
    private String rank;
    private String flightSpecialty;
    private String patronymic;
    private String category;

    public User(String firstName, String lastName, String patronymic, String rank, String flightSpecialty, String category) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.rank = rank;
        this.flightSpecialty = flightSpecialty;
        this.patronymic = patronymic;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getRank() {
        return rank;
    }

    public String getLastName() {
        return lastName;
    }

    public String getFlightSpecialty() {
        return flightSpecialty;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public String getCategory() {
        return category;
    }
}

