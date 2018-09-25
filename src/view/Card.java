package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import model.Card.Visibility;

public class Card extends JLabel {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 160;

    private Visibility currentVisibility;

    private model.Card model;

    public ImageIcon CARD_FRONT;
    public static final ImageIcon CARD_BACK = new ImageIcon(new ImageIcon("res/cards/red_joker.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));

    public Card(model.Card model) {
        super();
        this.model = model;

        String url = constructURL(model);
        CARD_FRONT = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));

        // only add the mouse listener if needed
        if(model.getVisibility() == Visibility.SPOILER) {
            addMouseListener(revealOnHover);
        }
        setVisibility(model.getVisibility());
    }

    public void setVisibility(Visibility visibility) {
        this.currentVisibility = visibility;

        setIcon(currentVisibility == Visibility.VISIBLE ? CARD_FRONT : CARD_BACK);
        revalidate();
    }

    private MouseAdapter revealOnHover = new MouseAdapter() {
        @Override
        public void mouseEntered(MouseEvent e) {
            setVisibility(Visibility.VISIBLE);
            super.mouseEntered(e);
        }

        @Override
        public void mouseExited(MouseEvent e) {
            setVisibility(Visibility.HIDDEN);
            super.mouseExited(e);
        }
    };

    // TODO add in action listen for cursor hover over

    private String constructURL(model.Card model) {
        String url = "res/cards/" + model.getNumber() + "_of_" + model.getSuit() + ".png";
        return url.toLowerCase();
    }
}
