package com.jasonrboyer.mytowergame.controllers;


/**
 * This class is used to generate game maps. It has a layout of the path for mobs to follow. Location of towers and the location
 * of the base.
 * @author Jason Boyer
 * @date 11/14/2015
 *
 */
public class MapLevel
{
    private int gameMap[][];
    private final int TILE_SIZE = 40;
    private final int ROWS=10;
    private final int COLUMNS=20;
    private final int MAP_WIDTH_PIXELS=COLUMNS*TILE_SIZE;
    private final int MAP_HEIGHT_PIXELS=COLUMNS*TILE_SIZE;
    private int startX = 0;
    private int startY = 5*TILE_SIZE;
    private int baseX = 2*TILE_SIZE;
    private int baseY = 19*TILE_SIZE;
    
    MapLevel(){
        this(1);
    }
    
    MapLevel(int level){
        switch(level) {
            case 1:  setLevelOne();
                     break;
            default: setLevelOne();
                     break;
        }
        
    }
    
    /**Method to return the size of each game tile in pixels.
     * @return size of tiles in pixels
     */
    public int getTileSize() {
        return TILE_SIZE;
    }
    /**
     * Method to get the number of rows in the map
     * @return row count for the map
     */
    public int getRowCount() {
        return ROWS;
    }
    
    /**Method to get the number of Columns in the map
     * @return column count for the map
     */
    public int getColumnCount() {
        return COLUMNS;
    }
    
    /**
     * This method checks to see if the current space is able to accept a tower
     * @param xCoord this is the xcoordinate to be checked
     * @param yCoord this is the ycoordinate to be checked
     * @return true if it is ok to add, false if no add is possible
     */
    public boolean canAdd(int xCoord, int yCoord) {
        int xLoc=TILE_SIZE*(Math.round(xCoord/TILE_SIZE));
        int yLoc=TILE_SIZE*(Math.round(yCoord/TILE_SIZE));
        if(gameMap[yLoc/TILE_SIZE][xLoc/TILE_SIZE]==00) {
            return true;
        }else {
            return false;
        }

    }
    
    /**
     * This method adds a tower to the map so that the map is updated to show no other tower can be added there.
     * @param xCoord x coordinate of the tower
     * @param yCoord y coordinate of the tower
     * @param buildingType integer that represents the type of tower
     */
    public void addTower(int xCoord, int yCoord, int buildingType) {
        if(canAdd(xCoord,yCoord)) {
           int xLoc=TILE_SIZE*(Math.round(xCoord/TILE_SIZE));
           int yLoc=TILE_SIZE*(Math.round(yCoord/TILE_SIZE));
           gameMap[yLoc/TILE_SIZE][xLoc/TILE_SIZE]=buildingType;
        }
        
    }
    /**This method returns the starting x coordinate for any mobs entering the map
     * @return starting x coordinate for mobs entering the map
     */
    public int getStartX() {
        return startX;
    }
    
    /**This method returns the starting y coordinate for any mobs entering the map
     * @return starting y coordinate for mobs entering the map
     */
    public int getStartY() {
        return startY;
    }
    
    /**This method returns the x coordinate for the base
     * @return starting x coordinate for mobs entering the map
     */
    public int getBaseX() {
        return baseX;
    }
    
    /**This method returns the y coordinate for the base
     * @return starting y coordinate for mobs entering the map
     */
    public int getBaseY() {
        return baseY;
    }
    
    /**The map is stored as a 2 dimensional array of integers. The integer values are used to represent the path as well as
     * the objects on the map such as grass, pavement, dirt, towers, etc. It does not represent mobs.
     * @return a 2-D array of integers that represents the current game map.
     */
    public int[][] getMap(){        
        return gameMap;
    }
    

    
    /**
     * Tile numbers are important both for mob navigation as well as animating the squares
     * 00=grass, 10= dirt, 20 = pavement
     * 11 = dirt next is north, 12 = dirt next is east, 13 = dirt next is south, 14 = dirt next is west
     * 21 = pavement next is north, 22 = pavement next is east, 23 = pavement next is south, 24 = next is west
     * 99=base
     */
    private void setLevelOne(){
        gameMap = new int[][]{
                {00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
                {00,12,10,10,13,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00},
                {00,10,00,00,10,00,00,00,00,00,00,00,00,12,10,10,10,10,10,99},
                {00,10,00,00,10,00,00,12,10,13,00,00,00,10,00,00,00,00,00,00},
                {00,10,00,00,12,10,10,11,00,10,00,00,00,10,00,00,00,00,00,00},
                {10,11,00,00,00,00,00,00,00,20,00,00,00,10,00,00,00,00,00,00},
                {00,00,00,23,20,20,20,20,20,24,00,00,00,10,00,00,00,00,00,00},
                {00,00,00,20,00,00,00,00,00,00,00,00,00,10,00,00,00,00,00,00},
                {00,00,00,22,20,20,20,20,20,22,20,20,10,11,00,00,00,00,00,00},
                {00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00,00}          
        };
    }
    

}
