package view;

import javax.swing.*;
import java.awt.*;

public class Card extends JLabel {

    private model.Card model;

    public Card(model.Card model) {
        super();
        this.model = model;
        this.setIcon(new ImageIcon(constructURL(model)));
    }

    private String constructURL(model.Card model) {
        String url = model.getValue() + "_of_" + model.getSuit();
        return url.toLowerCase();
    }
}
