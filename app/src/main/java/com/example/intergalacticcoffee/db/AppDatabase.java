package com.example.intergalacticcoffee.db;
import android.content.Context;

import com.example.intergalacticcoffee.resources.Planet;
import com.example.intergalacticcoffee.resources.Purchase;
import com.example.intergalacticcoffee.resources.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Purchase.class}, version = 1) // Increment every time I change something.
public abstract class AppDatabase extends RoomDatabase{
    public static final String DB_NAME = "COFFEE_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String PURCHASE_TABLE = "PURCHASE_TABLE";
    // Going to need to wipe the database on the emulator now.

    public abstract UserDAO getUserDAO();


}
