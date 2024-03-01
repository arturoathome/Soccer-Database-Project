package com370.sjc.ambNet.generation.player;

import java.util.Random;


public class NameGenerator {
    /**
     *
     * @param random provided random object
     * @return a random name from the com370.sjc.ambNet.generation.player.Names enum
     */
    public static String getName(Random random){
      return Names.values()[random.nextInt(Names.values().length)].toString();
    }
}
