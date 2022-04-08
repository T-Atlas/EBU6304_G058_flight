package com.app.flight.service;

import com.app.flight.entity.Flight;

import java.util.ArrayList;
/**
 * @author 贾博然
 */

public interface GetFlight {

    /**
     * 通过航班号查找航班信息
     * @param id
     * @return
     */
    Flight lookupFlight(String id);
}
