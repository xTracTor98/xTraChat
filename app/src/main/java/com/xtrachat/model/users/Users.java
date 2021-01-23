package com.xtrachat.model.users;

public class Users {
    private String userID;
    private String userName;
    private String userPhone;
    private String userProfile;
    private String imageCover;
    private String imageProfile;
    private String email;
    private String dateOfBirth;
    private String gender;
    private String status;
    private String bro;

    public Users() {
    }

    public Users(String userID, String userName, String userPhone, String userProfile, String imageCover, String email, String dateOfBirth, String gender, String status, String bro, String imageProfile) {
        this.userID = userID;
        this.userName = userName;
        this.userPhone = userPhone;
        this.userProfile = userProfile;
        this.imageCover = imageCover;
        this.imageProfile = imageProfile;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.gender = gender;
        this.status = status;
        this.bro = bro;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPhone() {
        return userPhone;
    }

    public void setUserPhone(String userPhone) {
        this.userPhone = userPhone;
    }

    public String getUserProfile() {
        return userProfile;
    }

    public void setUserProfile(String userProfile) {
        this.userProfile = userProfile;
    }

    public String getImageCover() {
        return imageCover;
    }

    public void setImageCover(String imageCover) {
        this.imageCover = imageCover;
    }

    public String getImageProfile(){
        return imageProfile;
    }

    public void setImageProfile(String imageProfile){
        this.imageProfile = imageProfile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getBro() {
        return bro;
    }

    public void setBro(String bro) {
        this.bro = bro;
    }
}
