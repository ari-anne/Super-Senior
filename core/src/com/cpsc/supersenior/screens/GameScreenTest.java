package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.ui.Label;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.tools.GameStage;
import com.cpsc.supersenior.tools.Score;

public class GameScreenTest implements Screen {

    private GameStage stage;

    SuperSenior game;
    Skin skin;
    Label scoreTxt;

    public GameScreenTest(SuperSenior game) {
        this.game = game;
        stage = new GameStage();
    }

    @Override
    public void show() {
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        scoreTxt = new Label("0", skin);

        scoreTxt.setBounds(Gdx.graphics.getWidth()/2 - scoreTxt.getWidth()/2, Gdx.graphics.getHeight() - scoreTxt.getHeight() - 50, scoreTxt.getWidth(), scoreTxt.getHeight());

        stage.addActor(scoreTxt);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        scoreTxt.setText(Integer.toString(Score.getScore()));

        stage.draw();
        stage.act(delta);
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
