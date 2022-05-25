package com.app.flight.entity;

import com.alibaba.fastjson2.annotation.JSONField;
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
    /**
     * The unique identification of account
     */
    @JSONField(ordinal = 1)
    private String id;

    /**
     * The password of account
     */
    @JSONField(ordinal = 2)
    private String password;

    @JSONField(ordinal = 3)
    private String name;

    @JSONField(ordinal = 4)
    private String telephone;


}

