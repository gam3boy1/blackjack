package Blackjack;

import javax.swing.*;
import javax.swing.border.EmptyBorder;

import Blackjack.MyTransitionListener.gameType;

import java.awt.*;
import java.awt.event.*;

public class GameGUI extends GUI implements ActionListener {

  private Hand player, dealer;
  private JLabel moneyLabel;
  private JPanel gamePanel, buttonPanel, playerPanel, dealerPanel;
  private JButton hitButton, stayButton;
  private double moneyAmount, money;
  private gameType moneyType;

  public GameGUI(gameType moneyType, double moneyAmount) {
    super("Game");
    Main.setMenuVisibility(true);
    this.moneyAmount = moneyAmount;
    if (moneyType == gameType.WAGER) {
      money = moneyAmount;
    } else {
      money = 100.0;
    }
    Deck.initDeck();
    Deck.shuffle();

    player = new Player();
    dealer = new Dealer();
    moneyLabel = new JLabel();
    gamePanel = new JPanel();
    buttonPanel = new JPanel();
    playerPanel = new JPanel();
    dealerPanel = new JPanel();

    gamePanel.setLayout(new GridLayout(1, 0));
    playerPanel.setLayout(new GridLayout(1, 0));
    dealerPanel.setLayout(new GridLayout(1, 0));

    hitButton = new JButton("Hit!");
    stayButton = new JButton("Stay!");
    hitButton.addActionListener(this);
    stayButton.addActionListener(this);

    buttonPanel.setLayout(new GridLayout(0, 1));
    buttonPanel.add(hitButton);
    buttonPanel.add(stayButton);
    buttonPanel.setBorder(new EmptyBorder(100, 0, 0, 0));

    addCard(playerPanel, player);
    addCard(playerPanel, player);
    addCard(dealerPanel, dealer);
    addCard(dealerPanel, dealer);

    gamePanel.add(playerPanel);
    gamePanel.add(dealerPanel);

    this.getPanel().add(moneyLabel);
    this.getPanel().add(gamePanel);
    this.getPanel().add(buttonPanel);

    setComponentAlignment(playerPanel);
    setComponentAlignment(dealerPanel);
    this.setComponentAlignment();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton button = ((JButton) e.getSource());

    if (button.getText().equalsIgnoreCase("hit!")) {
      addCard(playerPanel, player);

      if (player.didLose()) {
        showMessage("Dealer Won", "Sorry, the dealer won the game, because your deck value went above 21. " + 
        "\nYour deck value was: " + player.getValue());
        loseMoney();
        restartGame();
      }
    } else {
      showMessage("Dealer's turn", "The dealer will now keep hitting until the value of his cards is greater than 16." + 
      "\nCurrently the value of his deck is: " + dealer.getValue());
      ((Dealer) dealer).hitTillDone();
      if (dealer.didLose() || (player.getValue() > dealer.getValue())) {
        showMessage("You won!", "The dealer got a value greater than 21!" + 
        "\nThe value of his deck is: " + dealer.getValue());
        gainMoney();
      } else if (!dealer.didLose()) {
        showMessage("Dealer won!", "Sorry, the dealer was able to have a deck value greater than 16 and less, that was greater than the value of your hand/deck. " +
        "\nThe dealer had a value of: " + dealer.getValue());
        loseMoney();
      } else {
        if (player.getValue() > dealer.getValue()) {
          showMessage("You Won!", "The value of your hand is greater than the dealer's!" + 
          "\nThe value of your hand was: " + player.getValue() + ", while the value of the dealer's hand was: " + dealer.getValue());
          gainMoney();
        } else {
          showMessage("Dealer won!", "Sorry, the value of the dealer's hand is greater than yours." + 
          "\nThe value of the dealer's hand was: " + dealer.getValue() + ", while the value of your hand was: " + player.getValue());
          loseMoney();
        }
      }
      restartGame();
    }
  }

  private void restartGame() {
    Deck.restart();
    Deck.shuffle();
    player = new Player();
    dealer = new Dealer();
    if (money <= 0) {
      showMessage("You lost all your money!", "Sorry, you lost all of your money! You have to restart the entire game now :(");
      this.getPanel().setVisible(false);
      // return;
    } else {
      playerPanel.removeAll();
      dealerPanel.removeAll();
      hitButton.doClick();
      hitButton.doClick();
      addCard(dealerPanel, dealer);
      addCard(dealerPanel, dealer);
    }
  } 

  private void addCard(JPanel panel, Hand hand) {
    JLabel newCardLabel = new JLabel(hand.hit().getUnicode());
    if (panel.equals(dealerPanel) && hand.equals(dealer) && dealerPanel.getComponentCount() == 0) {
      newCardLabel.setText(new String(Character.toChars(Integer.parseInt("0001F0A0", 16))));
    }

    newCardLabel.setFont(new Font(GUI.defaultFont.getName(), Font.PLAIN, 200));
    newCardLabel.setHorizontalAlignment(JLabel.CENTER);

    panel.add(newCardLabel);
    panel.validate();
    panel.repaint();

    moneyLabel.setText(String.format("Total Money: $%.2f", money));
  }

  private void loseMoney() {
    if (moneyType == gameType.BET) {
      money -= moneyAmount;
    } else {
      money = 0.0;
    }
  }

  private void gainMoney() {
    if (moneyType == gameType.BET) {
      money += moneyAmount;
    } else {
      money *= 2.0;
    }
  }
}