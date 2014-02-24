package game;

import javax.swing.ImageIcon;

public class AirExplision extends Character {
	
    double frameCount = 0;
    String targetImg = "/img/aExp_0.gif";

    public AirExplision(int x, int y) {
        this.x = x;
        this.y = y;
        visible = true;
        ii = new ImageIcon(this.getClass().getResource(targetImg));
    }

	public void update() {
    	if (frameCount>18)
    		visible=false;
    	else{
    		targetImg = "/img/aExp_" + (int)frameCount + ".gif";
    		ii = new ImageIcon(this.getClass().getResource(targetImg));
    		image = ii.getImage();
    		frameCount+=.2;
    	}
    }
}

