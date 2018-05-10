package Game.GameManagement;

import javax.swing.*;
import java.awt.*;

public class View extends JComponent {

    private Image doubleBuffer;


    public View(){}

   /* private void draw(Graphics2D graph) {
        gameState.draw(graph);
        graph.dispose();
    }

    public void drawcurrentState(Graphics2D g) {
        try {
            currentState.draw(g);
        } catch (Exception e) {
        }
    }

    public void bufforImage() {
        Graphics2D g = (Graphics2D) getGraphics();
        Dimension size = getSize();
        if (doubleBuffer == null || doubleBuffer.getWidth(this) != size.width || doubleBuffer.getHeight(this) != size.height) {
            doubleBuffer = createImage(size.width, size.height);
        }

        if (doubleBuffer != null) {
            Graphics2D g2 = (Graphics2D) doubleBuffer.getGraphics();
            draw(g2);

            g.drawImage(doubleBuffer, 0, 0, null);
            g2.dispose();
        } else {
            draw(g);
        }
    }*/
}
