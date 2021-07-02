package net.demomaker.applegame.game.game; //package

/*Imports*/

import net.demomaker.applegame.engine.game.Game;
import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.input.Mouseboard;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.ResourceFinder;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.graphics.JavaGraphics;
import net.demomaker.applegame.game.logic.Screen;
import net.demomaker.applegame.game.logic.*;
import net.demomaker.applegame.game.scene.EndScene;
import net.demomaker.applegame.game.scene.GameScene;
import net.demomaker.applegame.game.scene.OptionScene;
import net.demomaker.applegame.game.scene.TitleScene;
import net.demomaker.applegame.engine.util.ImageObserver;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferStrategy;

import static net.demomaker.applegame.game.consts.SharedObjectKeys.*;

public class DemomakerGame extends Game {

	private static final long serialVersionUID = 1L;
	private static final int WIDTH = 900;
	private static final int HEIGHT = WIDTH / 16 * 9;
	private static final int SCALE = 3;

	private final String titlename = "DAG - Demomaker's Apple Game";
	private final JFrame frame = new JFrame();
	private final Keyboard key = new Keyboard();
	private final Mouseboard mouseboard = new Mouseboard();

	private boolean showFPS = false;
	private boolean FullScreen = false;
	private int winWIDTH = WIDTH;
	private int winHEIGHT = HEIGHT;
	private String FPSstring;

	private Keyboard.KeyboardListener keyboardListener = new Keyboard.KeyboardListener() {
		@Override
		public void onKeyPressed(int key) {
		}

		@Override
		public void onKeyReleased(int key) {
			if (key == KeyEvent.VK_F4) {
				GameWindow.fullscreen();
			}

			if (key == KeyEvent.VK_F3) {
				showFPS = !showFPS;
			}
		}
	};

	public DemomakerGame() {
		Dimension size = new Dimension(winWIDTH * SCALE, winHEIGHT * SCALE);
		setPreferredSize(size);
		frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentResized(ComponentEvent e) {
				winWIDTH = frame.getWidth();
				winHEIGHT = frame.getHeight();
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
		addKeyListener(key);
		addMouseListener(mouseboard);
		addMouseMotionListener(mouseboard);
		// Timer
		Timer timer = new Timer(1000, null);
		timer.addActionListener(IncreaseTime());
		timer.start();

	}

	@Override
	public void initGame() {
		SceneManager.init();
		SceneManager.setSharedObject(KeyboardKey, key);
		SceneManager.setSharedObject(FrameKey, frame);
		SceneManager.setSharedObject(PlaySoundKey, false);
		SceneManager.setSharedObject(PlayMusicKey, false);
		SceneManager.setSharedObject(DifficultyKey, Difficulty.Easy.ordinal());
		SceneManager.addScene(new EndScene());
		SceneManager.addScene(new GameScene());
		SceneManager.addScene(new TitleScene());
		SceneManager.addScene(new OptionScene());
		SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
	}

	@Override
	public void startGame() {
		ResourceFinder.setRoot(this);
		frame.setResizable(true);
		frame.setTitle(titlename);
		frame.add(this);
		frame.setVisible(true);
		frame.pack();
		frame.setSize(WIDTH, HEIGHT);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLocationRelativeTo(null);
		GameWindow.setName(titlename);
		GameWindow.setWidth(frame.getWidth());
		GameWindow.setHeight(frame.getHeight());
		ImageObserver.setImageObserver(this);
		frame.addComponentListener(new ComponentAdapter() {
			@Override
			public void componentResized(ComponentEvent e) {
				super.componentResized(e);
				GameWindow.setWidth(frame.getWidth());
				GameWindow.setHeight(frame.getHeight());
				Screen.getInstance().setSize(GameWindow.getWidth(), GameWindow.getHeight() - 39);
			}
		});
		start();
	}

	@Override
	public void endGame() {
		frame.dispatchEvent(new WindowEvent(frame, WindowEvent.WINDOW_CLOSING));
	}

	public ActionListener IncreaseTime() {
		return null;
	}

	@Override
	public void updateGame(float deltaTime) {
		if(!SceneManager.getActiveScene().finishedLoading()) return;
		if (Keyboard.keyPressed(KeyEvent.VK_F4)) {
			FullScreen();
			FullScreen = !FullScreen;
		}

		if (Keyboard.keyPressed(KeyEvent.VK_F3)) {
			showFPS = !showFPS;
		}
		SceneManager.getActiveScene().update(deltaTime);
	}

	@Override
	public void renderGame() {
		if(!SceneManager.getActiveScene().finishedLoading()) return;
		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;

		}
		JavaGraphics javaGraphics = new JavaGraphics();
		Graphics g = bs.getDrawGraphics();
		javaGraphics.setGraphics(g);
		GraphicsManager.init(javaGraphics);
		AdvancedImage image = AdvancedImage.createImageFromWidthAndHeight(GameWindow.getWidth(), GameWindow.getHeight());
		GraphicsManager.drawImage(image, new Vector3<>(0f, 0f, 0f));
		SceneManager.getActiveScene().draw();
		if (showFPS) {
			g.setColor(Color.WHITE);
			g.drawString(FPSstring, 0, 10);
		}
		GraphicsManager.cleanup();
		bs.show();
	}

	// loading JFrame
	public void FullScreen() {
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
		frame.setVisible(true);
		frame.setAlwaysOnTop(true);
		frame.setResizable(false);
		frame.setSize(WIDTH, HEIGHT);
		frame.setAlwaysOnTop(false);
		frame.setResizable(true);
	}

	public static void main(String[] args) {
		new DemomakerGame();
		GameWindow.start();
	}

	private GameWindow.GameWindowListener gameWindowListener = new GameWindow.GameWindowListener() {
		@Override
		public void onClose() {
			endGame();
		}

		@Override
		public void onStart() {
			startGame();
		}

		@Override
		public void onFullScreen() {
			FullScreen();
		}

		@Override
		public void onFPSSet() {
			FPSstring = ("Size:" + (GameWindow.getWidth() - 16) + ", " + (GameWindow.getHeight() - 39) + " | " + GameWindow.getFPS()
					+ " FPS | " + GameWindow.getUpdates() + " ups");
		}

		@Override
		public void onUpdatesSet() {
			FPSstring = ("Size:" + (GameWindow.getWidth() - 16) + ", " + (GameWindow.getHeight() - 39) + " | " + GameWindow.getFPS()
					+ " FPS | " + GameWindow.getUpdates() + " ups");
		}

	};
}
