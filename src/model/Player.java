package model;

public class Player {

    private String name;
    private double chips;
    private double bet;
    private Hand hand;

    public Player(String name, double chips) {
        this.name = name;
        this.chips = chips;
        this.hand = new Hand();
        this.bet = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getChips() {
        return chips;
    }

    public void setChips(double chips) {
        this.chips = chips;
    }

    public Hand getHand() {
        return hand;
    }

    public void setHand(Hand hand) {
        this.hand = hand;
    }

    public void applyBet(double amount) {
        this.chips -= amount;
        this.bet = amount;
    }

    public static final Player EMPTY_MODEL = new model.Player("(empty)", 0);
}
