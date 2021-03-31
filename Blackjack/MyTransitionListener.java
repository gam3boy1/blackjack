package Blackjack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyTransitionListener extends ComponentAdapter {
    public static enum gameType {
        WAGER(), BET()
    }
    @Override
    public void componentHidden(ComponentEvent e) {
        JPanel currentPanel = ((JPanel) e.getSource());

        switch (currentPanel.getName()) {
            case "Home":
            HomeGUI home = ((HomeGUI) Main.getGUI());
            Main.setGUI(new GameGUI(home.getGameChoice(), home.getMoneyInput()));
            break;

            case "Game":
            Main.setGUI(new HomeGUI());
            break;
        }
        currentPanel.removeAll();
        currentPanel.getParent().revalidate();
        currentPanel.getParent().repaint();
        currentPanel.getParent().add(Main.getGUI().getPanel(), BorderLayout.CENTER);
    }
}
