package Blackjack;

import javax.swing.*;

import Blackjack.MyTransitionListener.gameType;

import java.awt.Font;
import java.awt.event.*;

public class HomeGUI extends GUI implements ActionListener {
  private JLabel introLabel, infoLabel;
  private JButton onePlayerButton;
  private double moneyInput;
  private gameType gameChoice;
  private static String[] gameOptions = {"Wager", "Bet", "Cancel"};
  
  public HomeGUI() {
    super("Home");
    Main.setMenuVisibility(false);

    introLabel = new JLabel("Welcome to Blackjack!");
    infoLabel = new JLabel("Type the amount of money you would like to bet: ");
    onePlayerButton = new JButton("Play!");

    introLabel.setFont(new Font(introLabel.getFont().getName(), Font.PLAIN, 60));
    onePlayerButton.addActionListener(this);

    this.getPanel().add(introLabel);
    this.getPanel().add(infoLabel);
    this.getPanel().add(onePlayerButton);

    this.setComponentAlignment();
  }

  public gameType getGameChoice() {
    return this.gameChoice;
  }

  public double getMoneyInput() {
    return this. moneyInput;
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    int result = JOptionPane.showOptionDialog(Main.getFrame(),  "Would you like to wager or bet?", "Wager or Bet?", 
    JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE, null, gameOptions, gameOptions[2]);

    if (result == JOptionPane.YES_OPTION || result == JOptionPane.NO_OPTION) {
      boolean exceptionOccured;
      do {
        exceptionOccured = false;
        String input = JOptionPane.showInputDialog(Main.getFrame(), "Enter the amount of money you would like to " + gameOptions[result].toLowerCase() + ":");
        if (input.isEmpty()) { break; }
        try {
          moneyInput = Double.parseDouble(input);
          gameChoice = (result == JOptionPane.YES_OPTION)? gameType.WAGER : gameType.BET;
          this.getPanel().setVisible(false);
        } catch (Exception exception){
          exceptionOccured = true;
          showMessage("Error!", "Please only enter doubles/integers!");
        }
      } while (exceptionOccured);
      
    }
  }
}