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
        musicTxt = new Label("Music: On", skin);
        soundTxt = new Label("Sound: On", skin);
        settings = new TextButton("  Settings  ", skin, "header");

        if (!SuperSenior.gameMusic.musicOn) {
            musicTxt.setText("Music: Off");
            music.setChecked(true);
        }
        if (!SuperSenior.gameMusic.soundOn) {
            soundTxt.setText("Sound: Off");
            sound.setChecked(true);
        }

        btnWidth = 150;
        btnHeight = 150;
        padding = 50;

        back.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                SuperSenior.gameMusic.playClickSound();
                game.setScreen(new MainMenu(game));
            }
        });
        music.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (SuperSenior.gameMusic.musicOn) {
                    musicTxt.setText("Music: Off");
                    music.setChecked(true);
                    SuperSenior.gameMusic.musicOn = false;
                    SuperSenior.gameMusic.muteMusic();
                    SuperSenior.gameMusic.playClickSound();
                }
                else {
                    musicTxt.setText("Music: On");
                    music.setChecked(false);
                    SuperSenior.gameMusic.musicOn = true;
                    SuperSenior.gameMusic.playMusic();
                    SuperSenior.gameMusic.playClickSound();
                }
            }
        });
        sound.addListener(new ChangeListener() {
            @Override
            public void changed(ChangeEvent event, Actor actor) {
                if (SuperSenior.gameMusic.soundOn) {
                    soundTxt.setText("Sound: Off");
                    sound.setChecked(true);
                    SuperSenior.gameMusic.soundOn = false;
                    SuperSenior.gameMusic.playClickSound();
                }
                else {
                    soundTxt.setText("Sound: On");
                    sound.setChecked(false);
                    SuperSenior.gameMusic.soundOn = true;
                    SuperSenior.gameMusic.playClickSound();
                }
            }
        });

        stage.addActor(SuperSenior.background);
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
