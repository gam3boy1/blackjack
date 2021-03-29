package Blackjack;

public class Player extends Hand {

  public Player() {
    super();
  }

  @Override
  public boolean didLose() {
    return this.getValue() > 21;
  }
}
