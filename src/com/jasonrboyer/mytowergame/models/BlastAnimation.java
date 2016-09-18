package com.jasonrboyer.mytowergame.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 * This Class was written in late 2015. Commented in September of 2016. This
 * class is for weapons that have a specific animation(such as missles or laser
 * bolts) it will be used to create the animation.
 * 
 * @author jasonboyer
 *
 */
public class BlastAnimation {
    private int startX, startY;
    private int endX, endY;

    public BlastAnimation() {
        startX = 0;
        startY = 0;
        endX = 0;
        endY = 0;
    }

    /*
     * Initialize the blast with a start and end x and y. This will be the
     * length of something like a laser bolt, but the end x and y would be
     * irrelevant for a missle jpg that is just moving along.
     */
    public BlastAnimation(int startX, int startY, int endX, int endY) {
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }

    /* Fire the weapon, play any sound */
    public void fire() {
        String weaponFire = "res/GunFire.wav";
        try {
            InputStream in = new FileInputStream(weaponFire);
            AudioStream audioStream = new AudioStream(in);
            AudioPlayer.player.start(audioStream);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        catch (IOException e) {
            e.printStackTrace();
        }

    }

    public int getStartX() {
        return startX;
    }

    public void setStartX(int startX) {
        this.startX = startX;
    }

    public int getStartY() {
        return startY;
    }

    public void setStartY(int startY) {
        this.startY = startY;
    }

    public int getEndX() {
        return endX;
    }

    public void setEndX(int endX) {
        this.endX = endX;
    }

    public int getEndY() {
        return endY;
    }

    public void setEndY(int endY) {
        this.endY = endY;
    }

}
