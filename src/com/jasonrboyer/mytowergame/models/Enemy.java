package com.jasonrboyer.mytowergame.models;

import java.awt.Image;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

/**
 * This class was created in late 2015, comments added in September of 2016. It
 * represents the basic enemy class and can be expanded to implement other types
 * of enemies.
 * 
 * 
 * @author jasonboyer
 *
 */
public class Enemy {
    private int    health;
    private int    speed;
    private int    xLocation;
    private int    yLocation;
    private int    level;
    private int    speedModifier;
    private int    value;
    private int    damage;
    Image          enemyUp;
    Image          enemyLeft;
    private Image  enemyDown;
    Image          enemyRight;
    private String path = "images" + File.separator;
    String         sep  = File.separator;

    /*
     * The direction is important for pathfinding so that the enemies do not
     * turn around and go the wrong direction.
     */
    private enum Direction {
        UP, DOWN, LEFT, RIGHT
    }

    private Direction course;
    private Direction lastCourse;

    public Enemy() {
        health = 1000;
        speed = 1;
        xLocation = 0;
        yLocation = 0;
        level = 1;
        speedModifier = 1;
        value = 12;
        course = Direction.RIGHT;
        damage = 10;
        try {
            enemyUp = ImageIO.read(new File(path + "EnemyUp.png"));
            enemyLeft = ImageIO.read(new File(path + "EnemyLeft.png"));
            enemyRight = ImageIO.read(new File(path + "EnemyRight.png"));
            enemyDown = ImageIO.read(new File(path + "EnemyDown.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    };

    public Enemy(int wave) {
        this();
        health += wave * 100;
    };

    public Image getImage() {
        Image returnImage = enemyRight;
        switch (course) {
            case RIGHT:
                returnImage = enemyRight;
                break;
            case LEFT:
                returnImage = enemyLeft;
                break;
            case UP:
                returnImage = enemyUp;
                break;
            case DOWN:
                returnImage = enemyDown;
                break;

        }
        return returnImage;
    }

    public int getMoney() {
        return value;
    }

    public void takeDamage(int damage) {
        health -= damage;
    }

    public int getHealth() {
        return health;
    }

    public int getCenterX() {
        return xLocation + 20;
    }

    public int getCenterY() {
        return yLocation + 20;
    }

    public void setXLocation(int location) {
        xLocation = location;
    }

    public void setYLocation(int location) {
        yLocation = location;
    }

    public int getXLocation() {
        return xLocation;
    }

    public int getYLocation() {
        return yLocation;
    }

    public int getDamage() {
        return damage;
    }

    public void setLocation(int x, int y) {
        xLocation = x;
        yLocation = y;
    }

    public void move(int turnTile, int threshold) {
        setDirection(turnTile, threshold);
        switch (course) {
            case UP:
                yLocation -= speed * speedModifier;
                break;
            case LEFT:
                xLocation -= speed * speedModifier;
                break;
            case RIGHT:
                xLocation += speed * speedModifier;
                break;
            case DOWN:
                yLocation += speed * speedModifier;
                break;
        }

    }

    private void setDirection(int turnTile, int threshold) {
        int block = turnTile % 10;
        if (block == 1 && course == Direction.RIGHT) {
            if (xLocation >= threshold) {
                xLocation = threshold;
                course = Direction.UP;
            }
        }
        else if (block == 1 && course == Direction.LEFT) {
            if (xLocation <= threshold) {
                xLocation = threshold;
                course = Direction.UP;
            }
        }
        else if (block == 2 && course == Direction.UP) {
            if (yLocation <= threshold) {
                yLocation = threshold;
                course = Direction.RIGHT;
            }
        }
        else if (block == 2 && course == Direction.DOWN) {
            if (yLocation >= threshold) {
                yLocation = threshold;
                course = Direction.RIGHT;
            }
        }
        else if (block == 3 && course == Direction.RIGHT) {
            if (xLocation >= threshold) {
                xLocation = threshold;
                course = Direction.DOWN;
            }
        }
        else if (block == 3 && course == Direction.LEFT) {
            if (xLocation <= threshold) {
                xLocation = threshold;
                course = Direction.DOWN;
            }
        }
        else if (block == 4 && course == Direction.UP) {
            if (yLocation <= threshold) {
                yLocation = threshold;
                course = Direction.LEFT;
            }
        }
        else if (block == 4 && course == Direction.DOWN) {
            if (yLocation >= threshold) {
                yLocation = threshold;
                course = Direction.LEFT;
            }
        }
    }

}
