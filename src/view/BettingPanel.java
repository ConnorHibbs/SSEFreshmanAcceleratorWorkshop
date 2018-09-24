package view;

import javax.swing.*;

public class BettingPanel extends JPanel {

    private JButton pennyButton, nickelButton, dimeButton, quarterButton, fiftyButton, dollarButton, fiveButton, tenButton;

    public BettingPanel() {
        super();

        pennyButton = createButton(1);
        nickelButton = createButton(5);
        dimeButton = createButton(10);
        quarterButton = createButton(25);
        fiftyButton = createButton(50);
        dollarButton = createButton(100);
        fiveButton = createButton(500);
        tenButton = createButton(1000);
    }

    private JButton createButton(int amount) {
        JButton button = new JButton("$" + amount);
        button.addActionListener(e -> bet(amount));
        add(button);
        return button;
    }

    private void bet(int amount) {
        System.out.println("Trying to bet " + amount);
    }
}
