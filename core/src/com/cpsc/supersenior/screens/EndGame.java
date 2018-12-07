package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.ui.Skin;
import com.badlogic.gdx.scenes.scene2d.ui.Table;
import com.badlogic.gdx.scenes.scene2d.ui.TextButton;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpsc.supersenior.SuperSenior;
import com.cpsc.supersenior.tools.DataBaseApi;
import com.cpsc.supersenior.tools.TextListener;
import com.badlogic.gdx.Input;

import org.json.JSONArray;

public class EndGame implements Screen {

    final SuperSenior game;

    Stage stage;
    Table table;
    Skin skin;
    Button back;
    TextButton high_scores;
    TextButton[] top = new TextButton[3];
    JSONArray user_scores;
    TextListener username = new TextListener();

    public EndGame(SuperSenior game){
        this.game = game;
        DataBaseApi DBApi = new DataBaseApi();
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        back = new Button(skin, "arrow-left");
        high_scores = new TextButton("  High Scores  ", skin, "header");

        Gdx.input.getTextInput(username, "Enter Your Name", "", "ex. Super Crusher");

        stage.addActor(SuperSenior.background);
        stage.addActor(table);
        stage.addActor(back);
        stage.addActor(high_scores);

        table.setFillParent(true);
        table.add(back).width(150).height(150).padTop(50).padLeft(50);
        table.add(high_scores).expandX().padTop(50).padRight(50);
        table.row();
        table.add(top[0]).width(1000).colspan(2).expand().padTop(50).padLeft(200);
        table.row();
        table.add(top[1]).width(1000).colspan(2).expand().padTop(50).padLeft(200);
        table.row();
        table.add(top[2]).width(1000).colspan(2).expand().padTop(50).padLeft(200);
    }

    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.batch.end();

        stage.act();
        stage.draw();
    }

    @Override
    public void resize(int width, int height) { }

    @Override
    public void pause() { }

    @Override
    public void resume() { }

    @Override
    public void hide() { }

    @Override
    public void dispose() {
        Gdx.input.setInputProcessor(null);
    }
}

