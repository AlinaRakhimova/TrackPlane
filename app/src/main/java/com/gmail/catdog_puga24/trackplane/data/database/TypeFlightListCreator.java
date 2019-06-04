package com.gmail.catdog_puga24.trackplane.data.database;

import com.gmail.catdog_puga24.trackplane.data.database.entity.TypeFlight;

import java.util.ArrayList;
import java.util.List;

public class TypeFlightListCreator {

    private List<TypeFlight> typeFlights = new ArrayList<>();

    public TypeFlightListCreator() {
        fillTypeFlightList();
    }

    private void fillTypeFlightList() {
        TypeFlight typeFlight1 = new TypeFlight();
        typeFlight1.setName("вывозных");
        TypeFlight typeFlight2 = new TypeFlight();
        typeFlight2.setName("контрольных");
        TypeFlight typeFlight3 = new TypeFlight();
        typeFlight3.setName("тренировочных");
        TypeFlight typeFlight4 = new TypeFlight();
        typeFlight4.setName("зачетных");
        TypeFlight typeFlight5 = new TypeFlight();
        typeFlight5.setName("в качестве инструктора");

        typeFlights.add(typeFlight1);
        typeFlights.add(typeFlight2);
        typeFlights.add(typeFlight3);
        typeFlights.add(typeFlight4);
        typeFlights.add(typeFlight5);
    }

    public List<TypeFlight> getTypeFlightsList() {
        return typeFlights;
    }
}
