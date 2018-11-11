package com.cpsc.supersenior.entities;

import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Ground extends Actor {

    public static final float X = 0;
    public static final float Y = 0;
    public static final float WIDTH = 20f;
    public static final float HEIGHT = 3f;
    public static final float DENSITY = 0f;

    public static final GameStage.UserDataType TYPE = GameStage.UserDataType.GROUND;;

    Body body;

    public Ground(World world) {

        BodyDef bodyDef = new BodyDef();
        bodyDef.position.set(X, Y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(WIDTH, HEIGHT);

        body = world.createBody(bodyDef);
        body.createFixture(shape, DENSITY);
        body.setUserData(TYPE);

        shape.dispose();
    }

}
