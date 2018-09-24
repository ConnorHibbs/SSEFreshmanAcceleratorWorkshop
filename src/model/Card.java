package model;

public class Card {

    public enum Suit {
        NULL, SPADES, HEARTS, CLUBS, DIAMONDS;

        @Override
        public String toString() {
            return name();
        }
    }

    public enum Number {
        _NULL, _A, _2, _3, _4, _5, _6, _7, _8, _9, _10, _J, _Q, _K;

        @Override
        public String toString() {
            return name().substring(1);
        }
    }

    private Number number;
    private Suit suit;
    private int value;

    public Card(int number, int suit) {
        this(Number.values()[number], Suit.values()[suit]);
    }

    public Card(Number number, Suit suit) {
        this.number = number;
        this.suit = suit;
        this.value = convertCardToValue(number);
    }

    public static int convertCardToValue(Number card) {
        switch(card) {
            case _A: return 11;
            case _J:
            case _Q:
            case _K: return 10;
            default: return card.ordinal();
        }
    }

    public Number getNumber() {
        return number;
    }

    public void setNumber(Number number) {
        this.number = number;
    }

    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String toString() {
        return value + " of " + suit;
    }
}
