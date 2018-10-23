package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cpsc.supersenior.SuperSenior;

public class ScrollingBackground {

    // Scrolling Background https://www.youtube.com/watch?v=UyNm3n1WNAA&list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf&index=15

    // TODO: update to include individual background layers

    public static final int DEFAULT_SPEED = 80;
    public static final int ACCELERATION = 50;
    public static final int GOAL_REACH_ACCELERATION = 200;

    Texture image;
    float x1, x2;
    int speed;
    int goalSpeed;
    boolean fixedSpeed;

    public ScrollingBackground () {
        image = new Texture(Gdx.files.internal("backgrounds/game_background_1/game_background_1.png"));

        x1 = 0;
        x2 = SuperSenior.WIDTH;
        speed = 0;
        goalSpeed = DEFAULT_SPEED;
        fixedSpeed = true;
    }

    public void update(float deltaTime) {
        // speed adjustment to reach goal
        if (speed < goalSpeed) {
            speed += GOAL_REACH_ACCELERATION * deltaTime;
            if (speed > goalSpeed)
                speed = goalSpeed;
        } else if (speed > goalSpeed) {
            speed -= GOAL_REACH_ACCELERATION * deltaTime;
            if (speed < goalSpeed)
                speed = goalSpeed;
        }

        if (!fixedSpeed) {
            speed += ACCELERATION * deltaTime;
        }

        x1 -= speed * deltaTime;
        x2 -= speed * deltaTime;

        // move image after it scrolls off screen
        if (x1 + SuperSenior.WIDTH <= 0) {
            x1 = x2 + SuperSenior.WIDTH;
        }

        if (x2 + SuperSenior.WIDTH <= 0) {
            x2 = x1 + SuperSenior.WIDTH;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(image, x1, 0, SuperSenior.WIDTH, SuperSenior.HEIGHT);
        batch.draw(image, x2, 0, SuperSenior.WIDTH, SuperSenior.HEIGHT);
    }

    public void reset(){
        x1 = 0;
        x2 = SuperSenior.WIDTH;
        speed = 0;
    }

    public void setSpeed (int goalSpeed) {
        this.goalSpeed = goalSpeed;
    }

    public void setFixedSpeed(boolean fixedSpeed) {
        this.fixedSpeed = fixedSpeed;
    }
}
