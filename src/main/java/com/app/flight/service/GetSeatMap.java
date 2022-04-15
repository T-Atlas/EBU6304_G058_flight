package com.app.flight.service;

import java.util.Map;

public interface GetSeatMap {
    Map<String, Boolean> getSeatMap(String flightId);
}
