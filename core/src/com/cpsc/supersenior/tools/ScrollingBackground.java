package com.cpsc.supersenior.tools;

import com.badlogic.gdx.graphics.Texture;

public class ScrollingBackground {

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;

    Texture image;

    public ScrollingBackground(){
        image = new Texture("backgrounds/game_background_1/layers/clouds_1.png");
    }
}
