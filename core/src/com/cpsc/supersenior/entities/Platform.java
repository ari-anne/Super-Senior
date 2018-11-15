package com.cpsc.supersenior.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.entitydata.ActorSubtype;
import com.cpsc.supersenior.entitydata.UserData;

public class Platform extends Actor {

    // created when lava/water/hole is too wide to cross in one jump

    public static final float Y = ActorSubtype.ABOVE_GROUND + 2f;
    public static final float HEIGHT = 1f;
    public static final float DENSITY = ActorSubtype.GROUND_DENSITY;

    Body body;

    public Platform(World world, float groundX, float groundWidth) {
        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(groundX + 1f, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(groundWidth - 2f, HEIGHT);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setUserData(new UserData());

        body.resetMassData();
        shape.dispose();
    }

}
