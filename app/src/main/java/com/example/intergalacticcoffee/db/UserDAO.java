package com.example.intergalacticcoffee.db;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.intergalacticcoffee.resources.Purchase;
import com.example.intergalacticcoffee.resources.User;

import java.util.List;

@Dao
public interface UserDAO {

    @Insert
    void insert(User... users);

    @Update
    void update(User... users);

    @Delete
    void delete(User user);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE)
    List<User> getUsers();

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUsername = :username")
    User getUserByUsername(String username);

    @Query("SELECT * FROM " + AppDatabase.USER_TABLE + " WHERE mUserID = :userID")
    User getUserByID(int userID);

    // Purchase Table ---------------------------------------------------------------
    @Insert
    void insert(Purchase... purchases);

    @Update
    void update(Purchase... purchases);

    @Delete
    void delete(Purchase... purchases);

    @Query("SELECT * FROM " + AppDatabase.PURCHASE_TABLE)
    List<Purchase> getPurchases();

    @Query("SELECT * FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mPurchaseID = :purchaseID")
    Purchase getPurchaseByID(int purchaseID);

    @Query("SELECT * FROM " + AppDatabase.PURCHASE_TABLE + " WHERE mUserID = :userID")
    List<Purchase> getPurchasesByUser(int userID);

}
