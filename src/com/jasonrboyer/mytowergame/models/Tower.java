package com.jasonrboyer.mytowergame.models;

import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * Class written in late 2015, comments written in September of 2016. This class
 * describes the basic tower, it can be extended to other tower types.
 * 
 * @author jasonboyer
 *
 */
public class Tower {
    private int            range, damage, speed, xLocation, yLocation, level,
            speedModifier, damageModifier, row, col, towerType, cost,
            shootCounter;
    private BufferedImage  towerPic;
    private final int      ROW_WIDTH     = 40, COL_WIDTH = 40;
    private String         path          = "images" + File.separator;
    private String         towerImageStatus;
    private boolean        shootToggleOn = false, currentlyFiring = false;
    private BlastAnimation pewpew        = new BlastAnimation();

    public Tower() {
        damage = 1;
        speed = 1;
        level = 1;
        range = 100;
        cost = 100;
        speedModifier = 1;
        damageModifier = 1;
        towerType = 88;
        shootCounter = 0;
        try {
            towerPic =
                    ImageIO.read(new File(path + "BasicTowerSpriteSheet.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int newCost) {
        cost = newCost;
    }

    public int getType() {
        return towerType;
    }

    public void setLocation(int xLocation, int yLocation, int tileSize) {
        this.xLocation = xLocation;
        this.yLocation = yLocation;
    }

    public int getCenterX() {
        return xLocation + 20;
    }

    public int getCenterY() {
        return yLocation + 20;
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

    public int getDamage() {
        return damage;
    }

    public boolean isFiring() {
        return currentlyFiring;
    }

    public void fireWeapon() {
        if (isFiring()) {

        }
    }

    /*
     * This method controls what image is used while the cannon is firing. It
     * selects the appropriate area on the sprite sheet and toggles the flash or
     * non flash version to create the illusion of firing.
     */
    public void setImage(double angle, boolean firing) {
        currentlyFiring = firing;
        if (angle >= 20 && angle < 85) {
            row = 1;
        }
        else if (angle < 105 && angle >= 85) {
            row = 2;
        }
        else if (angle >= 105 && angle < 165) {
            row = 3;
        }
        else if (angle >= 165 && angle < 190) {
            row = 4;
        }
        else if (angle >= 190 && angle < 245) {
            row = 5;
        }
        else if (angle >= 245 && angle < 260) {
            row = 6;
        }
        else if (angle >= 260 && angle < 340) {
            row = 7;
        }
        else if (angle >= 340 || angle < 20) {
            row = 0;
        }
        if (firing) {
            shootCounter = shootCounter % 50;
            if (shootCounter == 0) {
                if (shootToggleOn) {
                    shootToggleOn = false;
                }
                else {
                    shootToggleOn = true;
                    pewpew.fire();
                }
            }
            shootCounter++;
        }
        else {
            shootToggleOn = false;
        }
        if (shootToggleOn) {
            col = 1;
        }
        else {
            col = 0;
        }
    }

    public Image getImage() {
        return towerPic.getSubimage(col * 40, row * 40, 40, 40);
    }

    /*
     * Method to determine if the target is in range of the tower.
     */
    public boolean inRange(int xCoord, int yCoord) {
        double x2 = Math.pow((xCoord - xLocation), 2);
        double y2 = Math.pow((yCoord - yLocation), 2);
        double distance = Math.sqrt((y2 + x2));
        boolean near = false;
        if (distance < range) {
            near = true;
        }
        return near;
    }

}
