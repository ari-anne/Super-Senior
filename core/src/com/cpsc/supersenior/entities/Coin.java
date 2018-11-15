package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.Randomize;
import com.cpsc.supersenior.entitydata.ActorSubtype;
import com.cpsc.supersenior.entitydata.CoinUserData;

public class Coin extends Actor {

    // fixtures http://box2d.org/manual.pdf

    ActorSubtype.CoinType coinType;
    Body body;
    Vector2 linearVelocity;

    public Coin(World world) {
        coinType = Randomize.coinType();

        // create body
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(coinType.x, coinType.y);
        body = world.createBody(bodyDef);
        body.setUserData(new CoinUserData(coinType, coinType.width, coinType.height));

        // define shape
        PolygonShape shape = new PolygonShape();
        shape.setAsBox(coinType.width, coinType.height);

        FixtureDef fixtureDef = new FixtureDef();
        fixtureDef.shape = shape;
        fixtureDef.density = coinType.density;
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
