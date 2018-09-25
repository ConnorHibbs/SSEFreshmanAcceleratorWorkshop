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

//        view.setOnHit(e -> hit());
//        view.setOnStay(e -> stay());
    }

    public void hit() {
        Card cardDealt = table.draw();
        deal(cardDealt);
        view.revalidate();

        // TODO evaluate if there is a bust
    }

    public void stay() {
        // TODO pass control back to the main game
        table.nextPlayer();
    }

    private void onBust() {

    }

    public void deal(Card c) {
        model.getHand().add(c);
        view.revalidate();
    }

    public model.Player getModel() {
        return model;
    }

    public view.Player getView() {
        return view;
    }

    public boolean canBet(int amount) {
        return amount <= model.getChips();
    }

    public void bet(int amount) {
        if(canBet(amount)) {
            model.applyBet(amount);
        }
    }
}
