package game;

import game.StartingClass.GameState;

import java.awt.Button;
import java.awt.Color;
import java.awt.Label;
import java.awt.Panel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ScoreBoard extends Panel implements ActionListener {
	private Label score;
	private int n;

	public ScoreBoard() {
		setBackground(Color.WHITE);
		Button b = new Button("Start");
		b.addActionListener(this);
		score = new Label("score: "+ n);
		add(b);
		add(score);
	}
	
	public void updateScore(){
		n++;
		score.setText("Score: " + n);
	}
	
	public void resetScore(){
		n = 0;
		score.setText("Score: " + n);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		StartingClass.state = GameState.Start;
	}
}
