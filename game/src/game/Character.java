package game;

import java.awt.Image;
import java.awt.Rectangle;
import javax.swing.ImageIcon;

public abstract class Character {
	protected int x;
	protected int y;
	protected Image image;
	ImageIcon ii;
    boolean visible;
    int BOARD_BOTTOM = 600;
    int width = 30;
    int height = 30;
    
    
    public Image getImage() {
        return image;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public boolean isVisible() {
        return visible;
    }

	public void setVisible(boolean b) {
		visible = b;
	}

    public Rectangle getBounds() {
        return new Rectangle(x, y, width, height);
    }
}
