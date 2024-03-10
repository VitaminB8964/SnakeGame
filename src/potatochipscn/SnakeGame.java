package potatochipscn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.File;
import java.util.Random;


public class SnakeGame extends JFrame implements ActionListener {
    private final int WIDTH = 500; // 游戏窗口的宽度
    private final int HEIGHT = 500; // 游戏窗口的高度
    private final int UNIT_SIZE = 10; // 蛇身和食物单元的大小
    private final int GAME_UNITS = (WIDTH * HEIGHT) / (UNIT_SIZE * UNIT_SIZE); // 游戏区域内的单位数量
    private final int DELAY = 75; // 游戏刷新速度
    private final int[] x = new int[GAME_UNITS]; // 蛇身的 x 坐标
    private final int[] y = new int[GAME_UNITS]; // 蛇身的 y 坐标
    private int snakeLength = 6; // 蛇的初始长度
    private int foodEaten = 0; // 吃掉的食物数量
    private int foodX; // 食物的 x 坐标
    private int foodY; // 食物的 y 坐标
    private char direction = 'D'; // 蛇的初始方向
    private boolean isRunning = false; // 游戏是否正在运行
    private Timer timer;

    public SnakeGame() {
        setTitle("贪吃蛇游戏");
        setSize((WIDTH), (HEIGHT));
        setResizable(false);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);
        setLocationRelativeTo(null);

        addKeyListener(new MyKeyAdapter());
        startGame();

    }

    public void startGame() {
        initializeSnake();
        placeFood();

        isRunning = true;
        timer = new Timer(DELAY, this);
        timer.start();
    }

    public void initializeSnake() {
        for (int i = 0; i < snakeLength; i++) {
            x[i] = 50 - i * UNIT_SIZE;
            y[i] = 50;
        }
    }

    public void placeFood() {
        Random random = new Random();
        foodX = random.nextInt((WIDTH-50) / UNIT_SIZE) * UNIT_SIZE;
        foodY = random.nextInt((HEIGHT-50) / UNIT_SIZE) * UNIT_SIZE;
    }

    public void move() {
        for (int i = snakeLength; i > 0; i--) {
            x[i] = x[i - 1];
            y[i] = y[i - 1];
        }

        switch (direction) {
            case 'U':
                y[0] = y[0] - UNIT_SIZE;
                break;
            case 'D':
                y[0] = y[0] + UNIT_SIZE;
                break;
            case 'L':
                x[0] = x[0] - UNIT_SIZE;
                break;
            case 'R':
                x[0] = x[0] + UNIT_SIZE;
                break;
        }
    }

    public void checkFoodCollision() {
        if (x[0] == foodX && y[0] == foodY) {
            snakeLength++;
            foodEaten++;
            placeFood();
        }
    }

    public void checkWallCollision() {
        if (x[0] < 0 || x[0] >= WIDTH || y[0] < 0 || y[0] >= HEIGHT) {
            isRunning = false;
        }
    }

    public void checkSelfCollision() {
        for (int i = snakeLength; i > 0; i--) {
            if (x[0] == x[i] && y[0] == y[i]) {
                isRunning = false;
            }
        }
    }

    public void gameOver() {
        timer.stop();
        JOptionPane.showMessageDialog(this, "游戏结束！你吃掉了 " + foodEaten + " 个食物。", "游戏结束", JOptionPane.INFORMATION_MESSAGE);
        foodEaten =0;direction='D';snakeLength=6;
            startGame();
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);

        if (isRunning) {
            // 绘制食物
            g.setColor(Color.red);
            g.fillRect(foodX, foodY, UNIT_SIZE, UNIT_SIZE);

            // 绘制蛇身
            for (int i = 0; i < snakeLength; i++) {
                if (i == 0) {
                    g.setColor(Color.GREEN);
                } else {
                    g.setColor(Color.GREEN.darker());
                }
                g.fillRect(x[i], y[i], UNIT_SIZE, UNIT_SIZE);
            }

            Toolkit.getDefaultToolkit().sync();
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (isRunning) {
            move();
            checkFoodCollision(); checkWallCollision();checkSelfCollision();
        }

        if (!isRunning) {
            gameOver();
        }
        repaint();
    }

    public class MyKeyAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            switch (e.getKeyCode()) {
                case KeyEvent.VK_UP:
                    if (direction != 'D') {
                        direction = 'U';
                    }
                    break;
                case KeyEvent.VK_DOWN:
                    if (direction != 'U') {
                        direction = 'D';
                    }
                    break;
                case KeyEvent.VK_LEFT:
                    if (direction != 'R') {
                        direction = 'L';
                    }
                    break;
                case KeyEvent.VK_RIGHT:
                    if (direction != 'L') {
                        direction = 'R';
                    }
                    break;
                case KeyEvent.VK_R:
                    gameOver();
                    break;
                case KeyEvent.VK_HOME:
                    foodEaten+=114514;
                    System.out.println("你的蛇蛇吃饭了！");
                    break;
                case KeyEvent.VK_INSERT:
                    snakeLength+=3;
                    System.out.println("你的蛇蛇变长了！");

            }
        }
    }

    public static void main(String[] args) {
        new SnakeGame();
    }
}
