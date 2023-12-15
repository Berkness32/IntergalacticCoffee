package com.example.intergalacticcoffee.resources;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.intergalacticcoffee.db.AppDatabase;

@Entity(tableName = AppDatabase.PURCHASE_TABLE)
public class Purchase {

    @PrimaryKey(autoGenerate = true)
    private int mPurchaseID;
    private String mItemName;
    private double mCost;
    private int mUserID;

    public Purchase(String itemName, double cost, int userID) {
        mItemName = itemName;
        mCost = cost;
        mUserID = userID;
    }

    public int getPurchaseID() {
        return mPurchaseID;
    }

    public void setPurchaseID(int purchaseID) {
        mPurchaseID = purchaseID;
    }

    public String getItemName() {
        return mItemName;
    }

    public void setItemName(String itemName) {
        mItemName = itemName;
    }

    public double getCost() {
        return mCost;
    }

    public void setCost(double cost) {
        mCost = cost;
    }

    public int getUserID() {
        return mUserID;
    }

    public void setUserID(int userID) {
        mUserID = userID;
    }
}
