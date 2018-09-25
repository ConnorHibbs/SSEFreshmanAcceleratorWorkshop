package view;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class Table extends JPanel {

    private ActionListener onStartRoundPressed, onRoundOver;

    private Player[] players;

    private controller.Player dealer;

    private model.Table model;

    private JButton startGame;

    public Table(model.Table model) {
        super();
        this.model = model;
        players = new Player[] {Player.EMPTY_SEAT, Player.EMPTY_SEAT, Player.EMPTY_SEAT};
        setLayout(new BorderLayout());
        setBackground(Color.GREEN.darker().darker());

        // TODO get the dealer and add it to the game

        // TODO add graphic center
        startGame = new JButton("Start New Round");
        startGame.addActionListener(e -> System.out.println("Start Game Pressed from " + this));
        add(startGame, BorderLayout.CENTER);
    }

    public void addPlayer(view.Player player) {
        // check for available spots at the table

        if(players[1] == Player.EMPTY_SEAT) {
            remove(players[1]);
            players[1] = player;
            add(players[1], BorderLayout.SOUTH);
        } else if(players[0] == Player.EMPTY_SEAT) {
            remove(players[0]);
            players[0] = player;
            add(players[0], BorderLayout.WEST);
        } else if(players[2] == Player.EMPTY_SEAT) {
            remove(players[2]);
            players[2] = player;
            add(players[2], BorderLayout.EAST);
        } else {
            throw new RuntimeException("Cannot add " + player.getName() + ", the table is full");
        }

        revalidate();
    }

    public void setDealer(controller.Player dealer) {
        this.dealer = dealer;
        add(dealer.getView(), BorderLayout.NORTH);
    }

    public int getPlayerCount() {
        int playerCount = 0;
        for(int i = 0; i < 3; i++) {
            if(players[i] != Player.EMPTY_SEAT) playerCount++;
        }
        return playerCount;
    }

    public Player removePlayer(int seat) {
        Player oldPlayer = players[seat];
        players[seat] = null;

        return oldPlayer;
    }

    public void setOnStartRoundPressed(ActionListener al) {
        startGame.removeActionListener(onStartRoundPressed);
        this.onStartRoundPressed = al;
        startGame.addActionListener(onStartRoundPressed);
    }

    public void setOnRoundOver(ActionListener actionListener) {
        this.onRoundOver = actionListener;
    }
}
