package com.jasonrboyer.mytowergame.views;

import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import com.jasonrboyer.mytowergame.models.Tower;

/**
 * This class is the button panel for control during gameplay. It was originally
 * written in late 2015. I am commenting it in September of 2016. It is fairly
 * straightforward since it is just creating buttons and adding them to a panel.
 * 
 * @author jasonboyer
 *
 */
public class ButtonPanel extends JPanel {
    String  path       = "images" + File.separator;
    JButton addBasic   =
            new JButton("Machine Gun", new ImageIcon(path + "TowerDown.png"));
    JLabel  basicLabel = new JLabel("Cost: " + (new Tower()).getCost());
    JButton play       = new JButton("Play");
    JButton pause      = new JButton("Pause");

    public ButtonPanel() {
        GridLayout buttonLayout = new GridLayout(3, 3);
        this.setLayout(buttonLayout);
        Dimension buttonDimension = new Dimension(40, 40);

        addBasic.setName("Basic");
        addBasic.setHorizontalTextPosition(AbstractButton.CENTER);
        addBasic.setVerticalTextPosition(AbstractButton.BOTTOM);
        addBasic.setSize(40, 40);

        play.setName("Play");
        play.setSize(40, 40);
        play.setPreferredSize(buttonDimension);
        play.setMinimumSize(buttonDimension);
        play.setMaximumSize(buttonDimension);

        pause.setName("Pause");
        pause.setSize(40, 40);
        pause.setPreferredSize(buttonDimension);
        pause.setMinimumSize(buttonDimension);
        pause.setMaximumSize(buttonDimension);

        this.add(addBasic);
        basicLabel.setBorder(BorderFactory.createLoweredBevelBorder());
        this.add(basicLabel);
        this.add(play);
        this.add(pause);

    }

    public void addActionListener(ActionListener thisListener) {
        addBasic.addActionListener(thisListener);
        play.addActionListener(thisListener);
        pause.addActionListener(thisListener);
    }
}
