package Blackjack;

public class Card {
    private String id;
    private char suite;
    private int sortId;

    public Card(String id, char suite, int sortId) {
        this.id = id;
        this.suite = suite;
        this.sortId = sortId;
    }

    @Override
    public String toString() {
        return suite + id;
    }

    public String getId() {
        return this.id;
    }

    public int getBlackJackId() {
        int temp;
        try {
            temp = Integer.parseInt(this.id);
        } catch (Exception e) {
            if (this.id.equals("A")) {
                temp = 1;
            } else {
                temp = 10;
            }
        }
        return temp;
    }

    public char getSuite() {
        return this.suite;
    }

    public int getSortId() {
        return this.sortId;
    }

}
