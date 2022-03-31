package com.app.flight.entity;


import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * @author SongBo
 * @author LianJunhong
 * @author JiaBoran
 * @version 2.0
 * @date 2022.3.29
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class Staff extends BaseAccount {

    /**
     * The company id of which staff belongs to
     */
    private String companyId;

}
