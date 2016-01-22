package com.jasonrboyer.mytowergame.controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;

/**
 * This is the main application window. It's purpose is to get initialize the game, display a startup image and credits. It is
 * just a place holder for now.
 *  
 * @author Jason Boyer
 * @version 0.1
 * @date 11/14/2015
 * 
 *
 */
public class Application extends JFrame {
    
    private boolean startGame=true;
    private boolean inMainPanel = true;
    private boolean exit=false;
    private MainFrame mainFrame;
    private GameFrame newGame;
    private int gameFrameID,mainPanelID;
    private final int MAIN_FRAME_X = 300, MAIN_FRAME_Y=300;
    private final int SPLASH_SCREEN_X = 400, SPLASH_SCREEN_Y=700;

    public Application() {
        

        mainFrame = new MainFrame(startGameListener);
        inMainPanel=false;
        mainFrame.addWindowListener(listenToWindow);

        mainFrame.setVisible(true);
        mainFrame.setSize(MAIN_FRAME_X,MAIN_FRAME_Y);
        setSize(SPLASH_SCREEN_X,SPLASH_SCREEN_Y);
                
        setTitle("TowerGame");
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
   
    }
    
    private void initUI() {
        
        newGame = new GameFrame();
        newGame.addWindowListener(listenToWindow); 
    }
    
 
    
    public static void main(String[] args)
    {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });

    }
    
    WindowListener listenToWindow = new WindowListener() {

        @Override
        public void windowActivated(WindowEvent arg0)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowClosed(WindowEvent arg0)
        {
            // TODO Auto-generated method stub
            if(arg0.getSource().equals(newGame)) {
                mainFrame.setVisible(true);
                setVisible(true);
                newGame.dispose();
            }  
        }

        @Override
        public void windowClosing(WindowEvent arg0)
        {
            if(arg0.getSource().equals(newGame)) {
                mainFrame.setVisible(true);
                setVisible(true);
                newGame.killTimers();
                newGame.removeAll();
                newGame.dispose();
            }  
        }

        @Override
        public void windowDeactivated(WindowEvent arg0)
        {
            
        }

        @Override
        public void windowDeiconified(WindowEvent arg0)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowIconified(WindowEvent arg0)
        {
            // TODO Auto-generated method stub
            
        }

        @Override
        public void windowOpened(WindowEvent arg0)
        {
            // TODO Auto-generated method stub
            
        }
        
    };

    
    ActionListener startGameListener = new ActionListener() {

        @Override
        public void actionPerformed(ActionEvent e)
        {     
            initUI();
            mainFrame.setVisible(false);
            setVisible(false);
        }
        
    };
}
