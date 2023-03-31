package wukongdev;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.Random;

import javax.swing.*;

public class controlPanel extends JPanel implements ActionListener {
    private JTextField getInfoUser;
    private ImageIcon iconLogo;
    private JLabel logo;
    private JLabel info;
    private JLabel userID;
    private JButton doneID;
    private gamePanel gamePanel;

    static final int DELAY = 75;
    static boolean running = false;

    Font customFontMinecraft;
    Font customFont04B;
    Random random;

    controlPanel(gamePanel gamePanel) {
        this.gamePanel = gamePanel;

        System.out.println("Main " + running);

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

        logo = new JLabel(scaledIcon);
        logo.setText("Snake Game");
        logo.setHorizontalTextPosition(SwingConstants.RIGHT);
        logo.setVerticalTextPosition(SwingConstants.CENTER);
        logo.setHorizontalAlignment(SwingConstants.CENTER);
        logo.setVerticalAlignment(SwingConstants.CENTER);
        logo.setFont(customFont04B.deriveFont(Font.BOLD, 25f));
        logo.setIconTextGap(25);
        // logo.setBackground(Color.BLUE);
        // logo.setOpaque(true);

        info = new JLabel();
        info.setText("Wecome to snakeGame");
        info.setHorizontalAlignment(SwingConstants.CENTER);
        info.setVerticalAlignment(SwingConstants.TOP);
        info.setFont(customFontMinecraft.deriveFont(Font.PLAIN, 12f));

        userID = new JLabel("Tên Người Chơi: ");
        int xStringUserID = userID.getText().length();
        userID.setBounds(ALLBITS, ABORT, 100, 20);

        getInfoUser = new JTextField();
        getInfoUser.setEditable(true);
        getInfoUser.setBounds(ALLBITS + xStringUserID * 7 - 10, ABORT, 100, 20);

        doneID = new JButton("Lưu");
        doneID.setFocusable(false);
        doneID.setBackground(Color.WHITE);
        doneID.setBorder(getBorder());
        doneID.setVerticalAlignment(SwingConstants.CENTER);
        doneID.setBounds(ALLBITS + xStringUserID * 7 - 10 + 120, ABORT, 75, 20);
        doneID.addActionListener(this);
        doneID.setEnabled(true);

        this.setPreferredSize(new Dimension(400, 800));
        this.setBackground(Color.LIGHT_GRAY);
        this.setLayout(new BorderLayout(20, 10));
        this.setBorder(BorderFactory.createEmptyBorder(50, 0, 0, 0));

        this.add(userID, BorderLayout.CENTER);
        this.add(getInfoUser, BorderLayout.CENTER);
        this.add(doneID, BorderLayout.CENTER);
        this.add(logo, BorderLayout.NORTH);
        this.add(info, BorderLayout.CENTER);

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
                userID.setBounds(ALLBITS, ABORT, 130, 20);
                getInfoUser.setBounds(ALLBITS + userID.getText().length() * 7 - 10, ABORT, 100, 20);
                doneID.setBounds(ALLBITS + userID.getText().length() * 7 + 110, ABORT, 75, 20);
            } else {
                getInfoUser.setEditable(false);
                doneID.setEnabled(false);

                userID.setVisible(false);
                getInfoUser.setVisible(false);
                doneID.setVisible(false);
                info.setText("Wecome back " + getInfoUser.getText() + " !!!");
                gamePanel.addKeyListener(new controlReceiver());

                System.out.println(getInfoUser.getText());
                System.out.println(gamePanel.xDirection);
                System.out.println("Running Button " + running);

                startGame();
            }
        }
    }
}
