package game;

import java.awt.Image;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import javax.swing.ImageIcon;

public class Chopper extends Character {

	private int dx, dy;
	private int speed = 3;
	private Animation currAnimation;
	private Animation animationL;
	private Animation animationR;
	private ArrayList<Rocket> rockets;
	

	public Chopper() {
		this.x = 100;
		this.y = 100;
		visible = true;
		animationL = new Animation();
		animationR = new Animation();
		loadAnimImages();
		currAnimation = animationR;
		rockets = new ArrayList<Rocket>();
	}

	private void loadAnimImages() {
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL0.png"));
		animationL.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL1.png"));
		animationL.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL2.png"));
		animationL.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL3.png"));
		animationL.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL4.png"));
		animationL.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL3.png"));
		animationL.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL2.png"));
		animationL.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperL1.png"));
		animationL.addFrame(ii.getImage(), 100);
		
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR0.png"));
		animationR.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR1.png"));
		animationR.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR2.png"));
		animationR.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR3.png"));
		animationR.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR4.png"));
		animationR.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR3.png"));
		animationR.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR2.png"));
		animationR.addFrame(ii.getImage(), 100);
		ii = new ImageIcon(this.getClass().getResource("/img/chopperR1.png"));
		animationR.addFrame(ii.getImage(), 100);
		
	}

	public void move() {
		if (x + dx > 0 && x + dx < StartingClass.WIDTH - 50) {
			x += dx;
			if (dx > 0)
				currAnimation = animationR;
			if (dx < 0)
				currAnimation = animationL;
		}
		if (y + dy > 0 && y + dy < StartingClass.HEIGTH - 50)
			y += dy;
	}

	public void keyPressed(KeyEvent e) {

		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			dx = -speed;
		}

		if (key == KeyEvent.VK_D) {
			dx = speed;
		}

		if (key == KeyEvent.VK_W) {
			dy = -speed;
		}

		if (key == KeyEvent.VK_S) {
			dy = speed;
		}
	}

	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();

		if (key == KeyEvent.VK_A) {
			dx = 0;
		}

		if (key == KeyEvent.VK_D) {
			dx = 0;
		}

		if (key == KeyEvent.VK_W) {
			dy = 0;
		}

		if (key == KeyEvent.VK_S) {
			dy = 0;
		}
	}

	public void fire(int xt, int yt) {
		rockets.add(new Rocket(x, y, xt, yt));
	}

	public void update() {
		animationL.update(10);
		animationR.update(10);
		move();
		for (int i = 0; i < rockets.size(); i++){
			if (rockets.get(i).visible)
				rockets.get(i).move();
			else 
				rockets.remove(i);
		}
	}

    @Override
	public Image getImage() {
        return currAnimation.getImage();
    }
    
	public ArrayList<Rocket> getRockets() {
		return rockets;
	}

}
