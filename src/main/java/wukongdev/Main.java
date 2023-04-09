package wukongdev;

public class Main {
    static Gui gameGui;

    public static void main(String[] args) {
        gameGui = new Gui();

        gameGui.pack();
        gameGui.setVisible(true);
        gameGui.setLocationRelativeTo(null);
    }
}
