package Blackjack;

public class Dealer extends Hand {

  public void hitTillDone() {
    while (this.getValue() <= 16) {
      System.out.println(this.getValue());
      hit();
    }
  }

  public boolean didWin() {
    return this.getValue() > 16 && this.getValue() < 21;
  }
}
