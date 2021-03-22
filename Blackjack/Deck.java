package Blackjack;

import java.util.Random;

public class Deck {
    private static Card[] deckArray = new Card[52];
    private static int numOfCardsLeft = 52;

    public static void initDeck() {
        for (int i = 0; i < 4; i++) {
            char suite;
            switch (i) {
                case 0:
                suite = 'C';
                break;

                case 1:
                suite = 'D';
                break;

                case 2:
                suite = 'H';
                break;
                
                default:
                suite = 'S';
                break;
            }
            for (int k = 0; k < 13; k++) {
                String id;
                switch (k) {
                    case 0:
                    id = "A";
                    break;

                    case 9: 
                    id = "10";
                    break;

                    case 10:
                    id = "J";
                    break;

                    case 11:
                    id = "Q";
                    break;

                    case 12:
                    id = "K";
                    break;

                    default:
                    id = String.valueOf(k + 1);
                    break;
                }
                int pos = (13 * i) + k;
                Deck.deckArray[pos] = new Card(id, suite, Deck.deckArray.length - pos + 1);
            }
        }
    }

    public static String getDeckAsString() {
        String s = "";
        if (numOfCardsLeft > 0) {
            for (int i = 1; i <= numOfCardsLeft; i++) {
                s += Deck.deckArray[i-1].toString() + " ";
            }
        } else {
            s = "0";
        }
        return s;
    }

    public static Card drawCard() {
        // Stack Data Structure - First In, First Out (FIFO)
        if (!isEmpty()) {
            Card temp = top();
            pop();
            return temp;
        } else {
            return null;
        }
    }

    public static void dealCards() {
        // int numOfPeople = Player.getPlayers().size();
        // for (int i = 0; i < numOfPeople; i++) {
        //     for (int j = 0; j < Deck.deckArray.length/numOfPeople; j++) {
        //         Player.getPlayers().get(i).getPlayerDeck()[j] = drawCard();
        //     }
        // }
    }

    public static void sortDeck() {
        for (int i = 0; i < numOfCardsLeft; i++) {
            for (int j = i; j > 0; j--) {
                if (Deck.deckArray[j].getSortId() < Deck.deckArray[j-1].getSortId()) {
                    swapCards(j, j-1);
                }
            }
        }
    }

    public static void shuffle() {
        for (int i = 0; i < numOfCardsLeft; i++) {
            int j = new Random().nextInt(i+1);
            swapCards(i, j);
        }
    }

    public static void restart() {
      Deck.numOfCardsLeft = Deck.deckArray.length;
    }

    private static void swapCards(int x, int y) {
        Card temp = Deck.deckArray[x];
        Deck.deckArray[x] = Deck.deckArray[y];
        Deck.deckArray[y] = temp;
    } 

    private static void pop() {
        Deck.numOfCardsLeft--;
    }

    private static Card top() {
        return Deck.deckArray[numOfCardsLeft-1];
    }

    private static boolean isEmpty() {
        return (numOfCardsLeft == 0)? true : false;
    }

    public static int getNumOfCardsLeft() {
        return Deck.numOfCardsLeft;
    }

    public static Card[] getDeckArray() {
        return Deck.deckArray;
    }
}
