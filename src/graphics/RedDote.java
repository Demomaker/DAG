package graphics;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import controller.DemomakerGame;

public class RedDote {
	int WIDTH = 900;
	int HEIGHT = WIDTH / 16 * 9;

	public boolean Touched = false;
	// Randomize X and Y Positions
	public Random rand = new Random();
	public Random rands = new Random();
	public int RedX = rand.nextInt(DemomakerGame.winWIDTH - 5) + 0;
	public int RedY = rands.nextInt(DemomakerGame.winHEIGHT - 30) + 0;
	private Random rande = new Random();
	private Random randse = new Random();
	public int RedXe = rand.nextInt(DemomakerGame.winWIDTH - 5) + 0;
	public int RedYe = rands.nextInt(DemomakerGame.winHEIGHT - 30) + 0;

	public void Random() {
		// Randomize X and Y Positions
		rand = new Random();
		rands = new Random();
		RedX = rand.nextInt(DemomakerGame.winWIDTH - 10) + 0;
		RedY = rands.nextInt(DemomakerGame.winHEIGHT - 30) + 0;
	}

	public void render(Graphics g) {
		// Show Red Dot
		g.setColor(Color.RED);
		g.fillRect(RedX, RedY, 10, 10);

	}

	public void ruf() {
		rande = new Random();
		randse = new Random();
	}

	public void rund() {
		RedXe = rande.nextInt(DemomakerGame.winWIDTH - 10) + 0;
		RedYe = randse.nextInt(DemomakerGame.winHEIGHT - 30) + 0;
	}

	public void bought(Graphics g) {

		g.setColor(Color.RED);
		g.fillRect(RedXe, RedYe, 10, 10);

	}

}
