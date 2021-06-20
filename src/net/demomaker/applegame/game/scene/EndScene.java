package net.demomaker.applegame.game.scene;

import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.scene.Scene;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.AssetRetreiver;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.utils.GameFont;

import java.awt.event.KeyEvent;

import static net.demomaker.applegame.game.consts.SharedObjectKeys.HighscoreKey;

public class EndScene implements Scene {
    public int Highscore = 0;
    private boolean finishedLoading = false;
    private EndScene referredThis = this;

    // Audio
    private final AdvancedImage GameEnd = AssetRetreiver.getImageFromPath("/resources/GameFinish.png");
    private final AdvancedImage Restart = AssetRetreiver.getImageFromPath("/resources/Restart.png");
    private final AdvancedImage HighScore = AssetRetreiver.getImageFromPath("/resources/HighScore.png");

    @Override
    public boolean finishedLoading() {
        return finishedLoading;
    }

    @Override
    public void onWindowResize() {

    }

    @Override
    public void init() {
        Highscore = (Integer) SceneManager.getSharedObject(HighscoreKey);
        finishedLoading = true;
    }

    @Override
    public void update(float deltaTime) {
    }

    @Override
    public void draw() {
        GraphicsManager.drawImage(GameEnd, new Vector3<Float>(300f, 168f, 0f));
        GraphicsManager.drawImage(Restart, new Vector3<Float>(0f, 168f, 0f));
        GraphicsManager.drawImage(HighScore, new Vector3<Float>(604f, 25f, 0f));
        AdvancedImage[] HighscoreImages = GameFont.getSymbolsfromNumber(Highscore);
        GraphicsManager.drawImage(HighscoreImages[2], new Vector3<Float>(604f, 56f, 0f));
        GraphicsManager.drawImage(HighscoreImages[1], new Vector3<Float>(663f, 56f, 0f));
        GraphicsManager.drawImage(HighscoreImages[0], new Vector3<Float>(722f, 56f, 0f));
    }

    @Override
    public void cleanup() {

    }

    @Override
    public void onResume() {

    }

    private Keyboard.KeyboardListener keyboardListener = new Keyboard.KeyboardListener(){

        @Override
        public void onKeyPressed(int key) {
            if(SceneManager.getActiveScene() != referredThis) return;
            if (key == KeyEvent.VK_P) {
                SceneManager.setActiveScene(SceneManager.getSceneByName("GameScene"));
            }
        }

        @Override
        public void onKeyReleased(int key) {
            if(SceneManager.getActiveScene() != referredThis) return;
            if (key == KeyEvent.VK_ESCAPE) {
                SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
            }
        }
    };
}
