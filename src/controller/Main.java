package controller;

import model.Card;

import java.util.Collections;
import java.util.Random;
import java.util.Scanner;
import java.util.Stack;

import model.Deck;
import model.Hand;
import model.Player;

import static model.Card.convertCardToValue;

public class Main {

    public static void main(String args[]) {
        System.out.println("Welcome to Black Jack!");
        Scanner scanner = new Scanner(System.in);

        Stack<Card> deck = getDeck();

//        Deck deck = new Deck();

        Card cardA = deck.pop();
        Card cardB = deck.pop();

        Player dealer = initializeDealer(cardA, cardB);

        System.out.println("How many people are playing?");
        int numberOfPlayers = Integer.parseInt(scanner.nextLine());
        Player[] players = new Player[numberOfPlayers];

        int i = numberOfPlayers;
        while (i >= 1) {
            System.out.println("Player " + (i) + ": Please enter your name.");
            String name = scanner.nextLine();

            System.out.println(name + ": How much would you like to bet?");
            int bet = scanner.nextInt();

            cardA = deck.pop();
            cardB = deck.pop();
            players[i - 1] = new Player(name, 100);
            players[i-1].applyBet(bet);
            Hand hand = players[i-1].getHand();
            hand.add(cardA);
            hand.add(cardB);
            players[i - 1].setHand(hand);
            System.out.print(players[i - 1].getName() + ": You got " + cardA + " and " + cardB + ".");
            System.out.println(players[i - 1].getName() + ": You have a total of " + hand.getTotal() + " points.");
            i--;
        }

        for(Player p: players){
            System.out.println(p.getName() + " would you like to hit or stay? (h/s)");
            String ans = scanner.next();

            while(ans.equals("h") && p.getHand().getTotal() < 21){
                Card card = deck.pop();
                p.getHand().add(card);
                System.out.println(p.getName() +": You got a " + card +".");
                System.out.println(p.getName() + "You have a total of " + p.getHand().getTotal() + " points.");

                System.out.print(p.getName() + " ");
                if(p.getHand().getTotal() > 21) {
                    System.out.println("You busted!");
                    ans = "s";
                } else if(p.getHand().getTotal() == 21){
                    System.out.println("You got a perfect score!");
                    ans = "s";
                }else{
                    System.out.println("Would you like to hit or stay? (h/s)");
                    ans = scanner.next();
                }
            }
        }

        while (dealer.getHand().getTotal() < 17){
            Card card = deck.pop();
            dealer.getHand().add(card);
            System.out.println(dealer.getName() +": You got a " + card +".");
            System.out.println(dealer.getName() + "You have a total of " + dealer.getHand().getTotal() + " points.");
        }

        for(Player p: players){
            System.out.print(p.getName() + ": ");
            if(p.getHand().getTotal() > 21 || p.getHand().getTotal() < dealer.getHand().getTotal()){
                System.out.println("Better Luck next time. The dealer won with a total of "
                        + dealer.getHand().getTotal() + " points.");
                System.out.println("You lost a bet of " + p.getBet() + ".");
            } else if(p.getHand().getTotal() > dealer.getHand().getTotal()){
                System.out.println("Congratulations! You beat the dealer with a total of "
                        + p.getHand().getTotal() + " points.");
                System.out.println("You made a bet of " + p.getBet()+ "\nYou get a total of " + 2*p.getBet() + ".");
            } else {
                System.out.println("You tied the dealer with a total of "
                        + p.getHand().getTotal() + " points.");
                System.out.println("You made a bet of " + p.getBet()+ "\nYou get a total of " + p.getBet() + ".");
            }
        }
    }

    private static Player initializeDealer(Card cardA, Card cardB) {
        Player dealer = new Player("Dealer", 0);
        Hand hand = dealer.getHand();
        hand.add(cardA);
        hand.add(cardB);
        dealer.setHand(hand);
        System.out.println(dealer.getName() + ": You got " + cardA + " and " + cardB + ".");
        System.out.println(dealer.getName() + ": You have a total of " + dealer.getHand().getTotal() + " points.");
        return dealer;
    }

    private static Stack<Card> getDeck() {
        Stack<Card> deck = new Stack();
        for(int suit = 1; suit <= 4; suit++){
            for(int number = 1; number < 14; number++){
                deck.push(new Card(number, suit));
            }
        }

        Collections.shuffle(deck);
        return deck;
    }
}
