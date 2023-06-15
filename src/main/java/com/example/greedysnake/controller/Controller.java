package com.example.greedysnake.controller;

import com.example.greedysnake.*;
import com.example.greedysnake.entity.Apple;
import com.example.greedysnake.entity.Ground;
import com.example.greedysnake.entity.Snake;
import com.example.greedysnake.entity.SnakeListener;
import com.example.greedysnake.util.Constants;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;

/**
 * 控制器
 * 控制Ground, Snake, apple
 * 负责游戏的逻辑
 * 处理按键事件
 * 实现了SnakeListener接口, 可以处理Snake 触发的事件
 */
public class Controller extends KeyAdapter implements SnakeListener {

    public long gameTime;
    private Snake snake;
    private Apple apple;
    private Ground ground;
    private GamePanel gamePanel;

    public int score = 0;

    public long gameTimeSecond;
    public Thread thread;

    List<Integer> keyEvents = new ArrayList<>();

    public Controller(Snake snake, Apple apple, Ground ground, GamePanel gamePanel) {
        super();
        this.snake = snake;
        this.apple = apple;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        switch (e.getKeyCode()) {
            case KeyEvent.VK_ENTER:
                snake.changePauseByEnter();
                break;
            case KeyEvent.VK_SPACE:
                snake.changePause();
                break;
            case KeyEvent.VK_SHIFT:
                newGame();
                break;
            case KeyEvent.VK_UP:
            case KeyEvent.VK_DOWN:
            case KeyEvent.VK_LEFT:
            case KeyEvent.VK_RIGHT:
                if (keyEvents.size() < 4) {
                    keyEvents.add(e.getKeyCode());
                }
                System.out.println(keyEvents.size());
        }
    }

    /**
     * 判断是否还可以放下食物
     * 当身体占满全部空位，没有地方再可以放食物时
     * 游戏结束
     * Global.count : 全局游戏界面总坐标，默认1000
     * this.snake.snakeBodyCount ： 蛇的身体总长度
     * ground.rocksCount ： 石头总数
     *
     */
    @Override
    public void snakeMove(Snake snake) {

        if (keyEvents.size() > 0) {
            int key = keyEvents.remove(0);
            switch (key) {
                case KeyEvent.VK_UP:
                    this.snake.chanceDirection(Snake.UP);
                    break;
                case KeyEvent.VK_DOWN:
                    this.snake.chanceDirection(Snake.DOWN);
                    break;
                case KeyEvent.VK_LEFT:
                    this.snake.chanceDirection(Snake.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    this.snake.chanceDirection(Snake.RIGHT);
                    break;
            }
        }

        //如果蛇吃到食物，，处理蛇吃到食物的方法，并获得新的食物
        if (apple.isSnakeEatApple(snake)) {
            snake.eatApple();
            apple.newApple(snake.getApplePoint());
            this.score += 1;

        }
        // 提示游戏结束并重新开始新游戏
        if (this.score >= Constants.PASS_SCORE) {
            JOptionPane.showMessageDialog(gamePanel, "您已经通过游戏，游戏结束！");
            newGame();
        }


        //判断是否吃到石头，如果吃到石头，蛇死亡。
        if (ground.isSnakeEatRock(snake)) {
            snake.lifeDesc();
            JOptionPane.showMessageDialog(gamePanel, "蛇撞墙死亡！");
            continueGame();
        }
        //如果蛇吃到身体也死亡
        if (snake.isEatBody()) {
            snake.lifeDesc();
            JOptionPane.showMessageDialog(gamePanel, "蛇咬到自己死亡");
            continueGame();
        }

        if (snake.getLifeCount() <= 0) {
            snake.die();
            JOptionPane.showMessageDialog(gamePanel, "生命值为0，游戏结束！");
            newGame();
        }
        //如果蛇死亡，最后一次不刷新画面，如果刷新，蛇头会与石头重叠
        if (!(ground.isSnakeEatRock(snake) | snake.isEatBody())) {
            gamePanel.display(snake, apple, ground);
        }
    }

    //开始游戏
    public void beginGame() {
        //开始游戏时，得分归零
        score = 0;
        //获得新的食物坐标
        apple.newApple(snake.getApplePoint());
        //开始蛇驱动的线程
        snake.start();
        //开启主窗体界面刷新的线程，用来更新分数
        new Thread(thread).start();
    }

    // 开始新游戏,重新设置生命值
    public void newGame() {
        continueGame();
        snake.setLifeCount(3);
    }

    /**
     * 判断蛇是否处于死亡状态，如果是，
     * 则在蛇驱动中已经跳出循环，不会触发蛇的监听
     * 此时再开始调用开始游戏，重新初始化游戏，重新监听蛇运动
     *
     * 如果蛇不是死亡状态，则不执行开始游戏初始化，此时蛇处于正常监听状态
     * 只重新初始化蛇和食物，分数即可开始新游戏。
     */
    public void continueGame() {
        //开始新游戏后，清除蛇的身体
        snake.bodyClear();
        //重新初始化蛇
        snake.init();
        //获得新食物坐标
        apple.newApple(snake.getApplePoint());

        if (snake.isDie) {
            beginGame();
            snake.isDie = false;
        }
    }


    //接收主窗体中刷新界面的线程
    public Thread startRefresh(Thread thread) {
        this.thread = thread;
        return this.thread;
    }

    public void timeCountDown() {
        this.gameTimeSecond--;
    }

    public long getGameTimeSecond() {
        return gameTimeSecond;
    }

    public void setGameTimeSecond(long gameTimeSecond) {
        this.gameTimeSecond = gameTimeSecond;
    }
}
