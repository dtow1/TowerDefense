package com.jasonrboyer.mytowergame.controllers;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This is the main screen for the game. Right now it is barebones and only allows the user to start the game. Eventually it 
 * will be where the user can configure the game, load saved games, or any other feature that they may want.
 * 
 * @author Jason Boyer
 * @date 11/14/2015
 *
 */
public class MainFrame extends JFrame
{
    
    private int DEFAULT_SIZE = 400;
    MainFrame(ActionListener startGameListener){
        new BorderLayout();
        JPanel testPanel = new JPanel(new BorderLayout());
        JLabel testLabel = new JLabel("Welcome to the Game");
        JButton startGame = new JButton("Start Game");
        setSize(DEFAULT_SIZE,DEFAULT_SIZE);
        startGame.addActionListener(startGameListener);
        testPanel.add(testLabel, BorderLayout.NORTH);
        testPanel.add(startGame, BorderLayout.EAST);
        add(testPanel);
        setTitle("Game in Progress");

        this.setVisible(true);
            
            
    }

}
