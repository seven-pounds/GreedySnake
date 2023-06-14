package com.example.greedysnake;

import javax.swing.*;
import java.awt.*;

/**
 * @author jzfan
 */

public class Game {

    public static void main(String[] args) {

        EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    MainWindow frame = new MainWindow();
                    frame.setVisible(true);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
