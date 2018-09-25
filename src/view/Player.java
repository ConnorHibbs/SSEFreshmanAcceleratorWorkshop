package view;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static model.Player.EMPTY_MODEL;

public class Player extends JPanel {

    private model.Player model;

//    private List<model.Card> currentCards;

    private int numCards = 0;

    public Player(model.Player model) {
        this.model = model;

//        currentCards = new ArrayList<>();

        add(new JLabel(model.getName()));
    }

    @Override
    public void revalidate() {
//        if(currentCards != null && model != null) {
            // draw to until we have all new cards
//            while(currentCards.size() != model.getHand().size()) {
//                System.out.println("Adding a new card to the screen");
//                model.Card cardModel = model.getHand().get(currentCards.size());
//                currentCards.add(cardModel);
//                view.Card newCard = new Card(cardModel);
//                add(newCard);
//            }

        if(model != null) {
            if(numCards != model.getHand().size()) {
                numCards++;
                view.Card newCard = new view.Card(model.getHand().getLast());
                add(newCard);
            }
        }

        super.repaint();
        super.revalidate();
    }

    public static final view.Player EMPTY_SEAT = new Player(EMPTY_MODEL);

}
