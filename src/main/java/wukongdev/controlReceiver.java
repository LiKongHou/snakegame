package wukongdev;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class controlReceiver extends KeyAdapter {
    static char direction = 'R';

    @Override
    public void keyPressed(KeyEvent e) {
        switch (e.getKeyCode()) {
            case KeyEvent.VK_LEFT:
                if (direction != 'R') {
                    direction = 'L';
                    // System.out.println(direction);
                }
                break;
            case KeyEvent.VK_RIGHT:
                if (direction != 'L') {
                    direction = 'R';
                    // System.out.println(direction);
                }
                break;
            case KeyEvent.VK_UP:
                if (direction != 'D') {
                    direction = 'U';
                    // System.out.println(direction);
                }
                break;
            case KeyEvent.VK_DOWN:
                if (direction != 'U') {
                    direction = 'D';
                    // System.out.println(direction);
                }
                break;
            default:
                // System.out.println("Hi");
                break;
        }

    }

}
