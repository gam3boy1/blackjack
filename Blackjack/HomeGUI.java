package Blackjack;

import javax.swing.*;
import java.awt.Font;
import java.awt.event.*;

public class HomeGUI extends GUI implements ActionListener {
  private JLabel introLabel, infoLabel;
  private JButton onePlayerButton;
  private JTextField moneyTextField;
  
  public HomeGUI() {
    super("Home");
    Main.setMenuVisibility(false);

    introLabel = new JLabel("Welcome to Blackjack!");
    infoLabel = new JLabel("Type the amount of money you would like to bet: ");
    moneyTextField = new JTextField();
    onePlayerButton = new JButton("Play!");

    introLabel.setFont(new Font(introLabel.getFont().getName(), Font.PLAIN, 60));
    onePlayerButton.addActionListener(this);

    this.getPanel().add(introLabel);
    this.getPanel().add(infoLabel);
    this.getPanel().add(moneyTextField);
    this.getPanel().add(onePlayerButton);

    this.setComponentAlignment();
  }

  public int getMoneyInput() {
    return Integer.parseInt(this.moneyTextField.getText());
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    try {
      Integer.parseInt(moneyTextField.getText());
      this.getPanel().setVisible(false);
    } catch (Exception exception){
      showMessage("Error!", "Please input integers only!");
    }
  }
}