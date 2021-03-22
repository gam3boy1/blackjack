package Blackjack;

import java.util.ArrayList;

public class Player extends Hand {
  private int money;

  public Player(int money) {
    super();
    this.money = money;
  }
  
  public int getMoney() {
    return this.money;
  }
}
