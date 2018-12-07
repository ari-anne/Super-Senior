package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.entities.Ground;
import com.cpsc.supersenior.entities.Runner;

public enum ObstacleType {
    SAW (Constants.SPAWN_X, Constants.OBSTACLE_SAW_Y, Constants.OBSTACLE_SAW_WIDTH, Constants.OBSTACLE_SAW_HEIGHT, Constants.OBSTACLE_DENSITY),
    SHORT_SPIKE (Constants.SPAWN_X, Ground.ABOVE_GROUND, Constants.OBSTACLE_SPIKE_WIDTH, Constants.OBSTACLE_SPIKE_SHORT_HEIGHT, Constants.OBSTACLE_DENSITY),
    LONG_SPIKE(Constants.SPAWN_X, Ground.ABOVE_GROUND + 0.5f, Constants.OBSTACLE_SPIKE_WIDTH, Constants.OBSTACLE_SPIKE_LONG_HEIGHT, Constants.OBSTACLE_DENSITY);

    public float x;
    public float y;
    public float width;
    public float height;
    public float density;

    public static class Constants {
        private static final float SPAWN_X = 21f;     // spawns off screen
        private static final float OBSTACLE_DENSITY = Runner.DENSITY;

        private static final float OBSTACLE_SAW_Y = Ground.ABOVE_GROUND + 2.5f;
        private static final float OBSTACLE_SAW_WIDTH = 1f;
        private static final float OBSTACLE_SAW_HEIGHT = 1f;

        private static final float OBSTACLE_SPIKE_WIDTH = 0.6f;
        private static final float OBSTACLE_SPIKE_SHORT_HEIGHT = 1f;
        private static final float OBSTACLE_SPIKE_LONG_HEIGHT = 1.4f;
    }

    ObstacleType(float x, float y, float width, float height, float density) {
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.density = density;
    }
}
