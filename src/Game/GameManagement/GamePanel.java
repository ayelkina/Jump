package Game.GameManagement;

import java.awt.*;
import javax.swing.JPanel;

import Game.GameManagement.View;

public class GamePanel extends JPanel {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private Image doubleBuffer;
    private View view;

    public GamePanel() {
//        this.view = view;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }

    public void update() {
        Graphics2D g = (Graphics2D) getGraphics();
        Dimension size = getSize();
        if (doubleBuffer == null || doubleBuffer.getWidth(this) != size.width || doubleBuffer.getHeight(this) != size.height) {
            doubleBuffer = createImage(size.width, size.height);
        }

        if (doubleBuffer != null) {
            Graphics2D g2 = (Graphics2D) doubleBuffer.getGraphics();
            View.draw(g2);

            g.drawImage(doubleBuffer, 0, 0, null);
            g2.dispose();
        } else {
//            if(g!= null)// System.out.println("panel");
                View.draw(g);
       }
    }





}
