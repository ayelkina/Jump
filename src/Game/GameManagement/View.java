package Game.GameManagement;

import java.awt.*;

public class View {

    private GameController gameController;

    public View( GameController gameController) {
        this.gameController = gameController;
    }

    public void draw(Graphics2D graph) {
        gameController.getViewState().draw(graph);
        graph.dispose();
    }

}
