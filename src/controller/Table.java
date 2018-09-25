package controller;

import model.Card;

public class Table {

    private view.Table view;
    private model.Table model;

    private Player[] players = {null, null, null};

//    private controller.Player currentPlayer;
    private int currentPlayer = 0;

    public Table(view.Table tableView, model.Table tableModel) {
        this.view = tableView;
        this.model = tableModel;

        view.setOnStartRoundPressed(e -> startRound());
        view.setOnRoundOver(e -> endRound());
    }

    public void startRound() {
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

        // TODO disable the betting panel
        // TODO disable the start round button
        // TODO disable players joining
    }

    public void addPlayer(controller.Player p) {
        if(players[1] == null) {
            players[1] = p;
        } else if(players[0] == null) {
            players[0] = p;
        } else if(players[2] == null) {
            players[2] = p;
        } else {
            throw new RuntimeException("Cannot add " + p.getModel().getName() + ", the table is full");
        }

        model.addPlayer(p.getModel());
        view.addPlayer(p.getModel());
    }

    public void endRound() {

        // TODO allocate out all of the chips for winners and losers
    }

    public Card draw() {
        return model.getDeck().draw();
    }

    public void setCurrentPlayer(int playerNum) {
        // TODO check if player num is valid
        this.currentPlayer = playerNum;
    }

    public controller.Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public boolean hasNextPlayer() {
        return currentPlayer <= 2;
    }

    public boolean nextPlayer() {
        currentPlayer = currentPlayer + 1;

        if(currentPlayer >= 3) {
            // TODO go to the dealer
            return false;
        } else  if(getCurrentPlayer() == null) {
            // move on to the next player slot
            return nextPlayer();
        } else {
            // everything is good
            return true;
        }
    }
}
