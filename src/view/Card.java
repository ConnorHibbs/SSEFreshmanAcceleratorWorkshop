package view;

import javax.swing.*;
import java.awt.*;

public class Card extends JLabel {

    private static final int WIDTH = 120;
    private static final int HEIGHT = 160;

    private model.Card model;

    public Card(model.Card model) {
        super();
        this.model = model;

        String url = constructURL(model);

        this.setIcon(new ImageIcon(new ImageIcon(url).getImage().getScaledInstance(WIDTH, HEIGHT, Image.SCALE_DEFAULT)));
    }

    private String constructURL(model.Card model) {
        String url = "res/cards/" + model.getValue() + "_of_" + model.getSuit() + ".png";
        return url.toLowerCase();
    }
}
