package com.jasonrboyer.mytowergame.views;

import java.awt.Graphics;
import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

import com.jasonrboyer.mytowergame.controllers.MapLevel;
import com.jasonrboyer.mytowergame.models.Base;
import com.jasonrboyer.mytowergame.models.BlastAnimation;
import com.jasonrboyer.mytowergame.models.Enemy;
import com.jasonrboyer.mytowergame.models.Tower;

/**
 * Game view was initially created in late 2015, comments added 2016. This panel
 * sets up the view for when the player is running a game. It controls the
 * display of the map, enemies, towers, base, ButtonPanel and OutputPanel.
 * 
 * @author jasonboyer
 *
 */

public class GameView extends JPanel {

    private int                       levelMap[][];
    private MapLevel                  gameMap;

    final int                         MAP_WIDTH  = 800;
    final int                         MAP_HEIGHT = 400;
    final int                         GRID_SIZE  = 40;

    private ArrayList<Enemy>          monsters;
    private ArrayList<Tower>          towers;
    private ArrayList<BlastAnimation> shots;

    private String                    path       = "images" + File.separator;
    private Image                     grass, dirt, pave, base, mob, tower;
    private Base                      myBase;

    public GameView() {

        /*
         * Setup the data structures to contain any monsters and towers that
         * have been added. BlastAnimations have not yet been implemented.
         */

        monsters = new ArrayList<Enemy>();
        towers = new ArrayList<Tower>();
        // shots=new ArrayList<BlastAnimation>();

        try {
            /*
             * load all of the basic images to be used for the map mobs and
             * tower objects contain their own image.
             * 
             */

            grass = ImageIO.read(new File(path + "Grass.jpg"));
            dirt = ImageIO.read(new File(path + "Dirt.jpg"));
            pave = ImageIO.read(new File(path + "Pavement.jpg"));
            base = ImageIO.read(new File(path + "Base.png"));
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /*
     * The newLevelMap is a 2D array. This is used to create a grid for elements
     * to be placed on. Each grid element indicates what type of tile to draw at
     * that location. These elements are important as they are also used for
     * navigation/pathfinding.
     */
    public void setGameMap(MapLevel newMap, int[][] newLevelMap) {
        gameMap = newMap;
        levelMap = newLevelMap;
    }

    /*
     * Set the enemy list with the current number of enemies for this round.
     */
    public void setEnemies(ArrayList<Enemy> enemies) {
        monsters = enemies;
    }

    /*
     * Set the tower list with each tower that has been added to the map.
     */
    public void setTowers(ArrayList<Tower> defenders) {
        towers = defenders;
    }

    /*
     * Not yet implemented
     */
    public void setWeaponShots(ArrayList<BlastAnimation> firedShots) {
        shots = firedShots;
    }

    /*
     * Set the graphic for the base. This changes as the base becomes damaged.
     */
    public void setBase(Base newBase) {
        myBase = newBase;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        drawMap(g);

    }

    private void drawMap(Graphics g) {

        /*
         * This draws each of the tiles. The cases correspond to the correct
         * tile type to display.
         */
        for (int i = 0; i < MAP_WIDTH; i += GRID_SIZE) {
            for (int j = 0; j < MAP_HEIGHT; j += GRID_SIZE) {
                switch (levelMap[j / GRID_SIZE][i / GRID_SIZE]) {
                    case 0:
                    case 88:
                        g.drawImage(grass, i, j, null);
                        break;
                    case 10:
                    case 11:
                    case 12:
                    case 13:
                    case 14:

                        g.drawImage(dirt, i, j, null);
                        break;
                    case 20:
                    case 21:
                    case 22:
                    case 23:
                    case 24:
                        g.drawImage(pave, i, j, null);
                        break;
                }

            }
        }

        g.drawImage(myBase.getImage(), myBase.getYLocation(),
                myBase.getXLocation(), null);

        /*
         * if all monsters have not yet been destroyed, get each one and draw it
         * on the map.
         */
        if (monsters.size() > 0) {
            for (int i = 0; i < monsters.size(); i++) {
                Enemy newMonster = monsters.get(i);
                mob = newMonster.getImage();
                g.drawImage(newMonster.getImage(), newMonster.getXLocation(),
                        newMonster.getYLocation(), null);
            }
        }

        /*
         * If any towers have been deployed, get them and draw them on the map
         */
        if (towers.size() > 0) {
            for (int i = 0; i < towers.size(); i++) {
                Tower newTower = towers.get(i);
                tower = newTower.getImage();
                g.drawImage(tower, newTower.getXLocation(),
                        newTower.getYLocation(), null);
            }
        }

        /*
         * If there are any weapons fired that have a shot animation, draw them
         */
        /*
         * if (shots.size() > 0) { Graphics2D g2 = (Graphics2D) g; while
         * (!shots.isEmpty()) { BlastAnimation shotAnimation = shots.remove(0);
         * }
         * 
         * }
         */

    }

}
