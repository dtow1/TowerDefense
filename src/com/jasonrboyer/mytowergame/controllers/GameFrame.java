package com.jasonrboyer.mytowergame.controllers;

import java.awt.BorderLayout;
import java.awt.Cursor;
import java.awt.Dimension;
import java.awt.Point;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.jasonrboyer.mytowergame.models.Base;
import com.jasonrboyer.mytowergame.models.BlastAnimation;
import com.jasonrboyer.mytowergame.models.Enemy;
import com.jasonrboyer.mytowergame.models.Tower;
import com.jasonrboyer.mytowergame.views.ButtonPanel;
import com.jasonrboyer.mytowergame.views.GameView;
import com.jasonrboyer.mytowergame.views.OutputPanel;

/**
 * This is the main game logic. The game is run from within this Frame.
 * 
 * @author Jason Boyer
 * @date 11/14/2015
 *
 */
public class GameFrame extends JFrame {

    private int                       levelMap[][];
    private MapLevel                  gameMap;
    private ArrayList<Enemy>          monsters;
    private ArrayList<Tower>          towers;
    private ArrayList<BlastAnimation> shots;
    private ArrayList<Tower>          shootingTowers;
    private MobGenerator              mobGen;
    private Base                      myBase           = new Base();
    private boolean                   paused           = true, newWave = true,
            addingBasicTower = false;
    private String                    path             =
            "images" + File.separator;
    private ButtonPanel               buttons;
    private JPanel                    text             = new JPanel();
    private JLabel                    money;
    private Timer                     mainTimer, mobTimer;
    private OutputPanel               output;
    private BlastAnimation            shotAnimation1   = new BlastAnimation(),
            shotAnimation2 = new BlastAnimation();
    private GameView                  playArea;
    private int                       firstFiringTower = -1,
            secondFiringTower = -1, wave = 1, lastSize = 0, delay2 = 0,
            pause = 0;
    private final int                 PLAY_SIZE_X      = 40, PLAY_SIZE_Y = 800,
            BUTTON_PANEL_X = 300, BUTTON_PANEL_Y = 300, WINDOW_SIZE_X = 1030,
            WINDOW_SIZE_Y = 700;
    private final int                 Y_OFFSET         = 23, X_OFFSET = 3,
            TILE_SIZE;

    GameFrame() {
        this(1, 1);
    }

