package com.cpsc.supersenior;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.viewport.StretchViewport;
import com.cpsc.supersenior.entities.Background;
import com.cpsc.supersenior.screens.EndGame;
import com.cpsc.supersenior.screens.GameScreen;


public class SuperSenior extends Game {

	public static final int WIDTH = 800;
	public static final int HEIGHT = 600;


	public SpriteBatch batch;
	public static Background background;

	public static OrthographicCamera cam;
	private StretchViewport viewport;


	//Variables for sound and music
	public static float MUSICVOLUME = 0.075f;
	public static float SOUNDVOLUME = 0.075f;
	public static Music tune;

	//Five different sounds
	public static Sound buzzSound;
	public static Sound clickSound;
	public static Sound coinSound;
	public static Sound endGameSound;
	public static Sound jumpSound;

	//Variable for turning sounds off
	private static boolean allSoundOn = true;

	@Override
	public void create() {

		//load music
		tune = Gdx.audio.newMusic(Gdx.files.internal("game_music_1.mp3"));

		//load sounds
		buzzSound = Gdx.audio.newSound(Gdx.files.internal("buzz_sound.mp3"));
		clickSound = Gdx.audio.newSound(Gdx.files.internal("click_sound.mp3"));
		coinSound = Gdx.audio.newSound(Gdx.files.internal("coin_sound.mp3"));
		endGameSound = Gdx.audio.newSound(Gdx.files.internal("end_game_sound.mp3"));
		jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump_sound.mp3"));

        //call to play background music
        this.playMusic();


		batch = new SpriteBatch();
		cam = new OrthographicCamera();
		viewport = new StretchViewport(WIDTH, HEIGHT, cam);

		viewport.apply();
		cam.position.set(WIDTH / 2, HEIGHT / 2, 0);
		cam.update();

		background = new Background();
		this.setScreen(new EndGame(this));



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



	//Sound Methods
    public static void playMusic(){

	    //play music
        tune.setLooping(true);
        tune.setVolume(MUSICVOLUME);

        if(allSoundOn) {
            tune.play();
        }
        else
        {
            tune.pause();
        }
    }

	public static void muteMusic() {
		tune.pause();

	}

	public static void normalizeMusic() {
		if(allSoundOn) {
		    playMusic();
		}
	}

	public static void getBuzzSound(){
		if(allSoundOn) {
			buzzSound.play(SOUNDVOLUME);
		}
	}

	public static void getClickSound(){
		if(allSoundOn) {
			clickSound.play(SOUNDVOLUME);
		}
	}

	public static void getCoinSound() {
		if (allSoundOn) {
			coinSound.play(SOUNDVOLUME);
		}
	}
	public static void getEndGameSound() {
		if (allSoundOn) {
			endGameSound.play(SOUNDVOLUME);
		}
	}
	public static void getJumpSound() {
		if (allSoundOn) {
			jumpSound.play(SOUNDVOLUME);
		}
	}
	public static void turnSoundOff()
	{
		allSoundOn = false;
	}

	public static void turnSoundOn()
	{
		allSoundOn = true;
	}


}
