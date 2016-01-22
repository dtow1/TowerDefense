package com.jasonrboyer.mytowergame.views;

import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class OutputPanel extends JPanel
{
    JLabel money;
    JLabel health;
    JLabel wave;
    
    public OutputPanel(){
        this.setLayout(new GridLayout(3,1));
        money = new JLabel("Money: ");
        money.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(money);
        health = new JLabel("Health: ");
        health.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(health);
        wave = new JLabel("Wave: ");
        wave.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(wave);
    }
    
    public void updateMoney(int newMoney) {
        money.setText("Money: " + newMoney);
        
    }
    
    public void updateHealth(int newHealth) {
        health.setText("Health: " + newHealth);
    }
    
    public void updateHealthandMoney(int newMoney, int newHealth) {
        money.setText("Money: " + newMoney);
        health.setText("Health: " + newHealth);
    }
    
    public void setWave(int newWave) {
        wave.setText("Wave: " + newWave);
    }

}
