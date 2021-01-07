package scenes;

import controller.DemomakerGame;
import controls.Keyboard;
import controls.Mouseboard;
import graphics.Screen;
import logic.*;
import logic.Button;
import sounds.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.net.URL;
import java.util.ArrayList;
import java.util.Random;

import static consts.SharedObjectKeys.KeyboardKey;
import static consts.SharedObjectKeys.PlaySoundKey;

public class TitleScene implements Scene {

    public static int WIDTH = 900;
    public static int HEIGHT = WIDTH / 16 * 9;
    public static int winWIDTH = WIDTH;
    public static int winHEIGHT = HEIGHT;
    private Ally titleAlly = new Ally();
    private Apple titleApple = new Apple();
    private final Random random = new Random();
    private Difficulty difficulty = Difficulty.Easy;
    public boolean Option = false;
    public boolean showFPS = false;
    public String FPSstring;
    private boolean playSounds = false;

    private Keyboard key;
    private final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

    // Title Screen URLs
    URL sound = resource("/resources/Sound.png");
    URL cross = resource("/resources/Cross.png");
    URL titlescreen = resource("/resources/titlescreen.png");

    // Title Screen Images
    private final Image TitleScreen = image(titlescreen);

    public URL resource(String name) {
        return DemomakerGame.class.getResource(name);
    }

    public Image image(URL url) {
        return defaultToolkit.getImage(url);
    }

    private Apple goodEntityTouchApple() {
        if (playSounds) {
            Sound.redtouch.play();
        }
        return generateNewApple();
    }

    private Apple generateNewApple() {
        Apple apple = new Apple();
        int appleX = random.nextInt(DemomakerGame.winWIDTH - 10);
        int appleY = random.nextInt(DemomakerGame.winHEIGHT - 30);
        apple.setPosition(new Vector3<Float>(appleX * 1.0f, appleY * 1.0f, 0f));
        return apple;
    }

    private Ally generateNewAlly() {
        Ally ally = new Ally();
        int allyX = random.nextInt(DemomakerGame.winWIDTH - 10);
        int allyY = random.nextInt(DemomakerGame.winHEIGHT - 30);
        ally.setPosition(new Vector3<Float>(allyX * 1.0f,allyY * 1.0f,0f));
        return ally;
    }

    private void manageKeys(Keyboard key)
    {
        // Difficulty selecting if game isnt running
        if (Option) {
            if (key.e) {
                difficulty = Difficulty.Easy;
            }
            if (key.n) {
                difficulty = Difficulty.Normal;
            }
            if (key.h) {
                difficulty = Difficulty.Hard;
            }
        }

        if (key.space) {
            SceneManager.setActiveScene(SceneManager.getSceneByName("GameScene"));
        }
    }

    @Override
    public void init() {
        key = (Keyboard) SceneManager.getSharedObject(KeyboardKey);
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        titleApple = generateNewApple();
        titleAlly = generateNewAlly();
        titleAlly.SetTarget(titleApple);
    }

    @Override
    public void update(float deltaTime) {
        key.update();
        manageKeys(key);
        for(Button button : Button.getAllButtons()) {
            if(Mouseboard.mousePressedButton(button)) {
                if(button.isShown())
                    button.press();
            }
        }
        titleAlly.MoveToTarget();
        if (titleAlly.ateTarget()) {
            titleApple = goodEntityTouchApple();
            titleAlly.SetTarget(titleApple);
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(TitleScreen, 0, 0, winWIDTH, winHEIGHT, null);
        titleAlly.draw(g);
        titleApple.draw(g);
        if (showFPS) {
            g.setColor(Color.WHITE);
            g.drawString(FPSstring, 0, 10);
        }
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void onResume() {

    }
}
