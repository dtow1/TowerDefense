package com.jasonrboyer.mytowergame.models;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

public class BlastAnimation
{
    private int startX,startY;
    private int endX,endY;
    private int fireCount=0;
    
    public BlastAnimation(){
        startX=0;
        startY=0;
        endX=0;
        endY=0;
    }
    
   public BlastAnimation(int startX, int startY, int endX, int endY){
        this.startX = startX;
        this.startY = startY;
        this.endX = endX;
        this.endY = endY;
    }
   
    public void fire() {
        //if(fireCount%3==0){  
            fireCount=0;
            String weaponFire="res/GunFire.wav";
            //String weaponFire="res/GunFireLong.wav";
            try
            {
                InputStream in = new FileInputStream(weaponFire);
                AudioStream audioStream = new AudioStream(in);
                AudioPlayer.player.start(audioStream);
            } catch (FileNotFoundException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            } catch (IOException e)
            {
                // TODO Auto-generated catch block
                e.printStackTrace();
            }
       // }
       // fireCount++;
    }
    public int getStartX()
    {
        return startX;
    }
    public void setStartX(int startX)
    {
        this.startX = startX;
    }
    public int getStartY()
    {
        return startY;
    }
    public void setStartY(int startY)
    {
        this.startY = startY;
    }
    public int getEndX()
    {
        return endX;
    }
    public void setEndX(int endX)
    {
        this.endX = endX;
    }
    public int getEndY()
    {
        return endY;
    }
    public void setEndY(int endY)
    {
        this.endY = endY;
    }
    
    

}
