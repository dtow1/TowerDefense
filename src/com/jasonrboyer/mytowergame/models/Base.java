package com.jasonrboyer.mytowergame.models;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class is used to represent the base. This is equivalent to the player
 * and stores money and health.
 * 
 * @author Jason Boyer
 * @date 11/14/2015
 */
public class Base {
    private int    health;
    private int    xLocation;
    private int    yLocation;
    private Image  basePic;
    private int    money;
    private String path = "images" + File.separator;

    /*
     * Load the image to use for the base, set the initial money to 200, and set
     * the initial health to 100
     */
    public Base() {

        try {
            basePic = ImageIO.read(new File(path + "Base.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
        money = 200;
        health = 100;

    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int newValue) {
        money = newValue;
    }

    public int addMoney(int toAdd) {
        money += toAdd;
        return money;
    }

    public int takeMoney(int toRemove) {
        money -= toRemove;
        return money;
    }

    public void takeDamage(int damage) {
        health -= damage;
        if (health <= 50) {
            try {
                basePic = ImageIO.read(new File(path + "damagedBase.png"));
            }
            catch (IOException e) {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
        }
    }

    public int getHealth() {
        return health;
    }

    public void setLocation(int xLocation, int yLocation) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public void setXLocation(int x) {
        xLocation = x;
    }

    public void setYLocation(int y) {
        yLocation = y;
    }

    public Image getImage() {
        return basePic;
    }
}
