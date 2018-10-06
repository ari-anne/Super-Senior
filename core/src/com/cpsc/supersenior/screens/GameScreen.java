package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.cpsc.supersenior.SuperSenior;

public class GameScreen implements Screen {

    public static final float SPEED = 120;

    Texture img;
    float x = 0, y = 0;

    SuperSenior game;

    public GameScreen(SuperSenior game){
        this.game = game;
    }

    @Override
    public void show() {
        img = new Texture("badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 0, 0, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        float time = Gdx.graphics.getDeltaTime();	// time between previous and current render() call
        // allows us to move objects in distance per second instead of frames per second

        if (Gdx.input.isKeyPressed(Input.Keys.UP)){
            y += SPEED * time;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.DOWN)){
            y -= SPEED * time;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.RIGHT)){
            x += SPEED * time;
        }
        if (Gdx.input.isKeyPressed(Input.Keys.LEFT)){
            x -= SPEED * time;
        }

        game.batch.begin();
        game.batch.draw(img, x, y);
        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        game.batch.dispose();
        img.dispose();
    }
}
