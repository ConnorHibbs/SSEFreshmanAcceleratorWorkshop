package view;

import javax.swing.*;

public class HitStayPanel extends JPanel {

    private controller.Table table;

    public HitStayPanel(controller.Table table) {
        super();
        this.table = table;

        JButton hit = new JButton("Hit");
        hit.addActionListener(e -> table.getCurrentPlayer().hit());

        JButton stay = new JButton("Stay");
        stay.addActionListener(e -> table.getCurrentPlayer().stay());

        add(hit);
        add(stay);
    }
}
