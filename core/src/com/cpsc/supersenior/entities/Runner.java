package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;

public class Runner {

    public static final float X = 2;
    public static final float Y = Ground.Y + Ground.HEIGHT;
    public static final float WIDTH = 2f;
    public static final float HEIGHT = 3f;
    public static final float DENSITY = 0.5f;

    public Runner() {

    }

    public static Body createRunner(World world) {
        BodyDef bodyDef = new BodyDef();
        Body body = world.createBody(bodyDef);
        PolygonShape shape = new PolygonShape();

        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(X, Y);
        shape.setAsBox(WIDTH/2, HEIGHT/2);
        body.createFixture(shape, DENSITY);

        body.resetMassData();
        shape.dispose();

        return body;
    }
}
