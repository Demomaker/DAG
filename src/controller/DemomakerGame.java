package controller; //package

/*Imports*/
/*********/

import graphics.RedDote;
import graphics.Screen;
import sounds.Sound;

import java.awt.event.*;
import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;
import java.util.TimerTask;
import javax.swing.JFrame;
import javax.swing.Timer;

import controls.Keyboard;
import controls.Mouseboard;

/*********/
// class
public class DemomakerGame extends Canvas implements Runnable {
	/**
	 * 
	 */
	// Timer
	Timer timer = new Timer(1000, null);
	// JFrame variables
	private static final long serialVersionUID = 1L;
	public static int WIDTH = 900;
	public static int HEIGHT = WIDTH / 16 * 9;
	public static int SCALE = 3;
	public static int winWIDTH = WIDTH;
	public static int winHEIGHT = HEIGHT;
	public static String titlename = "DAG - Demomaker's Apple controller";

	Image[] numberImages;
	ArrayList<Integer> redDotsX = new ArrayList<Integer>();
	ArrayList<Integer> redDotsY = new ArrayList<Integer>();
	ArrayList<Integer> numRedDots = new ArrayList<Integer>();
	ArrayList<Integer> numYellowDots = new ArrayList<Integer>();
	ArrayList<Integer> yellowDotsX = new ArrayList<Integer>();
	ArrayList<Integer> yellowDotsY = new ArrayList<Integer>();
	int YellowTitleX = 0;
	int YellowTitleY = 0;
	public Random randel = new Random();
	int RedTitleX = randel.nextInt(DemomakerGame.winWIDTH - 10) + 0;
	int RedTitleY = randel.nextInt(DemomakerGame.winHEIGHT - 30) + 0;
	int waittr = 0;
	int MeterX = 100;
	int MeterY = 50;
	int redDotX;
	int redDotY;
	int YellowCount = 0;
	int yellowSpeed = 0;
	int yellowActualSpeed = 1;
	public Random rande = new Random();
	public Random randse = new Random();
	// Random variables
	public boolean Option = false;
	public boolean showFPS = false;
	public String FPSshower;
	public boolean ScoreBonus = false;
	public boolean ResetRed = false;
	public int GameTimerUnit /* = 0 */;
	public int GameTimerDozen /* = 0 */;
	public int GameTimerHundred /* = 3 */;
	public boolean GameFinish = false;
	public boolean Replay = false;
	public static boolean FullScreen = false;
	public int FullTimer = 3000;
	public int ScoreInt = 0;
	public int Highscore = 0;
	int numberTotal = 0;
	boolean MusicPlay = false;
	int MoneyAdd = 1;
	int all;
	int ALLMoney;
	int Clicks;
	int j = 0;
	boolean StopCounting = false;
	public static boolean RedBuy = false;
	int ab = 0;
	int at = 0;
	int Second = 1;
	boolean AddRed = false;
	boolean Checked = false;
	boolean CheckedWinner = false;
	boolean ButtonOne = false;
	boolean CheckedTwo = false;
	boolean CheckedTwoWinner = false;
	boolean ButtonOneTwo = false;
	boolean ResetR = false;
	boolean ButtonOneR = false;
	int musicwait = 0;
	int Terminal = 0;
	public boolean Sounding = true;
	public boolean Musicing = true;

	public boolean GameTimer = false;
	public boolean Move = false;
	public boolean Escape = false;
	private boolean Game = false;

	private Thread thread;
	private boolean running = false;

