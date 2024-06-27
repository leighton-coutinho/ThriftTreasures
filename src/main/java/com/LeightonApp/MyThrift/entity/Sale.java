package com.LeightonApp.MyThrift.entity;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name="Sale")
@IdClass(SaleId.class)
public class Sale {

    @Id
    @Column(name="store_userID")
    private int storeID;

    @Id
    @Column(name="customer_userID")
    private int customerID;

    @Id
    @Column(name="item_id")
    private int itemID;

    @Column(name="status")
    private String status; // New field for status

    @ManyToOne
    @JoinColumn(name="store_userID", insertable = false, updatable = false)
    private Store store;

    @ManyToOne
    @JoinColumn(name="customer_userID", insertable = false, updatable = false)
    private Customer customer;

    @ManyToOne
    @JoinColumn(name="item_id", insertable = false, updatable = false)
    private Item item;

    public Sale() {
        this.status = "In Progress"; // Default status
    }

    public Sale(Store store, Customer customer, Item item) {
        this.store = store;
        this.customer = customer;
        this.item = item;
        this.storeID = store.getUserID();
        this.customerID = customer.getUserID();
        this.itemID = item.getId();
        this.status = "In Progress"; // Default status
    }

    public int getStoreID() {
        return storeID;
    }

    public void setStoreID(int storeID) {
        this.storeID = storeID;
    }

    public int getCustomerID() {
        return customerID;
    }

    public void setCustomerID(int customerID) {
        this.customerID = customerID;
    }

    public int getItemID() {
        return itemID;
    }

    public void setItemID(int itemID) {
        this.itemID = itemID;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Item getItem() {
        return item;
    }

    public void setItem(Item item) {
        this.item = item;
    }

    @Override
    public String toString() {
        return "Sale{" +
                "storeID=" + storeID +
                ", customerID=" + customerID +
                ", itemID=" + itemID +
                ", status=" + status +
                ", store=" + store +
                ", customer=" + customer +
                ", item=" + item +
                '}';
    }
}
