package com.clk.ailatrieuphujava;

import android.media.MediaPlayer;

public class MediaManeger {
    private static MediaManeger instance;
    private MediaPlayer playerBG;
    private MediaPlayer playerGame;
    private boolean isPauseGame;
    private boolean isPauseBG;

    public MediaManeger() {
    }

    public static MediaManeger getInstance() {
        if (instance == null) {
            instance = new MediaManeger();
        }
        return instance;
    }

    public void playBG(int soog) {
        if (playerBG != null) {
            playerBG.reset();
        }
        playerBG = MediaPlayer.create(App.getInstance(), soog);
//        playerBG.setAudioStreamType(AudioAttributes.USAGE_MEDIA);
        playerBG.setLooping(true);
        playerBG.start();
    }

    public void playGame(int soog, MediaPlayer.OnCompletionListener event) {
        if (playerGame != null) {
            playerGame.reset();
        }
        playerGame = MediaPlayer.create(App.getInstance(), soog);
//        playerGame.setAudioStreamType(AudioAttributes.USAGE_MEDIA);
        playerGame.setOnCompletionListener(event);
        playerGame.start();
    }

    public void pauseSong() {
        if (playerBG != null && playerBG.isPlaying()) {
            playerBG.pause();
            isPauseBG = true;
        }
        if (playerGame != null && playerGame.isPlaying()) {
            playerGame.pause();
            isPauseGame = true;
        }
    }

    public void playSong() {
        if (playerBG != null && isPauseBG) {
            isPauseBG = false;
            playerBG.start();
        }
        if (playerGame != null && isPauseGame) {
            isPauseGame = false;
            playerGame.start();
        }
    }

    public void stopBG() {
        if (playerBG != null) {
            playerBG.reset();
        }
    }

    public void stopGame() {
        if (playerGame != null) {
            playerGame.reset();
        }
    }
}
