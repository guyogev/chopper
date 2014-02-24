package game;

import java.util.ArrayList;
import java.util.Random;

public class AttackRound {
	private ArrayList<Airplane> airplanes;
	int y, direction, speed;
	Random rand = new Random();

	public AttackRound(int n){
		airplanes = new ArrayList<Airplane>();
		for (int i = 0; i<n; i++){
			direction = rand.nextInt(StartingClass.WIDTH)+1;
			speed = rand.nextInt(3)+3;
			y = rand.nextInt(StartingClass.HEIGTH-50)+1;
			System.out.println("" + " "+direction + " "+speed + " "+y);
			airplanes.add(new Airplane(direction, speed, y));
		}
	}
	
	
	public ArrayList<Airplane> getAirplanes() {
		return airplanes;
	}
}
