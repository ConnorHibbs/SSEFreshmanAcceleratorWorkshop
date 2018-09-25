package controller;

import model.Card;

public class Table {

    private view.Table view;
    private model.Table model;

    private controller.Player[] players = {null, null, null};
    private controller.Player dealer;

    private int currentPlayer = 0;

    public Table(view.Table tableView, model.Table tableModel) {
        this.view = tableView;
        this.model = tableModel;

        // create the dealer
        model.Player dealerModel = new model.Player("Dealer", -1);
        view.Player dealerView = new view.Player(dealerModel);
        dealer = new controller.Player(this, dealerModel, dealerView);

        view.setDealer(dealer);

        view.setOnStartRoundPressed(e -> startRound());
        view.setOnRoundOver(e -> endRound());
    }

    public void startRound() {
        System.out.println("Starting Round");

        // deal out the cards
        Card c;
        for (controller.Player player : players) {
            c = model.getDeck().draw();
            c.setVisibility(Card.Visibility.SPOILER);
            player.deal(c);
        }
        c = model.getDeck().draw();
        c.setVisibility(Card.Visibility.HIDDEN);
        dealer.deal(c);

        for (controller.Player player : players) {
            c = model.getDeck().draw();
            c.setVisibility(Card.Visibility.VISIBLE);
            player.deal(c);
        }
        c = model.getDeck().draw();
        c.setVisibility(Card.Visibility.VISIBLE);
        dealer.deal(c);

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
        view.addPlayer(p.getView());
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

    public void startDealer() {
        // TODO make the dealer draw cards
        System.out.println("Dealer's turn");

        model.Player dealer = model.getDealer();
    }

    public boolean nextPlayer() {
        currentPlayer = currentPlayer + 1;

        if(currentPlayer >= 3) {
            // reset the player, and tell them there were no more players
            currentPlayer = 0;
            return false;
        } else if (getCurrentPlayer() == null) {
            // move on to the next player slot
            return nextPlayer();
        } else {
            // everything is good
            return true;
        }
    }
}
