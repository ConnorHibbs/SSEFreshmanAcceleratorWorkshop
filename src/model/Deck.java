package model;

import java.util.Collections;
import java.util.Stack;

public class Deck extends Stack<Card> {

    public Deck() {
        for(int s = 1; s <= 4; s++) {
            for (int c = 1; c <= 13; c++) {
                add(new Card(c, s));
            }
        }
    }

    public Card draw() {
        return pop();
    }

    public void shuffle() {
        Collections.shuffle(this);
    }
}
