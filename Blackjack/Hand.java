package Blackjack;

import java.util.ArrayList;

public abstract class Hand {
  private ArrayList<Card> hand;
  private int value;

  public Hand() {
    this.hand = new ArrayList<Card>();
    this.value = 0;
  }

  public Card hit() {
    Card card = Deck.drawCard();
    hand.add(card);
    value += card.getBlackJackId();
    return card;
  }

  public ArrayList<Card> getHand() {
    return this.hand;
  }

  public int getValue() {
    return this.value;
  }

  public abstract boolean didLose();

  public ArrayList<Card> getPlayerDeck() {
    return this.hand;
  }
}

class Dealer extends Hand {

  public void hitTillDone() {
    while (this.getValue() <= 16) {
      // System.out.println(this.getValue());
      hit();
    }
  }

  @Override
  public boolean didLose() {
    return this.getValue() < 16 || this.getValue() > 21;
  }
}

class Player extends Hand {

  public Player() {
    super();
  }

  @Override
  public boolean didLose() {
    return this.getValue() > 21;
  }
}

