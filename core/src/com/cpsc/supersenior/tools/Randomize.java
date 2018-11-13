package com.cpsc.supersenior.tools;


import com.cpsc.supersenior.entitydata.ActorSubtype;

import java.util.Random;

public class Randomize {

    private static Random random = new Random();    // random number generator
    private static int max;
    private static int min;

    public static ActorSubtype.ObstacleType obstacleType() {
        ActorSubtype.ObstacleType[] type = ActorSubtype.ObstacleType.values();
        return type[random.nextInt(type.length)];
    }

    public static ActorSubtype.GroundType groundType() {
        ActorSubtype.GroundType[] type = ActorSubtype.GroundType.values();
        return type[random.nextInt(type.length)];
    }

    public static ActorSubtype.CoinType coinType() {
        ActorSubtype.CoinType[] type = ActorSubtype.CoinType.values();
        return type[random.nextInt(type.length)];
    }

    public static float groundWidth() {
        // range: 20 - 150
        max = 150;
        min = 20;
        return random.nextInt((max - min) + 1) + min;
    }

    public int numberOfSpikes() {
        // range: 1 - 4
        max = 4;
        min = 1;
        return random.nextInt((max - min) + 1) + min;
    }

    public int numberOFCoins() {
        // range: 3 - 5
        max = 5;
        min = 3;
        return random.nextInt((max - min) + 1) + min;
    }

}
