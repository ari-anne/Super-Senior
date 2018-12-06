package com.cpsc.supersenior.entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.entitydata.GroundUserData;

public class Ground extends Actor {

    private static final float X = 0;
    private static final float Y = 0;
    private static final float WIDTH = 20f;
    private static final float HEIGHT = 3f;
    private static final float DENSITY = 0f;

    Texture grass;

    public static final float ABOVE_GROUND = HEIGHT + Y;

    private Body body;

    public Ground(World world) {
        grass = new Texture("ground/GrassMid.png");

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setUserData(new GroundUserData(WIDTH, HEIGHT));

        body.resetMassData();
        shape.dispose();
    }

    @Override
    public void act(float delta) {
        super.act(delta);
    }

    @Override
    public void draw(Batch batch, float parentAlpha) {
        batch.draw(grass, 0, 0, WIDTH, HEIGHT);
    }
}
