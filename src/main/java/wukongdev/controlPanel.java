package wukongdev;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

public class controlPanel extends JPanel implements ActionListener {
    private ImageIcon iconLogo;
    private JLabel logo;
    private JLabel info;
    private JLabel userID;
    private JLabel score;
    private JTextField getInfoUser;
    private JButton doneID;
    private JButton buttonContinue;
    private JButton buttonPause;
    private JButton buttonStart;
    private JScrollPane jScrollPane;
    private gamePanel gamePanel;
    private JLabel headerTable;
    private scoreRating scoreRating;

    static boolean running = false;
    static int xInfoUser;

    int xTextField;

    Font customFontMinecraft;
    Font customFont04B;
    Font customFontLouisGeorgeCafe;
    Random random;

    controlPanel(gamePanel gamePanel) {
        scoreRating = new scoreRating();
        jScrollPane = new JScrollPane(scoreRating);

        this.gamePanel = gamePanel;

        random = new Random();

        try {
            customFontMinecraft = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("fontsGame/Minecraft.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFontMinecraft);

            customFont04B = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("fontsGame/04B.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFont04B);

            customFontLouisGeorgeCafe = Font.createFont(Font.TRUETYPE_FONT,
                    getClass().getResourceAsStream("fontsGame/LouisGeorgeCafe.ttf"));
            GraphicsEnvironment.getLocalGraphicsEnvironment().registerFont(customFontLouisGeorgeCafe);
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.iconLogo = new ImageIcon(getClass().getResource("imagesGame/logoWukong.png"));

        Image imageIconLogo = iconLogo.getImage();
        double aspectRatio = (double) imageIconLogo.getWidth(null) / imageIconLogo.getHeight(null);
        Image scaledImage = imageIconLogo.getScaledInstance(35, (int) (35 / aspectRatio), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        headerTable = new JLabel();
        headerTable.setText("-- Ranking --");
        headerTable.setFont(customFontLouisGeorgeCafe.deriveFont(Font.BOLD, 15f));
        headerTable.setHorizontalAlignment(SwingConstants.CENTER);
        headerTable.setBounds(0, 645, 400, 20);
        headerTable.setVisible(false);

        jScrollPane.setPreferredSize(new Dimension(400, 103));
        jScrollPane.setBounds(25, 670, 350, 105);
        jScrollPane.setBorder(null);
        jScrollPane.setVisible(false);

        logo = new JLabel(scaledIcon);
        logo.setText("Snake Game");
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setVerticalAlignment(SwingConstants.CENTER);
        logo.setFont(customFont04B.deriveFont(Font.BOLD, 25f));
        logo.setIconTextGap(25);
        logo.setBounds(0, 50, 400, 35);
        // logo.setBackground(Color.BLUE);
        // logo.setOpaque(true);

        info = new JLabel();
        info.setText("Wecome to snakeGame");
        info.setBounds(0, 90, 400, 15);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setVerticalAlignment(SwingConstants.CENTER);
        info.setFont(customFontLouisGeorgeCafe.deriveFont(Font.BOLD, 12f));

        userID = new JLabel("Your Name: ");
        userID.setFont(customFontLouisGeorgeCafe.deriveFont(Font.BOLD, 12f));
        userID.setBounds(ALLBITS + 20, ABORT, 100, 20);
        FontMetrics metrics1 = getFontMetrics(userID.getFont());
        xTextField = metrics1.stringWidth(userID.getText());

        getInfoUser = new JTextField();
        getInfoUser.setBounds(ALLBITS + xTextField + 40, ABORT, 100, 20);

        doneID = new JButton("Done");
        doneID.setFont(customFontLouisGeorgeCafe.deriveFont(Font.BOLD, 12f));
        doneID.setFocusable(false);
        doneID.setBackground(Color.WHITE);
        doneID.setBorder(getBorder());
        doneID.setBounds(ALLBITS + xTextField + 160, ABORT, 75, 20);
        doneID.addActionListener(this);
        doneID.setEnabled(true);

        buttonPause = new JButton("Pause");
        buttonPause.setFont(customFontLouisGeorgeCafe.deriveFont(Font.BOLD, 12f));
        buttonPause.setFocusable(false);
        buttonPause.setBackground(Color.WHITE);
        buttonPause.setBorder(getBorder());
        buttonPause.setBounds(100 - (75 / 2), ABORT, 75, 20);
        buttonPause.addActionListener(this);
        buttonPause.setEnabled(false);
        buttonPause.setVisible(false);

        buttonContinue = new JButton("Continue");
        buttonContinue.setFont(customFontLouisGeorgeCafe.deriveFont(Font.BOLD, 12f));
        buttonContinue.setFocusable(false);
        buttonContinue.setBackground(Color.WHITE);
        buttonContinue.setBorder(getBorder());
        buttonContinue.setBounds(100 - (75 / 2), ABORT, 75, 20);
        buttonContinue.addActionListener(this);
        buttonContinue.setEnabled(false);
        buttonContinue.setVisible(false);

        buttonStart = new JButton("Restart");
        buttonStart.setFont(customFontLouisGeorgeCafe.deriveFont(Font.BOLD, 12f));
        buttonStart.setFocusable(false);
        buttonStart.setBackground(Color.WHITE);
        buttonStart.setBorder(getBorder());
        buttonStart.setBounds(300 - (75 / 2), ABORT, 75, 20);
        buttonStart.addActionListener(this);
        buttonStart.setEnabled(false);
        buttonStart.setVisible(false);

        score = new JLabel();
        score.setBounds(0, 175, 400, 20);
        score.setHorizontalAlignment(SwingConstants.CENTER);
        score.setVerticalAlignment(SwingConstants.CENTER);
        score.setFont(customFontLouisGeorgeCafe.deriveFont(Font.PLAIN, 15f));
        score.setVisible(false);

        this.setPreferredSize(new Dimension(400, 800));
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);

        this.add(userID);
        this.add(getInfoUser);
        this.add(doneID);
        this.add(buttonPause);
        this.add(buttonContinue);
        this.add(buttonStart);
        this.add(score);
        this.add(logo);
        this.add(info);
        this.add(headerTable);
        this.add(jScrollPane);

    }

    public void startGame() {
        running = true;
        gamePanel.newApple();
        gamePanel.timer.start();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        score.setText("Your Score: " + wukongdev.gamePanel.applesEaten * 10);

        if (e.getSource() == doneID) {
            if (getInfoUser.getText().length() == 0) {
                userID.setText("Enter Your Name: ");

                FontMetrics metrics1 = getFontMetrics(userID.getFont());
                xTextField = metrics1.stringWidth(userID.getText());

                userID.setBounds(ALLBITS, ABORT, 130, 20);
                getInfoUser.setBounds(ALLBITS + xTextField + 20, ABORT, 100, 20);
                doneID.setBounds(ALLBITS + xTextField + 140, ABORT, 75, 20);
            } else {
                userID.setVisible(false);
                getInfoUser.setVisible(false);
                doneID.setVisible(false);
                info.setText("Wecome back " + getInfoUser.getText() + " !!!");
                gamePanel.addKeyListener(new controlReceiver());
                xInfoUser = getInfoUser.getText().length();
                headerTable.setVisible(true);
                jScrollPane.setVisible(true);
                buttonPause.setEnabled(true);
                buttonPause.setVisible(true);
                buttonStart.setEnabled(true);
                score.setVisible(true);
                Timer timer = new Timer(0, (ActionListener) this);
                timer.start();
                buttonStart.setVisible(true);
                startGame();
            }
        }
        if (e.getSource() == buttonPause) {
            buttonPause.setEnabled(false);
            buttonPause.setVisible(false);
            buttonContinue.setEnabled(true);
            buttonContinue.setVisible(true);
            running = false;
            wukongdev.gamePanel.message = "Pause";
        }
        if (e.getSource() == buttonContinue) {
            buttonContinue.setEnabled(false);
            buttonContinue.setVisible(false);
            buttonPause.setEnabled(true);
            buttonPause.setVisible(true);
            running = true;
            wukongdev.gamePanel.message = "Game Over";
        }
        if (e.getSource() == buttonStart) {
            running = true;
            wukongdev.gamePanel.bodyParts = 6;
            wukongdev.gamePanel.applesEaten = 0;
            gamePanel.newSnake();
            gamePanel.newApple();
            gamePanel.timer.start();
            buttonContinue.setEnabled(false);
            buttonContinue.setVisible(false);
            buttonPause.setEnabled(true);
            buttonPause.setVisible(true);
        }
    }
}
