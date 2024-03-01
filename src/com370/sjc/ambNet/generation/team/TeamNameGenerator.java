package com370.sjc.ambNet.generation.team;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TeamNameGenerator {
    private static final String[] animals = {"Dolphins", "Eagles", "Ponies", "Tigers", "Lions", "Elephants", "Lobsters", "Monkeys", "Deer", "Wolves", "Jaguars", "Vipers", "Urchins", "Gators", "Bison", "Cougars", "Barracuda", "Sharks", "Piranhas", "Wasps","Owls"};
    private static final String[] colors = {"Red", "Orange", "Yellow", "Green", "Blue", "Purple", "Magenta", "Brown", "Indigo", "Lime"};

    private static final String[] states = {"Alabama", "Alaska", "Arizona", "Arkansas", "California", "Colorado", "Connecticut", "Delaware", "Florida", "Georgia", "Hawaii", "Idaho", "Illinois", "Indiana", "Iowa", "Kansas", "Kentucky", "Louisiana", "Maine", "Maryland", "Massachusetts", "Michigan", "Minnesota", "Mississippi", "Missouri", "Montana", "Nebraska", "Nevada", "New Hampshire", "New Jersey", "New Mexico", "New York", "North Carolina", "North Dakota", "Ohio", "Oklahoma", "Oregon", "Pennsylvania", "Rhode Island", "South Carolina", "South Dakota", "Tennessee", "Texas", "Utah", "Vermont", "Virginia", "Washington", "West Virginia", "Wisconsin", "Wyoming"};

    static ArrayList<String> lAnimals;
    static ArrayList<String> prefixes;

    static {
        resetValues();
    }

    /**
     * Fills lAnimals with the data from the animals array
     * Fills prefixes with the data from both the colors and states array
     * Shuffles both arraylists
     */
    public static void resetValues() {
        lAnimals = new ArrayList<>(List.of(animals));
        prefixes = new ArrayList<>(List.of(colors));
        prefixes.addAll(List.of(states));

        Collections.shuffle(lAnimals);
        Collections.shuffle(prefixes);
    }

    /**
     *
     * @return A string that is a combination of a "prefix" and an "animal"
     */
    public static String getTeamName() {
        return prefixes.remove(0) + " " + lAnimals.remove(0);
    }
}
