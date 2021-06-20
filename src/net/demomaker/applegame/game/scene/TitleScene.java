package net.demomaker.applegame.game.scene;

import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.game.controller.DemomakerGame;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.scene.Scene;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.entity.Ally;
import net.demomaker.applegame.game.entity.Apple;
import net.demomaker.applegame.engine.ui.button.Button;
import net.demomaker.applegame.game.sound.Sound;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.AssetRetreiver;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.Random;

import static net.demomaker.applegame.game.consts.SharedObjectKeys.*;

public class TitleScene implements Scene {
    private Ally titleAlly = new Ally();
    private Apple titleApple = new Apple();
    private final Random random = new Random();
    public boolean showFPS = false;
    public String FPSstring;
    private boolean playSounds = false;
    private Button optionButton = new Button();
    private Keyboard.KeyboardListener keyboardListener = new KeyboardListener();

    // Title Screen Images
    private final AdvancedImage TitleScreen = AssetRetreiver.getImageFromPath("/resources/titlescreen.png");
    private final AdvancedImage Options = AssetRetreiver.getImageFromPath("/resources/Options.png");
    private boolean finishedLoading = false;

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
        optionButton.setActive(true);
        optionButton.setPosition(new Vector3<>(GameWindow.getWidth() / 2f - 64f, GameWindow.getHeight() / 2f - 36f, 0f));
        optionButton.setSize(new Vector3<>(128f, 72f, 0f));
        optionButton.setButtonNormalStateImage(Options);
        optionButton.setButtonPressedStateImage(Options);
        optionButton.setButtonReleasedStateImage(Options);
    }

    @Override
    public boolean finishedLoading() {
        return finishedLoading;
    }

    @Override
    public void onWindowResize() {

    }

    @Override
    public void init() {
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        titleApple = generateNewApple();
        titleAlly = generateNewAlly();
        titleAlly.SetTarget(titleApple);
        initGameButtons();
        finishedLoading = true;
    }

    @Override
    public void update(float deltaTime) {
        if(optionButton.isClicked()) {
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
    public void draw() {
        GraphicsManager.drawImage(TitleScreen, new Vector3<>(0f,0f,0f));
        optionButton.draw();
        titleAlly.draw();
        titleApple.draw();
        if (showFPS) {
            GraphicsManager.drawString(Color.WHITE, FPSstring, new Vector3<Float>(0f, 10f, 0f));
        }
    }

    @Override
    public void cleanup() {
        optionButton.setActive(false);
    }

    @Override
    public void onResume() {
        optionButton.setActive(true);
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
