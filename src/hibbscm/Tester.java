package hibbscm;

import model.Card;

import java.util.Collections;
import java.util.Stack;

public class Tester {
    public static void main(String[] args){
        Stack<Card> deck = new Stack();
        for(int i = 0; i < 4; i++){
            for(int k = 1; k < 14; k++)
                deck.push(new Card(k, i));
        }
        Collections.shuffle(deck);

        int i = 3;
        Player[] players = new Player[i];
        while(i > 0){
            Card cardA = deck.pop();
            Card cardB = deck.pop();

            players[i-1] = new Player(cardA, cardB, "Player " + i);
            System.out.println(cardA + " " + cardB);

            System.out.println(players[i-1].getName() + " has a total of " + players[i-1].getTotalPoints() + " points");
            i--;
        }
    }
}
