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
    private JTextField getInfoUser;
    private JButton doneID;
    private JScrollPane jScrollPane;
    private gamePanel gamePanel;
    private JLabel headerTable;
    private scoreRating scoreRating;

    static final int DELAY = 75;
    static boolean running = false;
    static int xInfoUser;

    int xTextField;

    Font customFontMinecraft;
    Font customFont04B;
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
        } catch (IOException | FontFormatException e) {
            e.printStackTrace();
        }

        this.iconLogo = new ImageIcon(getClass().getResource("imagesGame/logoWukong.png"));

        Image imageIconLogo = iconLogo.getImage();
        double aspectRatio = (double) imageIconLogo.getWidth(null) / imageIconLogo.getHeight(null);
        Image scaledImage = imageIconLogo.getScaledInstance(35, (int) (35 / aspectRatio), Image.SCALE_SMOOTH);
        ImageIcon scaledIcon = new ImageIcon(scaledImage);

        headerTable = new JLabel();
        headerTable.setText("Top Score");
        headerTable.setFont(customFont04B.deriveFont(Font.BOLD, 15f));
        headerTable.setHorizontalAlignment(SwingConstants.CENTER);
        headerTable.setBounds(0, 650, 400, 15);

        jScrollPane.setPreferredSize(new Dimension(400, 103));
        jScrollPane.setBounds(25, 670, 350, 105);

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
        info.setBounds(0, 95, 400, 20);
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setVerticalAlignment(SwingConstants.TOP);
        info.setFont(customFontMinecraft.deriveFont(Font.PLAIN, 12f));

        userID = new JLabel("Tên Người Chơi: ");
        userID.setBounds(ALLBITS, ABORT, 100, 20);
        FontMetrics metrics1 = getFontMetrics(userID.getFont());
        xTextField = metrics1.stringWidth(userID.getText());

        getInfoUser = new JTextField();
        getInfoUser.setBounds(ALLBITS + xTextField + 20, ABORT, 100, 20);

        doneID = new JButton("Lưu");
        doneID.setFocusable(false);
        doneID.setBackground(Color.WHITE);
        doneID.setBorder(getBorder());
        doneID.setBounds(ALLBITS + xTextField + 140, ABORT, 75, 20);
        doneID.addActionListener(this);
        doneID.setEnabled(true);

        this.setPreferredSize(new Dimension(400, 800));
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(null);

        this.add(userID);
        this.add(getInfoUser);
        this.add(doneID);
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

    public void stopGame() {
        running = false;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == doneID) {
            if (getInfoUser.getText().length() == 0) {
                userID.setText("Nhập Tên Người Chơi: ");

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
                // scoreRating.setVisible(true);
                startGame();
            }
        }
    }
}
