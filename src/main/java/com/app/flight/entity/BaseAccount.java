package com.app.flight.entity;

import lombok.Data;

/**
 * @author LianJunhong
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
