package com.cpsc.supersenior.tools;

import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.physics.box2d.Body;
import com.badlogic.gdx.physics.box2d.Box2DDebugRenderer;
import com.badlogic.gdx.physics.box2d.World;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.cpsc.supersenior.entities.Ground;
import com.cpsc.supersenior.entities.Runner;

public class GameStage extends Stage {

    private static final int VIEWPORT_WIDTH = 20;
    private static final int VIEWPORT_HEIGHT = 13;
    public static final Vector2 GRAVITY = new Vector2(0,-10);

    private float elapsedTime = 0f;
    private final float TIME_STEP = 1/300f;

    World world;
    Body ground;
    Body runner;

    Box2DDebugRenderer renderer;
    OrthographicCamera camera;

    public GameStage() {
        world = new World(GRAVITY, true);
        ground = Ground.createGround(world);
        runner = Runner.createRunner(world);

        renderer = new Box2DDebugRenderer();

        camera = new OrthographicCamera(VIEWPORT_WIDTH, VIEWPORT_HEIGHT);
        camera.position.set(camera.viewportWidth/2, camera.viewportHeight/2, 0f);
        camera.update();
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
}
