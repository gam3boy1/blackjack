package Blackjack;

import javax.imageio.ImageIO;
import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.util.ArrayList;


public class GameGUI extends GUI implements ActionListener {

  private Hand player, dealer;
  private JLabel moneyLabel;
  private JLabel valueLabel;
  private JPanel cardPanel;
  private JButton hitButton, stayButton;

  public GameGUI() {
    super("Game");
    Main.setMenuVisibility(true);

    Deck.initDeck();
    Deck.shuffle();
    player = new Player(100);
    dealer = new Dealer();
    moneyLabel = new JLabel();
    valueLabel = new JLabel();
    cardPanel = new JPanel();
    hitButton = new JButton("Hit!");
    stayButton = new JButton("Stay!");

    hitButton.addActionListener(this);
    stayButton.addActionListener(this);

    cardPanel.setLayout(new GridLayout(1, 0));
    // cardPanel.

    hitButton.doClick();
    hitButton.doClick();
    dealer.hit();
    dealer.hit();

    this.getPanel().add(moneyLabel);
    this.getPanel().add(valueLabel);
    this.getPanel().add(cardPanel);
    this.getPanel().add(hitButton);
    this.getPanel().add(stayButton);

    this.setComponentAlignment();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton button = ((JButton) e.getSource());
    if (button.getText().equalsIgnoreCase("hit!")) {
      Card newCard = player.hit();
      JLabel newCardLabel = new JLabel(newCard.toString());
      cardPanel.add(newCardLabel);
      newCardLabel.setHorizontalAlignment(JLabel.CENTER);
      cardPanel.validate();
      cardPanel.repaint();

      if (player.didLose()) {
        showMessage("Dealer Won", "Sorry, the dealer won the game, as your deck value went above 21. Your deck value was: " + player.getValue());
        // this.getPanel().setVisible(false);
        
        return;
      }
    } else {
      showMessage("Dealer's turn", "The dealer will now keep hitting until the value of his cards is greater than 16. Currently the value of his deck is: " + dealer.getValue());
      ((Dealer) dealer).hitTillDone();
      if (!((Dealer) dealer).didWin()) {
        showMessage("You won!", "The dealer got a value greater than 21! The value of his deck is: " + dealer.getValue());
      } else {
        showMessage("Dealer Won", "Sorry, the dealer was able to have a deck value greater than 16 and less. The dealer had a value of: " + dealer.getValue());
      }
      if (((Player) player).getMoney() <= 0) {
        showMessage("You lost all your money!", "Sorry, you lost all of your money! You have to restart the entire game now :(");
        this.getPanel().setVisible(false);
      }
    }
    moneyLabel.setText("Total money: " + ((Player) player).getMoney());
    valueLabel.setText("Total value of deck: " + player.getValue());
  }

  private void showMessage(String title, String message) {
    JOptionPane.showMessageDialog(Main.getFrame(), message, title, JOptionPane.OK_OPTION);
  }
}