    /**
     * @param level
     * @param wave
     */
    GameFrame(int level, int wave) {
        // Setup space for the map and items to populate it
        gameMap = new MapLevel();
        levelMap = gameMap.getMap();
        mobGen = new MobGenerator();
        monsters = new ArrayList<Enemy>();
        towers = new ArrayList<Tower>();
        shots = new ArrayList<BlastAnimation>();
        shootingTowers = new ArrayList<Tower>();
        TILE_SIZE = gameMap.getTileSize();

        // Set up the frame layout
        new BorderLayout();

        playArea = new GameView();
        playArea.setSize(PLAY_SIZE_X, PLAY_SIZE_Y);
        playArea.setGameMap(gameMap, levelMap);
        playArea.setBase(myBase);
        add(playArea, BorderLayout.CENTER);

        output = new OutputPanel();
        buttons = new ButtonPanel();
        buttons.setSize(BUTTON_PANEL_X, BUTTON_PANEL_Y);
        buttons.setMaximumSize(new Dimension(60, 60));
        add(output, BorderLayout.SOUTH);
        add(buttons, BorderLayout.EAST);

        setSize(WINDOW_SIZE_X, WINDOW_SIZE_Y);

        setTitle("Playing Tower Game");

        setVisible(true);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setLocationRelativeTo(null);

        /*
         * Action listener for the buttons
         * 
         * 
         */

        ActionListener buttonListener = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                String buttonPressed = ((JButton) e.getSource()).getName();
                switch (buttonPressed) {
                    case "Play":
                        mainTimer.start();
                        if (mobGen.hasNext()) {
                            mobTimer.start();
                        }
                        paused = false;
                        break;
                    case "Pause":
                        System.out.println("paused");
                        mainTimer.stop();
                        mobTimer.stop();
                        paused = true;
                        break;
                    case "Basic":
                        /* Set the cursor to the new tower icon */
                        addingBasicTower = true;
                        try {
                            BufferedImage buffered = ImageIO
                                    .read(new File(path + "TowerDown.png"));
                            Cursor cursor = Toolkit.getDefaultToolkit()
                                    .createCustomCursor(buffered,
                                            new Point(0, 0), "tower");
                            setCursor(cursor);
                        }
                        catch (IOException e1) {
                            e1.printStackTrace();
                        }
                        break;
                }
            }

        };

        buttons.addActionListener(buttonListener);

        this.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent arg0) {
                int xLoc = arg0.getX() + X_OFFSET;
                int yLoc = arg0.getY() - Y_OFFSET;
                int roundedX = xLoc % TILE_SIZE;
                System.out.println("roundedX: " + roundedX + " arg0.getX: "
                        + arg0.getX() + " xLoc: " + xLoc);
                int roundedY = yLoc % TILE_SIZE;
                Tower tempTower = null;
                if (addingBasicTower) {

                    // create a new tower to add
                    tempTower = new Tower();

                }

                if (tempTower.getCost() <= myBase.getMoney()) {
                    if (gameMap.canAdd(xLoc, yLoc)) {
                        tempTower.setLocation(xLoc - roundedX, yLoc - roundedY,
                                TILE_SIZE);
                        System.out.println("xloc: " + xLoc + " roundedX "
                                + roundedX + " yLoc: " + yLoc + " roundedY "
                                + roundedY);
                        towers.add(tempTower);
                        gameMap.addTower(xLoc, yLoc, tempTower.getType());
                        myBase.takeMoney(tempTower.getCost());

                    }
                }

                if (addingBasicTower) {
                    addingBasicTower = false;
                }
                setCursor(Cursor.getDefaultCursor());

            }

        });

        gameStart();
    }

    public void gameStart() {
        Thread gameThread = new Thread() {
            public void run() {

                Tower testTower = new Tower();
                towers.add(testTower);
                testTower = new Tower();

                myBase.setXLocation(gameMap.getBaseX());
                myBase.setYLocation(gameMap.getBaseY());

                mainTimer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        gameUpdate();
                        playArea.setEnemies(monsters);
                        playArea.setTowers(towers);
                        playArea.setWeaponShots(shots);
                        playArea.repaint();
                        if (myBase.getHealth() > 0 && !paused) {
                            mainTimer.restart();
                            mobTimer.start();

                        }
                        else if (myBase.getHealth() <= 0) {
                            mainTimer.stop();
                            if (mobTimer.isRunning())
                                mobTimer.stop();

                            // Run Game over logic
                        }
                        if (!mobGen.hasNext() && !paused) {
                            mobTimer.stop();
                        }
                    }
                });

                mainTimer.setInitialDelay(10);
                mobTimer = new Timer(1000, new ActionListener() {
                    public void actionPerformed(ActionEvent e) {
                        Enemy testMob = mobGen.getNext();
                        testMob.setLocation(gameMap.getStartX(),
                                gameMap.getStartY());
                        monsters.add(testMob);

                    }
                });
                mobTimer.setInitialDelay(10);
                if (!paused) {
                    mainTimer.start();
                    mobTimer.start();
                }

            }
        };
        repaint();
        gameThread.run();

    }

    public void gameUpdate() {
        if (newWave) {
            mobGen.generateWave(10, wave);
            newWave = false;
            output.setWave(wave);
        }
        output.updateHealthandMoney(myBase.getMoney(), myBase.getHealth());
        if (monsters.size() > 0 && !paused) {
            for (int monsterCount = 0; monsterCount < monsters
                    .size(); monsterCount++) {
                int xCoord = monsters.get(monsterCount).getXLocation();
                int yCoord = monsters.get(monsterCount).getYLocation();
                int tile = findTile(xCoord, yCoord);

                // Check to see if the monster has reached the base. If yes
                // damage the base and remove the monster
                if (monsters.get(monsterCount).getXLocation() == myBase
                        .getYLocation()
                        && monsters.get(monsterCount).getYLocation() == myBase
                                .getXLocation()) {
                    myBase.takeDamage(monsters.get(monsterCount).getDamage());
                    monsters.remove(monsterCount);
                }
                else {
                    // move the monsters
                    monsters.get(monsterCount).move(tile,
                            findThreshold(xCoord, yCoord, tile));
                    // process each tower(check for in range monsters, fire.
                    for (int i = 0; i < towers.size(); i++) {
                        boolean foundTarget = false;
                        // Cycle through the monster list to see if any are in
                        // range. The first monster that is in range gets shot
                        for (int j = 0; j < monsters.size()
                                && !foundTarget; j++) {
                            Tower currentTower = towers.get(i);
                            Enemy currentEnemy = monsters.get(j);
                            if (currentTower.inRange(
                                    currentEnemy.getXLocation(),
                                    currentEnemy.getYLocation())) {
                                // monsters.get(j).takeDamage(currentTower.getDamage());
                                currentEnemy
                                        .takeDamage(currentTower.getDamage());
                                foundTarget = true;
                                int towerX = currentTower.getCenterX(),
                                        towerY = currentTower.getCenterY(),
                                        monsterX = currentEnemy.getCenterX(),
                                        monsterY = currentEnemy.getCenterY();
                                currentTower.setImage(getAngle(towerX, towerY,
                                        monsterX, monsterY), true);
                                shots.add(new BlastAnimation(monsterX, monsterY,
                                        towerX, towerY));
                                currentTower.fireWeapon();
                            }
                            else {
                                currentTower.setImage(0, false);
                            }
                        }
                    }

                    // remove any monsters that have been killed
                    if (monsters.get(monsterCount).getHealth() < 0) {
                        myBase.addMoney(monsters.get(monsterCount).getMoney());

                        monsters.remove(monsterCount);

                        /*
                         * if(monsters.size()<=0) { newWave=true; wave++; }
                         */
                    }
                }

            }

            // if all monsters have died setup a new wave
            if (monsters.size() <= 0) {
                newWave = true;
                wave++;
            }
        }
    }

    /**
     * This method calculates the angle of the monster with respect to the
     * tower. It uses a vertical line through the the center of the tower.
     * 
     * @param towerX
     *            the xCoordinate of the tower
     * @param towerY
     *            the yCoordinate of the tower
     * @param monsterX
     *            the xCoordinate of the monster
     * @param monsterY
     *            the yCoordinate of the monster
     * @return the angle that was calculated with respect to the vertical y axis
     *         visually above the center of the tower(smaller y)
     */
    private double getAngle(int towerX, int towerY, int monsterX,
            int monsterY) {
        /*
         * The towerY-10 is just an arbitrary offset to a point that is in
         * vertical alignment with the tower
         */
        double d2 = Math.sqrt(Math.pow(towerX - towerX, 2)
                + Math.pow(towerY - (towerY - 10), 2));
        double d3 = Math.sqrt(Math.pow(towerX - monsterX, 2)
                + Math.pow(towerY - (monsterY), 2));
        double d1 = Math.sqrt(Math.pow(towerX - monsterX, 2)
                + Math.pow((towerY - 10) - (monsterY), 2));

        int normalizeAngle;
        double angle =
                Math.acos((Math.pow(d1, 2) + Math.pow(d2, 2) - Math.pow(d3, 2))
                        / (2 * d1 * d2)) * 180 / Math.PI;
        if (monsterX > towerX) {
            angle = 180 - angle;
        }
        else if (monsterX < towerX) {
            angle += 180;
        }

        if ((monsterX == towerX) && monsterY < towerY) {
            angle = 0;
        }
        if ((monsterX == towerX) && monsterY > towerY) {
            angle = 180;
        }
        return angle;

    }

    /**
     * The game maps are based on a tile system. This method finds the tile that
     * a coordinate belongs to.
     * 
     * @param xCoord
     *            xcoord of the object
     * @param yCoord
     *            ycoord of the object
     * @return integer value that indicates what type of tile is at the location
     */
    private int findTile(int xCoord, int yCoord) {
        int j = xCoord / gameMap.getTileSize();
        int i = yCoord / gameMap.getTileSize();
        return levelMap[i][j];
    }

    /**
     * Thresholds are used to determine when a monster needs to adjust it's
     * path. Certain types of tiles indicate that a mob will need to change
     * direction to N,E,S, or W. When a mob enters a tile like this, it will
     * change direction once it crosses the threshold. This is used to keep the
     * sprites aligned with the map tiles.
     * 
     * @param xCoord
     *            of the Mob
     * @param yCoord
     *            of the Mob
     * @param tile
     *            tile type
     * @return the threshold when the mob needs to turn
     */
    private int findThreshold(int xCoord, int yCoord, int tile) {
        int column = xCoord / TILE_SIZE;
        int row = yCoord / TILE_SIZE;
        int threshold = -99;
        switch (tile % 10) {
            case 1:
            case 3:
                threshold = (column * TILE_SIZE);
                break;
            case 2:
            case 4:
                threshold = (row * TILE_SIZE);
                break;
        }
        int squareType = levelMap[row][column];
        return threshold;

    }

    public void killTimers() {
        mainTimer.stop();
        mobTimer.stop();
    }

}
