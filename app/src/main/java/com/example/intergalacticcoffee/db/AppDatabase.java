package com.example.intergalacticcoffee.db;
import android.content.Context;

import com.example.intergalacticcoffee.resources.Planet;
import com.example.intergalacticcoffee.resources.User;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {User.class, Planet.class}, version = 9) // Increment every time I change something.
public abstract class AppDatabase extends RoomDatabase{
    public static final String DB_NAME = "COFFEE_DATABASE";
    public static final String USER_TABLE = "USER_TABLE";
    public static final String PLANET_TABLE = "PLANET_TABLE";

    public abstract UserDAO getUserDAO();
    public abstract PlanetDAO getPlanetDAO();


}
