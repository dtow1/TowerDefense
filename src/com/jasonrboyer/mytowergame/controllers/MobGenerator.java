package com.jasonrboyer.mytowergame.controllers;

import java.util.ArrayList;

import com.jasonrboyer.mytowergame.models.Enemy;
import com.jasonrboyer.mytowergame.models.*;

/**
 * This class is used to generate waves of mobs.
 * @author Jason Boyer
 * @date 11/14/2015
 * 
 *
 */
public class MobGenerator
{
    private int waveNumber;
    ArrayList<Enemy> thisWave = new ArrayList<Enemy>();
    
    MobGenerator(){
        waveNumber=1;
    }
    
    public void generateWave(int mobCount) {
        for(int i=0;i<mobCount;i++) {
            thisWave.add(new Enemy());
        }
    }
    public void generateWave(int mobCount,int wave) {
        for(int i=0;i<mobCount;i++) {
            thisWave.add(new Enemy(wave));
        }
    }
    public boolean hasNext() {
        return !thisWave.isEmpty();
    }
    public Enemy getNext() {
        Enemy nextEnemy=null;
        if(hasNext()) {
            nextEnemy=thisWave.get(0);
            thisWave.remove(0);
        }
        return nextEnemy;
    }

}
