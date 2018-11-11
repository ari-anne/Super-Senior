package com.cpsc.supersenior.entities;

import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.BodyDef;
import com.badlogic.gdx.physics.box2d.PolygonShape;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.cpsc.supersenior.tools.GameStage;

public class Obstacle extends Actor {

    public static final float X = 25f;
    public static final float DENSITY = Runner.DENSITY;
//    public static final float RUNNING_SHORT_Y = 1.5f;
//    public static final float RUNNING_LONG_Y = 2f;
//    public static final float FLYING_Y = 3f;

    public static final Vector2 LINEAR_VELOCITY = new Vector2(-10f, 0);
    public static final GameStage.UserDataType TYPE = GameStage.UserDataType.OBSTACLE;

    Body body;
    Vector2 linearVelocity;

    // TODO: find art assets for obstacle types
    // obstacle types are temporary and can be changed later
    public enum ObstacleType {
        GROUND_SMALL(X, 0, 1f, Ground.HEIGHT, 0),
        GROUND_LONG(X, 0, 2f, Ground.HEIGHT, 0);

        float x;
        float y;
        float width;
        float height;
        float density;

        ObstacleType (float x, float y, float width, float height, float density) {
            this.x = x;
            this.y = y;
            this.width = width;
            this.height = height;
            this.density = density;
        }

        public float getX() {
            return x;
        }

        public float getY() {
            return y;
        }

        public float getWidth() {
            return width;
        }

        public float getHeight() {
            return height;
        }

        public float getDensity() {
            return density;
        }
    }


    public Obstacle(World world) {
        linearVelocity = LINEAR_VELOCITY;

        // TODO: randomize obstacle type
        ObstacleType obstacleType = ObstacleType.GROUND_LONG;
        BodyDef bodyDef = new BodyDef();
        bodyDef.type = BodyDef.BodyType.KinematicBody;
        bodyDef.position.set(obstacleType.x, obstacleType.y);

        PolygonShape shape = new PolygonShape();
        shape.setAsBox(obstacleType.getWidth(), obstacleType.height);

        body = world.createBody(bodyDef);
        body.createFixture(shape, obstacleType.density);
        body.setUserData(TYPE);

        body.resetMassData();
        shape.dispose();
    }

    public void setLinearVelocity(Vector2 linearVelocity) {
        this.linearVelocity = linearVelocity;
    }

    public Vector2 getLinearVelocity() {
        return linearVelocity;
    }

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(linearVelocity);
    }

}
