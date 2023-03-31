package wukongdev;

import java.awt.FlowLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class Gui extends JFrame {
    private gamePanel gamePanel;
    private controlPanel controlPanel;

    Gui() {

        gamePanel = new gamePanel();
        controlPanel = new controlPanel(gamePanel);

        this.add(gamePanel);
        this.add(controlPanel);
        this.setIconImage(new ImageIcon(getClass().getResource("imagesGame/logoWukong.png")).getImage());
        this.setTitle("Wukong.dev snakeGame");
        this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
        this.setLayout(new FlowLayout());
        this.setResizable(false);

    }
}
