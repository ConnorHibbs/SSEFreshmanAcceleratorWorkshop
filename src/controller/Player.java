package controller;

import model.Card;

public class Player {

    private controller.Table table;
    private model.Player model;
    private view.Player view;

    public Player(controller.Table table, model.Player model, view.Player view) {
        this.table = table;
        this.model = model;
        this.view = view;

        view.setOnHit(e -> hit());
        view.setOnStay(e -> stay());
    }

    public void hit() {
        Card cardDealt = table.draw();
        model.deal(cardDealt);

        // TODO evaluate if there is a bust
    }

    public void stay() {
        // TODO pass control back to the main game
        table.nextPlayer();
    }

    private void onBust() {

    }
}
