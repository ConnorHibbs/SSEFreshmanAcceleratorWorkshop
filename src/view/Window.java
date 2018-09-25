package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    private JPanel content;
    private JPanel controlArea;

    public Window() {
        super("Black Jack");

        content = new JPanel();
        content.setLayout(new BorderLayout());

        controlArea = new JPanel();

        content.add(controlArea, BorderLayout.SOUTH);

        add(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void setTable(view.Table table) {
        content.add(table, BorderLayout.CENTER);
        revalidate();
    }

    public void setBettingPanel(BettingPanel bettingPanel) {
        controlArea.add(bettingPanel);
    }

    public void setHitStayPanel(HitStayPanel hitStayPanel) {
        controlArea.add(hitStayPanel);
    }
}
