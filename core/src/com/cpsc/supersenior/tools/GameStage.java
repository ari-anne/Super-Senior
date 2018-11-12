package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.utils.Array;
import com.cpsc.supersenior.entities.*;

public class GameStage extends Stage implements ContactListener {

    // following this tutorial: http://williammora.com/a-running-game-with-libgdx-part-1

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;
    public static final Vector2 GRAVITY = new Vector2(0,-10);

    private float elapsedTime = 0f;
    private final float TIME_STEP = 1/300f;

    World world;
    Ground ground;
    Runner runner;
    Obstacle obstacle;
    Coin coin;

    Box2DDebugRenderer renderer;
    OrthographicCamera camera;

    float linearVelocityX;

    Rectangle rightSide;
    Rectangle leftSide;

    public enum UserDataType {
        GROUND,
        RUNNER,
        OBSTACLE,
        COIN
    }

    public GameStage() {
        world = new World(GRAVITY, true);
        renderer = new Box2DDebugRenderer();
        world.setContactListener(this);

        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        camera.update();

        linearVelocityX = 3f;

//        addActor(new Background());
        makeGround();
        makeRunner();
//        makeObstacle();
        makeCoin();

        // TODO: implement swipe input
        // temporary controls to test gravity
        // divide screen in half, tapping right side jumps, tapping left side crouches
        rightSide = new Rectangle(getCamera().viewportWidth/2,0,getCamera().viewportWidth/2, getCamera().viewportHeight);
        leftSide = new Rectangle(0, 0, getCamera().viewportWidth/2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    public void makeGround() {
        ground = new Ground(world);
        ground.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(ground);
    }

    public void makeRunner() {
        runner = new Runner(world);
        runner.setLinearVelocity(new Vector2(linearVelocityX, 0));
        addActor(runner);
    }

    public void makeObstacle() {
        obstacle = new Obstacle(world);
        obstacle.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(obstacle);
    }

    public void makeCoin() {
        coin = new Coin(world);
        coin.setLinearVelocity(new Vector2(-linearVelocityX, 0));
        addActor(coin);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

        Array<Body> bodies = new Array<Body>(world.getBodyCount());
        world.getBodies(bodies);

        for (Body body : bodies) {
            if (!bodyInBounds(body)) {
                if(body.getUserData() == UserDataType.OBSTACLE && !runner.isHit()) {
                    makeObstacle();
                }
                world.destroyBody(body);
            }
        }

        elapsedTime += delta;

        while (elapsedTime >= delta) {
            world.step(TIME_STEP, 6, 2);
            elapsedTime -= TIME_STEP;
        }
    }

    @Override
    public void draw() {
        super.draw();
        renderer.render(world, camera.combined);
    }

    // TODO: implement swipe input
    // temporary controls to test gravity
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        // if right side of screen is touched, jump
        // else if left side of screen is touched, crouch
        if(rightSide.contains(x,y)) {
            runner.jump();
        }
        else if (leftSide.contains(x,y)) {
            runner.crouch();
        }

        return super.touchDown(x, y, pointer, button);
    }

    @Override
    public boolean touchUp(int screenX, int screenY, int pointer, int button) {
        if (runner.isDodging()) {
            runner.stand();
        }

        return super.touchUp(screenX, screenY, pointer, button);
    }

    @Override
    public void beginContact(Contact contact) {
        Body a = contact.getFixtureA().getBody();
        Body b = contact.getFixtureB().getBody();
        UserDataType typeA = (UserDataType) a.getUserData();
        UserDataType typeB = (UserDataType) b.getUserData();

        if ((typeA == UserDataType.GROUND && typeB == UserDataType.RUNNER) ||
                (typeA == UserDataType.RUNNER && typeB == UserDataType.GROUND)) {
            runner.landed();
        }
        else if ((typeA == UserDataType.RUNNER && typeB == UserDataType.OBSTACLE) ||
                (typeA == UserDataType.OBSTACLE && typeB == UserDataType.RUNNER)) {
            runner.hit();
        }
    }

    @Override
    public void endContact(Contact contact) {

    }

    @Override
    public void preSolve(Contact contact, Manifold oldManifold) {

    }

    @Override
    public void postSolve(Contact contact, ContactImpulse impulse) {

    }

    // TODO: accommodate randomized obstacles
    public static boolean bodyInBounds(Body body) {
        if(body.getUserData() == UserDataType.RUNNER) {
            return body.getPosition().x + Runner.WIDTH > 0;
        }
        else if (body.getUserData() == UserDataType.OBSTACLE) {
            return body.getPosition().x + Obstacle.ObstacleType.GROUND_LONG.getWidth() > 0;
        }
        return true;
    }
}
