package com.app.flight.entity;

import lombok.Data;

/**
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.31
 */
@Data
public abstract class BaseAccount {
    /**
     * The unique identification of account
     */
    private int id;

    /**
     * The password of account
     */
    private String password;
}
