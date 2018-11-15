package com.cpsc.supersenior.tools;


import com.cpsc.supersenior.entitydata.ActorSubtype;

import java.util.Random;

public class Randomize {

    private static Random random = new Random();    // random number generator
    private static float maxFloat;
    private static float minFloat;
    private static int maxInt;
    private static int minInt;

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

    public static float obstacleSpawnTime() {
        // range: 1 - 3 seconds
        maxFloat = 3f;
        minFloat = 1f;
        return random.nextFloat() * (maxFloat - minFloat) + minFloat;
    }

    public static float groundWidth() {
        // range: 20 - 150
        maxFloat = 150f;
        minFloat = 20f;
        return random.nextFloat() * (maxFloat - minFloat) + minFloat;
    }

    public int numberOfSpikes() {
        // range: 1 - 4
        maxInt = 4;
        minInt = 1;
        return random.nextInt((maxInt - minInt) + 1) + minInt;
    }

    public int numberOFCoins() {
        // range: 3 - 5
        maxInt = 5;
        minInt = 3;
        return random.nextInt((maxInt - minInt) + 1) + minInt;
    }

}
