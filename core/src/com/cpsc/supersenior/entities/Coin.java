package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.entitydata.ObstacleType;
import com.cpsc.supersenior.entitydata.CoinUserData;

public class Coin extends Actor {

    // fixtures http://box2d.org/manual.pdf

    private static final float X = 21f;
    private static final float Y = Ground.ABOVE_GROUND + 1f;
    private static final float WIDTH = 0.3f;
    private static final float HEIGHT = 0.3f;
    private static final float DENSITY = 0f;

    private Body body;
    private Vector2 linearVelocity;

    public Coin(World world) {
        float y_offset = 0f;
        makeBody(world, y_offset);
    }

    public Coin(World world, ObstacleType obstacleType) {
        float y_offset = 0f;
        switch (obstacleType) {
            case LONG_SPIKE:
                y_offset = 1f;
                break;
            case SHORT_SPIKE:
                y_offset = 0.5f;
        }
        makeBody(world, y_offset);
    }

    private void makeBody(World world, float y_offset) {
        // create body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(X, Y + y_offset);
        body = world.createBody(bodyDef);
        body.setUserData(new CoinUserData(WIDTH, HEIGHT));

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

    public boolean should_delete(){
        CoinUserData userdata = (CoinUserData)body.getUserData();
        return userdata.toDelete;
    }

    public Vector2 getBodyPosition(){
        return body.getPosition();
    }

    @Override
    public boolean remove(){
        body.getWorld().destroyBody(body);
        return super.remove();
    }

}
