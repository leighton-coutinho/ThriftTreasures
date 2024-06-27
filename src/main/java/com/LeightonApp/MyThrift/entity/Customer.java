package com.LeightonApp.MyThrift.entity;

import jakarta.persistence.*;

@Entity
@Table(name="Customer")
public class Customer {
    @Id
    @Column(name="userID")
    private int userID;

    @OneToOne(cascade = CascadeType.ALL)
    @MapsId
    @JoinColumn(name = "userID")
    private User user;

    public Customer() {}

    public Customer(String name, String email, String username, String password)
    {
        User customerUser = new User(name, email, username, password);
        this.user = customerUser;
    }

    public Customer(User user) {
        this.user = user;
    }

    public int getUserID() {
        return userID;
    }

    public void setUserID(int userID) {
        this.userID = userID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Customer{" +
                "userID=" + userID +
                ", user=" + user +
                '}';
    }
}
