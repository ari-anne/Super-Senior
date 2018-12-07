package com.cpsc.supersenior.tools;

import com.cpsc.supersenior.entitydata.ObstacleType;

import java.util.Random;

public class Randomize {

    private static Random random = new Random();    // random number generator

    public static ObstacleType obstacleType() {
        ObstacleType[] type = ObstacleType.values();
        return type[random.nextInt(type.length)];
    }

    public static float obstacleSpawnTime(float max, float min) {
        return random.nextFloat() * (max - min) + min;
    }
}
