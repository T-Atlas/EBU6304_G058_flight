package com.app.flight.entity;


/**
 * @author SongBo
 * @author LianJunhong
 * @author JiaBoran
 * @version 2.0
 * @date 2022.3.29
 */
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

    public Staff() {
    }

    public int getStaffId() {
        return staffId;
    }

    public void setStaffId(int staffId) {
        this.staffId = staffId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getCompanyId() {
        return companyId;
    }

    public void setCompanyId(String companyId) {
        this.companyId = companyId;
    }

    @Override
    public String toString() {
        return "Staff{" +
                "staffId=" + staffId +
                ", password='" + password + '\'' +
                ", companyId='" + companyId + '\'' +
                '}';
    }
}
