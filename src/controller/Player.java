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
    }

    public void hit() {
        Card cardDealt = table.draw();
        deal(cardDealt);

        if(model.getHand().getTotal() > 21) {
            // see if we can reduce any aces
            for(Card c : getModel().getHand()) {
                if(c.getNumber() == Card.Number._A && c.getValue() == 11) {
                    c.setValue(1);
                    break;
                }
            }

            // if still over 21, stay
            if(model.getHand().getTotal() > 21) {
                stay();
            }
        }

        view.revalidate();
    }

    public void stay() {
        boolean morePlayers = table.nextPlayer();

        if(!morePlayers) {
            // trigger the dealer to go
            table.startDealer();
        }
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
