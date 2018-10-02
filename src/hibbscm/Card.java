package hibbscm;

public class Card {

    private int number;
    private int suit;
    private int value;
    
    public Card(int number, int suit) {
        this.number = number;
        this.suit = suit;
        this.value = convertCardToValue(number);
    }

    public int getNumber(){
        return number;
    }

    public int getSuit(){
        return suit;
    }

    public int getValue(){
        return convertCardToValue(value);
    }

    private static int convertCardToValue(int card) {
        switch(card) {
            case 1: return 11;
            case 11:
            case 12:
            case 13: return 10;
            default: return card;
        }
    }

    @Override
    public String toString(){
        String returnValue = "";
        switch(value) {
            case 1:
                returnValue += "Ace";
                break;
            case 11:
                returnValue += "Jack";
                break;
            case 12:
                returnValue += "Queen";
                break;
            case 13:
                returnValue += "King";
                break;
            default:
                returnValue += value;
        }

        returnValue += " of ";

        switch(suit){
            case 1:
                returnValue += "Clubs";
                break;
            case 2:
                returnValue += "Spades";
                break;
            case 3:
                returnValue += "Hearts";
                break;
            default:
                returnValue += "Diamonds";
                break;
        }

        return returnValue;
    }

    @Override
    public boolean equals(Object obj){
        return obj != null && obj instanceof Card && ((Card)obj).number == number && ((Card)obj).suit == suit;
    }
}
