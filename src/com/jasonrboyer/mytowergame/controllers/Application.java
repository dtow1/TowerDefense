package com.jasonrboyer.mytowergame.controllers;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

/**
 * This is the main application window. It's purpose is to get initialize the
 * game, display a startup image and credits. It is just a place holder for now.
 * 
 * @author Jason Boyer
 * @version 0.1
 * @date 11/14/2015
 * 
 *
 */
public class Application extends JFrame {

    private boolean   startGame       = true;
    private boolean   inMainPanel     = true;
    private boolean   exit            = false;
    private GameFrame newGame;
    private int       gameFrameID, mainPanelID;
    private final int MAIN_FRAME_X    = 300, MAIN_FRAME_Y = 300;
    private final int SPLASH_SCREEN_X = 400, SPLASH_SCREEN_Y = 700;
    private String    path            = "images" + File.separator;

    public Application() {

        // Setup the display elements for the opening screen
        inMainPanel = false;
        setSize(SPLASH_SCREEN_X, SPLASH_SCREEN_Y);
        setTitle("Tower Defense Game");
        this.setVisible(true);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        new BorderLayout();
        JPanel testPanel = new JPanel(new BorderLayout());
        JLabel testLabel = new JLabel(
                "<html><h1 style='font-weight: bolder'>Tower Defense</h1></html>");
        testLabel.setHorizontalAlignment(SwingConstants.CENTER);
        BufferedImage image;
        try {
            image = ImageIO.read(new File(path + "towerimage.jpeg"));
            JLabel towerImage = new JLabel(new ImageIcon(image));
            testPanel.add(towerImage, BorderLayout.CENTER);
        }
        catch (IOException e) {
            e.printStackTrace();
        }

        JButton startGame = new JButton("Start Game");
        startGame.addActionListener(startGameListener);
        testPanel.add(testLabel, BorderLayout.NORTH);
        testPanel.add(startGame, BorderLayout.SOUTH);
        add(testPanel);

    }

    private void initUI() {

        newGame = new GameFrame();
        newGame.addWindowListener(listenToWindow);
    }

    public static void main(String[] args) {
        EventQueue.invokeLater(new Runnable() {
            @Override
            public void run() {
                Application ex = new Application();
                ex.setVisible(true);
            }
        });

    }

    WindowListener listenToWindow    = new WindowListener() {

                                         @Override
                                         public void windowActivated(
                                                 WindowEvent arg0) {
                                             // TODO Auto-generated method stub

                                         }

                                         @Override
                                         public void windowClosed(
                                                 WindowEvent arg0) {
                                             // TODO Auto-generated method stub
                                             if (arg0.getSource()
                                                     .equals(newGame)) {
                                                 setVisible(true);
                                                 newGame.dispose();
                                             }
                                         }

                                         @Override
                                         public void windowClosing(
                                                 WindowEvent arg0) {
                                             if (arg0.getSource()
                                                     .equals(newGame)) {
                                                 setVisible(true);
                                                 newGame.killTimers();
                                                 newGame.removeAll();
                                                 newGame.dispose();
                                             }
                                         }

                                         @Override
                                         public void windowDeactivated(
                                                 WindowEvent arg0) {

                                         }

                                         @Override
                                         public void windowDeiconified(
                                                 WindowEvent arg0) {
                                             // TODO Auto-generated method stub

                                         }

                                         @Override
                                         public void windowIconified(
                                                 WindowEvent arg0) {
                                             // TODO Auto-generated method stub

                                         }

                                         @Override
                                         public void windowOpened(
                                                 WindowEvent arg0) {
                                             // TODO Auto-generated method stub

                                         }

                                     };

    ActionListener startGameListener = new ActionListener() {

                                         @Override
                                         public void actionPerformed(
                                                 ActionEvent e) {
                                             initUI();
                                             setVisible(false);
                                         }

                                     };
}