	// Buffering Variables
	private BufferedImage image = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_RGB);
	private int[] pixels = ((DataBufferInt) image.getRaster().getDataBuffer()).getData();

	// variables
	private Screen screen;
	public JFrame frame;
	private Keyboard key;
	private Mouseboard button;
	private RedDote Red;
	private RedDote RedT;
	int Score = 0;
	int TenScores = 0;
	int OneHundredScores = 0;
	int ThousandScores = 0;
	int ShopButtonX;
	int ShopButtonY = HEIGHT - 69;
	int Money = 2000;
	int Checking = 0;
	int Checkclicks = 0;
	int MenuX = winWIDTH - (winWIDTH / 2) - 128 / 2;
	int MenuY = winHEIGHT - (winHEIGHT / 2) - 72 / 2;
	boolean ShopOpen = true;
	boolean NewRedDote = false;
	boolean ReBuy;
	// Audio

	// Image URLs
	// controller Extras URLs
	URL cored = DemomakerGame.class.getResource("/resources/NewScore.png");
	URL grid = DemomakerGame.class.getResource("/resources/Grid.png");
	URL menu = DemomakerGame.class.getResource("/resources/Menu.png");
	URL time = DemomakerGame.class.getResource("/resources/Timer.png");
	URL gameend = DemomakerGame.class.getResource("/resources/GameFinish.png");
	URL restart = DemomakerGame.class.getResource("/resources/Restart.png");
	URL highscore = DemomakerGame.class.getResource("/resources/HighScore.png");
	URL escape = DemomakerGame.class.getResource("/resources/Escape.png");
	URL options = DemomakerGame.class.getResource("/resources/Options.png");
	URL optionmenu = DemomakerGame.class.getResource("/resources/OptionMenu.png");
	URL meter = DemomakerGame.class.getResource("/resources/Meter.png");
	URL sound = DemomakerGame.class.getResource("/resources/Sound.png");
	URL cross = DemomakerGame.class.getResource("/resources/Cross.png");
	URL music = DemomakerGame.class.getResource("/resources/Music.png");

	// Title Screen URLs
	URL titlescreen = DemomakerGame.class.getResource("/resources/titlescreen.png");
	// URL play = DemomakerGame.class.getResource("/resources/Nope.png");
	URL ifficulty = DemomakerGame.class.getResource("/resources/Difficulty.png");
	URL hard = DemomakerGame.class.getResource("/resources/Hard.png");
	URL normal = DemomakerGame.class.getResource("/resources/Normal.png");
	URL easy = DemomakerGame.class.getResource("/resources/Easy.png");

	// Shop URLs
	URL shopbutton = DemomakerGame.class.getResource("/resources/ShopButton.png");
	URL shopbackground = DemomakerGame.class.getResource("/resources/ShopBackground.png");
	URL reddoteimage = DemomakerGame.class.getResource("/resources/RedDoteImage.png");
	URL reddotebuy = DemomakerGame.class.getResource("/resources/RedDoteBuy.png");
	URL reddotebuyhover = DemomakerGame.class.getResource("/resources/RedDoteBuyHover.png");
	URL reddotebuyaction = DemomakerGame.class.getResource("/resources/RedDoteBuyAction.png");
	URL moneyaddimage = DemomakerGame.class.getResource("/resources/MoneyAddImage.png");
	URL moneyaddbuy = DemomakerGame.class.getResource("/resources/MoneyAddBuy.png");
	URL moneyaddbuyaction = DemomakerGame.class.getResource("/resources/MoneyAddBuyAction.png");
	URL moneyaddbuyhover = DemomakerGame.class.getResource("/resources/MoneyAddBuyHover.png");
	URL yellowautoimage = DemomakerGame.class.getResource("/resources/YellowAutoImage.png");
	URL yellowaddbuy = DemomakerGame.class.getResource("/resources/YellowAddBuy.png");
	URL yellowaddbuyaction = DemomakerGame.class.getResource("/resources/YellowAddBuyAction.png");
	URL yellowaddbuyhover = DemomakerGame.class.getResource("/resources/YellowAddBuyHover.png");
	URL checkbox = DemomakerGame.class.getResource("/resources/Checkbox.png");
	URL checker = DemomakerGame.class.getResource("/resources/Checker.png");
	URL redreset = DemomakerGame.class.getResource("/resources/RedReset.png");

	/* Number URLs */
	/**************/
	URL zero = DemomakerGame.class.getResource("/resources/Zero.png");
	URL one = DemomakerGame.class.getResource("/resources/One.png");
	URL two = DemomakerGame.class.getResource("/resources/Two.png");
	URL three = DemomakerGame.class.getResource("/resources/Three.png");
	URL four = DemomakerGame.class.getResource("/resources/Four.png");
	URL five = DemomakerGame.class.getResource("/resources/Five.png");
	URL six = DemomakerGame.class.getResource("/resources/Six.png");
	URL seven = DemomakerGame.class.getResource("/resources/Seven.png");
	URL eight = DemomakerGame.class.getResource("/resources/Eight.png");
	URL nine = DemomakerGame.class.getResource("/resources/Nine.png");
	/**************/

	int ij;

	// controller Extras Images
	public Image scored = Toolkit.getDefaultToolkit().getImage(cored);
	public Image Grid = Toolkit.getDefaultToolkit().getImage(grid);
	private Image Menu = Toolkit.getDefaultToolkit().getImage(menu);
	private Image Time = Toolkit.getDefaultToolkit().getImage(time);
	private Image GameEnd = Toolkit.getDefaultToolkit().getImage(gameend);
	private Image Restart = Toolkit.getDefaultToolkit().getImage(restart);
	private Image HighScore = Toolkit.getDefaultToolkit().getImage(highscore);
	private Image Escap = Toolkit.getDefaultToolkit().getImage(escape);
	private Image Options = Toolkit.getDefaultToolkit().getImage(options);
	private Image OptionMenu = Toolkit.getDefaultToolkit().getImage(optionmenu);
	private Image Meter = Toolkit.getDefaultToolkit().getImage(meter);
	private Image Sounds = Toolkit.getDefaultToolkit().getImage(sound);
	private Image Cross = Toolkit.getDefaultToolkit().getImage(cross);
	private Image Music = Toolkit.getDefaultToolkit().getImage(music);

	// Title Screen Images
	private Image TitleScreen = Toolkit.getDefaultToolkit().getImage(titlescreen);
	// private Image Play = Toolkit.getDefaultToolkit().getImage(play);
	private Image Difficulty = Toolkit.getDefaultToolkit().getImage(ifficulty);
	private Image Hard = Toolkit.getDefaultToolkit().getImage(hard);
	private Image Normal = Toolkit.getDefaultToolkit().getImage(normal);
	private Image Easy = Toolkit.getDefaultToolkit().getImage(easy);
	public String difficulty = "Normal";

	// Shop Images

	private Image ShopButton = Toolkit.getDefaultToolkit().getImage(shopbutton);
	private Image ShopBackground = Toolkit.getDefaultToolkit().getImage(shopbackground);
	private Image RedDoteImage = Toolkit.getDefaultToolkit().getImage(reddoteimage);
	private Image RedDoteBuy = Toolkit.getDefaultToolkit().getImage(reddotebuy);
	private Image RedDoteBuyHover = Toolkit.getDefaultToolkit().getImage(reddotebuyhover);
	private Image RedDoteBuyAction = Toolkit.getDefaultToolkit().getImage(reddotebuyaction);
	private Image MoneyAddImage = Toolkit.getDefaultToolkit().getImage(moneyaddimage);
	private Image MoneyAddBuy = Toolkit.getDefaultToolkit().getImage(moneyaddbuy);
	private Image MoneyAddBuyAction = Toolkit.getDefaultToolkit().getImage(moneyaddbuyaction);
	private Image MoneyAddBuyHover = Toolkit.getDefaultToolkit().getImage(moneyaddbuyhover);
	private Image YellowAutoImage = Toolkit.getDefaultToolkit().getImage(yellowautoimage);
	private Image YellowAddBuy = Toolkit.getDefaultToolkit().getImage(yellowaddbuy);
	private Image YellowAddBuyAction = Toolkit.getDefaultToolkit().getImage(yellowaddbuyaction);
	private Image YellowAddBuyHover = Toolkit.getDefaultToolkit().getImage(yellowaddbuyhover);

	private Image Checkbox = Toolkit.getDefaultToolkit().getImage(checkbox);
	private Image Checker = Toolkit.getDefaultToolkit().getImage(checker);
	private Image RedReset = Toolkit.getDefaultToolkit().getImage(redreset);

	/* Number Images */
	/***************/
	private Image ZERO = Toolkit.getDefaultToolkit().getImage(zero);
	private Image ONE = Toolkit.getDefaultToolkit().getImage(one);
	private Image TWO = Toolkit.getDefaultToolkit().getImage(two);
	private Image THREE = Toolkit.getDefaultToolkit().getImage(three);
	private Image FOUR = Toolkit.getDefaultToolkit().getImage(four);
	private Image FIVE = Toolkit.getDefaultToolkit().getImage(five);
	private Image SIX = Toolkit.getDefaultToolkit().getImage(six);
	private Image SEVEN = Toolkit.getDefaultToolkit().getImage(seven);
	private Image EIGHT = Toolkit.getDefaultToolkit().getImage(eight);
	private Image NINE = Toolkit.getDefaultToolkit().getImage(nine);

	/***************/

	// Other Class adding
	// TimerTask
	TimerTask timerTask;

	public DemomakerGame() {
		Dimension size = new Dimension(WIDTH * SCALE, HEIGHT * SCALE);
		setPreferredSize(size);
		screen = new Screen(WIDTH, HEIGHT);
		frame = new JFrame();
		key = new Keyboard();
		button = new Mouseboard();
		Red = new RedDote();
		RedT = new RedDote();
		addKeyListener(key);
		addMouseListener(button);
		addMouseMotionListener(button);
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

	public ActionListener IncreaseTime() {
		return null;

	}

	// Rendering bits
	public void run() {
		long Timer = System.currentTimeMillis();
		long lastTime = System.nanoTime();
		int frames = 0;
		int updates = 0;
		final double ns = 1000000000.0 / 60.0;
		double delta = 0;
		// TODO Auto-generated method stub
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;
			while (delta >= 1) {
				update();
				updates++;
				delta--;

			}

			render();
			frames++;
			if (System.currentTimeMillis() - Timer > 1000) {
				Timer += 1000;
				FPSshower = ("Size:" + (frame.getWidth() - 16) + ", " + (frame.getHeight() - 39) + " | " + frames
						+ " FPS | " + updates + " ups");
				updates = 0;
				frames = 0;
			}

		}
		stop();

	}

	// Yellow Guy x and y coords;
	protected int x = 0;
	protected int y = 0;
	int wait = 0;

	public void update() {
		key.update();

		if (key.ffour) {
			FullScreen();
			FullScreen = !FullScreen;
			wait = 0;
		}
		if (wait > 100) {
			wait = 0;
		}

		if (key.fthree && key.kr) {
			showFPS = !showFPS;
			key.kr = false;
		}
		// Difficulty selecting if game isnt running
		if (Option) {
			if (key.e) {
				difficulty = "Easy";
			}
			if (key.n) {
				difficulty = "Normal";
			}
			if (key.h) {
				difficulty = "Hard";
			}
		}

		if (Game == false) {
			x = 0;
			y = 0;
			if (key.space) {
				Game = true;
			}
			ResetRed = true;
		}

		// When the game finishes, Reset Scores
		if (GameFinish == true) {
			if (key.p) {
				Replay = true;
				Score = 0;
				TenScores = 0;
				OneHundredScores = 0;
			}
		}
		// When player is playing
		if (Game == true && !Escape) {

			Move = true;
			if (key.m) {
				Game = false;
			}
			// Moving Speed with different difficulties
			if (difficulty == "Normal") {
				if (key.up) {
					y = y - 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.down) {
					y = y + 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.left) {
					x = x - 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.right) {
					x = x + 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
			}
			if (difficulty == "Easy") {
				if (key.up) {
					y = y - 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.down) {
					y = y + 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.left) {
					x = x - 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.right) {
					x = x + 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
			}
			if (difficulty == "Hard") {
				if (key.up) {
					y = y - 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.down) {
					y = y + 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.left) {
					x = x - 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
				if (key.right) {
					x = x + 1;
					if (Sounding)
						Sound.yellowbeep.play();
				}
			}
		}

	}

	// Music Playing
	public void musicp() {
		Sound.music.play();
	}

	// Rendering Things
	public void render() {

		// ShopButtonValues
		ShopButtonX = frame.getWidth() - 116;

		BufferStrategy bs = getBufferStrategy();
		if (bs == null) {
			createBufferStrategy(3);
			return;

		}
		screen.Clear();
		screen.render();

		for (int i = 0; i < pixels.length; i++) {
			pixels[i] = screen.pixels[i];

		}

		// If Player is with red, Touching is true
		if (!Escape) {
			if (Red.RedX == x) {
				if (Red.RedY == y) {
					Red.Touched = true;
				}
			}

			if (YellowTitleX == RedTitleX && YellowTitleY == RedTitleY) {
				RedTitleX = randel.nextInt(winWIDTH - 10) + 0;
				RedTitleY = randel.nextInt(winHEIGHT - 30) + 0;
				if (Sounding == true) {
					Sound.redtouch.play();
				}
			}

			for (int i = 0; i < redDotsX.size(); i++) {
				if (redDotsX.get(i) == x) {
					if (redDotsY.get(i) == y) {
						RedT.Touched = true;
						redDotsX.remove(i);
						redDotsY.remove(i);
						redDotsX.add(rande.nextInt(winWIDTH - 10) + 0);
						redDotsY.add(rande.nextInt(winHEIGHT - 30) + 0);

					}
				}
				for (int j = 0; j < yellowDotsX.size(); j++) {
					if (yellowDotsX.get(j).equals(redDotsX.get(i)) && yellowDotsY.get(j).equals(redDotsY.get(i))) {
						System.out.println("Just Right");
						RedT.Touched = true;
						redDotsX.remove(i);
						redDotsY.remove(i);
						redDotsX.add(rande.nextInt(winWIDTH - 10) + 0);
						redDotsY.add(rande.nextInt(winHEIGHT - 30) + 0);

					}
				}
			}

			// If Touching is true, add money
			if (Red.Touched == true && Sounding == true || RedT.Touched == true && Sounding == true) {
				Sound.redtouch.play();
				for (int j = 0; j < MoneyAdd; j++) {
					Money++;
				}

			}
		}

		// winWIDTH and winHEIGHT equals to frame Width and Height;
		winWIDTH = frame.getWidth();
		winHEIGHT = frame.getHeight();

		// When Red is touched, Actions
		if (Red.Touched == true) {
			Score++;
			ScoreInt++;
			ScoreBonus = true;
			Red.rand = new Random();
			Red.rands = new Random();
			Red.RedX = Red.rand.nextInt(WIDTH) + 0;
			Red.RedY = Red.rands.nextInt(HEIGHT) + 0;
			Red.Touched = false;

		}
		// Red Replacement when Resetting Red or Replay
		if (ResetRed == true || Replay == true) {
			Red.rand = new Random();
			Red.rands = new Random();
			Red.RedX = Red.rand.nextInt(WIDTH) + 0;
			Red.RedY = Red.rands.nextInt(HEIGHT) + 0;
			ResetRed = false;

		}
		// When RedT is touched, Actions
		if (RedT.Touched == true) {
			Score++;
			ScoreInt++;
			ScoreBonus = true;
			RedT.Touched = false;
		}
		// When Player goes off the map, returns other side
		if (x > frame.getWidth() - 14) {

			x = -8;

		}

		if (x < -8) {

			x = frame.getWidth() - 14;
		}

		if (y > frame.getHeight() - 39) {

			y = -5;

		}

		if (y < -5) {

			y = frame.getHeight() - 39;

		}

		// Red Placement calculating
		if (Red.RedX >= 250 && Red.RedX <= 325) {
			Red.rand = new Random();
			Red.RedX = Red.rand.nextInt(WIDTH - 5) + 0;

			if (Red.RedY >= 0 && Red.RedY <= 56) {
				Red.rands = new Random();
				Red.RedY = Red.rands.nextInt(HEIGHT - 30) + 0;
			}
		}

		while (Red.RedX <= 0 || Red.RedX >= 880) {
			Red.rand = new Random();
			Red.RedX = Red.rand.nextInt(WIDTH - 5) + 0;

			while (Red.RedY <= 0 || Red.RedY >= 484) {

				Red.rands = new Random();
				Red.RedY = Red.rands.nextInt(HEIGHT - 30) + 0;

			}
		}
		// redDots Placement calculating
		for (int i = 0; i < numRedDots.size(); i++) {
			if (redDotsX.get(i) >= 250 && redDotsX.get(i) <= 325) {
				rande = new Random();
				redDotsX.remove(i);
				redDotsX.add(rande.nextInt(WIDTH) + 0);

				if (redDotsY.get(i) >= 0 && redDotsY.get(i) <= 56) {
					rande = new Random();
					redDotsY.remove(i);
					redDotsY.add(rande.nextInt(HEIGHT - 30) + 0);
				}
			}

			while (redDotsX.get(i) <= 0 || redDotsX.get(i) >= frame.getWidth()) {
				rande = new Random();
				redDotsX.remove(i);
				redDotsX.add(rande.nextInt(WIDTH - 5) + 0);

				while (redDotsY.get(i) <= 0 || redDotsY.get(i) >= frame.getHeight()) {

					rande = new Random();
					redDotsY.remove(i);
					redDotsY.add(rande.nextInt(HEIGHT - 30) + 0);

				}
			}
		}

		// Timer adjusting
		if (Game == true && FullTimer >= 0 && !Escape) {
			FullTimer = FullTimer - 5;
			if (FullTimer == -1) {
				FullTimer = 0;
			}
		}
		// When time runs out, game finishes
		if (FullTimer == 0) {
			GameFinish = true;
		}

		// Reset Timer and Score when replayed
		if (Game == false || Replay == true) {
			FullTimer = 60000;
			Score = 0;
		}
		// When player replays
		if (Replay == true) {

			GameFinish = false;
			x = 0;
			y = 0;
			Replay = false;

		}

		// Score Calculating
		if (GameFinish == true) {

			at = 0;

			if (at < 1) {
				at++;
				if (Score > Highscore) {
					Highscore = Score;
				}
			}

			Score = 0;
		}

		if (Second == 0) {

			Second = 1;

		}
		rande = new Random();

		if (Game == false) {
			yellowDotsX.clear();
			yellowDotsX.clear();
			yellowDotsY.clear();
			numYellowDots.clear();
			redDotsX.clear();
			redDotsY.clear();
			numRedDots.clear();
			MoneyAdd = 1;
		}

		// Graphics drawing
		Graphics g = bs.getDrawGraphics();
		{

			// When player isnt playing
			if (Game == false) {
				// TitleScreen showing
				Sound.music.stop();
				Score = 0;
				TenScores = 0;
				OneHundredScores = 0;
				ThousandScores = 0;
				MusicPlay = false;
				musicwait = 0;
				g.drawImage(TitleScreen, 0, 0, getWidth(), getHeight(), this);
				g.setColor(Color.yellow);
				g.fillRect(YellowTitleX, YellowTitleY, 10, 10);
				g.setColor(Color.red);
				g.fillRect(RedTitleX, RedTitleY, 10, 10);
				if (YellowTitleX < RedTitleX) {
					YellowTitleX++;
				}
				if (YellowTitleX > RedTitleX) {
					YellowTitleX--;
				}
				if (YellowTitleY < RedTitleY) {
					YellowTitleY++;
				}
				if (YellowTitleY > RedTitleY) {
					YellowTitleY--;
				}

				// g.drawImage(Play, 0, 0, getWidth(), getHeight(),this);
				if (showFPS) {
					g.setColor(Color.WHITE);
					g.drawString(FPSshower, 0, 10);
				}

				// Difficulties showing

			}

			if (MusicPlay && musicwait == 0 && Musicing == true) {

				Sound.music.play();

			}

			if (MusicPlay) {

				musicwait++;

			}

			if (musicwait > 6400) {

				musicwait = 0;

			}

			// When player is playing
			if (Game == true) {
				MusicPlay = true;
				g.drawImage(image, 0, 0, getWidth(), getHeight(), this);
				g.drawImage(Grid, 0, 0, getWidth(), getHeight(), this);
				g.drawImage(Checkbox, frame.getWidth() - 35, HEIGHT / 2 - 50, this);
				g.drawImage(Checkbox, frame.getWidth() - 50, HEIGHT / 2 - 50, this);

				// Checkboxes and Checkers for Shop Opening and Closing
				if (Mouseboard.getX() > frame.getWidth() - 75 && Mouseboard.getX() < frame.getWidth() - 25
						&& Mouseboard.getY() > ShopButtonY + 40 && Mouseboard.getY() < ShopButtonY + 90
						&& Mouseboard.getButton() == 1 && !Escape) {
					ButtonOneR = true;
				}

				if (Mouseboard.getX() > frame.getWidth() - 75 && Mouseboard.getX() < frame.getWidth() - 25
						&& Mouseboard.getY() > ShopButtonY + 40 && Mouseboard.getY() < ShopButtonY + 90
						&& ButtonOneR == true && Mouseboard.getButton() == -1 && !Escape) {
					ResetR = true;
					ButtonOneR = false;
				}

				if (Checked == true && CheckedTwo == false && !Escape) {
					CheckedWinner = true;
				}

				if (Checked == false && CheckedTwo == true && !Escape) {
					CheckedTwoWinner = true;
				}
				if (Checked == true && CheckedTwo == true && CheckedWinner == true && !Escape) {
					Checked = false;
					CheckedWinner = false;
				}
				if (Checked == true && CheckedTwo == true && CheckedTwoWinner == true && !Escape) {
					CheckedTwo = false;
					CheckedTwoWinner = false;
				}

				if (Mouseboard.getX() > frame.getWidth() - 35 && Mouseboard.getX() < frame.getWidth() - 25
						&& Mouseboard.getY() > HEIGHT / 2 - 50 && Mouseboard.getY() < HEIGHT / 2 - 40
						&& Mouseboard.getButton() == 1 && !Escape) {
					ButtonOne = true;
				}
				if (Mouseboard.getX() > frame.getWidth() - 35 && Mouseboard.getX() < frame.getWidth() - 25
						&& Mouseboard.getY() > HEIGHT / 2 - 50 && Mouseboard.getY() < HEIGHT / 2 - 40
						&& ButtonOne == true && Mouseboard.getButton() == -1 && !Escape) {
					Checked = !Checked;
					ButtonOne = false;
				}

				if (Mouseboard.getX() > frame.getWidth() - 35 && Mouseboard.getX() < frame.getWidth() - 25
						&& Mouseboard.getY() > HEIGHT / 2 - 50 && Mouseboard.getY() < HEIGHT / 2 - 40 && !Escape
						|| Checked == true) {
					g.drawImage(Checker, frame.getWidth() - 35, HEIGHT / 2 - 60, this);
				}

				if (Mouseboard.getX() > frame.getWidth() - 50 && Mouseboard.getX() < frame.getWidth() - 40
						&& Mouseboard.getY() > HEIGHT / 2 - 50 && Mouseboard.getY() < HEIGHT / 2 - 40
						&& Mouseboard.getButton() == 1 && !Escape) {
					ButtonOneTwo = true;
				}
				if (Mouseboard.getX() > frame.getWidth() - 50 && Mouseboard.getX() < frame.getWidth() - 40
						&& Mouseboard.getY() > HEIGHT / 2 - 50 && Mouseboard.getY() < HEIGHT / 2 - 40
						&& ButtonOneTwo == true && Mouseboard.getButton() == -1 && !Escape) {
					CheckedTwo = !CheckedTwo;
					ButtonOneTwo = false;
				}

				if (Mouseboard.getX() > frame.getWidth() - 50 && Mouseboard.getX() < frame.getWidth() - 40
						&& Mouseboard.getY() > HEIGHT / 2 - 50 && Mouseboard.getY() < HEIGHT / 2 - 40 && !Escape
						|| CheckedTwo == true) {
					g.drawImage(Checker, frame.getWidth() - 50, HEIGHT / 2 - 60, this);
				}

				if (showFPS) {
					g.setColor(Color.WHITE);
					g.drawString(FPSshower, 0, 10);
				}

				// Opening and Closing Shop
				if (Mouseboard.getX() > ShopButtonX && Mouseboard.getY() > ShopButtonY
						&& Mouseboard.getX() < ShopButtonX + 100 && Mouseboard.getY() < ShopButtonY + 50
						&& Checked == false && !Escape) {

					if (ShopButtonY >= 247 && ShopOpen == true) {
						ShopButtonY--;
					}
					if (ShopButtonY <= 247 || ShopOpen == false) {
						ShopButtonY++;
						ShopOpen = false;
					}
					if (ShopButtonY >= frame.getHeight() - 69 && ShopOpen == false) {
						ShopOpen = true;
					}

				}

				if (Checked == true) {
					ShopButtonY = 247;
				}

				if (CheckedTwo == true) {
					ShopButtonY = frame.getHeight() - 69;
				}

				// Money Showing
				g.setColor(Color.YELLOW);
				g.drawString("Money: " + Money, ShopButtonX + 25, ShopButtonY);

				// Show Time if Time hasnt run out
				if (GameFinish != true) {
					g.drawImage(Time, 615, 0, this);
				}
				// Show Highscore if Time has run out
				if (GameFinish == true) {
					g.drawImage(HighScore, 604, 25, this);
				}
				/* Time Drawing */
				/****************/
				/* GameTimerHundred */
				if (GameFinish == false) {
					int TimerNumber = FullTimer / 200;
					Image[] TimerImages = TransformNumbers(TimerNumber);
					g.drawImage(TimerImages[2], 604, 56, this);
					g.drawImage(TimerImages[1], 663, 56, this);
					g.drawImage(TimerImages[0], 722, 56, this);
				}

				if (GameFinish == true) {

					/* Highscore Drawing */
					/****************/
					/* HighScoreThousand */
					Image[] HighscoreImages = TransformNumbers(Highscore);
					g.drawImage(HighscoreImages[2], 604, 56, this);
					g.drawImage(HighscoreImages[1], 663, 56, this);
					g.drawImage(HighscoreImages[0], 722, 56, this);
				}

				/* Score Drawing */
				/**************/
				/* Units */
				Image[] ScoreImages = TransformNumbers(Score);
				g.drawImage(ScoreImages[2], 256, 56, this);
				g.drawImage(ScoreImages[1], 315, 56, this);
				g.drawImage(ScoreImages[0], 374, 56, this);

				/*********************/
				if (Score >= 1000) {
					g.setColor(Color.WHITE);
					g.drawString("" + (Score / 1000) + "", (374 + 59), 16);
				}
				// Adding Score only when Red is Touched
				if (ScoreBonus == true) {
				}
				if (Red.Touched == false) {
					ScoreBonus = false;
				}

				if (RedBuy == true) {
					numRedDots.add(1);
					redDotsX.add(rande.nextInt(winWIDTH - 10) + 0);
					redDotsY.add(rande.nextInt(winHEIGHT - 30) + 0);

					RedBuy = false;
				}

				if (GameFinish == false) {
					// Yellow Dote Showing

					g.setColor(Color.YELLOW);
					g.fillRect(x, y, 10, 10);

					// Red Dote Showing

					Red.render(g);

					// Red Reset Action
					if (ResetR == true) {
						Red.Random();
						Red.render(g);
						ResetR = false;
					}
					// Red Dote ++ Buying Extras
					for (ij = 0; ij < numRedDots.size(); ij++) {

						g.setColor(Color.RED);
						g.fillRect(redDotsX.get(ij), redDotsY.get(ij), 10, 10);
						redDotX = redDotsX.get(ij);
						redDotY = redDotsY.get(ij);

					}

					// Yellow Dote ++ Buying Extras
					// System.out.println(numYellowDots.size());
					//System.out.println(ij);

					for (int i = 0; i < numYellowDots.size(); i++) {

						g.setColor(Color.YELLOW);
						g.fillRect(yellowDotsX.get(i), yellowDotsY.get(i), 10, 10);

						for (int j = 0; j < numRedDots.size(); j++) {

							waittr++;

							if (numYellowDots.size() > numRedDots.size()) {
								numberTotal = numYellowDots.size() - numRedDots.size();
								if (numberTotal < 0) {
									numberTotal = -numberTotal;
								}

								Terminal = i - numberTotal;
								if (Terminal < 0) {
									Terminal = numRedDots.size() - 1;
								}
							}

							if (numYellowDots.size() <= numRedDots.size() || numYellowDots.size() == 1) {
								Terminal = i;
							}
							if (yellowDotsY.get(i) < redDotsY.get(Terminal)
									&& redDotsY.get(Terminal) != yellowDotsY.get(i) && !Escape) {
								yellowDotsY.set(i, yellowDotsY.get(i) + 1);
								// System.out.println("Y is smaller");
								waittr = 0;

							}

							if (redDotsY.get(Terminal) < yellowDotsY.get(i)
									&& redDotsY.get(Terminal) != yellowDotsY.get(i) && !Escape) {
								yellowDotsY.set(i, yellowDotsY.get(i) - 1);
								// System.out.println("Y is bigger");
								waittr = 0;

							}

							if (redDotsX.get(Terminal) > yellowDotsX.get(i)
									&& yellowDotsX.get(i) != redDotsX.get(Terminal) && !Escape) {
								yellowDotsX.set(i, yellowDotsX.get(i) + 1);
								// System.out.println("X is smaller");
								waittr = 0;

							}

							if (redDotsX.get(Terminal) < yellowDotsX.get(i)
									&& yellowDotsX.get(i) != redDotsX.get(Terminal) && !Escape) {
								yellowDotsX.set(i, yellowDotsX.get(i) - 1);
								// System.out.println("X is bigger");
								waittr = 0;

							}
							// }

							// redDotX = redDotsX.get(i);
							// redDotY = redDotsY.get(i);

						}

					}

					if (Game == true) {
						g.drawImage(ShopButton, ShopButtonX, ShopButtonY, this);
						g.drawImage(ShopBackground, frame.getWidth() - 900, ShopButtonY + 30, this);
						g.drawImage(RedDoteImage, frame.getWidth() - 745, ShopButtonY + 40, this);
						g.drawImage(RedDoteBuy, frame.getWidth() - 895, ShopButtonY + 40, this);
						g.drawImage(MoneyAddImage, frame.getWidth() - 745, ShopButtonY + 100, this);
						g.drawImage(MoneyAddBuy, frame.getWidth() - 895, ShopButtonY + 100, this);
						g.drawImage(YellowAutoImage, frame.getWidth() - 745, ShopButtonY + 160, this);
						g.drawImage(YellowAddBuy, frame.getWidth() - 895, ShopButtonY + 160, this);
						g.drawImage(RedReset, frame.getWidth() - 75, ShopButtonY + 40, this);

						// Money ++ Buy Action
						if (Mouseboard.getX() >= frame.getWidth() - 895 && Mouseboard.getY() >= ShopButtonY + 100
								&& Mouseboard.getX() <= frame.getWidth() - 745 && Mouseboard.getY() <= ShopButtonY + 150
								&& !Escape) {
							g.drawImage(MoneyAddBuyHover, frame.getWidth() - 895, ShopButtonY + 100, this);
						}

						if (Mouseboard.getButton() == 1) {
							if (Mouseboard.getX() >= frame.getWidth() - 895 && Mouseboard.getY() >= ShopButtonY + 100
									&& Mouseboard.getX() <= frame.getWidth() - 745
									&& Mouseboard.getY() <= ShopButtonY + 150 && Mouseboard.mouseRelease && !Escape) {

								g.drawImage(MoneyAddBuyAction, frame.getWidth() - 895, ShopButtonY + 100, this);

								if (Money >= 20) {
									Money = Money - 20;
									MoneyAdd++;
								}
								Mouseboard.mouseRelease = false;

							}
						}
						// RedDote ++ Buy Action
						if (Mouseboard.getX() >= frame.getWidth() - 895 && Mouseboard.getY() >= ShopButtonY + 40
								&& Mouseboard.getX() <= frame.getWidth() - 745 && Mouseboard.getY() <= ShopButtonY + 90
								&& !Escape) {
							g.drawImage(RedDoteBuyHover, frame.getWidth() - 895, ShopButtonY + 40, this);
						}

						if (Mouseboard.getButton() == 1) {
							if (Mouseboard.getX() >= frame.getWidth() - 895 && Mouseboard.getY() >= ShopButtonY + 40
									&& Mouseboard.getX() <= frame.getWidth() - 745
									&& Mouseboard.getY() <= ShopButtonY + 90 && Mouseboard.mouseRelease && !Escape) {

								g.drawImage(RedDoteBuyAction, frame.getWidth() - 895, ShopButtonY + 40, this);
								if (Money >= 10) {
									Money = Money - 10;
									j++;
									RedBuy = true;
								}
								Mouseboard.mouseRelease = false;

							}
						}
						// Yellow ++ Buy Action
						if (Mouseboard.getX() >= frame.getWidth() - 895 && Mouseboard.getY() >= ShopButtonY + 160
								&& Mouseboard.getX() <= frame.getWidth() - 745 && Mouseboard.getY() <= ShopButtonY + 210
								&& !Escape) {
							g.drawImage(YellowAddBuyHover, frame.getWidth() - 895, ShopButtonY + 160, this);
						}

						if (Mouseboard.getButton() == 1) {
							if (Mouseboard.getX() >= frame.getWidth() - 895 && Mouseboard.getY() >= ShopButtonY + 160
									&& Mouseboard.getX() <= frame.getWidth() - 745
									&& Mouseboard.getY() <= ShopButtonY + 210 && Mouseboard.mouseRelease && !Escape) {

								g.drawImage(YellowAddBuyAction, frame.getWidth() - 895, ShopButtonY + 160, this);

								if (Money >= 50) {
									Money = Money - 50;
									j++;
									numYellowDots.add(1);
									yellowDotsX.add(rande.nextInt(DemomakerGame.winWIDTH - 10) + 0);
									yellowDotsY.add(rande.nextInt(DemomakerGame.winHEIGHT - 30) + 0);

								}

								Mouseboard.mouseRelease = false;
							}
						}

					}
				}
				// Show M for Menu message and Score Title Image

				g.drawImage(scored, 250, 0, this);

				// Time runs out
				if (GameFinish == true) {
					// Show to tell to restart
					numYellowDots.removeAll(yellowDotsX);
					numYellowDots.removeAll(yellowDotsY);
					numRedDots.removeAll(redDotsX);
					numRedDots.removeAll(redDotsY);

					g.drawImage(GameEnd, 300, 168, this);
					g.drawImage(Restart, 0, 168, this);

				}
				GameTimer = true;
			}
			if (key.esc && key.kr && Game != false && Option == false) {
				Escape = !Escape;
				key.kr = false;
				if (Escape == false && Musicing == true) {
					Sound.music.play();
				}
			}

			if (Escape) {
				MenuX = winWIDTH - (winWIDTH / 2) - 128 / 2;
				MenuY = winHEIGHT - (winHEIGHT / 2) - 72 / 2;

				g.drawImage(Escap, 0, 0, winWIDTH, winHEIGHT, null);
				Sound.music.stop();
				Sound.redtouch.stop();
				Sound.yellowbeep.stop();
				g.drawImage(Menu, MenuX, MenuY, 128, 72, this);

				if (Mouseboard.getButton() == 1) {
					if (Mouseboard.getX() > MenuX && Mouseboard.getX() < MenuX + 128 && Mouseboard.getY() > MenuY
							&& Mouseboard.getY() < MenuY + 72) {
						Game = false;
						Escape = !Escape;
					}
				}
				g.drawImage(Options, MenuX, MenuY - 144, 128, 72, this);
				if (Mouseboard.getButton() == 1) {
					if (Mouseboard.getX() > MenuX && Mouseboard.getX() < MenuX + 128 && Mouseboard.getY() > MenuY - 144
							&& Mouseboard.getY() < MenuY - 72 && Mouseboard.mouseRelease && Option == false) {

						Option = !Option;
						Mouseboard.mouseRelease = false;

					}
					// Temporary

				}
				if (Option) {
					g.drawImage(OptionMenu, 0, 0, winWIDTH, winHEIGHT, null);

					g.drawImage(Difficulty, 100, 50, Difficulty.getWidth(null), Difficulty.getHeight(null), this);

					g.drawImage(Sounds, 100, 150, null);

					g.drawImage(Music, 150, 150, null);

					if (Mouseboard.mouseRelease == true && Mouseboard.getButton() == 1 && Mouseboard.getX() >= 100
							&& Mouseboard.getX() <= 150 && Mouseboard.getY() >= 150 && Mouseboard.getY() <= 200) {
						Sounding = !Sounding;
						Mouseboard.mouseRelease = false;
					}
					if (Mouseboard.mouseRelease == true && Mouseboard.getButton() == 1 && Mouseboard.getX() >= 150
							&& Mouseboard.getX() <= 200 && Mouseboard.getY() >= 150 && Mouseboard.getY() <= 200) {
						Musicing = !Musicing;
						Mouseboard.mouseRelease = false;
					}

					if (Sounding == false) {
						g.drawImage(Cross, 100, 150, null);
					}

					if (Musicing == false) {
						g.drawImage(Cross, 150, 150, null);
					}

					if (key.h) {
						MeterX = 380;
						// g.drawImage(Meter, 100 + 280, 50,
						// Meter.getWidth(null), Meter.getHeight(null),this);
					}
					if (key.n) {
						// g.drawImage(Meter, 100 + 140, 50,
						// Meter.getWidth(null), Meter.getHeight(null),this);
						MeterX = 240;
					}
					if (key.e) {
						// g.drawImage(Meter, 100 , 50, Meter.getWidth(null),
						// Meter.getHeight(null),this);
						MeterX = 100;
					}

					if (Mouseboard.getX() > MeterX && Mouseboard.getX() < MeterX + 140 && Mouseboard.getY() > MeterY
							&& Mouseboard.getY() < MeterY + 42 && Mouseboard.getX() > 100 && Mouseboard.getX() < 520
							&& Option) {
						if (MeterX + 70 < Mouseboard.getX() && MeterX + 140 <= 520 && Mouseboard.getButton() == 1) {

							MeterX = MeterX + (Mouseboard.getX() - (MeterX + 70));
						}

						if (MeterX + 70 > Mouseboard.getX() && MeterX > 100 && Mouseboard.getButton() == 1) {

							MeterX = MeterX + (Mouseboard.getX() - (MeterX + 70));
						}

						Mouseboard.mouseRelease = false;

					}

					if (MeterX >= 100 && MeterX <= 240 - 70) {
						difficulty = "Easy";
						g.drawImage(Easy, 100, 92, Easy.getWidth(null), Easy.getHeight(null), this);
					}
					if (MeterX >= 241 - 70 && MeterX <= 380 - 70) {
						difficulty = "Normal";
						g.drawImage(Normal, 240, 92, Normal.getWidth(null), Normal.getHeight(null), this);
					}
					if (MeterX >= 380 - 70 && MeterX <= 520) {
						difficulty = "Hard";
						g.drawImage(Hard, 380, 92, Hard.getWidth(null), Hard.getHeight(null), this);
					}
					g.drawImage(Meter, MeterX, MeterY, null);

					if (key.esc) {
						Option = !Option;
					}
				}
			}

		}
		g.dispose();
		bs.show();
	}

	// loading JFrame
	public void FullScreen() {
		if (FullScreen == true && key.kr == true) {
			frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
			frame.setVisible(true);
			frame.setAlwaysOnTop(true);
			frame.setResizable(false);
		}
		if (FullScreen == false && key.kr == true) {
			frame.setSize(WIDTH, HEIGHT);
			frame.setAlwaysOnTop(false);
			frame.setResizable(true);
		}
	}

	public void CalculatePath(int yellowX, int yellowY, int x, int y, int distanceX, int distanceY) {
		distanceX = 0;
		distanceY = 0;
	}

	// Transformer les nombres en images
	//
	//
	public Image[] TransformNumbers(int number) {
		int numCharactersNeeded = 3;
		char[] numberToast;
		String numberInString = String.valueOf(number);
		numberToast = numberInString.toCharArray();
		char[] numberCharacters = new char[numCharactersNeeded];
		for (int i = 0; i < numberCharacters.length; i++) {
			if (numberToast.length > i) {
				numberCharacters[i] = numberToast[numberToast.length - 1 - i];
			}
		}
		if (number != 0 && numCharactersNeeded <= numberCharacters.length) {
			numberImages = new Image[numberCharacters.length];
		} else if (numCharactersNeeded > numberCharacters.length || number == 0) {
			numberImages = new Image[numCharactersNeeded];
		}

		for (int i = 0; i < numberCharacters.length; i++) {
			if (numberCharacters[i] == '0') {
				numberImages[i] = ZERO;
			}
			if (numberCharacters[i] == '1') {
				numberImages[i] = ONE;
			}
			if (numberCharacters[i] == '2') {
				numberImages[i] = TWO;
			}
			if (numberCharacters[i] == '3') {
				numberImages[i] = THREE;
			}
			if (numberCharacters[i] == '4') {
				numberImages[i] = FOUR;
			}
			if (numberCharacters[i] == '5') {
				numberImages[i] = FIVE;
			}
			if (numberCharacters[i] == '6') {
				numberImages[i] = SIX;
			}
			if (numberCharacters[i] == '7') {
				numberImages[i] = SEVEN;
			}
			if (numberCharacters[i] == '8') {
				numberImages[i] = EIGHT;
			}
			if (numberCharacters[i] == '9') {
				numberImages[i] = NINE;
			}
		}

		if (numCharactersNeeded > numberToast.length) {
			for (int i = numberToast.length; i < numCharactersNeeded; i++) {
				numberImages[i] = ZERO;
			}
		}
		if (number == 0) {
			for (int i = 0; i < numCharactersNeeded; i++) {
				numberImages[i] = ZERO;
			}
		}
		return numberImages;

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
		game.start();

	}

}
