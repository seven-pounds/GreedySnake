package com.example.greedysnake.util;

/**
 * @author jzfan
 */
public class Global {

    /**
     * 每个格子的大小
     */
    public static final int CELL_SIZE = 20;
    /**
     * 游戏界面的宽度和高度
     */
    public static int WIDTH = 40;
    public static int HEIGHT = 25;

    /**
     * 游戏界面的格子数
     */
    public static final int count = WIDTH * HEIGHT;


    public static int getWIDTH() {
        return WIDTH;
    }

    public static void setWIDTH(int WIDTH) {
        Global.WIDTH = WIDTH;
    }

    public static int getHEIGHT() {
        return HEIGHT;
    }

    public static void setHEIGHT(int HEIGHT) {
        Global.HEIGHT = HEIGHT;
    }

}
