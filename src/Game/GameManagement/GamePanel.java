package Game.GameManagement;

import java.awt.*;
import javax.swing.*;

public class GamePanel extends JPanel{


    public static final int HEIGHT = (int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()*3/4;
    public static final int WIDTH = HEIGHT*3/4;

    private Image doubleBuffer;
    private View view;

    public GamePanel(View view) {
        this.view = view;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void update() {
        Graphics2D graph = (Graphics2D) getGraphics();

        if (doubleBuffer == null || doubleBuffer.getWidth(this) != GamePanel.WIDTH || doubleBuffer.getHeight(this) != GamePanel.HEIGHT) {
            doubleBuffer = createImage(GamePanel.WIDTH, GamePanel.HEIGHT);
        }

        if (doubleBuffer != null) {
            Graphics2D g2 = (Graphics2D) doubleBuffer.getGraphics();
            view.draw(g2);

            graph.drawImage(doubleBuffer, 0, 0, null);
            g2.dispose();

        } else {
            if (graph != null)
                view.draw(graph);
        }
    }
}