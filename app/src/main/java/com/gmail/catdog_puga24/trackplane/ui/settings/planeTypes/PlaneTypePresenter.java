package com.gmail.catdog_puga24.trackplane.ui.settings.planeTypes;

import com.gmail.catdog_puga24.trackplane.data.database.entity.TypePlane;

import java.util.List;

import io.reactivex.Maybe;

interface PlaneTypePresenter {

    void addPlaneType(String planeType);

    Maybe<List<TypePlane>> getPlaneTypeList();
}
