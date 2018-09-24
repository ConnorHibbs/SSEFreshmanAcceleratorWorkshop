package controller;

import model.Card;

public class Table {

    private view.Table view;
    private model.Table model;

    private boolean roundInProgress = false;

    public Table(view.Table tableView, model.Table tableModel) {
        this.view = tableView;
        this.model = tableModel;

        view.setOnStartRoundPressed(e -> startRound());
        view.setOnRoundOver(e -> endRound());
    }

    public void startRound() {
        roundInProgress = true;

        System.out.println("Starting Round");

        for(int i = 0; i < 2; i++) {
            for (model.Player player : model.getPlayers()) {
                Card c = model.getDeck().draw();
                player.deal(c);
            }
        }

        for(model.Player player : model.getPlayers()) {
            System.out.println(player.getName() + " has:");
            for(Card c : player.getHand()) {
                System.out.println("\t" + c);
            }
            System.out.println();
        }
    }

    public void addPlayer(model.Player p) {
        model.addPlayer(p);
        view.addPlayer(p);
    }

    public void endRound() {
        roundInProgress = false;

        // allocate out all of the chips for winners and losers
    }

    public Card draw() {
        return model.getDeck().draw();
    }

    public void nextPlayer() {
        // TODO switch out to the next player
    }
}
