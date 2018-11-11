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

    public static final Vector2 JUMPING_LINEAR_IMPULSE = new Vector2(0, 13f);

    Body body;
    Vector2 jumpingLinearImpulse;
    boolean jumping;

    GameStage.UserDataType type;

    public Runner(Body body) {
        this.body = body;
        type = GameStage.UserDataType.RUNNER;
        jumpingLinearImpulse = JUMPING_LINEAR_IMPULSE;
    }

    public static Body createRunner(World world) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.DynamicBody;
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH/2, HEIGHT/2);

        Body body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);

        body.resetMassData();
        shape.dispose();

        return body;
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

    public GameStage.UserDataType getUserDataType() {
        return type;
    }

    public Vector2 getJumpingLinearImpulse() {
        return jumpingLinearImpulse;
    }
}
