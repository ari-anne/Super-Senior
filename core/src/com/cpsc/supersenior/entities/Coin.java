package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.Randomize;
import com.cpsc.supersenior.entitydata.ActorSubtype;
import com.cpsc.supersenior.entitydata.CoinUserData;

public class Coin extends Actor {

    // fixtures http://box2d.org/manual.pdf

    public static final ActorSubtype.CoinType COIN_TYPE = Randomize.coinType();

    Body body;
    Vector2 linearVelocity;

    public Coin(World world) {
        // create body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(COIN_TYPE.x, COIN_TYPE.y);
        body = world.createBody(bodyDef);
        body.setUserData(new CoinUserData(COIN_TYPE, COIN_TYPE.width, COIN_TYPE.height));

        // define shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(COIN_TYPE.width, COIN_TYPE.height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = COIN_TYPE.density;
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
