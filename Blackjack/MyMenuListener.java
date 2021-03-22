package Blackjack;

import javax.swing.*;
import java.awt.event.*;

public class MyMenuListener extends AbstractAction {
  private static final long serialVersionUID = 1L;

  @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = ((JMenuItem) e.getSource());
        switch (menuItem.getName()) {
            case "Show all stats":
            // ((GameGUI) Main.getGUI()).showStats();
            break;

            case "Show/hide basic stats":
            // ((GameGUI) Main.getGUI()).setBasicStatsVisibility();
            break;

            case "View solution":
            // ((GameGUI) Main.getGUI()).showSolution();
            break;

            default:
            Main.getGUI().getPanel().setVisible(false);
            break;
        }        
    }
  
}
