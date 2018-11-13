package com.cpsc.supersenior.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.Randomize;
import com.cpsc.supersenior.entitydata.ActorSubtype;
import com.cpsc.supersenior.entitydata.GroundUserData;

public class Ground extends Actor {

    // TODO: change into kinematic body

    public static final ActorSubtype.GroundType GROUND_TYPE = Randomize.groundType();

    Body body;
//    Vector2 linearVelocity;

    public Ground(World world) {
        BodyDef bodyDef = new BodyDef();
//        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(GROUND_TYPE.x, GROUND_TYPE.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(GROUND_TYPE.width, GROUND_TYPE.height);

        body = world.createBody(bodyDef);
        body.createFixture(shape, GROUND_TYPE.density);
        body.setUserData(new GroundUserData(GROUND_TYPE, GROUND_TYPE.width, GROUND_TYPE.height));

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
