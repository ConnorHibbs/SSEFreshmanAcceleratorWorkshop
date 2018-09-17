package hibbscm;

public class Card {

    public int number;
    public int suit;
    public int value;
    
    public Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
        this.value = convertCardToValue(number);
    }

    public static int convertCardToValue(int card) {
        switch(card) {
            case 1: return 11;
            case 11:
            case 12:
            case 13: return 10;
            default: return card;
        }
    }
}
