package com.cpsc.supersenior.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.entitydata.GroundUserData;
import com.cpsc.supersenior.tools.GameStage;

public class Ground extends Actor {

    private static final float X = 0;   // body x pos
    private static final float Y = 0;
    private static final float WIDTH = 20f;
    private static final float HEIGHT = 3f;
    private static final float DENSITY = 0f;
    public static final float ABOVE_GROUND = HEIGHT + Y;

    private static final float DEFAULT_SPEED = 400;
    private static final float INCREASE_SPEED = 100;

    Texture grass;
    float x1, x2;       // image x pos
    float speed;

    private Body body;

    public Ground(World world) {
        grass = new Texture(Gdx.files.internal("ground/GrassMid.png"));
        x1 = 0;
        x2 = WIDTH * GameStage.WORLD_X;
        speed = DEFAULT_SPEED;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setUserData(new GroundUserData(WIDTH/2, HEIGHT/2));

        body.resetMassData();
        shape.dispose();
    }

    public void speedUp() {
        speed += INCREASE_SPEED;
    }

    @Override
    public void act(float delta) {
        x1 -= speed * delta;
        x2 -= speed * delta;

        // reposition image after it scrolls off screen
        if (x1 + WIDTH * GameStage.WORLD_X <= 0) {
            x1 = x2 + WIDTH * GameStage.WORLD_X;
        }
        if (x2 + WIDTH * GameStage.WORLD_X <= 0) {
            x2 = x1 + WIDTH * GameStage.WORLD_X;
        }
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(grass, x1, Y, WIDTH * GameStage.WORLD_X, HEIGHT * GameStage.WORLD_Y);
        batch.draw(grass, x2, Y, WIDTH * GameStage.WORLD_X, HEIGHT * GameStage.WORLD_Y);
    }
}
