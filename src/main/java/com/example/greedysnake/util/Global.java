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

    /**
     * 游戏时间
     */
    public static int min = 15;

    /**
     * 游戏次数
     */
    public static int gameTimes = 0;

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

    public static int getMin() {
        return min;
    }

    public static void setMin(int min) {
        Global.min = min;
    }

    public static int getGameTimes() {
        return gameTimes;
    }

    public static void setGameTimes(int gameTimes) {
        Global.gameTimes = gameTimes;
    }
}
