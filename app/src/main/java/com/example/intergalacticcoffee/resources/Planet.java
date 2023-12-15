package com.example.intergalacticcoffee.resources;

import com.example.intergalacticcoffee.db.UserDAO;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * I initially wanted to set this up so that it would be its own
 * table in the database. I wanted it to prepopulate from a text file
 * but I ran out of time so this is what it turned into. <br><br>
 *
 * I am going to need to be careful about only using one instance of
 * planet as to not take up too much memory.<br><br>
 *
 * @version 0.1
 */

public class Planet {

    private ArrayList<String> planetNameList;
    private ArrayList<String> planetDescriptionList;
    private ArrayList<String> coffeeNameList;
    private ArrayList<Double> coffeePriceList;
    private ArrayList<Integer> cart;
    UserDAO mUserDAO;

    public Planet() {
        planetNameList = new ArrayList<>();
        planetDescriptionList = new ArrayList<>();
        coffeeNameList = new ArrayList<>();
        coffeePriceList = new ArrayList<>();
        cart = new ArrayList<>();

        // planetNameList ---------------------------------------------
        planetNameList.add("Tantooine");
        planetNameList.add("Kamino");
        planetNameList.add("Yaavin");
        planetNameList.add("Death Star");

        // planetDescriptionList ---------------------------------------
        planetDescriptionList.add("These coffee beans are grown through a special process " +
                "that has been passed down from generation to generation of Jawas.");
        planetDescriptionList.add("Genetically modified coffee beans that are" +
                "infinitely cloned. Great taste at an unbeatable price.");
        planetDescriptionList.add("Hidden in the Yaavin forests, there lies a pocket " +
                "of Coffea plants with the richest flavor on this side of the galaxy.");
        planetDescriptionList.add("Held to inhumane conditions, these stormtroopers " +
                "work themselves to death in order to make the best coffee possible.");

        // coffeeNameList -----------------------------------------------
        coffeeNameList.add("Dark Roast");
        coffeeNameList.add("Light Roast");

        // coffeePriceList ----------------------------------------------
        coffeePriceList.add(5.99);
        coffeePriceList.add(4.99);

        // --------------------------------------------------------------
    }

    public ArrayList<String> getAllPlanetNames() {
        return planetNameList;
    }

    public String getPlanetName(int position) {
        return planetNameList.get(position);
    }

    public String getPlanetDescription(int position) {
        return planetDescriptionList.get(position);
    }

    public ArrayList<String> getAllCoffeeNames() {
        return coffeeNameList;
    }

    public String getCoffeeName(int position) {
        return coffeeNameList.get(position);
    }

    public double getCoffeePrice(int position) {
        return coffeePriceList.get(position);
    }

    public void addToCart(int position) {
        // Coffee name and price are stored at the same position
        cart.add(position);
    }

    public ArrayList<Integer> getCart() {
        return cart;
    }

    public void makePurchase(int userID, ArrayList<Integer> cart) { // Maybe add cart as a parameter instead.
        for (int i = 0; i < cart.size(); i++) {
            int position = cart.get(i);
            Purchase purchase = new Purchase(getCoffeeName(position), getCoffeePrice(position), userID);
            mUserDAO.insert(purchase);
        }
    }

}
