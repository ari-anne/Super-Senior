package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Image;
import com.cpsc.supersenior.SuperSenior;

public class GameScreen implements Screen {

    public static final float SPEED = 120;
    Stage stage = new Stage();

    Texture img;
    float x = 0, y = 0;

    SuperSenior game;

    public GameScreen(SuperSenior game){
        this.game = game;
    }

    @Override
    public void show() {
        Gdx.input.setInputProcessor(stage);
        img = new Texture("backgrounds/game_background_3/game_background_3.1.png");
        Image background = new Image(img);
        stage.addActor(background);
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
        stage.act();
        stage.draw();
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
