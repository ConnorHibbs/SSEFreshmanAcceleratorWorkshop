package model;

import java.util.ArrayList;
import java.util.List;

public class Table {

    private ArrayList<model.Player> players;
    private Player dealer;

    private Deck deck;

    public Table() {
        players = new ArrayList<>();
        dealer = new Player("Dealer", Double.MAX_VALUE);

        deck = new Deck();
        deck.shuffle();
    }

    public int getPlayerCount() {
        return players.size();
    }

    public boolean addPlayer(Player p) {
        return (getPlayerCount() < 3) && players.add(p);
    }

    public List<Player> getPlayers() {
        return players;
    }

    public Player getDealer() {
        return dealer;
    }

    public Deck getDeck() {
        return deck;
    }

}
