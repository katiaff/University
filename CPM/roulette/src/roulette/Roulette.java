package roulette;

import java.util.Random;


/**
 *
 * @author Evan and Carla
 */
public class Roulette {

	private int number;

	public int getNumber() {
		return number;
	}

	public void startGame() {
		// Between 0-37
//		number = (int) (Math.random() * 37);
		number = new Random().nextInt(37);
	}

	
	

	
	
}
