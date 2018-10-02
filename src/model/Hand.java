package model;

import java.util.ArrayList;

public class Hand extends ArrayList<Card> {

    public Hand() {

    }

    public Card getLast() {
        return get(size() - 1);
    }

    public int getTotal() {
        return this.stream().mapToInt(Card::getValue).sum();
    }
}
