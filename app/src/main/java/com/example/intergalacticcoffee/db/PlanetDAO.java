package com.example.intergalacticcoffee.db;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.intergalacticcoffee.resources.Planet;

import java.util.List;

/**
 * There is no confirmation that this file works yet. <br><br>
 *
 * 19:11 - Adding Users to the GymLog <br>
 * I may have to combine all of my DAOs into one. <br><br>
 *
 * @version Alpha
 */

@Dao
public interface PlanetDAO {

    @Insert
    void insert(Planet... planets);

    @Update
    void update(Planet... planets);

    @Query("SELECT * FROM " + AppDatabase.PLANET_TABLE)
    List<Planet> getPlanets();

    @Query("SELECT * FROM " + AppDatabase.PLANET_TABLE + " WHERE mPlanetID = :planetID")
    List<Planet> getPlanetByID(int planetID);
}
