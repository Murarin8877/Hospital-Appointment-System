package com.example.reservationsystem;

public class member {
    public String phoneNO;
    public  String password;
    public  String spassword;

    public member() {
    }

    public member(String phoneNO, String password, String spassword) {
        this.phoneNO = phoneNO;
        this.password = password;
        this.spassword = spassword;
    }

    public String getPhoneNO() {
        return phoneNO;
    }

    public void setPhoneNO(String phoneNO) {
        this.phoneNO = phoneNO;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSpassword() {
        return spassword;
    }

    public void setSpassword(String spassword) {
        spassword = spassword;
    }
}
