package view;

import javax.swing.*;

import java.awt.event.ActionListener;

import static model.Player.EMPTY_MODEL;

public class Player extends JPanel {

    private model.Player model;

    private ActionListener onHit, onStay;

    public Player(model.Player model) {
        this.model = model;

        add(new JLabel(model.getName()));
    }

    public void setOnHit(ActionListener al) {
        this.onHit = al;
    }

    public void setOnStay(ActionListener al) {
        this.onStay = al;
    }

    @Override
    public boolean equals(Object obj) {
        return obj instanceof JPanel && model.equals( ((view.Player)obj).model );
    }

    public static final view.Player EMPTY_SEAT = new Player(EMPTY_MODEL);

}
