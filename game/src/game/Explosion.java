package game;

import javax.swing.ImageIcon;

public class Explosion extends Character{
	    double frameCount = 0;
	    String targetImg = "img/exp/frame_0.gif";

	    public Explosion(int x) {
	        this.x = x;
	        visible = true;
	        ii = new ImageIcon(this.getClass().getResource(targetImg));
	    }

		public void update() {
	    	if (frameCount>24)
	    		visible=false;
	    	else{
	    		targetImg = "img/exp/frame_" + (int)frameCount + ".gif";
	    		ii = new ImageIcon(this.getClass().getResource(targetImg));
	    		image = ii.getImage();
	    		frameCount+=.2;
	    	}
	    }
	}