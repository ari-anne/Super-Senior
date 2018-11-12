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

    public static final GameStage.ActorType TYPE = GameStage.ActorType.RUNNER;
    public static final Vector2 JUMPING_LINEAR_IMPULSE = new Vector2(0, 10f);
    public static final float HIT_ANGULAR_IMPULSE = 3f;

    Body body;
//    Vector2 linearVelocity;
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
        body.setUserData(TYPE);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body.createFixture(shape, DENSITY);
        body.setGravityScale(GRAVITY_SCALE);

        body.resetMassData();
        shape.dispose();
    }

//    public void setLinearVelocity(Vector2 linearVelocity) {
//        this.linearVelocity = linearVelocity;
//    }

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

    public void stand() {
        dodging = false;
        if (!hit) {
            body.setTransform(new Vector2(X, Y + HEIGHT), 0f);
        }
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

//    @Override
//    public void act(float delta) {
//        super.act(delta);
//        body.setLinearVelocity(linearVelocity);
//    }
}
