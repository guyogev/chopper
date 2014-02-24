package game;

import javax.swing.ImageIcon;

public class Background extends Character {
	public Background(){
		ii = new ImageIcon(this.getClass().getResource("/img/bg.jpg"));
        image = ii.getImage();
        x = 0;
        y = 0;
	}
}
