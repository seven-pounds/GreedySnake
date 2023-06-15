package com.example.greedysnake;

/*
 * 这些代码基本都在窗体直接涉及而成，所有代码基本可以忽略不看。
 * 只看有注释的关键地方即可。
 * 在设计中，除了让GamePanel获得焦点，其他组件都不能获得焦点。
 */

import com.example.greedysnake.controller.Controller;
import com.example.greedysnake.entity.Apple;
import com.example.greedysnake.entity.Ground;
import com.example.greedysnake.entity.Snake;
import com.example.greedysnake.util.Global;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.*;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.border.EmptyBorder;
import javax.swing.border.EtchedBorder;

@SuppressWarnings("serial")
public class MainWindow extends JFrame {

    protected static final Object SnakeListener = null;

    private JPanel contentPane;

    Snake snake = new Snake();
    Apple apple = new Apple();
    Ground ground = new Ground();
    public JTextField gameTime;
    private JTextField lifeTimes;
    private JTextField timeCountDown;

    GamePanel gamePanel = new GamePanel();
    Controller controller = new Controller(snake, apple, ground, gamePanel);

    public MainWindow() {
        setResizable(false);
        setTitle("Greedy Snake Game");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        //让窗体居中
        setLocation(getToolkit().getScreenSize().width / 2 - Global.CELL_SIZE * Global.WIDTH / 2,
                getToolkit().getScreenSize().height / 2 - Global.CELL_SIZE * Global.WIDTH / 2);

        setSize(821, 760);
        addKeyListener(controller);
        contentPane = new JPanel();
        contentPane.setFocusCycleRoot(true);
        contentPane.setFocusTraversalPolicyProvider(true);
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        setContentPane(contentPane);


        JPanel panel = new JPanel();
        panel.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        panel.setFocusCycleRoot(true);
        panel.setFocusTraversalPolicyProvider(true);
        gamePanel.setFocusTraversalPolicyProvider(true);
        gamePanel.setFocusCycleRoot(true);


        gamePanel.setSize(Global.CELL_SIZE * Global.WIDTH, Global.CELL_SIZE * Global.HEIGHT);
        gamePanel.setLayout(new BorderLayout(0, 0));
        GroupLayout gl_panel = new GroupLayout(panel);
        gl_panel.setHorizontalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel.createSequentialGroup()
                                .addComponent(gamePanel, GroupLayout.PREFERRED_SIZE, 800, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        gl_panel.setVerticalGroup(
                gl_panel.createParallelGroup(Alignment.LEADING)
                        .addComponent(gamePanel, GroupLayout.DEFAULT_SIZE, 505, Short.MAX_VALUE)
        );
        panel.setLayout(gl_panel);

        JPanel panel_1 = new JPanel();
        panel_1.setFocusable(false);
        GroupLayout gl_contentPane = new GroupLayout(contentPane);
        gl_contentPane.setHorizontalGroup(
                gl_contentPane.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 801, Short.MAX_VALUE)
                                .addGap(10))
                        .addGroup(Alignment.LEADING, gl_contentPane.createSequentialGroup()
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 795, Short.MAX_VALUE)
                                .addContainerGap())
        );
        gl_contentPane.setVerticalGroup(
                gl_contentPane.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_contentPane.createSequentialGroup()
                                .addComponent(panel, GroupLayout.PREFERRED_SIZE, 505, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(panel_1, GroupLayout.PREFERRED_SIZE, 205, Short.MAX_VALUE)
                                .addContainerGap())
        );

        JPanel lable = new JPanel();
        lable.setFocusable(false);
        lable.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));

        JPanel panel_control = new JPanel();
        panel_control.setFocusable(false);
        panel_control.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));


        JPanel panel_display = new JPanel();
        panel_display.setFocusable(false);
        panel_display.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel_display, 0, 0, Short.MAX_VALUE)
                                        .addComponent(panel_control, 0, 0, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lable, GroupLayout.PREFERRED_SIZE,
                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                .addGap(19))
        );
        gl_panel_1.setVerticalGroup(
                gl_panel_1.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.LEADING)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(panel_display,
                                                        GroupLayout.DEFAULT_SIZE, 123, Short.MAX_VALUE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(panel_control,
                                                        GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                        .addComponent(lable, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                                .addContainerGap())
        );

        JLabel gimeTimeLabel = new JLabel("please set game time");
        gimeTimeLabel.setFocusable(false);
        gimeTimeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        gimeTimeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        gimeTimeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        gameTime = new JTextField();
        gameTime.setText("15");
        // 游戏开始前可以修改时间
        gameTime.setEditable(true);
        gameTime.setFocusable(true);
        gameTime.setColumns(10);

        JLabel timeCountDownLabel = new JLabel("time count down");
        timeCountDownLabel.setFocusable(false);

        timeCountDown = new JTextField();
        timeCountDown.setText(controller.gameTimeSecond + "/s");
        timeCountDown.setEditable(false);
        timeCountDown.setFocusable(false);
        timeCountDown.setColumns(10);

        JLabel lifeTimesLabel = new JLabel("life times");
        lifeTimesLabel.setFocusable(false);

        lifeTimes = new JTextField();
        lifeTimes.setText(3 + " 次 ");
        lifeTimes.setEditable(false);
        lifeTimes.setFocusable(false);
        gimeTimeLabel.setLabelFor(lifeTimes);
        lifeTimes.setColumns(10);
        GroupLayout gl_panel_display = new GroupLayout(panel_display);
        gl_panel_display.setHorizontalGroup(
                gl_panel_display.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_display.createSequentialGroup()
                                .addGap(5)
                                .addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING)
                                        .addComponent(gimeTimeLabel,
                                                0, 0, Short.MAX_VALUE)
                                        .addComponent(timeCountDownLabel,
                                                0, 0, Short.MAX_VALUE)
                                        .addComponent(lifeTimesLabel,
                                                0, 0, Short.MAX_VALUE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING, false)
                                        .addComponent(timeCountDown,
                                                GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addComponent(lifeTimes,
                                                GroupLayout.DEFAULT_SIZE, 111, Short.MAX_VALUE)
                                        .addComponent(gameTime))
                                .addContainerGap(26, Short.MAX_VALUE))
        );
        gl_panel_display.setVerticalGroup(
                gl_panel_display.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_display.createSequentialGroup()
                                .addContainerGap(24, Short.MAX_VALUE)
                                .addGroup(gl_panel_display.createParallelGroup(Alignment.TRAILING)
                                        .addGroup(gl_panel_display.createSequentialGroup()
                                                .addComponent(gameTime, GroupLayout.PREFERRED_SIZE,
                                                        21, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(timeCountDown, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lifeTimes, GroupLayout.PREFERRED_SIZE,
                                                        GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
                                                .addGap(16))
                                        .addGroup(gl_panel_display.createSequentialGroup()
                                                .addComponent(gimeTimeLabel, GroupLayout.PREFERRED_SIZE,
                                                        18, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addComponent(timeCountDownLabel, GroupLayout.PREFERRED_SIZE,
                                                        25, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(lifeTimesLabel)
                                                .addGap(21))))
        );
        panel_display.setLayout(gl_panel_display);




        JButton button_pause = new JButton("start/pause/continue");
        button_pause.setFocusable(false);
        button_pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                // get value from gameTime and check if it is a number
                String gameTimeStr = gameTime.getText();
                if (gameTimeStr.matches("[0-9]+")) {
                    int min = Integer.parseInt(gameTimeStr) ;
                    if(min>20){
                        JOptionPane.showMessageDialog(null,
                                "please input a number less than 20 for game time");
                        return;
                    }
                    controller.setGameTimeSecond(min * 60);
                    timeCountDown.setText(controller.getGameTimeSecond() + "/s");
                    // 游戏开始后不可以修改时间
                    gameTime.setEditable(false);
                    gameTime.setFocusable(false);
                } else {
                    JOptionPane.showMessageDialog(null,
                            "please input a number for game time");
                    return;
                }
                snake.changePause();
            }
        });
        button_pause.setFocusPainted(false);

        JButton button_newGame = new JButton("new game");
        button_newGame.setFocusable(false);
        button_newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.newGame();
                // 更新面板
                gameTime.setEditable(true);
                gameTime.setFocusable(true);
                lifeTimes.setText(3 + "/times");
                controller.setGameTimeSecond(0);
                timeCountDown.setText(controller.getGameTimeSecond() + "/s");
            }
        });
        button_newGame.setFocusPainted(false);

        GroupLayout gl_panel_control = new GroupLayout(panel_control);
        gl_panel_control.setHorizontalGroup(
                gl_panel_control.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_control.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(button_newGame, GroupLayout.PREFERRED_SIZE,
                                        150, GroupLayout.PREFERRED_SIZE)
                                .addGap(3)
                                .addComponent(button_pause, GroupLayout.PREFERRED_SIZE,
                                        150, GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
        gl_panel_control.setVerticalGroup(
                gl_panel_control.createParallelGroup(Alignment.TRAILING)
                        .addGroup(Alignment.LEADING, gl_panel_control.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(gl_panel_control.createParallelGroup(Alignment.BASELINE)
                                        .addComponent(button_newGame, GroupLayout.PREFERRED_SIZE,
                                                44, GroupLayout.PREFERRED_SIZE)
                                        .addComponent(button_pause, GroupLayout.PREFERRED_SIZE,
                                                44, GroupLayout.PREFERRED_SIZE)
                                )
                                .addContainerGap(800, Short.MAX_VALUE))
        );
        panel_control.setLayout(gl_panel_control);

        JLabel lblNewLabel = new JLabel("illustration");
        lblNewLabel.setFocusable(false);
        lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
        lblNewLabel.setHorizontalTextPosition(SwingConstants.CENTER);

        JLabel label = new JLabel("direction key: up, down, left, right");
        label.setFocusable(false);

        JLabel label_1 = new JLabel("space key: start/pause/continue");
        label_1.setFocusable(false);

        JLabel lblShift = new JLabel("Shift key: new game");
        lblShift.setFocusable(false);


        GroupLayout gl_lable = new GroupLayout(lable);
        gl_lable.setHorizontalGroup(
                gl_lable.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_lable.createSequentialGroup()
                                .addGroup(gl_lable.createParallelGroup(Alignment.LEADING)
                                        .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
                                                250, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_lable.createSequentialGroup()
                                                .addGap(5)
                                                .addGroup(gl_lable.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(label_1, 0, 0, Short.MAX_VALUE)
                                                        .addComponent(label, 0, 0, Short.MAX_VALUE)
                                                        .addComponent(lblShift))))
                                .addContainerGap())
        );
        gl_lable.setVerticalGroup(
                gl_lable.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_lable.createSequentialGroup()
                                .addGap(8)
                                .addComponent(lblNewLabel, GroupLayout.PREFERRED_SIZE,
                                        30, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(label, GroupLayout.PREFERRED_SIZE,
                                        26, GroupLayout.PREFERRED_SIZE)
                                .addGap(2)
                                .addComponent(label_1, GroupLayout.PREFERRED_SIZE,
                                        26, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addComponent(lblShift, GroupLayout.PREFERRED_SIZE,
                                        25, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                .addContainerGap(39, Short.MAX_VALUE))
        );
        lable.setLayout(gl_lable);
        panel_1.setLayout(gl_panel_1);
        contentPane.setLayout(gl_contentPane);
        //给游戏面板和蛇添加监听器
        gamePanel.addKeyListener(controller);
        snake.addSnakeListener(controller);
        //开始一个新的线程，用来更新分数
        controller.startRefresh(new Thread(new refresh()));
        //开始游戏
        controller.beginGame();
    }

    //创建一个线程让一直刷新分数
    public class refresh implements Runnable {
        @Override
        public void run() {
            //当蛇活着的时候才进行循环
            while (!snake.isDie) {
                if(!snake.isPause()){
                    controller.timeCountDown();
                    timeCountDown.setText(controller.getGameTimeSecond() + "/s");
                    lifeTimes.setText(snake.getLifeCount() + "/times");
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }

            }
        }
    }
}