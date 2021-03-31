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

    public String getUnicode() {
        String unicodeString;
        char suiteChar = ' ', idChar = ' ';
        switch (this.suite) {
            case 'S':
            suiteChar = 'A';
            break;

            case 'H':
            suiteChar = 'B';
            break;

            case 'D':
            suiteChar = 'C';
            break;

            case 'C':
            suiteChar = 'D';
            break;
        };

        switch (this.id) {
            case "10":
            idChar = 'A';
            break;

            case "A":
            idChar = '1';
            break;

            case "J":
            idChar = 'B';
            break;

            case "Q":
            idChar = 'D';
            break;

            case "K":
            idChar = 'E';
            break;

            default:
            idChar = (char) (Integer.parseInt(id) + '0');
            break;
        };
        
        /* 
        Logic:
        
        */
        unicodeString = "0001F0" + suiteChar + idChar;
        int codePoint = Integer.parseInt(unicodeString, 16);
        return new String(Character.toChars(codePoint));
    }

    public char getSuite() {
        return this.suite;
    }

    public int getSortId() {
        return this.sortId;
    }
}
