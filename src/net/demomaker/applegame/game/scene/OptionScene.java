package net.demomaker.applegame.game.scene;

import net.demomaker.applegame.engine.scene.Scene;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.ui.Slider;
import net.demomaker.applegame.engine.ui.button.CheckBox;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.controller.DemomakerGame;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.input.Mouseboard;
import net.demomaker.applegame.game.logic.Screen;
import net.demomaker.applegame.game.logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.net.URL;

import static net.demomaker.applegame.game.consts.SharedObjectKeys.*;

public class OptionScene implements Scene {
    private Difficulty difficulty = Difficulty.Easy;
    private boolean playSounds = false;
    private boolean playMusic = false;
    private Slider difficultySlider = new Slider();
    private String returnSceneName = "GameScene";
    private KeyboardListener keyboardListener = new KeyboardListener();
    private OptionScene referredThis = this;
    private class KeyboardListener extends Keyboard.KeyboardListener {
        @Override
        public void onKeyPressed(int key) {
            if(SceneManager.getActiveScene() != referredThis) return;
            if (key == KeyEvent.VK_H) {
                setDifficultySliderValue(Difficulty.Hard);
            }
            if (key == KeyEvent.VK_N) {
                setDifficultySliderValue(Difficulty.Normal);
            }
            if (key == KeyEvent.VK_E) {
                setDifficultySliderValue(Difficulty.Easy);
            }
        }

        @Override
        public void onKeyReleased(int key) {
            if(SceneManager.getActiveScene() != referredThis) return;
            if (key == KeyEvent.VK_ESCAPE) {
                SceneManager.setActiveScene(SceneManager.getSceneByName(returnSceneName));
            }
        }
    }

    /* Buttons */
    private CheckBox musicButton;
    private CheckBox soundButton;

    // variables
    private Screen screen;
    public JFrame frame;
    private Keyboard key;
    private final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
    // Audio
    URL optionmenu = resource("/resources/OptionMenu.png");
    URL meter = resource("/resources/Meter.png");
    URL sound = resource("/resources/Sound.png");
    URL cross = resource("/resources/Cross.png");
    URL music = resource("/resources/Music.png");

    URL ifficulty = resource("/resources/Difficulty.png");
    URL hard = resource("/resources/Hard.png");
    URL normal = resource("/resources/Normal.png");
    URL easy = resource("/resources/Easy.png");

    /* Number URLs */
    private final Image OptionMenu = image(optionmenu);
    private final Image Meter = image(meter);
    private final Image Sounds = image(sound);
    private final Image Cross = image(cross);
    private final Image Music = image(music);


    private final Image DifficultyImage = image(ifficulty);
    private final Image Hard = image(hard);
    private final Image Normal = image(normal);
    private final Image Easy = image(easy);

    public URL resource(String name) {
        return DemomakerGame.class.getResource(name);
    }

    public Image image(URL url) {
        return defaultToolkit.getImage(url);
    }

    private void initGameButtons() {
        musicButton = new CheckBox(null);
        soundButton = new CheckBox(null);
        soundButton.setUncheckedImage(Cross);
        soundButton.setCheckedImage(Sounds);
        soundButton.setPosition(new Vector3<>(100f, 150f,0f));
        soundButton.setSize(new Vector3<>(50f, 50f, 0f));
        musicButton.setUncheckedImage(Cross);
        musicButton.setCheckedImage(Music);
        musicButton.setPosition(new Vector3<>(150f, 150f, 0f));
        musicButton.setSize(new Vector3<>(50f, 50f, 0f));
        difficultySlider.setMinValue(0);
        difficultySlider.setMaxValue(100);
        difficultySlider.setSliderForegroundImage(Meter);
        difficultySlider.setSliderBackgroundImage(DifficultyImage);
        difficultySlider.setSliderImage(Easy);
        difficultySlider.setSize(new Vector3<>(420f, 42f, 0f));
        difficultySlider.setSliderSize(new Vector3<>(140f,42f,0f));
        difficultySlider.setPosition(new Vector3<>(100f, 50f, 0f));
        difficultySlider.setSliderPosition(new Vector3<>(100f, 50f, 0f));
        setDifficultySliderValue(Difficulty.Easy);
        setDifficultySliderValue(difficulty);
    }

    private void setDifficultySliderValue(Difficulty difficulty) {
        difficultySlider.setSliderValue(50 * (difficulty.ordinal()));
    }

    @Override
    public void init() {
        key = (Keyboard) SceneManager.getSharedObject(KeyboardKey);
        screen = (Screen) SceneManager.getSharedObject(ScreenKey);
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        playMusic = (Boolean) SceneManager.getSharedObject(PlayMusicKey);
        difficulty =  Difficulty.values()[((Integer) SceneManager.getSharedObject(DifficultyKey))];
        returnSceneName = (String) SceneManager.getSharedObject(OptionReturnSceneKey);
        initGameButtons();
        if(playSounds)
            soundButton.Check();
        else
            soundButton.Uncheck();
        if(playMusic)
            musicButton.Check();
        else
            musicButton.Uncheck();
    }

    @Override
    public void update(float deltaTime) {
        if (difficultySlider.sliderPressed()) {
            if(difficultySlider.isOnLeftSideOfSlider(Mouseboard.getX())) {
                int differenceInX = 10;
                difficultySlider.SlideToTheLeft(differenceInX);
            }
            if(difficultySlider.isOnRightSideOfSlider(Mouseboard.getX())) {
                int differenceInX = 10;
                difficultySlider.SlideToTheRight(differenceInX);
            }
        }

        int sliderValue = difficultySlider.getSliderValue();
        if (sliderValue >= 0 && sliderValue <= 33) {
            difficulty = Difficulty.Easy;
            difficultySlider.setSliderImage(Easy);
        }
        if (sliderValue >= 34 && sliderValue <= 67) {
            difficulty = Difficulty.Normal;
            difficultySlider.setSliderImage(Normal);
        }
        if (sliderValue >= 68 && sliderValue <= 100) {
            difficulty = Difficulty.Hard;
            difficultySlider.setSliderImage(Hard);
        }

        if (Mouseboard.mousePressedUIElement(soundButton)) {
            soundButton.press();
        }

        if (Mouseboard.mousePressedUIElement(musicButton)) {
            musicButton.press();
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(OptionMenu, 0, 0, winWIDTH, winHEIGHT, null);
        soundButton.draw(g);
        musicButton.draw(g);
        difficultySlider.draw(g);
    }

    @Override
    public void cleanup() {
        SceneManager.setSharedObject(PlaySoundKey, soundButton.isChecked());
        SceneManager.setSharedObject(PlayMusicKey, musicButton.isChecked());
        SceneManager.setSharedObject(DifficultyKey, difficulty.ordinal());
    }

    @Override
    public void onResume() {

    }
}
