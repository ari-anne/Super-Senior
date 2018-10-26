package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.cpsc.supersenior.SuperSenior;

public class ScrollingBackground {

    // Scrolling Background https://www.youtube.com/watch?v=UyNm3n1WNAA&list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf&index=15

    static int DEFAULT_SPEED = 80;

    Texture image;
    float x1, x2;
    int speed;
    int acceleration;
    int goal;
    boolean fixedSpeed;

    public ScrollingBackground () {
        image = new Texture(Gdx.files.internal("backgrounds/game_background_1/game_background_1.png"));

        x1 = 0;
        x2 = Gdx.graphics.getWidth();

        speed = DEFAULT_SPEED;
        acceleration = 50;
        goal = 150;

        fixedSpeed = true;
    }

    public void update(float delta) {
        if (!fixedSpeed) {
            speed += acceleration * delta;
        }

        if (speed > goal) {
            speed = goal;
        }

        x1 -= speed * delta;
        x2 -= speed * delta;

        // reposition image after it scrolls off screen
        if (x1 + SuperSenior.WIDTH <= 0) {
            x1 = x2 + SuperSenior.WIDTH;
        }
        if (x2 + SuperSenior.WIDTH <= 0) {
            x2 = x1 + SuperSenior.WIDTH;
        }
    }

    public void render(SpriteBatch batch){
        batch.draw(image, x1, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
        batch.draw(image, x2, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    public void reset(){
        x1 = 0;
        x2 = Gdx.graphics.getWidth();
        speed = DEFAULT_SPEED;
    }

    public void resetSpeed() {
        speed = DEFAULT_SPEED;
    }

    public void setFixedSpeed(boolean fixedSpeed) {
        this.fixedSpeed = fixedSpeed;
    }
}