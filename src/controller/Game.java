package controller;

import model.Player;
import view.Window;

public class Game {

    public Game() {

    }

    public static void main(String[] args) {
        Window window = new Window();

        model.Table model = new model.Table();
        view.Table view = new view.Table(model);
        window.setTable(view);

        model.Player p1 = new model.Player("Player 1", 10);
        model.Player p2 = new model.Player("Player 2", 20);
        model.Player p3 = new model.Player("Player 3", 30);


        controller.Table controller = new controller.Table(view, model);

        controller.addPlayer(p1);
        controller.addPlayer(p2);
        controller.addPlayer(p3);
    }
}
