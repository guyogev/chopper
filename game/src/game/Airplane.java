package game;

import javax.swing.ImageIcon;

public class Airplane extends Character {
	private double dx, dy;
	private int speed;

	public Airplane(int direction, int speed, int y) {
		if (direction > StartingClass.WIDTH / 2) {
			ii = new ImageIcon(this.getClass().getResource("/img/planeR.png"));
			this.x = -50;
			this.speed = speed;
		} else {
			ii = new ImageIcon(this.getClass().getResource("/img/planeL.png"));
			this.x = StartingClass.WIDTH;
			this.speed = -speed;
		}
		image = ii.getImage();
		visible = true;
		this.y = y;
		this.dx = this.speed;
	}

	public void move() {
		x += dx;
		y += dy;
		if (x > StartingClass.WIDTH || x < -50)
			visible = false;
	}
}
