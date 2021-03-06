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

import static consts.SharedObjectKeys.HighscoreKey;
import static consts.SharedObjectKeys.KeyboardKey;

public class EndScene implements Scene {
    public int Highscore = 0;
    private Keyboard key;

    // variables
    private final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
    // Audio

    // Image URLs
    URL gameend = resource("/resources/GameFinish.png");
    URL restart = resource("/resources/Restart.png");
    URL highscore = resource("/resources/HighScore.png");

    /* Number URLs */
    /**************/
    URL zero = resource("/resources/Zero.png");
    URL one = resource("/resources/One.png");
    URL two = resource("/resources/Two.png");
    URL three = resource("/resources/Three.png");
    URL four = resource("/resources/Four.png");
    URL five = resource("/resources/Five.png");
    URL six = resource("/resources/Six.png");
    URL seven = resource("/resources/Seven.png");
    URL eight = resource("/resources/Eight.png");
    URL nine = resource("/resources/Nine.png");
    /**************/

    private final Image GameEnd = image(gameend);
    private final Image Restart = image(restart);
    private final Image HighScore = image(highscore);

    /* Number Images */
    /***************/
    private final Image ZERO = image(zero);
    private final Image ONE = image(one);
    private final Image TWO = image(two);
    private final Image THREE = image(three);
    private final Image FOUR = image(four);
    private final Image FIVE = image(five);
    private final Image SIX = image(six);
    private final Image SEVEN = image(seven);
    private final Image EIGHT = image(eight);
    private final Image NINE = image(nine);

    public URL resource(String name) {
        return DemomakerGame.class.getResource(name);
    }

    public Image image(URL url) {
        return defaultToolkit.getImage(url);
    }

    private void manageKeys(Keyboard key)
    {
        // When the game finishes, Reset Scores
        if (key.p) {
            SceneManager.setActiveScene(SceneManager.getSceneByName("GameScene"));
        }

        if (key.esc && key.kr) {
            SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
        }
    }


    // Transformer les nombres en images
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
        Image[] numberImages;
        if (number != 0) {
            numberImages = new Image[numberCharacters.length];
        } else {
            numberImages = new Image[numCharactersNeeded];
        }

        for (int i = 0; i < numberCharacters.length; i++) {
            numberImages[i] = getImageFromCharacter(numberCharacters[i]);
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

    private Image getImageFromCharacter(char character) {
        return switch (character) {
            case '1' -> ONE;
            case '2' -> TWO;
            case '3' -> THREE;
            case '4' -> FOUR;
            case '5' -> FIVE;
            case '6' -> SIX;
            case '7' -> SEVEN;
            case '8' -> EIGHT;
            case '9' -> NINE;
            default -> ZERO;
        };
    }

    @Override
    public void init() {
        key = (Keyboard) SceneManager.getSharedObject(KeyboardKey);
        Highscore = (Integer) SceneManager.getSharedObject(HighscoreKey);
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
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(GameEnd, 300, 168, null);
        g.drawImage(Restart, 0, 168, null);
        g.drawImage(HighScore, 604, 25, null);
        Image[] HighscoreImages = TransformNumbers(Highscore);
        g.drawImage(HighscoreImages[2], 604, 56, null);
        g.drawImage(HighscoreImages[1], 663, 56, null);
        g.drawImage(HighscoreImages[0], 722, 56, null);
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void onResume() {

    }
}
