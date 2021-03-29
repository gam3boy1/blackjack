package Blackjack;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.io.UnsupportedEncodingException;


public class GameGUI extends GUI implements ActionListener {

  private Hand player, dealer;
  private JLabel moneyLabel;
  private JLabel playerValueLabel, dealerValueLabel;
  private JPanel gamePanel, playerPanel, dealerPanel, playerCardPanel, dealerCardPanel;
  private JButton hitButton, stayButton;
  private double money = 100.0;
  private double betMoney;

  public GameGUI(double betMoney) {
    super("Game");
    Main.setMenuVisibility(true);
    this.betMoney = betMoney;
    Deck.initDeck();
    Deck.shuffle();

    player = new Player();
    dealer = new Dealer();
    moneyLabel = new JLabel();
    gamePanel = new JPanel();
    playerPanel = new JPanel();
    dealerPanel = new JPanel();
    playerCardPanel = new JPanel();
    playerValueLabel = new JLabel();
    dealerCardPanel = new JPanel();
    dealerValueLabel = new JLabel();

    Dimension frameSize = Main.getFrame().getSize();
    gamePanel.setLayout(new GridLayout(1, 0));
    playerPanel.setLayout(new GridLayout(0, 1));
    dealerPanel.setLayout(new GridLayout(0, 1));
    playerCardPanel.setLayout(new GridLayout(1, 0));
    dealerCardPanel.setLayout(new GridLayout(1, 0));

    hitButton = new JButton("Hit!");
    stayButton = new JButton("Stay!");
    hitButton.addActionListener(this);
    stayButton.addActionListener(this);

    JPanel tempPanel = new JPanel();
    tempPanel.setLayout(new GridLayout(0, 1));
    tempPanel.add(hitButton);
    tempPanel.add(stayButton);
    gamePanel.setBorder(new EmptyBorder(0, 0, 100, 0));
    tempPanel.setBorder(new EmptyBorder(100, 0, 0, 0));

    hitButton.doClick();
    hitButton.doClick();
    addCard(dealerCardPanel, dealer);
    addCard(dealerCardPanel, dealer);

    playerPanel.add(playerValueLabel);
    playerPanel.add(playerCardPanel);
    dealerPanel.add(dealerValueLabel);
    dealerPanel.add(dealerCardPanel);
    gamePanel.add(playerPanel);
    gamePanel.add(dealerPanel);
    
    playerPanel.remove(playerValueLabel);
    dealerPanel.remove(dealerValueLabel);

    this.getPanel().add(moneyLabel);
    this.getPanel().add(gamePanel);
    this.getPanel().add(tempPanel);

    setComponentAlignment(playerPanel);
    setComponentAlignment(dealerPanel);
    this.setComponentAlignment();
  }

  @Override
  public void actionPerformed(ActionEvent e) {
    JButton button = ((JButton) e.getSource());

    if (button.getText().equalsIgnoreCase("hit!")) {
      addCard(playerCardPanel, player);

      if (player.didLose()) {
        showMessage("Dealer Won", "Sorry, the dealer won the game, because your deck value went above 21. Your deck value was: " + player.getValue());
        money -= betMoney;
        restartGame();
      }
    } else {
      showMessage("Dealer's turn", "The dealer will now keep hitting until the value of his cards is greater than 16. Currently the value of his deck is: " + dealer.getValue());
      ((Dealer) dealer).hitTillDone();
      if (dealer.didLose() || (player.getValue() > dealer.getValue())) {
        showMessage("You won!", "The dealer got a value greater than 21! The value of his deck is: " + dealer.getValue());
        money += betMoney;
      } else {
        showMessage("Dealer Won", "Sorry, the dealer was able to have a deck value greater than 16 and less, that was greater than the value of your hand/deck. The dealer had a value of: " + dealer.getValue());
        money -= betMoney;
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
      return;
    } else {
      playerCardPanel.removeAll();
      dealerCardPanel.removeAll();
      hitButton.doClick();
      hitButton.doClick();
      addCard(dealerCardPanel, dealer);
      addCard(dealerCardPanel, dealer);
    }
  } 

  // Benefits of polymorphism
  private void addCard(JPanel panel, Hand hand) {
    JLabel newCardLabel = new JLabel(hand.hit().getUnicode());
    newCardLabel.setFont(new Font(GUI.defaultFont.getName(), Font.PLAIN, 150));
    panel.add(newCardLabel);
    newCardLabel.setHorizontalAlignment(JLabel.CENTER);
    panel.validate();
    panel.repaint();

    moneyLabel.setText(String.format("Total Money: $%.2f", money));
    playerValueLabel.setText("Total value of your deck: " + player.getValue());
    dealerValueLabel.setText("Total value of the dealer's deck: " + dealer.getValue());
  }
}