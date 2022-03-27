package com.app.flight.entity;

/**
 * @author JiaBoran
 * @author LianJunhong
 * @version 0.1
 * @date 2022.3.27
 */
public class Admin {
    private int adminId;
    private String password;

    public Admin(){
    }

    public int getAdminId() {
        return adminId;
    }

    public void setAdminId(int adminId) {
        this.adminId = adminId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Admin{" +
                "adminId=" + adminId +
                ", password='" + password + '\'' +
                '}';
    }
}

