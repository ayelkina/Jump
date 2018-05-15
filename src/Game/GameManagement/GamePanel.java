package Game.GameManagement;

import java.awt.*;
import javax.swing.JPanel;

import Game.View.View;

public class GamePanel extends JPanel {

    public static final int WIDTH = 600;
    public static final int HEIGHT = 800;

    private Image doubleBuffer;
    private View view;

    public GamePanel(View view) {
        this.view = view;
        setPreferredSize(new Dimension(WIDTH, HEIGHT));
        setFocusable(true);
        requestFocus();
    }
    public void update() {
        paint();
    }

    public void paint() {
        Graphics2D g = (Graphics2D) getGraphics();
            Dimension size = getSize();
            if(size.height > 0 && size.width>0)
            if (doubleBuffer == null || doubleBuffer.getWidth(this) != size.width || doubleBuffer.getHeight(this) != size.height) {
                doubleBuffer = createImage(size.width, size.height);
            }

            if (doubleBuffer != null) {
                Graphics2D g2 = (Graphics2D) doubleBuffer.getGraphics();
                view.draw(g2);

                g.drawImage(doubleBuffer, 0, 0, null);
                g2.dispose();
            } else {
                if(g!=null) //System.out.println("no null");
                view.draw(g);
            }
    }
}