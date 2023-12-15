package com.example.intergalacticcoffee.resources;

import java.util.ArrayList;
import java.util.Random;

/**
 * boolean mAllegiance determines what bounties will show up.
 * Could be nice to overload the constructor so that it takes
 * allegiance as a parameter and constructs the object based
 * on allegiance.
*/
public class BountyHunter {

    private ArrayList<String> names;
    private ArrayList<String> descriptions;
    private ArrayList<Integer> difficulty;

    public BountyHunter() {
        this.names = new ArrayList<>();
        this.descriptions = new ArrayList<>();
        this.difficulty = new ArrayList<>();

        // Names -----------------------------------------
        names.add("Stormtrooper");
        names.add("Rebel");
        names.add("Imperial Officer");
        names.add("Force Wielder");
        names.add("Repurposed Clanker");
        names.add("Wookie Warrior");
        names.add("Purge Trooper");
        names.add("Ewok Army");
        names.add("Nightsister");
        names.add("Order 66 Survivor");

        // Description -----------------------------------
        descriptions.add("Guerrilla warfare tactics have led the Rebels " +
                "to search for spare stormtrooper armor. Take down one of " +
                "these guys and return the armor to Rebel HQ. ");
        descriptions.add("These acts of terrorism must come to an end. " +
                "Our noble Emperor seeks justice! Following the recent prison" +
                " breaks, the law stands that any known rebel sympathizers will receive " +
                "capitol punishment. ");
        descriptions.add("Tactically smarter than the rest of the standard " +
                "troopers. Taking down one of these guys will greatly help the " +
                "rebellion.");
        descriptions.add("A child has began to show signs of force sensitivity. " +
                "The empire wishes to have the child brought to them in hopes of " +
                "gaining a new weapon.");
        descriptions.add("Empire factions in the outer rim have begun to repurpose Battle " +
                "Droids and push them out as enforcers. These brutal machines need to be stopped.");
        descriptions.add("These warriors are feared across the galaxy by Empire forces. " +
                "Their monstrous strength has been rumored to be capable of tearing limbs " +
                "off of a fully armored stormtrooper. ");
        descriptions.add("A specialized variant of stormtroopers utilized by the mysterious Inquisitorius " +
                "of the Galactic Empire as special forces and expendable death squads.");
        descriptions.add("Easy to take these little guys lightly but in large groups, they can" +
                " be quite frightening. They can be hard to see in the thick foliage of their " +
                "home planet... especially at night.");
        descriptions.add("These dark side users are able to perform magick that is unlike " +
                "anything else seen in the galaxy. They are truly frightening foes to face.");
        descriptions.add("The few jedis who survived the horrors of order 66 were molded by " +
                "adversity. There are no fiercer warriors than those who have had to face the " +
                "unending pursuit of the empire.");

        // Difficulty -----------------------------------
        for (int i = 0; i < 5; i++) {
            difficulty.add(i + 2);
            difficulty.add(i + 2);
        }
    }

    public String getName(int position) {
        return names.get(position);
    }

    public int getNameSize() {
        return names.size();
    }

    public ArrayList<String> getNames() {
        return names;
    }

    public String getDescription(int position) {
        return descriptions.get(position);
    }

    public int getDifficulty(int position) {
        return difficulty.get(position);
    }

    // Still needs work ----------------------------------
    public int rollDice() {
        Random random = new Random();
        int chance = random.nextInt(6) + 1;
        return chance;
    }

    public String isSuccess(int chance, int position) {
        if (chance > difficulty.get(position)) {
            return "Success!";
        } else {
            return "Failure";
        }
    }
}
