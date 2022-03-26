package com.app.fight.entity;

public class Company {
    private String CompanyId;//the order number of company
    private String name;//the name of the airline company
    private String introduction;////the introduction of the airline company

    public Company(){ }

    public String getCompanyId() {
        return CompanyId;
    }

    public void setCompanyId(String companyId) {
        CompanyId = companyId;
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
}
