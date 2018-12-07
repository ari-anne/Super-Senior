package com.cpsc.supersenior.tools;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.audio.Music;

public class GameMusic {

    private static float MUSIC_VOLUME = 0.1f;
    private static float SOUND_VOLUME = 0.1f;

    private static Music tune;

    private static Sound buzzSound;
    private static Sound clickSound;
    private static Sound coinSound;
    private static Sound endGameSound;
    private static Sound jumpSound;

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

        tune.setLooping(true);
        tune.setVolume(MUSIC_VOLUME);
    }

    public void playMusic() {
        if(musicOn) {
            tune.play();
        }
    }

    public void muteMusic() {
        tune.pause();
    }

    public void playBuzzSound(){
        if(soundOn) {
            buzzSound.play(SOUND_VOLUME);
        }
    }

    public void playClickSound(){
        if(soundOn) {
            clickSound.play(SOUND_VOLUME);
        }
    }

    public void playCoinSound() {
        if (soundOn) {
            coinSound.play(SOUND_VOLUME);
        }
    }

    public void playEndGameSound() {
        if (soundOn) {
            endGameSound.play(SOUND_VOLUME);
        }
    }

    public void playJumpSound() {
        if (soundOn) {
            jumpSound.play(SOUND_VOLUME);
        }
    }

}
