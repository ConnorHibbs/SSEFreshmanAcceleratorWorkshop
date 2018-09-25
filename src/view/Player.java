package view;

import javax.swing.*;

import java.util.ArrayList;
import java.util.List;

import static model.Player.EMPTY_MODEL;

public class Player extends JPanel {

    private model.Player model;

    private JPanel cardSpace;

    private int numCards = 0;

    public Player(model.Player model) {
        this.model = model;
        cardSpace = new JPanel();

        add(new JLabel(model.getName()));
        add(cardSpace);
    }

    @Override
    public void revalidate() {
        // if it is wrong, literally redraw everything
        if(model != null && numCards != model.getHand().size()) {
            this.removeAll();
            for(model.Card c : model.getHand()) {
                view.Card newCard = new view.Card(c);
                add(newCard);
            }
        }

        super.repaint();
        super.revalidate();
    }

    public static final view.Player EMPTY_SEAT = new Player(EMPTY_MODEL);

}
