package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Runner extends Actor {

    public static final float X = 2;
    public static final float Y = Ground.Y + Ground.HEIGHT;
    public static final float WIDTH = 2f;
    public static final float HEIGHT = 3f;
    public static final float DENSITY = 0.5f;
    public static final float GRAVITY_SCALE = 2f;
    public static final float CROUCH_X = 2f;
    public static final float CROUCH_Y = 1.5f;

    public static final Vector2 JUMPING_LINEAR_IMPULSE = new Vector2(0, 40f);
    public static final GameStage.UserDataType TYPE = GameStage.UserDataType.RUNNER;

    Body body;
    Vector2 jumpingLinearImpulse;
    boolean jumping;

    public Runner(World world) {
        jumpingLinearImpulse = JUMPING_LINEAR_IMPULSE;

        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH/2, HEIGHT/2);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setGravityScale(GRAVITY_SCALE);
        body.setUserData(TYPE);

        body.resetMassData();
        shape.dispose();
    }

    public void jump() {
        if (!jumping) {
            body.applyLinearImpulse(jumpingLinearImpulse, body.getWorldCenter(), true);
            jumping = true;
        }
    }

    public void landed() {
        jumping = false;
    }

    public void setJumpingLinearImpulse(Vector2 jumpingLinearImpulse) {
        this.jumpingLinearImpulse = jumpingLinearImpulse;
    }
}
