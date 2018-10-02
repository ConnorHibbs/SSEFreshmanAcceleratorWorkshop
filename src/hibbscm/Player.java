package hibbscm;

import model.Card;

import java.util.ArrayList;
import java.util.List;

public class Player {

    private int balance;
    private List<Card> cardList;
    private String name;

    public Player(Card cardA, Card cardB, String name){
        cardList = new ArrayList<>();
        add(cardA);
        add(cardB);
        balance = 0;
        this.name = name;
    }

    public void add(Card card){
        cardList.add(card);
    }

    public int getTotalPoints(){
        int pointTotal = 0;
        int aceCount = 0;

        for(Card c : cardList) {
            if (c.getValue() == 11)
                aceCount++;
            else
                pointTotal += c.getValue();
        }

        if(pointTotal >= 21 || aceCount > 0)
            return pointTotal;
        else{
            for(int i = 0; i < aceCount; i++){
                if(21 - pointTotal <= 11)
                    pointTotal += 11;
                else
                    pointTotal += 1;
            }
        }

        return pointTotal;
    }

    public int getBalance() {
        return balance;
    }

    public String getName() {
        return name;
    }

}
