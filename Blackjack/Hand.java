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

  public boolean didLose() {
    return this.value > 21;
  }

}
