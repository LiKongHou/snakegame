package wukongdev;


public class Main {
    static Gui gameGui;

    public static void main(String[] args) {
        gameGui = new Gui();

        gameGui.pack();
        gameGui.setVisible(true);
        gameGui.setLocationRelativeTo(null);

        // Dimension size = gameGui.getSize();
        int width = gameGui.getWidth();
        int height = gameGui.getHeight();
        System.out.println("Current size of the frame: " + width + " x " + height);
    }
}
