package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Ground {

    public static final float X = 0;
    public static final float Y = 0;
    public static final float WIDTH = 45f;
    public static final float HEIGHT = 5f;
    public static final float DENSITY = 0f;

    public Ground() {
    }

    public static Body createGround(World world) {
        BodyDef bodyDef = new BodyDef();
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();

        bodyDef.position.set(0, 0);
        shape.setAsBox(WIDTH/2, HEIGHT/2);
        body.createFixture(shape, DENSITY);

        shape.dispose();

        return body;
    }

}
