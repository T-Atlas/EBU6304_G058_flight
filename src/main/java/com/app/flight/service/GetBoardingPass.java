package com.app.flight.service;

import com.app.flight.entity.BoardingPass;
import com.app.flight.service.impl.GetBoardingPassImpl;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 * @see GetBoardingPassImpl
 */
public interface GetBoardingPass {
    /**
     * Find the passenger's boarding pass by the flight number and the passenger's name.
     *
     * @return passenger's boarding pass
     */
    BoardingPass lookupBoardingPass();
}
