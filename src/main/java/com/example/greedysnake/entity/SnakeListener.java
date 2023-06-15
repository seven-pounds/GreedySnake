package com.example.greedysnake.entity;

import com.example.greedysnake.entity.Snake;

/**
 * @author jzfan
 */
public interface SnakeListener {
    //蛇移动的监听
    void snakeMove(Snake snake);

}
