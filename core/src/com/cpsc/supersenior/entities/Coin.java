package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Coin extends Actor {

    public static final float X = 25f;
    public static final float Y = Ground.Y + Ground.HEIGHT + 1f;
    public static final float WIDTH = 0.3f;
    public static final float HEIGHT = 0.3f;
    public static final float DENSITY = 0f;

    public static final GameStage.ActorType TYPE = GameStage.ActorType.COIN;

    Body body;
    Vector2 linearVelocity;

    public Coin(World world) {
        // create body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(X, Y);
        body = world.createBody(bodyDef);
        body.setUserData(TYPE);

        // define shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = DENSITY;
        fixtureDef.isSensor = true;     // detects collision, but has no response
        body.createFixture(fixtureDef);

        body.resetMassData();
        shape.dispose();
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(linearVelocity);
    }

}
