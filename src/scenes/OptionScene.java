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

import static consts.SharedObjectKeys.*;

public class OptionScene implements Scene {
    public static int WIDTH = 900;
    public static int HEIGHT = WIDTH / 16 * 9;
    public static int winWIDTH = WIDTH;
    public static int winHEIGHT = HEIGHT;
    private Difficulty difficulty = Difficulty.Easy;
    private int MeterX = 100;
    private boolean playSounds = false;
    private boolean playMusic = false;

    /* Buttons */
    private CheckBox musicButton;
    private CheckBox soundButton;

    public boolean Option = false;

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
    }


    private void manageKeys(Keyboard key)
    {
        // Difficulty selecting if game isnt running
        if (key.e) {
            difficulty = Difficulty.Easy;
        }
        if (key.n) {
            difficulty = Difficulty.Normal;
        }
        if (key.h) {
            difficulty = Difficulty.Hard;
        }

        if (key.esc && key.kr) {
            key.kr = false;
            SceneManager.setActiveScene(SceneManager.getSceneByName("GameScene"));
        }
    }

    @Override
    public void init() {
        key = (Keyboard) SceneManager.getSharedObject(KeyboardKey);
        screen = (Screen) SceneManager.getSharedObject(ScreenKey);
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        playMusic = (Boolean) SceneManager.getSharedObject(PlayMusicKey);
        difficulty =  Difficulty.values()[((Integer) SceneManager.getSharedObject(DifficultyKey))];
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
        key.update();
        manageKeys(key);
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(OptionMenu, 0, 0, winWIDTH, winHEIGHT, null);
        g.drawImage(DifficultyImage, 100, 50, DifficultyImage.getWidth(null), DifficultyImage.getHeight(null), null);
        soundButton.draw(g);
        musicButton.draw(g);

        if (Mouseboard.mousePressedButton(soundButton)) {
            soundButton.press();
        }

        if (Mouseboard.mousePressedButton(musicButton)) {
            musicButton.press();
        }

        if (key.h) {
            MeterX = 380;
        }
        if (key.n) {
            MeterX = 240;
        }
        if (key.e) {
            MeterX = 100;
        }

        int meterY = 50;
        if (Mouseboard.getX() > MeterX && Mouseboard.getX() < MeterX + 140 && Mouseboard.getY() > meterY
                && Mouseboard.getY() < meterY + 42 && Mouseboard.getX() > 100 && Mouseboard.getX() < 520
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
            difficulty = Difficulty.Easy;
            g.drawImage(Easy, 100, 92, Easy.getWidth(null), Easy.getHeight(null), null);
        }
        if (MeterX >= 241 - 70 && MeterX <= 380 - 70) {
            difficulty = Difficulty.Normal;
            g.drawImage(Normal, 240, 92, Normal.getWidth(null), Normal.getHeight(null), null);
        }
        if (MeterX >= 380 - 70 && MeterX <= 520) {
            difficulty = Difficulty.Hard;
            g.drawImage(Hard, 380, 92, Hard.getWidth(null), Hard.getHeight(null), null);
        }
        g.drawImage(Meter, MeterX, meterY, null);
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
