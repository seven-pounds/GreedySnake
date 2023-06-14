package com.example.greedysnake;

import com.example.greedysnake.util.Global;

import java.awt.*;

/**
 * @author jzfan
 */
public class Food extends Point{
    Point point = null;
    //设置食物位置坐标
    public void newFood(Point p) {
        this.point = p;
        this.setLocation(p);
    }
    //判断蛇是否吃到食物
    public boolean isSnakeEatFood(Snake snake) {
        return this.equals(snake.getHead());
    }
    //显示食物
    public void drawMe(Graphics g) {
        g.setColor(Color.GREEN);
        g.fill3DRect(point.x * Global.CELL_SIZE, point.y * Global.CELL_SIZE,
                Global.CELL_SIZE, Global.CELL_SIZE, true);
    }
}
