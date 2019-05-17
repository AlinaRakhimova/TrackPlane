package com.gmail.catdog_puga24.trackplane.data.database.entity;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

import static com.gmail.catdog_puga24.trackplane.constants.Constant.TABLE_PLANE_TYPE;

@Entity(tableName = TABLE_PLANE_TYPE)
public class TypePlane {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String name;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
