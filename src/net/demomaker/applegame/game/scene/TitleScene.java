package net.demomaker.applegame.game.scene;

import net.demomaker.applegame.game.controller.DemomakerGame;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.input.Mouseboard;
import net.demomaker.applegame.engine.scene.Scene;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.entity.Ally;
import net.demomaker.applegame.game.entity.Apple;
import net.demomaker.applegame.engine.ui.button.Button;
import net.demomaker.applegame.game.sound.Sound;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;
import java.util.Random;

import static net.demomaker.applegame.game.consts.SharedObjectKeys.*;

public class TitleScene implements Scene {
    private Ally titleAlly = new Ally();
    private Apple titleApple = new Apple();
    private final Random random = new Random();
    public boolean showFPS = false;
    public String FPSstring;
    private boolean playSounds = false;
    private Button optionButton = new Button(null);
    private Keyboard.KeyboardListener keyboardListener = new KeyboardListener();
    private Keyboard key;
    private final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();

    // Title Screen URLs
    URL titlescreen = resource("/resources/titlescreen.png");
    URL options = resource("/resources/Options.png");

    // Title Screen Images
    private final Image TitleScreen = image(titlescreen);
    private final Image Options = image(options);

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

    private void initGameButtons() {
        optionButton.setPosition(new Vector3<>(winWIDTH / 2f - 64f, winHEIGHT / 2f - 36f, 0f));
        optionButton.setSize(new Vector3<>(128f, 72f, 0f));
        optionButton.setButtonNormalStateImage(Options);
        optionButton.setButtonPressedStateImage(Options);
        optionButton.setButtonReleasedStateImage(Options);
        optionButton.press();
    }

    @Override
    public void init() {
        key = (Keyboard) SceneManager.getSharedObject(KeyboardKey);
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        titleApple = generateNewApple();
        titleAlly = generateNewAlly();
        titleAlly.SetTarget(titleApple);
        initGameButtons();
    }

    @Override
    public void update(float deltaTime) {
        if(Mouseboard.mouseUpUIElement(optionButton)) {
            SceneManager.setSharedObject(OptionReturnSceneKey, "TitleScene");
            SceneManager.setActiveScene(SceneManager.getSceneByName("OptionScene"));
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
        optionButton.draw(g);
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

    private class KeyboardListener extends Keyboard.KeyboardListener {
        public KeyboardListener() {
            super();
        }

        @Override
        public void onKeyPressed(int key) {
            if(SceneManager.getActiveScene() != SceneManager.getSceneByName("TitleScene")) return;
        }

        @Override
        public void onKeyReleased(int key) {
            if(SceneManager.getActiveScene() != SceneManager.getSceneByName("TitleScene"))  return;
            if (key == KeyEvent.VK_SPACE) {
                SceneManager.setActiveScene(SceneManager.getSceneByName("GameScene"));
            }
        }
    }
}
