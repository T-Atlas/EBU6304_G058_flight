package com.app.flight.service;

import com.app.flight.entity.BoardingPass;

/**
 * @author SongBo
 * @version 1.0
 * @date 2022.4.22
 */
public interface GetBoardingPass {
    BoardingPass lookupBoardingPass(String id);
}
