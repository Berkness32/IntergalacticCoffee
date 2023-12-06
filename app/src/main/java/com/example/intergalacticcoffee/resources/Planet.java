package com.example.intergalacticcoffee.resources;


import androidx.room.Entity;
import androidx.room.PrimaryKey;

import com.example.intergalacticcoffee.db.AppDatabase;

/**
 * There is no confirmation that this file works yet.
 * @version Alpha
 */

@Entity(tableName = AppDatabase.PLANET_TABLE)
public class Planet {

    @PrimaryKey(autoGenerate = true)
    private int mPlanetID;

    private String planetName;
    private String planetDescription;
    private String coffeeName;
    private double coffeePrice;
    //private String bountyName; // Likely will be a foreign reference

    public Planet(String planetName, String planetDescription, String coffeeName, double coffeePrice) {
        this.planetName = planetName;
        this.planetDescription = planetDescription;
        this.coffeeName = coffeeName;
        this.coffeePrice = coffeePrice;
    }

    public int getPlanetID() {
        return mPlanetID;
    }

    public void setPlanetID(int planetID) {
        this.mPlanetID = planetID;
    }

    public String getPlanetName() {
        return planetName;
    }

    public void setPlanetName(String planetName) {
        this.planetName = planetName;
    }

    public String getPlanetDescription() {
        return planetDescription;
    }

    public void setPlanetDescription(String planetDescription) {
        this.planetDescription = planetDescription;
    }

    public String getCoffeeName() {
        return coffeeName;
    }

    public void setCoffeeName(String coffeeName) {
        this.coffeeName = coffeeName;
    }

    public double getCoffeePrice() {
        return coffeePrice;
    }

    public void setCoffeePrice(double coffeePrice) {
        this.coffeePrice = coffeePrice;
    }
}
