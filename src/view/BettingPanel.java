package view;

import java.util.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class BettingPanel extends JPanel {

    private JLabel currentPlayerLabel = new JLabel("?");
    private JLabel currentBetLabel = new JLabel("$0");
    private int currentBet = 0;

    private controller.Table table;

    private List<JLabel> bettingButtons;

    public BettingPanel(controller.Table table) {
        super();
        this.table = table;

        setLayout(new BorderLayout());

        final int[] VALUES = {1, 5, 10, 25, 50, 100, 500, 1000, 5000, 10000, 50000};

        JPanel bettingButtonsPanel = new JPanel();
        bettingButtons = new ArrayList<>();
        for(Integer value : VALUES) {
            JLabel button = createButton(value);
            bettingButtons.add(button);
            bettingButtonsPanel.add(button);
        }

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

    private JLabel createButton(int amount) {
        ImageIcon icon = new ImageIcon("res/chips/" + amount + ".png");
        icon = new ImageIcon(icon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT));
        JLabel button = new JLabel(icon);
        button.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                addToBet(amount);
            }
        });
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

        // after they bet, go to the next player or begin the game
        if(table.nextPlayer()) {
            loadValuesForPlayer(table.getCurrentPlayer().getModel());
        } else {
            table.startRound();
        }
    }

    private void loadValuesForPlayer(model.Player player) {
        // TODO see if the player has a previous bet
        // if they do, attempt to start at that number.
        // else, reset to 0

        revalidate();
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
