package com.example.whatsappmy.Model;

public class UserModel {
    String profilepic;
    String userName;
    String mail;
    String userid;
    String password;
    String lastmsg;


    public UserModel(String profilepic, String userName, String mail, String userid, String password, String lastmsg) {
        this.profilepic = profilepic;
        this.userName = userName;
        this.mail = mail;
        this.userid = userid;
        this.password = password;
        this.lastmsg = lastmsg;


    }
    // EMPTY CONSTRUCTOR
    public UserModel(){};

    // CONSTRUCTOR FOR SIGNUP

    public UserModel( String userName, String mail,  String password ) {

        this.userName = userName;
        this.mail = mail;
        this.password = password;


    }

    public String getProfilepic() {
        return profilepic;
    }

    public void setProfilepic(String profilepic) {
        this.profilepic = profilepic;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getMail() {
        return mail;
    }

    public void setMail(String mail) {
        this.mail = mail;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getLastmsg() {
        return lastmsg;
    }

    public void setLastmsg(String lastmsg) {
        this.lastmsg = lastmsg;
    }
}
