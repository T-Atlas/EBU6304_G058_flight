package com.app.flight.service;

import com.app.flight.entity.Reservation;

import java.util.ArrayList;

/**
 * @author SongBo
 */
public interface GetReservation {
    /**
     * 查找用户预定的航班信息
     * @param id
     * @return
     */
    ArrayList<Reservation> lookupReservations(String id) ;
}
