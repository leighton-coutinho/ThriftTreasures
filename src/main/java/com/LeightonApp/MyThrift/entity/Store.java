package com.LeightonApp.MyThrift.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Store")
public class Store {
    @Id
    @Column(name="userID")
    private int userID;

    @Column(name="address")
    private String address;

    @Column(name="image_Path")
    private String imagePath; // New field for image path

    @Column(name="stripe_account")
    private String stripeAccount;

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public String getStripeAccount() {
        return stripeAccount;
    }

    public void setStripeAccount(String stripeAccount) {
        this.stripeAccount = stripeAccount;
    }

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "userID")
    private User user;

    public Store() {}

    public Store(String name, String email, String username, String password, String address, String imagePath)
    {
        User storeUser = new User(name, email, username, password);
        this.user = storeUser;
        this.address = address;
        this.imagePath = imagePath;
    }
    public Store(String name, String email, String username, String password, String address, String imagePath, String stripeAccount)
    {
        User storeUser = new User(name, email, username, password);
        this.user = storeUser;
        this.address = address;
        this.imagePath = imagePath;
        this.stripeAccount = stripeAccount;
    }

    public Store(User user, String address, String imagePath) {
        this.user = user;
        this.address = address;
        this.imagePath = imagePath;
    }

    public int getUserID() {
        return userID;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getImagePath() {
        return imagePath;
    }

    public void setImagePath(String imagePath) {
        this.imagePath = imagePath;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Store{" +
                "userID=" + userID +
                ", address='" + address + '\'' +
                ", imagePath='" + imagePath + '\'' +
                ", user=" + user +
                '}';
    }
}
