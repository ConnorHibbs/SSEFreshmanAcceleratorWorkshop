//package controller;
//
//import java.util.Random;
//import java.util.Scanner;
//
//import static model.Card.convertCardToValue;
//
//public class Main {
//
//    public static void main(String args[]) {
//        System.out.println("Welcome to Blackjack!");
//
//        String playerName;
//
//        System.out.println("Please enter your name");
//        Scanner scanner = new Scanner(System.in);
//        playerName = scanner.next();
//
//        int playerTotal = 0;
//        int dealerTotal = 0;
//
//        int newValue = getCard();
//        System.out.println(playerName + ", you got a " + newValue);
//        playerTotal += convertCardToValue(newValue);
//
////        String response;
////        do {
////            newValue = getCard();
////            System.out.println(playerName + ", you got a " + newValue);
////            playerTotal += convertCardToValue(newValue);
////            System.out.println(playerName + ", your total is " + playerTotal);
////
////            System.out.println("Would you like to hit or stay? (h/s)");
////            response = scanner.next();
////        } while(!response.equals("s") && playerTotal <= 21);
//
//
//        newValue = getCard();
//        System.out.println(playerName + ", you got a " + newValue);
//        playerTotal += convertCardToValue(newValue);
//
//        System.out.println(playerName + ", your total is " + playerTotal);
//
//        System.out.println("Would you like to hit or stay? (h/s)");
//        String response = scanner.next();
//
//        while (!response.equals("s") && playerTotal <= 21){
//            newValue = getCard();
//            System.out.println(playerName + ", you got a " + newValue);
//            playerTotal += convertCardToValue(newValue);
//            System.out.println(playerName + ", your total is " + playerTotal);
//
//            System.out.println("Would you like to hit or stay? (h/s)");
//            response = scanner.next();
//        }
//
////        dealerTotal += convertCardToValue(getCard());
////        dealerTotal += convertCardToValue(getCard());
//        while(dealerTotal <= 17) {
//            dealerTotal += convertCardToValue(getCard());
//        }
//
//        System.out.println("Dealer gets " + dealerTotal);
//
//        if (playerTotal > 21) {
//            System.out.println("Player loses");
//        } else if (dealerTotal > 21) {
//            System.out.println("Player wins");
//        } else if(playerTotal > dealerTotal) {
//            System.out.println("Player wins");
//        } else if(playerTotal == dealerTotal){
//            System.out.println("It's a draw");
//        } else {
//            System.out.println("Dealer wins");
//        }
//
//
//
//
//    }
//
//    /**
//     * Should return a number between 1 and 13 to
//     * represent a card
//     * @return number between 1 and 13
//     */
//    public static int getCard() {
//        Random random = new Random();
//        return random.nextInt(13) + 1;
//    }
//
//
//}
