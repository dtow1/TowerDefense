package com.jasonrboyer.mytowergame.views;

import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.Line2D;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;

import com.jasonrboyer.mytowergame.controllers.MapLevel;
import com.jasonrboyer.mytowergame.models.Base;
import com.jasonrboyer.mytowergame.models.BlastAnimation;
import com.jasonrboyer.mytowergame.models.Enemy;
import com.jasonrboyer.mytowergame.models.Tower;

public class GameView extends JPanel
{
    
    
    private int levelMap[][];
    private MapLevel gameMap;
    
    final int MAP_WIDTH = 800;
    final int MAP_HEIGHT = 400;
    final int GRID_SIZE = 40;
    
    private ArrayList<Enemy> monsters;
    private ArrayList<Tower> towers;
    private ArrayList<BlastAnimation> shots;
    
    private String path= "images" + File.separator;
    private Image grass,dirt,pave,base,mob,tower;
    private Base myBase;
    
    public GameView(){

        monsters = new ArrayList<Enemy>();
        towers = new ArrayList<Tower>();
        shots=new ArrayList<BlastAnimation>();
        
        try
        {
            grass = ImageIO.read(new File(path+ "Grass.jpg"));
            dirt = ImageIO.read(new File(path+ "Dirt.jpg"));
            pave = ImageIO.read(new File(path+ "Pavement.jpg"));
            tower = ImageIO.read(new File(path+ "BasicTowerSpriteSheet.png"));
            base = ImageIO.read(new File(path+ "Base.png"));
        } catch (IOException e)
        {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }//ImageIO.read(ResourceLoader.load(path+"Grass.jpg"));//
    }
    public void setGameMap(MapLevel newMap, int[][] newLevelMap) {
        gameMap=newMap;
        levelMap=newLevelMap;
    }
    public void setEnemies(ArrayList<Enemy> enemies){
        monsters=enemies;
    }
    
    public void setTowers(ArrayList<Tower> defenders) {
        towers=defenders;
    }
    
    public void setWeaponShots(ArrayList<BlastAnimation> firedShots) {
        shots=firedShots;
    }
    
    public void setBase(Base newBase) {
        myBase=newBase;
    }
    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        drawMap(g);

    }
    

    private void drawMap(Graphics g) {

        
        
                       
                        
    //Draw Map
        //Image dirt = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Dirt.jpg"));
        //Image grass = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Grass.jpg"));
        //Image pave = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Pavement.jpg"));
        //Image mob = ImageIO.read(getClass().getClassLoader().getResourceAsStream("EnemeyRight.png"));
        //Image tower = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Tower.jpg"));
        //Image base = ImageIO.read(getClass().getClassLoader().getResourceAsStream("Base.jpg"));
       // Image dirt = ImageIO.read(new File(path+ "Dirt.jpg"));//ImageIO.read(ResourceLoader.load(path+"Dirt.jpg"));//ImageIO.read(getClass().getResourceAsStream(path + "Dirt.jpg"));//;
        //Image grass = ImageIO.read(new File(path+ "Grass.jpg"));//ImageIO.read(ResourceLoader.load(path+"Grass.jpg"));//
        //Image pave = ImageIO.read(new File(path+ "Pavement.jpg"));//ImageIO.read(ResourceLoader.load(path+"Pavement.jpg"));//
        //Image mob = ImageIO.read(new File(path + "EnemyRight.png"));//ImageIO.read(ResourceLoader.load(path+"EnemyRight.png"));//;
        //Image tower = ImageIO.read(new File(path+ "Tower.jpg"));//ImageIO.read(ResourceLoader.load(path+"Tower.jpg"));//
        //Image base = ImageIO.read(new File(path+ "Base.jpg"));//ImageIO.read(ResourceLoader.load(path+"Base.jpg"));//
        //buttons.setBounds(800,0,200,200);
        //output.setBounds(0,400,200,150);
        //output.updateHealthandMoney(myBase.getMoney(),myBase.getHealth());
      //  buttons.setBounds(800,0,buttons.getWidth(),buttons.getHeight());
        
        
        for(int i=0; i<MAP_WIDTH;i+=GRID_SIZE) {
             for(int j=0;j<MAP_HEIGHT;j+=GRID_SIZE) {
                 switch (levelMap[j/GRID_SIZE][i/GRID_SIZE]) {
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
        g.drawImage(myBase.getImage(),myBase.getYLocation(),myBase.getXLocation(),null);

        if(monsters.size()>0) {
            for(int i=0; i<monsters.size();i++) {
                Enemy newMonster=monsters.get(i);
                mob = newMonster.getImage();
                g.drawImage(newMonster.getImage(), newMonster.getXLocation(), newMonster.getYLocation(),null);
            }
        }
        if(towers.size()>0) {
            for(int i=0;i<towers.size();i++) {
                Tower newTower = towers.get(i);
                tower=newTower.getImage();
                g.drawImage(tower,newTower.getXLocation(), newTower.getYLocation(), null);
            }
        }
        if(shots.size()>0) {
            Graphics2D g2 = (Graphics2D)g;
            while(!shots.isEmpty()) {
                BlastAnimation shotAnimation = shots.remove(0);
                //g2.draw(new Line2D.Double(shotAnimation.getStartX(),shotAnimation.getStartY(),shotAnimation.getEndX(),shotAnimation.getEndY()));
            }
        }
        
    }



}
