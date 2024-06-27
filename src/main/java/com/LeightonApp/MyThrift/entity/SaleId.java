package com.LeightonApp.MyThrift.entity;

import java.io.Serializable;
import java.util.Objects;

public class SaleId implements Serializable {
    private int storeID;
    private int customerID;
    private int itemID;

    public SaleId() {}

    public SaleId(int storeID, int customerID, int itemID) {
        this.storeID = storeID;
        this.customerID = customerID;
        this.itemID = itemID;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        SaleId saleId = (SaleId) o;
        return storeID == saleId.storeID && customerID == saleId.customerID && itemID == saleId.itemID;
    }

    @Override
    public int hashCode() {
        return Objects.hash(storeID, customerID, itemID);
    }

    @Override
    public String toString() {
        return "SaleId{" +
                "storeID=" + storeID +
                ", customerID=" + customerID +
                ", itemID=" + itemID +
                '}';
    }
}
