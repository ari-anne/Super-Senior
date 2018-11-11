package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Runner extends Actor {

    public static final float X = 2f;
    public static final float Y = Ground.Y + Ground.HEIGHT;
    public static final float WIDTH = 0.5f;
    public static final float HEIGHT = 1f;
    public static final float DENSITY = 0.5f;
    public static final float GRAVITY_SCALE = 2f;
    public static final float CROUCH_X = X;
    public static final float CROUCH_Y = Ground.HEIGHT + WIDTH;

    public static final Vector2 JUMPING_LINEAR_IMPULSE = new Vector2(0, 10f);
    public static final GameStage.UserDataType TYPE = GameStage.UserDataType.RUNNER;

    Body body;
    Vector2 jumpingLinearImpulse;
    boolean jumping;
    boolean dodging;

    public Runner(World world) {
        jumpingLinearImpulse = JUMPING_LINEAR_IMPULSE;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setGravityScale(GRAVITY_SCALE);
        body.setUserData(TYPE);

        body.resetMassData();
        shape.dispose();
    }

    public void jump() {
        if (!jumping && !dodging) {
            body.applyLinearImpulse(jumpingLinearImpulse, body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void landed() {
        jumping = false;
    }

    public void crouch() {
        if (!jumping) {
           body.setTransform(new Vector2(CROUCH_X, CROUCH_Y), getDodgeAngle());
           dodging = true;
        }
    }

    public void stand() {
        dodging = false;
        body.setTransform(new Vector2(X, Y+HEIGHT), 0f);
    }

    public float getDodgeAngle() {
        return (float) (-90f * (Math.PI / 180f));   // radians
    }

    public boolean isDodging() {
        return dodging;
    }

}
