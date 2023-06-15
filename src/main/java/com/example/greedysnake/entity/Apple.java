package com.example.greedysnake.entity;

import com.example.greedysnake.util.Global;

import java.awt.*;

/**
 * @author jzfan
 */
public class Apple extends Point implements Food{
    Point point = null;
    //设置食物位置坐标
    public void newApple(Point p) {
        this.point = p;
        this.setLocation(p);
    }
    //判断蛇是否吃到食物
    public boolean isSnakeEatApple(Snake snake) {
        return this.equals(snake.getHead());
    }
    //显示食物
    public void drawMe(Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(point.x * Global.CELL_SIZE, point.y * Global.CELL_SIZE,
                Global.CELL_SIZE, Global.CELL_SIZE, true);
    }
}
