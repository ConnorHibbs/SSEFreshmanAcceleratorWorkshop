package view;

import javax.swing.*;
import java.awt.*;

import model.Card.Visibility;

public class Card extends JLabel {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 160;

    private Visibility currentVisibility;

//    public enum Visibility {HIDDEN, SPOILER, SHOWING}

    private model.Card model;

//    private ImageIcon showingIcon;
    public ImageIcon CARD_FRONT;
    public static final ImageIcon CARD_BACK = new ImageIcon(new ImageIcon("res/cards/red_joker.png").getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));

    public Card(model.Card model) {
        super();
        this.model = model;

        String url = constructURL(model);
        CARD_FRONT = new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT));
        setVisibility(model.getVisibility());
    }

    public void setVisibility(Visibility visibility) {
        this.currentVisibility = visibility;

        setIcon(currentVisibility == Visibility.VISIBLE ? CARD_FRONT : CARD_BACK);
        revalidate();
    }

    // TODO add in action listen for cursor hover over

    private String constructURL(model.Card model) {
        String url = "res/cards/" + model.getNumber() + "_of_" + model.getSuit() + ".png";
        return url.toLowerCase();
    }
}
