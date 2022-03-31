package com.app.flight.entity;

import lombok.Data;

/**
 * @author Huanghong
 * @author LianJunhong
 * @version 1.0
 * @date 2022.3.27
 */
@Data
public class Company {
    /**
     * the order number of company
     */
    private String companyId;

    /**
     * the name of the airline company
     */
    private String name;

    /**
     * the introduction of the airline company
     */
    private String introduction;

}
