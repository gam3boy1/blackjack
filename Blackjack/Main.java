package Blackjack;

import java.awt.*;
import javax.swing.*;

public class Main {
    private static JFrame frame;
    private static JMenuBar menuBar;
    private static JMenu fileMenu;
    private static GUI UI;
    public static void main(String[] args) {
        initializeMenuBar();
        initializeFrame();
        System.out.println('\u2660');
    }

    private static void initializeFrame() {
        frame = new JFrame();
        UI = new HomeGUI();
        frame.add(UI.getPanel(), BorderLayout.CENTER);
        frame.setJMenuBar(menuBar);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setExtendedState(Frame.MAXIMIZED_BOTH);
        frame.setTitle("Blackjack");
        frame.pack();
        frame.setVisible(true);
    }

    private static void initializeMenuBar() {
        menuBar = new JMenuBar();
        fileMenu = new JMenu("File");
        fileMenu.add(new JMenuItem("New/Restart"));
        fileMenu.add(new JMenuItem("Show all stats"));
        fileMenu.add(new JMenuItem("Show/hide basic stats"));
        fileMenu.add(new JMenuItem("View solution"));

        menuBar.add(fileMenu);

        for (Component menu : menuBar.getComponents()) {
            for (Component item : ((JMenu) menu).getMenuComponents()) {
                item.setName(((JMenuItem) item).getText());
                ((JMenuItem) item).addActionListener(new MyMenuListener());
            }
        }

        System.setProperty("apple.laf.useScreenMenuBar", "true");
        System.setProperty("com.apple.mrj.application.apple.menu.about.name", "Stack");
    }

    public static void setMenuVisibility(boolean bool) {
        for (Component menu : menuBar.getComponents()) {
            for (Component item : ((JMenu) menu).getMenuComponents()) {
                ((JMenuItem) item).setEnabled(bool);
            }
        }
    }

    public static void setGUI(GUI ui) {
        Main.UI = ui;
    }

    public static JFrame getFrame() {
        return Main.frame;
    }

    public static GUI getGUI() {
        return Main.UI;
    }
}