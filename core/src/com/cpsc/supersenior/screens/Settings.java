package com.cpsc.supersenior.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.Stage;
import com.badlogic.gdx.scenes.scene2d.ui.*;
import com.badlogic.gdx.scenes.scene2d.utils.ChangeListener;
import com.cpsc.supersenior.SuperSenior;

public class Settings implements Screen {

    final SuperSenior game;

    Stage stage;
    Table table;
    Skin skin;
    Button back;
    Button music;
    Button sound;
    Label musicTxt;
    Label soundTxt;
    TextButton settings;

    float btnWidth, btnHeight;
    int padding;

    public Settings (SuperSenior game){
        this.game = game;
    }

    @Override
    public void show() {
        stage = new Stage();
        Gdx.input.setInputProcessor(stage);

        table = new Table();
        skin = new Skin(Gdx.files.internal("buttons/button.json"));
        back = new Button(skin, "arrow-left");
        music = new Button(skin, "music");
        sound = new Button(skin, "sound");
        musicTxt = new Label ("Music: ON ",skin);
        soundTxt = new Label ("Sound: ON ", skin);
        settings = new TextButton("  Settings  ", skin, "header");

        btnWidth = 150;
        btnHeight = 150;
        padding = 50;

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                game.setScreen(new MainMenu(game));
            }
        });
        music.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // toggle music
                if (music.isChecked()) {
                    musicTxt.setText("Music: OFF");
                }
                else {
                    musicTxt.setText("Music: ON ");
                }
            }
        });
        sound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                // toggle sound
                if (sound.isChecked()) {
                    soundTxt.setText("Sound: OFF");
                }
                else {
                    soundTxt.setText("Sound: ON ");
                }
            }
        });

        stage.addActor(table);
        stage.addActor(back);
        stage.addActor(music);
        stage.addActor(sound);
        stage.addActor(musicTxt);
        stage.addActor(soundTxt);
        stage.addActor(settings);

        // table.setDebug(true);
        table.setFillParent(true);
        table.add(back).width(btnWidth).height(btnHeight).padLeft(padding).padTop(padding);
        table.add(settings).colspan(3).height(200).expandX().padRight(padding).padTop(padding);
        table.row().pad(padding).padTop(150);
        table.add(musicTxt).colspan(3).right();
        table.add(music).left().width(btnWidth).height(btnHeight);
        table.row().pad(padding).padBottom(150);
        table.add(soundTxt).colspan(3).right();
        table.add(sound).left().width(btnWidth).height(btnHeight);
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
        Gdx.input.setInputProcessor(null);
    }
}
