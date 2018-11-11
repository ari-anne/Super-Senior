package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.physics.box2d.*;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cpsc.supersenior.entities.Ground;
import com.cpsc.supersenior.entities.Runner;

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

    Box2DDebugRenderer renderer;
    OrthographicCamera camera;

    Rectangle rightSide;
    Rectangle leftSide;
    Vector3 touchPoint;

    public enum UserDataType {
        GROUND,
        RUNNER
    }

    public GameStage() {
        world = new World(GRAVITY, true);
        ground = new Ground(world);
        runner = new Runner(world);
        renderer = new Box2DDebugRenderer();

        world.setContactListener(this);

        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        camera.update();

        addActor(ground);
        addActor(runner);

        // TODO: implement touch input
        // temporary controls to test gravity
        touchPoint = new Vector3();
        rightSide = new Rectangle(getCamera().viewportWidth/2,0,getCamera().viewportWidth/2, getCamera().viewportHeight);
        leftSide = new Rectangle(0, 0, getCamera().viewportWidth/2, getCamera().viewportHeight);
        Gdx.input.setInputProcessor(this);
    }

    @Override
    public void act(float delta) {
        super.act(delta);

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

    // TODO: implement touch input
    // temporary controls to test gravity
    @Override
    public boolean touchDown(int x, int y, int pointer, int button) {
        getCamera().unproject(touchPoint.set(x, y, 0));

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

        if (a.getUserData() == UserDataType.GROUND && b.getUserData() == UserDataType.RUNNER ||
                a.getUserData() == UserDataType.RUNNER && b.getUserData() == UserDataType.GROUND ) {
            runner.landed();
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
}
