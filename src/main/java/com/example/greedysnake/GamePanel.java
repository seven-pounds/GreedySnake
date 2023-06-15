package com.example.greedysnake;

import com.example.greedysnake.entity.Apple;
import com.example.greedysnake.entity.Ground;
import com.example.greedysnake.entity.Snake;
import com.example.greedysnake.util.Global;

import javax.swing.*;
import java.awt.*;

/**
 * @author jzfan
 */
@SuppressWarnings("serial")
public class GamePanel extends JPanel {

    private Snake snake;
    private Apple apple;
    private Ground ground;
    //显示画面
    public void display(Snake snake, Apple apple, Ground ground) {
        this.snake = snake;
        this.apple = apple;
        this.ground = ground;
        //会重新显示，此方法会调用下面的方法
        this.repaint();
    }
    @Override
    protected void paintComponent(Graphics g) {
        //重新显示
        //设置背景颜色
        g.setColor(Color.LIGHT_GRAY);
        g.fillRect(0, 0, Global.WIDTH * Global.CELL_SIZE,
                Global.HEIGHT * Global.CELL_SIZE);
        if(ground != null && snake != null && apple != null ) {
            this.ground.drawMe(g);
            this.snake.drawMe(g);
            this.apple.drawMe(g);
        }

    }
}
