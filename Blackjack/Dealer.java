package Blackjack;

public class Dealer extends Hand {

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
