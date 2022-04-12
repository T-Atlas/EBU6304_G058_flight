package com.app.flight.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author LianJunhong
 * @author JiaBoran
 * @version 1.0
 * @date 2022.3.31
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Admin extends BaseAccount {

    private String telephone;

    private String name;

}

