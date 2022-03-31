package com.app.flight.entity;


import lombok.Data;

/**
 * @author SongBo
 * @author LianJunhong
 * @author JiaBoran
 * @version 2.0
 * @date 2022.3.29
 */
@Data
public class Staff {
    /**
     * The unique identification of staff
     */
    private int staffId;

    /**
     * The password of staff
     */
    private String password;

    /**
     * The company id of which staff belongs to
     */
    private String companyId;

}
