
package com.cpsc.supersenior;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cpsc.supersenior.entities.Background;
import com.cpsc.supersenior.screens.MainMenu;
import com.cpsc.supersenior.tools.GameMusic;


public class SuperSenior extends Game {

	public static final int WIDTH = 1920;
	public static final int HEIGHT = 1080;

	public SpriteBatch batch;
	public static Background background;

	public static OrthographicCamera cam;
	private StretchViewport viewport;

	public static GameMusic gameMusic;

	@Override
	public void create() {
		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, cam);

		viewport.apply();
		cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		cam.update();

		gameMusic = new GameMusic();
		background = new Background();
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render() {
		batch.setProjectionMatrix(cam.combined);
		super.render();
	}

	public void resize(int width, int height) {
		viewport.update(width, height);
		super.resize(width, height);
	}

}
