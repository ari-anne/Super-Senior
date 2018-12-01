package com.cpsc.supersenior.entitydata;

import com.cpsc.supersenior.entities.Runner;
import com.cpsc.supersenior.tools.Randomize;

public class ActorSubtype {

    private static final float SPAWN_X = 25f;     // spawns off screen

    private static final float GROUND_Y = 0;
    private static final float GROUND_WIDTH = 20f;
    public static final float GROUND_HEIGHT = 3f;
    private static final float GROUND_DENSITY = 0f;

    public static final float ABOVE_GROUND = GROUND_Y + GROUND_HEIGHT;  // keeps obstacles and coins above ground

    private static final float COIN_Y = ABOVE_GROUND + 1f;
    private static final float COIN_WIDTH = 0.3f;
    private static final float COIN_HEIGHT = 0.3f;
    private static final float COIN_DENSITY = 0f;

    private static final float OBSTACLE_SAW_Y = ABOVE_GROUND + 2f;
    private static final float OBSTACLE_SAW_WIDTH = 0.5f;
    private static final float OBSTACLE_SAW_HEIGHT = 0.5f;
    private static final float OBSTACLE_SPIKE_WIDTH = 0.3f;
    private static final float OBSTACLE_SPIKE_SHORT_HEIGHT = 0.5f;
    private static final float OBSTACLE_SPIKE_LONG_HEIGHT = 0.7f;
    private static final float OBSTACLE_DENSITY = Runner.DENSITY;

    public enum CoinType {
        COIN (SPAWN_X, COIN_Y, COIN_WIDTH, COIN_HEIGHT, COIN_DENSITY);

        public float x;
        public float y;
        public float width;
        public float height;
        public float density;

        CoinType(float x, float y, float width, float height, float density) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.density = density;
        }
    }

    public enum GroundType {
        GRASS(0, 0, GROUND_WIDTH, GROUND_HEIGHT, GROUND_DENSITY);

        public float x;
        public float y;
        public float width;
        public float height;
        public float density;

        GroundType(float x, float y, float width, float height, float density) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.density = density;
        }
    }

    public enum ObstacleType {
        SAW (SPAWN_X, OBSTACLE_SAW_Y, OBSTACLE_SAW_WIDTH, OBSTACLE_SAW_HEIGHT, OBSTACLE_DENSITY),
        SHORT_SPIKE (SPAWN_X, ABOVE_GROUND, OBSTACLE_SPIKE_WIDTH, OBSTACLE_SPIKE_SHORT_HEIGHT, OBSTACLE_DENSITY),
        LONG_SPIKE(SPAWN_X, ABOVE_GROUND, OBSTACLE_SPIKE_WIDTH, OBSTACLE_SPIKE_LONG_HEIGHT, OBSTACLE_DENSITY);

        public float x;
        public float y;
        public float width;
        public float height;
        public float density;

        ObstacleType(float x, float y, float width, float height, float density) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.density = density;
        }
    }
}
