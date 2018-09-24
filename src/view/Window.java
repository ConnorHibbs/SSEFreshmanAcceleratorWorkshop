package view;

import model.Player;

import javax.swing.*;
import java.awt.*;

public class Window extends JFrame {

    JPanel content;

    public Window() {
        super("Black Jack");

        content = new JPanel();
        content.setLayout(new BorderLayout());

        BettingPanel bettingPanel = new BettingPanel();
        content.add(bettingPanel, BorderLayout.SOUTH);

        add(content);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setVisible(true);
        pack();
    }

    public void setTable(view.Table table) {
        content.add(table, BorderLayout.CENTER);
        revalidate();
    }
}
