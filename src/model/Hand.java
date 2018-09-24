package model;

import java.util.ArrayList;

public class Hand extends ArrayList<Card> {

    public Hand() {

    }

    public int getTotal() {
        return this.stream().mapToInt(Card::getValue).sum();
    }
}
