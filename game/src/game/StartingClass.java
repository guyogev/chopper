package game;

import java.applet.Applet;
import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.Frame;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

public class StartingClass extends Applet implements Runnable, KeyListener,
		MouseListener {
	enum GameState {
		Begin, Running, Start, GameOver
	};

	public static GameState state;
	public static final int HEIGTH = 600;
	public static final int WIDTH = 800;

	private Image bufferImage;
	private Graphics bufferGraphics;
	private ScoreBoard scoreBoard;

	private Background background;
	private Chopper chopper;
	private ArrayList<Airplane> enemies;
	private double attackRoundSize;
	private AttackRound attackRound;
	private ArrayList<AirExplision> airExplosions;

	@Override
	public void init() {

		setSize(WIDTH, HEIGTH);
		background = new Background();
		Frame frame = (Frame) this.getParent().getParent();
		//frame.setTitle("myGame");
		setLayout(new BorderLayout());
		scoreBoard = new ScoreBoard();
		add(scoreBoard, BorderLayout.PAGE_END);
		setFocusable(true);
		state = GameState.Begin;

	}

	@Override
	public void start() {
		Thread thread = new Thread(this);
		thread.start();
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
	}

	@Override
	public void destroy() {
		// TODO Auto-generated method stub
	}

	@Override
	public void run() {
		// main game loops
		while (true) {
			// System.out.println(state);
			if (state != GameState.Begin) {
				if (state == GameState.Start) {
					startGame();
					state = GameState.Running;
				}
				if (state == GameState.Running)
					if (enemies.size() == 0) {
						attackRoundSize += .5;
						attackRound = new AttackRound((int)attackRoundSize);
						enemies = attackRound.getAirplanes();
					}
				updateBaord();
				repaint();
				try {
					Thread.sleep(17);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}
	}

	private void startGame() {
		chopper = new Chopper();
		attackRoundSize = 2;
		attackRound = new AttackRound((int)attackRoundSize);
		enemies = attackRound.getAirplanes();
		airExplosions = new ArrayList<AirExplision>();
		scoreBoard.resetScore();
		addKeyListener(this);
		addMouseListener(this);
		requestFocusInWindow();
		System.out.println("game started");
	}

	@Override
	public void paint(Graphics g) {
		Graphics2D g2d = (Graphics2D) g;

		if (state == GameState.Begin) {
			g2d.drawImage(background.getImage(), background.getX(),
					background.getY(), this);
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString("press Start", 360, 240);
		}
		if (state == GameState.Running) {
			g2d.drawImage(background.getImage(), background.getX(),
					background.getY(), this);
			g2d.drawImage(chopper.getImage(), chopper.getX(), chopper.getY(),
					this);
			chopper.update();
			ArrayList<Rocket> rockets = chopper.getRockets();
			for (int i = 0; i < rockets.size(); i++) {
				Rocket r = rockets.get(i);
				g2d.drawImage(r.getImage(), r.getX(), r.getY(), this);
			}
			for (int i = 0; i < enemies.size(); i++) {
				Airplane a = enemies.get(i);
				g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
			for (int i = 0; i < airExplosions.size(); i++) {
				AirExplision a = airExplosions.get(i);
				g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
		}
		if (state == GameState.GameOver) {
			g2d.drawImage(background.getImage(), background.getX(),
					background.getY(), this);
			g.setFont(new Font("default", Font.BOLD, 16));
			g.drawString("Game Over", 360, 240);
			for (int i = 0; i < airExplosions.size(); i++) {
				AirExplision a = airExplosions.get(i);
				g2d.drawImage(a.getImage(), a.getX(), a.getY(), this);
			}
		}

	}

	public void updateBaord() {
		if (!chopper.visible)
			state = GameState.GameOver;
		chopper.update();
		for (int i = 0; i < enemies.size(); i++) {
			if (!enemies.get(i).visible)
				enemies.remove(i);
			else
				enemies.get(i).move();
		}
		checkCollisions();
		for (int i = 0; i < airExplosions.size(); i++) {
			if (!airExplosions.get(i).visible)
				airExplosions.remove(i);
			else
				airExplosions.get(i).update();
		}
	}

	@Override
	public void update(Graphics g) {
		if (bufferImage == null) {
			bufferImage = createImage(this.getWidth(), this.getHeight());
			bufferGraphics = bufferImage.getGraphics();
		}
		bufferGraphics.setColor(getBackground());
		bufferGraphics.fillRect(0, 0, getWidth(), getHeight());
		bufferGraphics.setColor(getForeground());
		paint(bufferGraphics);
		g.drawImage(bufferImage, 0, 0, this);
	}

	public void checkCollisions() {
		for (int i = 0; i < enemies.size(); i++) {
			Rectangle r1 = enemies.get(i).getBounds();
			for (int j = 0; j < chopper.getRockets().size(); j++) {
				Rectangle r2 = chopper.getRockets().get(j).getBounds();
				if (r1.intersects(r2)) {
					airExplosions.add(new AirExplision((int) r1.getCenterX(),
							(int) r1.getCenterY()));
					enemies.get(i).setVisible(false);
					chopper.getRockets().get(j).setVisible(false);
					scoreBoard.updateScore();
					System.out.println("collision detected");
				}
			}
			Rectangle r2 = chopper.getBounds();
			if (r1.intersects(r2)){
				airExplosions.add(new AirExplision((int) r1.getCenterX(),
						(int) r1.getCenterY()));
				chopper.setVisible(false);
				enemies.get(i).setVisible(false);
			}
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		// System.out.println(chopper.getX());
		chopper.keyPressed(e);
	}

	@Override
	public void keyReleased(KeyEvent e) {
		chopper.keyReleased(e);

	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseClicked(MouseEvent e) {

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mousePressed(MouseEvent e) {
		System.out.println("mouse pressed");
		chopper.fire(e.getX(), e.getY());
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

}
