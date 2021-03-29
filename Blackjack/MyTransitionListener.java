package Blackjack;

import java.awt.*;
import javax.swing.*;
import java.awt.event.*;

public class MyTransitionListener extends ComponentAdapter {
    @Override
    public void componentHidden(ComponentEvent e) {
        JPanel currentPanel = ((JPanel) e.getSource());
        switch (currentPanel.getName()) {
            case "Home":
            double money = ((HomeGUI) Main.getGUI()).getMoneyInput();
            Main.setGUI(new GameGUI(money));
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
