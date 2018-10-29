package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpsc.supersenior.SuperSenior;

public class MainMenu implements Screen{

    // Labels       https://libgdx.info/basic-label/
    // Skins        https://github.com/libgdx/libgdx/wiki/Skin#skin-json
    // Tables       https://github.com/EsotericSoftware/tablelayout
    // ButtonSkin   https://stackoverflow.com/questions/21488311/how-to-create-a-button-in-libgdx

    final SuperSenior game;

    Stage stage;
    Table table;
    Skin skin;
    TextButton title;
    Button play;
    Button high_scores;
    Button settings;

    int padding = 100;


    public MainMenu (SuperSenior game){
        this.game = game;
    }


    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        title = new TextButton("  Super Senior  ", skin, "title");
        play = new Button(skin, "play");
        high_scores = new Button(skin, "leaderboard");
        settings = new Button(skin, "settings");

        game.scrollingBackground.setFixedSpeed(true);

        play.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new GameScreen(game));
            }
        });
        high_scores.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new HighScores(game));
            }
        });
        settings.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new Settings(game));
            }
        });

        stage.addActor(table);
        stage.addActor(title);
        stage.addActor(play);
        stage.addActor(high_scores);
        stage.addActor(settings);

        table.setFillParent(true);
        table.row().height(500);
        table.add(title).colspan(3).padTop(padding);
        table.row();
        table.add(play).pad(padding);
        table.add(high_scores).pad(padding);
        table.add(settings).pad(padding);
    }


    @Override
    public void render(float delta) {
        Gdx.gl.glClearColor(1, 1, 1, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        game.batch.begin();
        game.scrollingBackground.update(delta);
        game.scrollingBackground.render(game.batch);
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
        game.batch.dispose();
        Gdx.input.setInputProcessor(null);
    }
}
