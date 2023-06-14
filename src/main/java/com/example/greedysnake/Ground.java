package com.example.greedysnake;

import com.example.greedysnake.util.Global;

import java.awt.*;
import java.util.Random;

/**
 * @author jzfan
 */
public class Ground {
    /*
     *定义存放石头坐标的数组，1为石头，0为空白区域。
     *一定要用全局静态变量，始终占用空间。否则会在产生食物时出错
     */
    private static final int rocks[][] =
            new int[Global.WIDTH][Global.HEIGHT];
    //存放石头的个数
    public int rocksCount = 0;
    //是否画网格
    private boolean isDrawGriding;
    //选择地图是使用
    public int MAP = 1;
    //构造方法，初始化地图
    public Ground() {
        init();
    }
    //清除所有石头
    public void clear() {
        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                rocks[x][y] = 0;
            }
        }
    }
    //初始化石头位置
    public void init() {
        //清除所有石头
        clear();
        map1();
        getScoksCount();
    }
    //第一组默认地图石头坐标
    public void map1() {
        for(int x = 0; x < Global.WIDTH; x++) {
            rocks[x][0] = 1;
            rocks[x][Global.HEIGHT-1] = 1;
        }
        for(int y = 0; y < Global.HEIGHT; y++) {
            rocks[0][y] = 1;
            rocks[Global.WIDTH-1][y]  = 1;
        }
    }

    //获得石头总共数目
    public void getScoksCount() {
        //每次更换地图时清零，重新获得
        rocksCount = 0;
        for (int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (rocks[x][y] == 1) {
                    rocksCount++;
                }
            }
        }
    }
    //判断蛇是否吃到石头
    //把蛇的所有节点与石头坐标进行比较如果想等则证明吃到石头
    public boolean isSnakeEatRock(Snake snake) {
        for(int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (rocks[x][y] == 1
                        && x == snake.getHead().x
                        && y == snake.getHead().y) {
                    return true;
                }
            }
        }
        return false;
    }
    //获得不会与石头重叠的随机坐标
    public Point getPoint() {
        Random random = new Random();
        int x = 0, y = 0;
        do{
            x = random.nextInt(Global.WIDTH);
            y = random.nextInt(Global.HEIGHT);
        }while(rocks[x][y] == 1);
        return new Point(x, y);
    }
    //画石头和网格
    public void drawMe(Graphics g) {
        drawRocks(g);
        if (isDrawGriding) {
            drawGriding(g);
        }
    }
    //画石头
    public void drawRocks(Graphics g) {
        for(int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                if (rocks[x][y] == 1) {
                    g.setColor(Color.DARK_GRAY);
                    g.fill3DRect(x * Global.CELL_SIZE, y * Global.CELL_SIZE,
                            Global.CELL_SIZE, Global.CELL_SIZE, true);
                }
            }
        }
    }
    //画网格
    public void drawGriding(Graphics g) {
        for(int x = 0; x < Global.WIDTH; x++) {
            for (int y = 0; y < Global.HEIGHT; y++) {
                g.setColor(Color.GRAY);
                g.fillRect(x * Global.CELL_SIZE , y * Global.CELL_SIZE,
                        1 , Global.HEIGHT * Global.CELL_SIZE);
                g.fillRect(x * Global.CELL_SIZE , y * Global.CELL_SIZE,
                        Global.HEIGHT * Global.CELL_SIZE, 1 );

            }
        }
    }
    //需要要画网格
    public void drawGriding() {
        isDrawGriding = true;
    }
    //不需要画网格
    public void notDrawGriding() {
        isDrawGriding = false;
    }
}
