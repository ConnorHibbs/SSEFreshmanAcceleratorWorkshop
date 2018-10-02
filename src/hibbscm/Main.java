package hibbscm;

import java.util.*;

public class Main {

    public static void main(String args[]) {
        System.out.println("Welcome to Blackjack!");
        Scanner scanner = new Scanner(System.in);

        Stack<Card> deck = new Stack();
        for(int i = 0; i < 4; i++){
            for(int k = 1; k < 14; k++)
                deck.push(new Card(k, i));
        }
        Collections.shuffle(deck);

        Card cardA = deck.pop();
        Card cardB = deck.pop();
        Player dealer = new Player(cardA, cardB, "Dealer");
        System.out.println(dealer.getName() + ": You got " + cardA + " and " + cardB + ".");
        System.out.println(dealer.getName() + ": You have a total of " + dealer.getTotalPoints() + " points.");

        System.out.println("How many people are playing?");
        int numberOfPlayers = Integer.parseInt(scanner.nextLine());
        Player[] players = new Player[numberOfPlayers];

        int i = numberOfPlayers;
        while (i >= 1){
            System.out.println("Player " + (i) +  ": Please enter your name.");
            String name = scanner.nextLine();
            cardA = deck.pop();
            cardB = deck.pop();
            players[i-1] = new Player(cardA, cardB, name);
            System.out.print(players[i-1].getName() + ": You got " + cardA + " and " + cardB + ".");
            System.out.println(players[i-1].getName() + ": You have a total of " + players[i-1].getTotalPoints() + " points.");
            i--;
        }

        for(Player p: players){
            System.out.println(p.getName() + " would you like to hit or stay? (h/s)");
            String ans = scanner.next();

            while(ans.equals("h") && p.getTotalPoints() < 21){
                Card card = deck.pop();
                p.add(card);
                System.out.println(p.getName() +": You got a " + card +".");
                System.out.println(p.getName() + "You have a total of " + p.getTotalPoints() + " points.");

                System.out.print(p.getName() + " ");
                if(p.getTotalPoints() > 21) {
                    System.out.println("You busted!");
                    ans = "s";
                } else if(p.getTotalPoints() == 21){
                    System.out.println("You got a perfect score!");
                    ans = "s";
                }else{
                    System.out.println("Would you like to hit or stay? (h/s)");
                    ans = scanner.next();
                }
            }
        }

        while (dealer.getTotalPoints() < 17){
            Card card = deck.pop();
            dealer.add(card);
            System.out.println(dealer.getName() +": You got a " + card +".");
            System.out.println(dealer.getName() + "You have a total of " + dealer.getTotalPoints() + " points.");
        }

        for(Player p: players){
            System.out.print(p.getName() + ": ");
            if(p.getTotalPoints() > 21 || p.getTotalPoints() < dealer.getTotalPoints()){
                System.out.println("Better Luck next time. The dealer won with a total of "
                        + dealer.getTotalPoints() + " points.");
            } else if(p.getTotalPoints() > dealer.getTotalPoints()){
                System.out.println("Congratulations! You beat the dealer with a total of "
                        + p.getTotalPoints() + " points.");
            } else {
                System.out.println("You tied the dealer with a total of "
                        + p.getTotalPoints() + " points.");
            }
        }
    }

}
