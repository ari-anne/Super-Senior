package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Ground extends Actor {

    public static final float X = 0;
    public static final float Y = 0;
    public static final float WIDTH = 20f;
    public static final float HEIGHT = 3f;
    public static final float DENSITY = 0f;

    public static final Vector2 LINEAR_VELOCITY = new Vector2(-10f, 0);
    public static final GameStage.UserDataType TYPE = GameStage.UserDataType.GROUND;;

    Body body;
    Vector2 linearVelocity;

    public Ground(World world) {
        linearVelocity = LINEAR_VELOCITY;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setUserData(TYPE);

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
