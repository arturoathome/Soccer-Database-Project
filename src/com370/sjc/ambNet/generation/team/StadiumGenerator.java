package com370.sjc.ambNet.generation.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class StadiumGenerator {
    private static final String[] brands = {"Costco", "Roblox", "Fortnite", "Hershey", "Crocs", "Visa", "Google", "American Express", "McDonald\\'s", "Fedex",
            "Amazon", "Nike", "Gatorade", "Trader Joe\\'s", "New Balance", "Dunkin\\' Donuts", "Apple", "Nintendo", "Geartek", "Crate & Barrel"};

    //Used to make sure brands don't repeat
    static ArrayList<String> lBrands;

    static {
        resetValues();
    }

    /**
     * Fills lBrands with a copy of the data contained in brands and shuffles it.
     */
    public static void resetValues() {
        lBrands = new ArrayList<>(List.of(brands));
        Collections.shuffle(lBrands);
    }

    /**
     *
     * @param random provided random object
     * @return a stadium name created from two words:
     * the first will be a brand name(which is then removed from lBrands)
     * the second will be either "Arena" or "Stadium"
     */
    public static String getStadium(Random random) {
        return lBrands.remove(0) + " " +
                (random.nextInt(2) == 1 ? "Arena" : "Stadium");
    }
}
