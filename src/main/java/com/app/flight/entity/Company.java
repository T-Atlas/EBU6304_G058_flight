package com.app.flight.entity;

/**
 * @author Huanghong
 * @author LianJunhong
 * @version 0.1
 * @date 2022.3.27
 */
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

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIntroduction() {
        return introduction;
    }

    public void setIntroduction(String introduction) {
        this.introduction = introduction;
    }

    @Override
    public String toString() {
        return "Company{" +
                "companyId='" + companyId + '\'' +
                ", name='" + name + '\'' +
                ", introduction='" + introduction + '\'' +
                '}';
    }
}
