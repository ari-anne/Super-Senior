package com.cpsc.supersenior.entities;

import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.Randomize;
import com.cpsc.supersenior.entitydata.ActorSubtype;
import com.cpsc.supersenior.entitydata.GroundUserData;

public class Ground extends Actor {

    private ActorSubtype.GroundType groundType;
    private Body body;

    public Ground(World world) {
        groundType = ActorSubtype.GroundType.GRASS;

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(groundType.x, groundType.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(groundType.width, groundType.height);

        body = world.createBody(bodyDef);
        body.createFixture(shape, groundType.density);
        body.setUserData(new GroundUserData(groundType, groundType.width, groundType.height));

        body.resetMassData();
        shape.dispose();
    }

}
