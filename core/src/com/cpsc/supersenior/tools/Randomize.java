package com.cpsc.supersenior.tools;

import com.cpsc.supersenior.entitydata.ActorSubtype;

import java.util.Random;

public class Randomize {

    private static Random random = new Random();    // random number generator
    private static float max;
    private static float min;

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
        max = 3f;
        min = 1f;
        return random.nextFloat() * (max - min) + min;
    }

    public static float groundWidth() {
        // range: 20 - 150
        max = 150f;
        min = 20f;
        return random.nextFloat() * (max - min) + min;
    }
}
