package view;

import javax.swing.*;

import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static model.Player.EMPTY_MODEL;

public class Player extends JPanel {

    private model.Player model;

    private List<Card> cardImages;

    public Player(model.Player model) {
        this.model = model;

        cardImages = new ArrayList<>();

        add(new JLabel(model.getName()));
    }

    @Override
    public void revalidate() {
        if(cardImages != null && model != null) {
            if (cardImages.size() != model.getHand().size()) {
                model.Card cardModel = model.getHand().getLast();
                view.Card newCard = new Card(cardModel);
                cardImages.add(newCard);
                add(newCard);
            }
        }

        super.repaint();
        super.revalidate();
    }

    public static final view.Player EMPTY_SEAT = new Player(EMPTY_MODEL);

}
