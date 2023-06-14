package com.example.greedysnake;

/*
 * 这些代码基本都在窗体直接涉及而成，所有代码基本可以忽略不看。
 * 只看有注释的关键地方即可。
 * 在设计中，除了让GamePanel获得焦点，其他组件都不能获得焦点。
 */

import com.example.greedysnake.util.Global;

import java.awt.BorderLayout;
import java.awt.Component;
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
    Food food = new Food();
    Ground ground = new Ground();
    public JTextField gameTime;

    GamePanel gamePanel = new GamePanel();
    Controller controller = new Controller(snake, food, ground, gamePanel);

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

        // 游戏控制面板
        JPanel panel_set = new JPanel();
        panel_set.setFocusable(false);

        // 游戏时间设置面板
        JPanel panel_display = new JPanel();
        panel_display.setFocusable(false);
        panel_display.setBorder(new EtchedBorder(EtchedBorder.LOWERED, null, null));
        GroupLayout gl_panel_1 = new GroupLayout(panel_1);
        gl_panel_1.setHorizontalGroup(
                gl_panel_1.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_1.createSequentialGroup()
                                .addComponent(panel_set, GroupLayout.PREFERRED_SIZE,
                                        302, GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_1.createParallelGroup(Alignment.TRAILING)
                                        .addComponent(panel_display, GroupLayout.PREFERRED_SIZE,
                                                216, GroupLayout.PREFERRED_SIZE)
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
                                        .addComponent(panel_set,
                                                GroupLayout.DEFAULT_SIZE, 197, Short.MAX_VALUE)
                                        .addGroup(gl_panel_1.createSequentialGroup()
                                                .addComponent(panel_display,
                                                        GroupLayout.PREFERRED_SIZE, 123, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addComponent(panel_control,
                                                        GroupLayout.DEFAULT_SIZE, 68, Short.MAX_VALUE))
                                        .addComponent(lable, GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE))
                                .addContainerGap())
        );

        JLabel timeLabel = new JLabel("set game time");
        timeLabel.setFocusable(false);
        timeLabel.setHorizontalAlignment(SwingConstants.LEFT);
        timeLabel.setHorizontalTextPosition(SwingConstants.CENTER);
        timeLabel.setAlignmentX(Component.CENTER_ALIGNMENT);


        gameTime = new JTextField();
        gameTime.setText("15");
        gameTime.setEditable(true);
        gameTime.setFocusable(false);
        gameTime.setColumns(10);


        GroupLayout gl_panel_display = new GroupLayout(panel_display);
        gl_panel_display.setHorizontalGroup(
                gl_panel_display.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_display.createSequentialGroup()
                                .addGap(6)
                                .addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING)
                                        .addComponent(timeLabel,
                                                GroupLayout.PREFERRED_SIZE, 75, GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_display.createParallelGroup(Alignment.LEADING, false)
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
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGap(16))
                                        .addGroup(gl_panel_display.createSequentialGroup()
                                                .addComponent(timeLabel, GroupLayout.PREFERRED_SIZE,
                                                        18, GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(ComponentPlacement.UNRELATED)
                                                .addPreferredGap(ComponentPlacement.RELATED)
                                                .addGap(21))))
        );
        panel_display.setLayout(gl_panel_display);



        GroupLayout gl_panel_set = new GroupLayout(panel_set);
        gl_panel_set.setHorizontalGroup(
                gl_panel_set.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_set.createSequentialGroup()
                                .addGroup(gl_panel_set.createParallelGroup(Alignment.LEADING, false)
                                        .addGroup(gl_panel_set.createSequentialGroup()
                                                .addGap(10))
                                        .addGroup(gl_panel_set.createSequentialGroup()
                                                .addGap(20)
                                                .addPreferredGap(ComponentPlacement.UNRELATED))
                                        .addGroup(gl_panel_set.createSequentialGroup()
                                                .addGap(20))
                                        .addGroup(gl_panel_set.createSequentialGroup()
                                                .addGap(20))
                                        .addGroup(gl_panel_set.createSequentialGroup()
                                                .addGap(14))
                                        .addGroup(gl_panel_set.createSequentialGroup()
                                                .addContainerGap()))
                                .addGap(10))
                        .addGroup(gl_panel_set.createSequentialGroup()
                                .addContainerGap()
                                .addContainerGap())
        );
        gl_panel_set.setVerticalGroup(
                gl_panel_set.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_panel_set.createSequentialGroup()
                                .addGap(10)
                                .addGap(10)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGroup(gl_panel_set.createParallelGroup(Alignment.BASELINE))
                                .addGap(12)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGap(12)
                                .addPreferredGap(ComponentPlacement.RELATED)
                                .addGap(14))
        );


        panel_set.setLayout(gl_panel_set);

        JButton button_pause = new JButton("pause");
        button_pause.setFocusable(false);
        button_pause.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(gameTime.isEditable()){
                    // get value from text field and check if it is a number
                    try {
                        int time = Integer.parseInt(gameTime.getText());
                        gameTime.setEditable(false);
                    } catch (NumberFormatException ex) {
                        JOptionPane.showMessageDialog(null, "Please enter a number");
                    }
                }
                snake.changePause();
            }
        });
        button_pause.setFocusPainted(false);

        JButton button_newGame = new JButton("new");
        button_newGame.setFocusable(false);
        button_newGame.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                controller.newGame();

            }
        });
        button_newGame.setFocusPainted(false);
        GroupLayout gl_panel_control = new GroupLayout(panel_control);
        gl_panel_control.setHorizontalGroup(
                gl_panel_control.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_panel_control.createSequentialGroup()
                                .addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(button_newGame, GroupLayout.PREFERRED_SIZE,
                                        95, GroupLayout.PREFERRED_SIZE)
                                .addGap(3)
                                .addComponent(button_pause, GroupLayout.PREFERRED_SIZE,
                                        94, GroupLayout.PREFERRED_SIZE)
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
                                                44, GroupLayout.PREFERRED_SIZE))
                                .addContainerGap(18, Short.MAX_VALUE))
        );
        panel_control.setLayout(gl_panel_control);

        JLabel illustration = new JLabel("illustration");
        illustration.setFocusable(false);
        illustration.setHorizontalAlignment(SwingConstants.CENTER);
        illustration.setHorizontalTextPosition(SwingConstants.CENTER);

        JLabel label = new JLabel("direction key: up, down, left, right");
        label.setFocusable(false);

        JLabel label_1 = new JLabel("space key: start/pause");
        label_1.setFocusable(false);

        JLabel lblShift = new JLabel("Shift key: new game");
        lblShift.setFocusable(false);


        GroupLayout gl_lable = new GroupLayout(lable);
        gl_lable.setHorizontalGroup(
                gl_lable.createParallelGroup(Alignment.TRAILING)
                        .addGroup(gl_lable.createSequentialGroup()
                                .addGroup(gl_lable.createParallelGroup(Alignment.LEADING)
                                        .addComponent(illustration, GroupLayout.PREFERRED_SIZE,
                                                71, GroupLayout.PREFERRED_SIZE)
                                        .addGroup(gl_lable.createSequentialGroup()
                                                .addGap(26)
                                                .addGroup(gl_lable.createParallelGroup(Alignment.LEADING)
                                                        .addComponent(label_1,
                                                                GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                                                        .addComponent(label, GroupLayout.PREFERRED_SIZE,
                                                                113, GroupLayout.PREFERRED_SIZE)
                                                        .addComponent(lblShift))))
                                .addContainerGap())
        );
        gl_lable.setVerticalGroup(
                gl_lable.createParallelGroup(Alignment.LEADING)
                        .addGroup(gl_lable.createSequentialGroup()
                                .addGap(8)
                                .addComponent(illustration, GroupLayout.PREFERRED_SIZE,
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
        //开始游戏
        controller.beginGame();
    }
}