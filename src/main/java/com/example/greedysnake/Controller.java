package com.example.greedysnake;

import com.example.greedysnake.util.Global;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/*控制器
 * 控制Ground, Snake, Food<BR>
 * 负责游戏的逻辑
 * 处理按键事件
 * 实现了SnakeListener接口, 可以处理Snake 触发的事件
 */
public class Controller extends KeyAdapter implements SnakeListener {

    public long gameTime;
    private Snake snake;
    private Food food;
    private Ground ground;
    private GamePanel gamePanel;

    public int score = 0;

    public int maxScore;
    public Thread thread;

    List<Integer> keyEvents = new ArrayList<>();

    //构造方法，初始化
    public Controller(Snake snake, Food food, Ground ground, GamePanel gamePanel) {
        super();
        this.snake = snake;
        this.food = food;
        this.ground = ground;
        this.gamePanel = gamePanel;
    }

    @Override
    //处理按键事件
    public void keyPressed(KeyEvent e) {

        if (keyEvents.size() < 4) {
            keyEvents.add(e.getKeyCode());
        }
        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:
                snake.chanceDirection(Snake.UP);
                break;
            case KeyEvent.VK_DOWN:
                snake.chanceDirection(Snake.DOWN);
                break;
            case KeyEvent.VK_LEFT:
                snake.chanceDirection(Snake.LEFT);
                break;
            case KeyEvent.VK_RIGHT:
                snake.chanceDirection(Snake.RIGHT);
                break;
            case KeyEvent.VK_SPACE:
                snake.changePause();
                break;
            case KeyEvent.VK_SHIFT:
                newGame();
                break;
        }
    }

    //处理Snake 触发的 snakeMoved 事件
    @Override
    public void snakeMove(Snake snake) {
        /*
         * 判断是否还可以放下食物
         * 当身体占满全部空位，没有地方再可以放食物时
         * 游戏结束
         * Global.count : 全局游戏界面总坐标，默认1000
         * this.snake.snakeBodyCount ： 蛇的身体总长度
         * ground.rocksCount ： 石头总数
         *
         */
        if (Global.count - this.snake.snakeBodyCount - ground.rocksCount < 3) {
            snake.die();
            //弹出消息框，提示游戏结束
            JOptionPane.showMessageDialog(gamePanel, "您已获得最高分，游戏结束！");
        }
        //如果蛇吃到食物，，处理蛇吃到食物的方法，并获得新的食物
        if (food.isSnakeEatFood(snake)) {
            snake.eatFood();
            food.newFood(snake.getFoodPoint());
            this.score += 10;

        }
        //判断是否吃到石头，如果吃到石头，蛇死亡。
        if (ground.isSnakeEatRock(snake)) {
            snake.die();
            //弹出消息框，提示游戏结束
            JOptionPane.showMessageDialog(gamePanel, "蛇撞墙死亡，游戏结束！" );
        }
        //如果蛇吃到身体也死亡
        if (snake.isEatBody()) {
            snake.die();
            JOptionPane.showMessageDialog(gamePanel, "蛇咬到自己死亡，游戏结束！");
        }
        //如果蛇死亡，最后一次不刷新画面，如果刷新，蛇头会与石头重叠
        if (!(ground.isSnakeEatRock(snake) | snake.isEatBody())) {
            gamePanel.display(snake, food, ground);
        }
    }

    //开始游戏
    public void beginGame() {
        //开始游戏时，得分归零
        score = 0;
        //获得新的食物坐标
        food.newFood(snake.getFoodPoint());
        //开始蛇驱动的线程
        snake.start();
        //开启主窗体界面刷新的线程，用来更新分数
        new Thread(thread).start();
    }

    //开始新游戏
    public void newGame() {
        //开始新游戏后，清除蛇的身体
        snake.bodyClear();
        //重新初始化蛇
        snake.init();
        //获得新食物坐标
        food.newFood(snake.getFoodPoint());
        /*
         * 判断蛇是否处于死亡状态，如果是，
         * 则在蛇驱动中已经跳出循环，不会触发蛇的监听
         * 此时再开始调用开始游戏，重新初始化游戏，重新监听蛇运动
         *
         * 如果蛇不是死亡状态，则不执行开始游戏初始化，此时蛇处于正常监听状态
         * 只重新初始化蛇和食物，分数即可开始新游戏。
         */
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
}
