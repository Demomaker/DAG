package controller; //package

/*Imports*/

import controls.Keyboard;
import controls.Mouseboard;
import graphics.Screen;
import logic.*;
import logic.Button;
import scenes.EndScene;
import scenes.GameScene;
import scenes.OptionScene;
import scenes.TitleScene;
import sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import static consts.SharedObjectKeys.*;

/***
 * Main class
 */
public class DemomakerGame extends Canvas implements Runnable {
	// JFrame variables
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 900;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;
	public static int winWIDTH = WIDTH;
	public static int winHEIGHT = HEIGHT;
	public static String titlename = "DAG - Demomaker's Apple Game";
	private Thread thread;
	private boolean running = false;
	private final Screen screen;
	public JFrame frame;
	private final Keyboard key;
	public String FPSstring;
	public boolean showFPS = false;
	public static boolean FullScreen = false;

	public DemomakerGame() {
		Dimension size = new Dimension(winWIDTH * SCALE, winHEIGHT * SCALE);
		setPreferredSize(size);
		screen = new Screen(winWIDTH, winHEIGHT);
		frame = new JFrame();
		key = new Keyboard();
		frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				winWIDTH = frame.getWidth();
				winHEIGHT = frame.getHeight();
				screen.setSize(winWIDTH, winHEIGHT);
			}

			@Override
			public void componentMoved(ComponentEvent e) {

			}

			@Override
			public void componentShown(ComponentEvent e) {

			}

			@Override
			public void componentHidden(ComponentEvent e) {

			}
		});
		Mouseboard button = new Mouseboard();
		addKeyListener(key);
		addMouseListener(button);
		addMouseMotionListener(button);
		// Timer
		Timer timer = new Timer(1000, null);
		timer.addActionListener(IncreaseTime());
		timer.start();

	}

	// Start Thread
	public synchronized void start() {
		running = true;
		thread = new Thread(this, "Display");
		thread.start();
	}

	// Stop Thread
	public synchronized void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	private void init() {
		SceneManager.init();
		SceneManager.setSharedObject(KeyboardKey, key);
		SceneManager.setSharedObject(ScreenKey, screen);
		SceneManager.setSharedObject(FrameKey, frame);
		SceneManager.setSharedObject(PlaySoundKey, false);
		SceneManager.setSharedObject(PlayMusicKey, false);
		SceneManager.setSharedObject(DifficultyKey, Difficulty.Easy.ordinal());
		SceneManager.addScene(new EndScene());
		SceneManager.addScene(new GameScene());
		SceneManager.addScene(new TitleScene());
		SceneManager.addScene(new OptionScene());
		SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
		SceneManager.getActiveScene().init();
	}

	public ActionListener IncreaseTime() {
		return null;
	}

	// Rendering bits
	public void run() {
		init();
		long Timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		int frames = 0;
		int updates = 0;
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		boolean continueGame = true;
		// TODO Auto-generated method stub
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update((float)delta);
				updates++;
				delta--;

			}

			render();
			frames++;
			if (System.currentTimeMillis() - Timer > 1000) {
				Timer += 1000;
				FPSstring = ("Size:" + (winWIDTH - 16) + ", " + (winHEIGHT - 39) + " | " + frames
						+ " FPS | " + updates + " ups");
				updates = 0;
				frames = 0;
			}

		}
		stop();

	}

	public void update(float deltaTime) {
		if (key.ffour) {
			FullScreen();
			FullScreen = !FullScreen;
		}

		if (key.fthree && key.kr) {
			showFPS = !showFPS;
			key.kr = false;
		}
		SceneManager.getActiveScene().update(deltaTime);
	}

	// Rendering Things
	public void render() {

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;

		}
		Graphics g = bs.getDrawGraphics();
		SceneManager.getActiveScene().draw(g);
		if (showFPS) {
			g.setColor(Color.WHITE);
			g.drawString(FPSstring, 0, 10);
		}
		g.dispose();
		bs.show();
	}

	// loading JFrame
	public void FullScreen() {
		if (FullScreen && key.kr) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			frame.setResizable(false);
		}
		if (FullScreen || !key.kr) {
			return;
		}
		frame.setSize(WIDTH, HEIGHT);
		frame.setAlwaysOnTop(false);
		frame.setResizable(true);
	}

	public static void main(String[] args) {
		DemomakerGame game = new DemomakerGame();
		game.frame.setResizable(true);
		game.frame.setTitle(DemomakerGame.titlename);
		game.frame.add(game);
		game.frame.setVisible(true);
		game.frame.pack();
		game.frame.setSize(WIDTH, HEIGHT);
		game.frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		game.frame.setLocationRelativeTo(null);
		winWIDTH = game.frame.getWidth();
		winHEIGHT = game.frame.getHeight();
		game.start();
	}

}
