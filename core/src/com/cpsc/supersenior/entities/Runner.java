package com.cpsc.supersenior.entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.entitydata.ActorSubtype;
import com.cpsc.supersenior.entitydata.RunnerUserData;
import com.cpsc.supersenior.tools.GameStage;


public class Runner extends Actor {

    public static final float X = 2f;
    public static final float Y = ActorSubtype.ABOVE_GROUND;
    public static final float WIDTH = 0.5f;
    public static final float HEIGHT = 1f;
    public static final float DENSITY = 0.5f;
    public static final float GRAVITY_SCALE = 2f;
    public static final float CROUCH_X = X;
    public static final float CROUCH_Y = ActorSubtype.GROUND_HEIGHT + WIDTH;

    public static final Vector2 JUMPING_LINEAR_IMPULSE = new Vector2(0, 10f);
    public static final float HIT_ANGULAR_IMPULSE = 3f;

    Body body;
    boolean jumping;
    boolean dodging;
    boolean hit;


    public Runner(World world) {
        jumping = false;
        dodging = false;
        hit = false;


        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(X, Y);
        body = world.createBody(bodyDef);
        body.setUserData(new RunnerUserData(WIDTH, HEIGHT));

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body.createFixture(shape, DENSITY);
        body.setGravityScale(GRAVITY_SCALE);

        body.resetMassData();
        shape.dispose();
    }

    public void jump() {
        if (!jumping && !dodging && !hit) {
            body.applyLinearImpulse(JUMPING_LINEAR_IMPULSE, body.getWorldCenter(), true);
            jumping = true;
        }


    }

    public void landed() {
        jumping = false;
    }

    public void crouch() {
        if (!jumping && !hit) {
            body.setTransform(new Vector2(CROUCH_X, CROUCH_Y), getCrouchAngle());
            dodging = true;
        }
    }

    public boolean is_crouching() {
        return dodging;
    }

    public boolean is_jumping() {
        return jumping;
    }

    public boolean is_standing() {
            return !(jumping || dodging);
    }

    public void stand() {
        dodging = false;
        if (!hit) {
            body.setTransform(new Vector2(X, Y + HEIGHT), 0f);
        }
    }

    public Vector2 getBodyPosition(){
        return body.getPosition();
    }

    public float getCrouchAngle() {
        return (float) (-90f * (Math.PI / 180f));   // in radians
    }

    public boolean isCrouching() {
        return dodging;
    }

    public void hit() {
        body.applyAngularImpulse(HIT_ANGULAR_IMPULSE, true);
        hit = true;
    }

    public boolean isHit() {
        return hit;
    }


}
