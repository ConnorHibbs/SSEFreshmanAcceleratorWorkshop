package view;

import javax.swing.*;
import java.awt.*;

public class BettingPanel extends JPanel {

    private JLabel currentPlayerLabel = new JLabel("?");
    private JLabel currentBetLabel = new JLabel("$0");
    private int currentBet = 0;
    private JButton pennyButton, nickelButton, dimeButton, quarterButton, fiftyButton, dollarButton, fiveButton, tenButton;

    private controller.Table table;

    public BettingPanel(controller.Table table) {
        super();
        this.table = table;

        setLayout(new BorderLayout());

        JPanel bettingButtonsPanel = new JPanel();
        pennyButton = createButton(bettingButtonsPanel, 1);
        nickelButton = createButton(bettingButtonsPanel, 5);
        dimeButton = createButton(bettingButtonsPanel, 10);
        quarterButton = createButton(bettingButtonsPanel, 25);
        fiftyButton = createButton(bettingButtonsPanel, 50);
        dollarButton = createButton(bettingButtonsPanel, 100);
        fiveButton = createButton(bettingButtonsPanel, 500);
        tenButton = createButton(bettingButtonsPanel, 1000);

        JPanel bettingControlPanel = new JPanel();
        bettingControlPanel.add(currentBetLabel);

        JButton resetButton = new JButton("Reset Bet");
        resetButton.addActionListener(e -> this.currentBet = 0);
        bettingControlPanel.add(resetButton);

        JButton placeBetButton = new JButton("Place Bet");
        placeBetButton.addActionListener(e -> bet());
        bettingControlPanel.add(placeBetButton);

        add(currentPlayerLabel, BorderLayout.WEST);
        add(bettingButtonsPanel, BorderLayout.CENTER);
        add(bettingControlPanel, BorderLayout.EAST);
    }

    private JButton createButton(JPanel parent, int amount) {
        JButton button = new JButton("$" + amount);
        button.addActionListener(e -> addToBet(amount));
        parent.add(button);
        return button;
    }

    private void addToBet(int amount) {
        System.out.println("Trying to bet an additional " + amount);
        if(table.getCurrentPlayer().canBet(currentBet + amount)) {
            currentBet += amount;
            System.out.println("Total bet is now " + currentBet);
        } else {
            // TODO throw error message saying insufficient funds
            System.out.println("Insufficient funds");
        }

        // TODO in the future, disable buttons that can't be used



        revalidate();
    }

    private void bet() {
        System.out.println("Trying to bet " + currentBet);
        if(table.getCurrentPlayer().canBet(currentBet)) {
            table.getCurrentPlayer().bet(currentBet);
            System.out.println(table.getCurrentPlayer().getModel().getName() + " has bet " + currentBet);
        }
    }

    @Override
    public void revalidate() {
        if(table != null && table.getCurrentPlayer() != null) {
            currentPlayerLabel.setText(table.getCurrentPlayer().getModel().getName());
            currentBetLabel.setText(String.valueOf(currentBet));
        }
        super.revalidate();
    }
}
