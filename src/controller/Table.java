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

    public void resetTable() {
        model.resetDeck();

        // reset the players
        for(controller.Player pController : players) {
            // no matter what, set their bet to 0
            pController.getModel().reset();

            // clear their cards, and update their money label
            pController.getView().revalidate();
        }

        // reset the dealer
        dealer.getModel().reset();
        dealer.getView().revalidate();
    }

    public void startRound() {
        System.out.println("Starting Round");
        resetTable();

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

    /**
     * Called when all cards have been dealt. This method is responsible
     * for paying out the players, or collecting their chips
     */
    public void endRound() {
        int dealerTotal = dealer.getModel().getHand().getTotal();
        System.out.println(dealer.getModel().getName() + " has a total of " + dealerTotal);

        // determine the winners
        for(controller.Player pController : players) {
            model.Player p = pController.getModel();
            p.getHand().get(0).setVisibility(Card.Visibility.VISIBLE);
            int playerTotal = p.getHand().getTotal();

            if (playerTotal > 21) {
                // bust, do nothing, their bet will be cleared later
                System.out.println(p.getName() + " has busted");
            } else if (dealerTotal > 21) {
                // dealer busted, win
                System.out.println("The dealer busted, " + p.getName() + " wins!");
            } else if (playerTotal < dealerTotal) {
                // lost
                System.out.println(p.getName() + " has lost");
            } else if (playerTotal > dealerTotal) {
                // win
                p.setChips(p.getChips() + (p.getBet() * 2));
                System.out.println(p.getName() + " has won");
            } else if (playerTotal == dealerTotal) {
                // push
                p.setChips(p.getChips() + p.getBet());
                System.out.println(p.getName() + " has pushed");
            }

            pController.getView().revalidate();
        }
    }

    public Card draw() {
        return model.getDeck().draw();
    }

    public controller.Player getCurrentPlayer() {
        return players[currentPlayer];
    }

    public void startDealer() {
        // change all of the dealer's cards to visible
        dealer.getModel().getHand().get(0).setVisibility(Card.Visibility.VISIBLE);

        while(dealer.getModel().getHand().getTotal() < 17) {
            dealer.hit();
        }

        dealer.getView().revalidate();
        endRound();
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
