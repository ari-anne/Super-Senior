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
    public static final float Y = Ground.Y + Ground.HEIGHT + 2f;
    public static final float WIDTH = 0.5f;
    public static final float HEIGHT = 0.5f;
    public static final float DENSITY = Runner.DENSITY;

    public static final GameStage.ActorType TYPE = GameStage.ActorType.OBSTACLE;

    Body body;
    Vector2 linearVelocity;

    // TODO: find art assets for obstacle types
    // obstacle types are temporary and can be changed later
    public enum ObstacleType {
        SAW (X, Y, WIDTH, HEIGHT, DENSITY);

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

        // TODO: randomize obstacle type
        ObstacleType obstacleType = ObstacleType.SAW;
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

    @Override
    public void act(float delta) {
        super.act(delta);
        body.setLinearVelocity(linearVelocity);
    }

}