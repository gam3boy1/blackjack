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
            int money = ((HomeGUI) Main.getGUI()).getMoneyInput();
            Main.setGUI(new GameGUI(money));
            break;

            case "Game":
            Main.setGUI(new HomeGUI());
            break;
        }
        currentPanel.removeAll();
        Main.getFrame().validate();
        Main.getFrame().repaint();
        Main.getFrame().add(Main.getGUI().getPanel(), BorderLayout.CENTER);
    }
}
