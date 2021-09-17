package com.example.grabbers;

public class Users {
    private String username;
    private String Phonenumber;
    private String Email;
    private String Password;;
    private String Usertype;

    public Users() {

    }

    public String getUsertype() {
        return Usertype;
    }

    public void setUsertype(String usertype) {
        Usertype = usertype;
    }

    public Users(String username, String email, String phonenumber, String password, String usertype) {
        this.username = username;
        Phonenumber = phonenumber;
        Email = email;
        Password = password;
        Usertype = usertype;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPhonenumber() {
        return Phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        Phonenumber = phonenumber;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }
}
