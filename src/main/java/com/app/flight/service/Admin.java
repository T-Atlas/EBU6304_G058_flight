package com.app.flight.service;

import java.util.ArrayList;

public interface Admin {

    ArrayList<String[]> searchCheckedInfoByFlightId(String flightId);

    ArrayList<String> getFlightId();
}
