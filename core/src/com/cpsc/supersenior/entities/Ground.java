package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Ground extends Actor {

    public static final float X = 0;
    public static final float Y = 0;
    public static final float WIDTH = 20f;
    public static final float HEIGHT = 3f;
    public static final float DENSITY = 0f;

    public static final GameStage.ActorType TYPE = GameStage.ActorType.GROUND;

    Body body;
//    Vector2 linearVelocity;

    public Ground(World world) {
        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setUserData(TYPE);

//        FixtureDef fixtureDef = new FixtureDef();
//        fixtureDef.shape = shape;
//        fixtureDef.density = DENSITY;
//        body.createFixture(fixtureDef);

        body.resetMassData();
        shape.dispose();
    }

//    public void setLinearVelocity(Vector2 linearVelocity) {
//        this.linearVelocity = linearVelocity;
//    }
//
//    @Override
//    public void act(float delta) {
//        super.act(delta);
//        body.setLinearVelocity(linearVelocity);
//    }
}
