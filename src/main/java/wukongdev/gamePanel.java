package wukongdev;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;

public class gamePanel extends JPanel implements ActionListener {
    private gamePanel xGamePanel;
    private controlPanel controlPanel;
    controlReceiver controlReceiver;

    static final int SCREEN_WIDTH = 800;
    static final int SCREEN_HEIGHT = 800;
    static final int UNIT_SIZE = 10;
    static final int GAME_UNITS = (SCREEN_WIDTH * SCREEN_HEIGHT) / (UNIT_SIZE * UNIT_SIZE);
    final int[] xGame = new int[GAME_UNITS];
    final int[] yGame = new int[GAME_UNITS];
    int bodyParts = 6;
    int applesEaten;
    static int appleX;
    static int appleY;
    static String message;
    Timer timer;

    gamePanel() {
        controlPanel = new controlPanel(xGamePanel);
        controlReceiver = new controlReceiver();
        timer = new Timer(wukongdev.controlPanel.DELAY, (ActionListener) this);

        this.setPreferredSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        this.setBackground(Color.DARK_GRAY);
        this.setFocusable(true);

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        draw(g);
    }

    public void newApple() {
        appleX = controlPanel.random.nextInt((SCREEN_WIDTH / UNIT_SIZE)) * UNIT_SIZE;
        appleY = controlPanel.random.nextInt((SCREEN_HEIGHT / UNIT_SIZE)) * UNIT_SIZE;
    }

    public void draw(Graphics g) {

        if (wukongdev.controlPanel.running) {

            // for (int i = 0; i < SCREEN_HEIGHT / UNIT_SIZE; i++) {
            // g.drawLine(i * UNIT_SIZE, 0, i * UNIT_SIZE, SCREEN_HEIGHT);
            // g.drawLine(0, i * UNIT_SIZE, SCREEN_WIDTH, i * UNIT_SIZE);
            // }

            g.setColor(Color.RED);
            g.fillOval(appleX, appleY, UNIT_SIZE, UNIT_SIZE);

            for (int i = 0; i < bodyParts; i++) {
                if (i == 0) {
                    g.setColor(Color.GRAY);
                    g.fillRect(xGame[i], yGame[i], UNIT_SIZE, UNIT_SIZE);
                } else {
                    g.setColor(Color.LIGHT_GRAY);
                    // g.setColor(new
                    // Color(controlPanel.random.nextInt(255),controlPanel.random.nextInt(255),controlPanel.random.nextInt(255)));
                    g.fillRect(xGame[i], yGame[i], UNIT_SIZE, UNIT_SIZE);
                }
            }
            // g.setColor(Color.red);
            // g.setFont(new Font("Ink Free", Font.BOLD, 40));
            // FontMetrics metrics = getFontMetrics(g.getFont());
            // g.drawString("Score: " + applesEaten, (SCREEN_WIDTH -
            // metrics.stringWidth("Score: " + applesEaten)) / 2,
            // g.getFont().getSize());
        } else {
            if (wukongdev.controlPanel.xInfoUser == 0) {
                displayGameStart(g);
            } else {
                displayGameOver(g);
            }
        }

    }

    public void move() {
        for (int i = bodyParts; i > 0; i--) {
            xGame[i] = xGame[i - 1];
            yGame[i] = yGame[i - 1];
        }

        switch (wukongdev.controlReceiver.direction) {
            case 'U':
                yGame[0] = yGame[0] - UNIT_SIZE;
                break;
            case 'D':
                yGame[0] = yGame[0] + UNIT_SIZE;
                break;
            case 'L':
                xGame[0] = xGame[0] - UNIT_SIZE;
                break;
            case 'R':
                xGame[0] = xGame[0] + UNIT_SIZE;
                break;
            default:
                break;
        }

    }

    public void checkApple() {
        if ((xGame[0] == appleX) && (yGame[0] == appleY)) {
            bodyParts++;
            applesEaten++;
            newApple();
        }
    }

    public void checkCollisions() {
        // checks if head collides with body
        for (int i = bodyParts; i > 0; i--) {
            if ((xGame[0] == xGame[i]) && (yGame[0] == yGame[i])) {
                wukongdev.controlPanel.running = false;
                message = "Game Over";
            }
        }
        // check if head touches left border
        if (xGame[0] < 0) {
            wukongdev.controlPanel.running = false;
            message = "Game Over";
        }
        // check if head touches right border
        if (xGame[0] > SCREEN_WIDTH) {
            wukongdev.controlPanel.running = false;
            message = "Game Over";
        }
        // check if head touches top border
        if (yGame[0] < 0) {
            wukongdev.controlPanel.running = false;
            message = "Game Over";
        }
        // check if head touches bottom border
        if (yGame[0] > SCREEN_HEIGHT) {
            wukongdev.controlPanel.running = false;
            message = "Game Over";
        }

        if (!wukongdev.controlPanel.running) {
            timer.stop();
        }
    }

    private void displayGameOver(Graphics g) {
        // // Score
        // g.setColor(Color.red);
        // g.setFont(new Font("Ink Free", Font.BOLD, 40));
        // FontMetrics metrics1 = getFontMetrics(g.getFont());
        // g.drawString("Score: " + applesEaten, (SCREEN_WIDTH -
        // metrics1.stringWidth("Score: " + applesEaten)) / 2,
        // g.getFont().getSize());
        // Game Over text
        g.setColor(Color.red);
        g.setFont(controlPanel.customFont04B.deriveFont(Font.PLAIN, 50f));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString(message, (SCREEN_WIDTH - metrics2.stringWidth(message)) / 2, SCREEN_HEIGHT / 2);
    }

    private void displayGameStart(Graphics g) {
        g.setColor(Color.CYAN);
        g.setFont(controlPanel.customFont04B.deriveFont(Font.PLAIN, 50f));
        FontMetrics metrics2 = getFontMetrics(g.getFont());
        g.drawString("Game Start", (SCREEN_WIDTH - metrics2.stringWidth("Game Start")) / 2, SCREEN_HEIGHT / 2);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (wukongdev.controlPanel.running) {
            move();
            checkApple();
            checkCollisions();
        }
        repaint();

    }
}
