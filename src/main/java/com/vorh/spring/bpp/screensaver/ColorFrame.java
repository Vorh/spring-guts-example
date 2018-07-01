package com.vorh.spring.bpp.screensaver;

import org.springframework.stereotype.Service;

import javax.swing.*;
import java.awt.*;
import java.util.Random;

/**
 * Created by vorh on 7/1/18.
 */
@Service
public abstract class ColorFrame extends JFrame{


    public ColorFrame() throws HeadlessException {
        setSize(200,200);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
    }
    public void showOnRandomPlace(){
        Random random = new Random();

        setLocation(random.nextInt(1200),random.nextInt(700));
        getContentPane().setBackground(getColor());
        repaint();
    }

    protected abstract Color getColor();
}
