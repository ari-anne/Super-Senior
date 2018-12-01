package com.cpsc.supersenior.tools;

import com.cpsc.supersenior.entitydata.ActorSubtype;

import java.util.Random;

public class Randomize {

    private static Random random = new Random();    // random number generator

    public static ActorSubtype.ObstacleType obstacleType() {
        ActorSubtype.ObstacleType[] type = ActorSubtype.ObstacleType.values();
        return type[random.nextInt(type.length)];
    }

    public static float obstacleSpawnTime(float max, float min) {
        return random.nextFloat() * (max - min) + min;
    }
}
