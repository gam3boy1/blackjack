package Blackjack;

import javax.swing.*;
import java.awt.event.*;

public class MyMenuListener extends AbstractAction {
  private static final long serialVersionUID = 1L;

  @Override
    public void actionPerformed(ActionEvent e) {
        JMenuItem menuItem = ((JMenuItem) e.getSource());
        switch (menuItem.getName().toLowerCase()) {

            default:
            Main.getGUI().getPanel().setVisible(false);
            break;
        }        
    }
  
}
