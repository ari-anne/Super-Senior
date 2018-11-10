package com.cpsc.supersenior;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cpsc.supersenior.screens.GameScreen;
import com.cpsc.supersenior.screens.GameScreenTest;
import com.cpsc.supersenior.screens.HighScores;
import com.cpsc.supersenior.screens.MainMenu;
import com.cpsc.supersenior.tools.ScrollingBackground;

public class SuperSenior extends Game {

    public static final int WIDTH = 800;
    public static final int HEIGHT = 600;

	public SpriteBatch batch;
	public ScrollingBackground scrollingBackground;

	public static OrthographicCamera cam;
	private StretchViewport viewport;

	@Override
	public void create () {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, cam);

		viewport.apply();
		cam.position.set(WIDTH/2, HEIGHT/2, 0);
		cam.update();

		this.scrollingBackground = new ScrollingBackground();
		this.setScreen(new GameScreenTest(this));
	}

	@Override
	public void render () {
		batch.setProjectionMatrix(cam.combined);
		super.render();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		super.resize(width, height);
	}
}
