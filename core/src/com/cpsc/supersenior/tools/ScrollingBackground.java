package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class ScrollingBackground {

    // almost exactly from https://www.youtube.com/watch?v=UyNm3n1WNAA&list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf&index=15

    // UPDATE LATER TO INCLUDE INDIVIDUAL BACKGROUND LAYERS

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;

    Texture image;
    float x1, x2;
    int speed;  //In pixels / second
    int goalSpeed;
    boolean speedFixed;

    public ScrollingBackground () {
        image = new Texture(Gdx.files.internal("backgrounds/game_background_1/game_background_1.png"));

        x1 = 0;
        x2 = image.getWidth();
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        speedFixed = true;
    }

    public void render (float deltaTime, SpriteBatch batch) {
        //Speed adjustment to reach goal
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!speedFixed)
            speed += ACCELERATION * deltaTime;

        x1 -= speed * deltaTime;
        x2 -= speed * deltaTime;

        //if image reached the bottom of screen and is not visible, put it back on top
        if (x1 + image.getWidth() <= 0)
            x1 = x2 + image.getWidth();

        if (x2 + image.getWidth() <= 0)
            x2 = x1 + image.getWidth();

        //Render
        batch.draw(image, x1, 0);
        batch.draw(image, x2, 0);
    }

    public void paused(SpriteBatch batch){
        batch.draw(image, x1, 0);
        batch.draw(image, x2, 0);
    }

    public void reset(){
        x1 = 0;
        x2 = image.getWidth();
    }

    public void setSpeed (int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setSpeedFixed (boolean speedFixed) {
        this.speedFixed = speedFixed;
    }

}
