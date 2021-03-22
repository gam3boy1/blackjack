package Blackjack;

import java.util.ArrayList;

public class Player extends Hand {
  private ArrayList<Card> playerDeck;
  private static int money = 100;

  public Player(int money) {
    super();
    // this.money = money;
  }
  
  public int getMoney() {
    return Player.money;
  }

  @Override
  public boolean didLose() {
    if (super.didLose()) { Player.money -= 10; }
    return super.didLose();
  }

  public void stand() {

  }
}
