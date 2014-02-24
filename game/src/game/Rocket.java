package game;

import javax.swing.ImageIcon;

public class Rocket extends Character {

	private double dx, dy, a, x0, y0;
	private int speed = 2;

	public Rocket(int x0, int y0, int x1, int y1) {
		this.x0 = x0;
		this.y0 = y0;
		this.x = x0;
		this.y = y0;
		this.a = (double) (y1 - y0) / (x1 - x0);
		visible = true;

		int dh = x0 - x1;
		int dl = y0 - y1;
		if (dl > 50) {
			this.dy = -speed;
			// shot north
			if (dh <= 50 && dh >= -50)
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketN.png"));
			else if (dh > 0) {
				this.dx = -speed;
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketNW.png"));
			} else {
				this.dx = speed;
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketNE.png"));
			}
		} else if (dl < -50) {
			this.dy = speed;
			// shot south
			if (dh <= 50 && dh >= -50)
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketS.png"));
			else if (dh > 0) {
				this.dx = -speed;
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketSW.png"));
			} else {
				this.dx = speed;
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketSE.png"));
			}
		} else {
			// shot east or west
			if (dh > 0) {
				this.dx = -speed;
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketW.png"));
			} else {
				this.dx = speed;
				ii = new ImageIcon(this.getClass().getResource(
						"/img/rocketE.png"));
			}
		}

		image = ii.getImage();

	}

	public void move() {
		x += dx;
		if (dx != 0)
			y = (int) ((x - x0) * a + y0);
		else {
			y += dy;
		}
		if (x > StartingClass.WIDTH || x < 0 || y > StartingClass.HEIGTH
				|| y < 0)
			visible = false;
	}

}