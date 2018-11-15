package com.cpsc.supersenior.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.scenes.scene2d.Actor;

public class Background extends Actor {

    // Scrolling Background https://www.youtube.com/watch?v=UyNm3n1WNAA&list=PLrnO5Pu2zAHKAIjRtTLAXtZKMSA6JWnmf&index=15

    // TODO: sync background speed with obstacle velocity

    private static final float DEFAULT_SPEED = 80;
    private static final float ACCELERATION = 50;
    private static final float MAX_SPEED = 150;
    private static final float SCREEN_WIDTH = Gdx.graphics.getWidth( );
    private static final float SCREEN_HEIGHT = Gdx.graphics.getHeight();

    private Texture image;
    private float x1, x2;
    private float speed;
    private boolean fixedSpeed;

    public Background() {
        image = new Texture("game_background_1.png");
        reset();
        fixedSpeed = true;
    }

    public void reset() {
        x1 = 0;
        x2 = SCREEN_WIDTH;
        resetSpeed();
    }

    public void resetSpeed() {
        speed = DEFAULT_SPEED;
    }

    public void setFixedSpeed(boolean fixedSpeed) {
        this.fixedSpeed = fixedSpeed;
    }

    @Override
    public void act(float delta) {
        if (!fixedSpeed) {
            speed += ACCELERATION * delta;
        }

        if (speed > MAX_SPEED) {
            speed = MAX_SPEED;
        }

        x1 -= speed * delta;
        x2 -= speed * delta;

        // reposition image after it scrolls off screen
        if (x1 + SCREEN_WIDTH <= 0) {
            x1 = x2 + SCREEN_WIDTH;
        }
        if (x2 + SCREEN_WIDTH <= 0) {
            x2 = x1 + SCREEN_WIDTH;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(image, x1, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
        batch.draw(image, x2, 0, SCREEN_WIDTH, SCREEN_HEIGHT);
    }
}
