package net.demomaker.applegame.game.scene;

import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.scene.Scene;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.ui.Slider;
import net.demomaker.applegame.engine.ui.button.Button;
import net.demomaker.applegame.engine.ui.button.ButtonListener;
import net.demomaker.applegame.engine.ui.button.CheckBox;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.input.Mouseboard;
import net.demomaker.applegame.game.logic.*;
import net.demomaker.applegame.engine.util.AssetRetreiver;

import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;

import static net.demomaker.applegame.game.consts.SharedObjectKeys.*;

public class OptionScene implements Scene {
    private Difficulty difficulty = Difficulty.Easy;
    private final Slider difficultySlider = new Slider();
    private String returnSceneName = "GameScene";
    private final KeyboardListener keyboardListener = new KeyboardListener();
    private final OptionScene referredThis = this;
    private CheckBox musicButton;
    private CheckBox soundButton;
    private boolean finishedLoading = false;
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private boolean movingButtonPressed = false;
    private boolean movingButtonHovered = false;

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

    private final AdvancedImage OptionMenu = AssetRetreiver.getImageFromPath("/resources/OptionMenu.png");
    private final AdvancedImage Meter = AssetRetreiver.getImageFromPath("/resources/Meter.png");
    private final AdvancedImage Sounds = AssetRetreiver.getImageFromPath("/resources/Sound.png");
    private final AdvancedImage Cross = AssetRetreiver.getImageFromPath("/resources/Cross.png");
    private final AdvancedImage Music = AssetRetreiver.getImageFromPath("/resources/Music.png");
    private final AdvancedImage DifficultyImage = AssetRetreiver.getImageFromPath("/resources/Difficulty.png");
    private final AdvancedImage Hard = AssetRetreiver.getImageFromPath("/resources/Hard.png");
    private final AdvancedImage Normal = AssetRetreiver.getImageFromPath("/resources/Normal.png");
    private final AdvancedImage Easy = AssetRetreiver.getImageFromPath("/resources/Easy.png");


    private void initGameButtons() {
        musicButton = new CheckBox();
        soundButton = new CheckBox();
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
        soundButton.setActive(true);
        musicButton.setActive(true);
        soundButton.setChecked((Boolean) SceneManager.getSharedObject(PlaySoundKey));
        musicButton.setChecked((Boolean) SceneManager.getSharedObject(PlayMusicKey));
    }

    private void setDifficultySliderValue(Difficulty difficulty) {
        difficultySlider.setSliderValue(50 * (difficulty.ordinal()));
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
        difficulty =  Difficulty.values()[((Integer) SceneManager.getSharedObject(DifficultyKey))];
        returnSceneName = (String) SceneManager.getSharedObject(OptionReturnSceneKey);
        initGameButtons();
        finishedLoading = true;
    }

    @Override
    public void update(float deltaTime) {
        if (canSlide()) {
            if(difficultySlider.isOnLeftSideOfSlider(lastMouseX)) {
                int differenceInX = 10;
                difficultySlider.SlideToTheLeft(differenceInX);
            }
            if(difficultySlider.isOnRightSideOfSlider(lastMouseX)) {
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
    }

    private boolean canSlide() {
        return movingButtonPressed && movingButtonHovered;
    }

    @Override
    public void draw() {
        GraphicsManager.drawImage(OptionMenu, new Vector3<Float>(0f, 0f, 0f));
        soundButton.draw();
        musicButton.draw();
        difficultySlider.draw();
    }

    @Override
    public void cleanup() {
        SceneManager.setSharedObject(PlaySoundKey, soundButton.isChecked());
        SceneManager.setSharedObject(PlayMusicKey, musicButton.isChecked());
        SceneManager.setSharedObject(DifficultyKey, difficulty.ordinal());
        soundButton.setActive(false);
        musicButton.setActive(false);
    }

    @Override
    public void onResume() {
        finishedLoading = true;
    }

    private Mouseboard.MouseboardListener mouseboardListener = new Mouseboard.MouseboardListener() {
        @Override
        public void onMouseMoved(MouseEvent mouse) {
            lastMouseX = mouse.getX();
            lastMouseY = mouse.getY();
        }

        @Override
        public void onMouseClicked(MouseEvent mouse) {

        }

        @Override
        public void onMousePressed(MouseEvent mouse) {
            movingButtonPressed = true;
        }

        @Override
        public void onMouseReleased(MouseEvent mouse) {
            movingButtonPressed = false;
        }
    };

    private ButtonListener buttonListener = new ButtonListener() {
        @Override
        public void onClick(Button button) {
            if(button == musicButton) {
                musicButton.isChecked();
            }

            if(button == soundButton) {
                soundButton.isChecked();
            }
        }

        @Override
        public void onPress(Button button) {
        }

        @Override
        public void onRelease(Button button) {
            if(button == difficultySlider.getMovingButton())
                movingButtonHovered = false;
        }

        @Override
        public void onHover(Button button) {
            if(button == difficultySlider.getMovingButton())
                movingButtonHovered = true;
        }
    };
}
