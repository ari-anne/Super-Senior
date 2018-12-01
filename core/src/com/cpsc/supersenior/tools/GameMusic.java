package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class GameMusic {

    public static float MUSICVOLUME = 0.5f;
    public static float SOUNDVOLUME = 0.5f;

    public static Music tune;

    public static Sound buzzSound;
    public static Sound clickSound;
    public static Sound coinSound;
    public static Sound endGameSound;
    public static Sound jumpSound;

    public boolean soundOn;
    public boolean musicOn;

    public GameMusic() {
        tune = Gdx.audio.newMusic(Gdx.files.internal("game_music_1.mp3"));
        buzzSound = Gdx.audio.newSound(Gdx.files.internal("buzz_sound.mp3"));
        clickSound = Gdx.audio.newSound(Gdx.files.internal("click_sound.mp3"));
        coinSound = Gdx.audio.newSound(Gdx.files.internal("coin_sound.mp3"));
        endGameSound = Gdx.audio.newSound(Gdx.files.internal("end_game_sound.mp3"));
        jumpSound = Gdx.audio.newSound(Gdx.files.internal("jump_sound.mp3"));

        soundOn = true;
        musicOn = true;
    }

    public void playMusic(){
        tune.setLooping(true);
        tune.setVolume(MUSICVOLUME);

        if(soundOn) {
            tune.play();
        }
        else {
            tune.pause();
        }
    }

    public void muteMusic() {
        tune.pause();
    }

    public void normalizeMusic() {
        if(soundOn) {
            playMusic();
        }
    }

    public void getBuzzSound(){
        if(soundOn) {
            buzzSound.play(SOUNDVOLUME);
        }
    }

    public void getClickSound(){
        if(soundOn) {
            clickSound.play(SOUNDVOLUME);
        }
    }

    public void getCoinSound() {
        if (soundOn) {
            coinSound.play(SOUNDVOLUME);
        }
    }

    public void getEndGameSound() {
        if (soundOn) {
            endGameSound.play(SOUNDVOLUME);
        }
    }

    public void getJumpSound() {
        if (soundOn) {
            jumpSound.play(SOUNDVOLUME);
        }
    }

    public void turnSoundOff()
    {
        soundOn = false;
    }

    public void turnSoundOn() {
        soundOn = true;
    }

}
