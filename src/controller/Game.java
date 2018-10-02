package controller;

import model.Player;
import view.BettingPanel;
import view.HitStayPanel;
import view.Window;

public class Game {

    public Game() {

    }

    public static void main(String[] args) {
        Window window = new Window();

        // create the table model and views
        model.Table model = new model.Table();
        view.Table view = new view.Table(model);

        // create the controller
        controller.Table controller = new controller.Table(view, model);

        // Add the (temp) players

        // create some (temp) players // TODO delete this section
        model.Player p1 = new model.Player("Player 1", 10);
        model.Player p2 = new model.Player("Player 2", 20);
        model.Player p3 = new model.Player("Player 3", 30);

        view.Player pv1 = new view.Player(p1);
        view.Player pv2 = new view.Player(p2);
        view.Player pv3 = new view.Player(p3);

        controller.Player pc1 = new controller.Player(controller, p1, pv1);
        controller.Player pc2 = new controller.Player(controller, p2, pv2);
        controller.Player pc3 = new controller.Player(controller, p3, pv3);

        controller.addPlayer(pc1);
        controller.addPlayer(pc2);
        controller.addPlayer(pc3);

        // create the betting panel
        BettingPanel bettingPanel = new BettingPanel(controller);
        HitStayPanel hitStayPanel = new HitStayPanel(controller);

        // send components to the window
        window.setTable(view);
        window.setBettingPanel(bettingPanel);
        window.setHitStayPanel(hitStayPanel);
    }
}
