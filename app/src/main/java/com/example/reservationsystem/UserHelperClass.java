package com.example.reservationsystem;

public class UserHelperClass {
    public String idcard;
    public String name;
    public  String password;
    public  String spassword;

    public UserHelperClass(String idcard,String name, String password, String spassword) {
        this.idcard = idcard;
        this.name = name;
        this.password = password;
        this.spassword = spassword;
    }

    public UserHelperClass() {
    }

    public String getidcard() {
        return idcard;
    }

    public void setidcard(String phoneNO) {
        this.idcard = idcard;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        this.spassword = spassword;
    }
}
