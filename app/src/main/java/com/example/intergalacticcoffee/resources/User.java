package com.example.intergalacticcoffee.resources;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.intergalacticcoffee.db.AppDatabase;

@Entity(tableName = AppDatabase.USER_TABLE)
public class User {

    @PrimaryKey(autoGenerate = true)
    private int mUserID;

    private String mUsername;
    private String mPassword;
    private boolean mAllegiance; // True = Rebel
    private boolean mIsAdmin;
    private int mCredits;

    public User(String username, String password, boolean allegiance) {
        mUsername = username;
        mPassword = password;
        mAllegiance = allegiance;
        mIsAdmin = false;
        if (username.equals("berk")) {
            mIsAdmin = true;
        }
        mCredits = 100;
    }

    public int getUserID() {
        return mUserID;
    }

    public void setUserID(int userID) {
        mUserID = userID;
    }

    public String getUsername() {
        return mUsername;
    }

    public void setUsername(String username) {
        mUsername = username;
    }

    public String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public boolean isAllegiance() {
        return mAllegiance;
    }

    public void setAllegiance(boolean allegiance) {
        mAllegiance = allegiance;
    }

    public boolean isAdmin() {
        return mIsAdmin;
    }

    public void setIsAdmin(boolean admin) {
        mIsAdmin = admin;
    }

    public int getCredits() {
        return mCredits;
    }

    public void setCredits(int credits) {
        mCredits = credits;
    }

    @Override
    public String toString() {
        return "User{" +
                "mUserID=" + mUserID +
                ", mUsername='" + mUsername + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
