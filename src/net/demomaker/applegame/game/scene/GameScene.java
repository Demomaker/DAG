package net.demomaker.applegame.game.scene;

import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.scene.Scene;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.ui.button.ButtonListener;
import net.demomaker.applegame.engine.util.GameState;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.ui.button.Button;
import net.demomaker.applegame.game.entity.Ally;
import net.demomaker.applegame.game.entity.Apple;
import net.demomaker.applegame.game.entity.Player;
import net.demomaker.applegame.game.logic.Screen;
import net.demomaker.applegame.game.logic.*;
import net.demomaker.applegame.game.shop.Shop;
import net.demomaker.applegame.game.shop.ShopAct;
import net.demomaker.applegame.game.shop.ShopActQueue;
import net.demomaker.applegame.game.sound.Sound;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.AssetRetreiver;
import net.demomaker.applegame.game.utils.GameFont;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static net.demomaker.applegame.game.consts.SharedObjectKeys.*;

public class GameScene implements Scene {
    private Player player = null;
    private final ArrayList<Apple> apples = new ArrayList<>();
    private final ArrayList<Ally> allies = new ArrayList<>();
    private final Random random = new Random();
    private Difficulty difficulty = Difficulty.Easy;
    private GameState gameState = GameState.START;
    private boolean playMusic = false;
    private boolean playSounds = false;
    private boolean finishedLoading = false;
    private Button menuButton;
    private Button optionButton;
    private int startingAmountOfTimer = 3000;
    private int amountOfTimerToDecreasePerSecond = 5;
    private int appleValue = 1;
    private int currentScore = 0;
    private final AdvancedImage scoreLabel = AssetRetreiver.getImageFromPath("/resources/NewScore.png");
    private final AdvancedImage backgroundGrid = AssetRetreiver.getImageFromPath("/resources/Grid.png");
    private final AdvancedImage menuButtonImage = AssetRetreiver.getImageFromPath("/resources/Menu.png");
    private final AdvancedImage timerLabel = AssetRetreiver.getImageFromPath("/resources/Timer.png");
    private final AdvancedImage pauseScreenBackground = AssetRetreiver.getImageFromPath("/resources/Escape.png");
    private final AdvancedImage optionButtonImage = AssetRetreiver.getImageFromPath("/resources/Options.png");
    private boolean canActivateMenu = false;

    @Override
    public boolean finishedLoading() {
        return finishedLoading;
    }

    @Override
    public void onWindowResize() {
        Shop.getInstance().onWindowResize();
    }

    @Override
    public void init() {
        Shop.getInstance().init();
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        playMusic = (Boolean) SceneManager.getSharedObject(PlayMusicKey);
        difficulty =  Difficulty.values()[((Integer) SceneManager.getSharedObject(DifficultyKey))];
        player = new Player();
        apples.clear();
        allies.clear();
        if(playMusic)
            Sound.music.loop();
        initGameButtons();
        StartGame();
        activateButtons();
        finishedLoading = true;
    }

    @Override
    public void update(float deltaTime) {
        manageKeys();
        if(Shop.getInstance().finishedLoading())
            Shop.getInstance().update();
        performShopActs();

        for (Ally ally : allies) {
            ally.MoveToTarget();
        }

        makeAlliesEatApples();

        for (int i = 0; i < apples.size(); i++) {
            Apple currentApple = apples.get(i);
            if(!Screen.getInstance().entityIsWithin(currentApple)) {
                regenerateAppleInList(currentApple);
            }
        }

        if (canSubtractFromTimer() && !isGamePaused()) {
            startingAmountOfTimer -= amountOfTimerToDecreasePerSecond;
        }

        // When time runs out, net.demomaker.applegame.game finishes
        if (startingAmountOfTimer == 0) {
            EndGame();
        }
    }

    @Override
    public void draw() {
        GraphicsManager.drawImage(backgroundGrid, new Vector3<Float>(0f, 0f,0f));

        if(!isGamePaused()) {
            drawGame();
        }

        if (isGamePaused()) {
            drawPauseScreen();
        }
    }

    @Override
    public void cleanup() {
        gameState = GameState.PAUSE;
        allies.clear();
        apples.clear();
        if (SceneManager.getSharedObject(HighscoreKey) == null || currentScore > (int) SceneManager.getSharedObject(HighscoreKey)) {
            SceneManager.setSharedObject(HighscoreKey, currentScore);
        }
        currentScore = 0;
        deactivateButtons();
    }

    @Override
    public void onResume() {
        Shop.getInstance().init();
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        playMusic = (Boolean) SceneManager.getSharedObject(PlayMusicKey);
        difficulty = Difficulty.values()[((Integer) SceneManager.getSharedObject(DifficultyKey))];
        activateMenu();
    }

    private void initGameButtons() {
        menuButton = new Button();
        optionButton = new Button();
        menuButton.setPosition(new Vector3<>((float)(GameWindow.getWidth() - (GameWindow.getWidth() / 2) - 128 / 2),(float)(GameWindow.getHeight() - (GameWindow.getHeight() / 2) - 72 / 2),0f));
        menuButton.setButtonNormalStateImage(menuButtonImage);
        menuButton.setSize(new Vector3<>(128f, 72f,0f));
        optionButton.setPosition(new Vector3<>(menuButton.getPosition().getX(), menuButton.getPosition().getY() - 144f, 0f));
        optionButton.setButtonNormalStateImage(optionButtonImage);
        optionButton.setSize(new Vector3<>(128f, 72f,0f));
    }
    private Apple goodEntityTouchApple() {
        onEntityTouchApple();
        return Apple.generateNewApple();
    }

    private void onEntityTouchApple() {
        currentScore++;
        if (playSounds) {
            Sound.redtouch.play();
        }
        Shop.getInstance().addMoney(appleValue);
    }

    private void regenerateAllApplesInList() {
        for(int i = 0; i < apples.size(); i++) {
            regenerateAppleInList(apples.get(i));
        }
    }

    private void regenerateAppleInList(Apple currentApple){
        apples.remove(currentApple);
        generateAppleInAppleList();
    }

    private void generateAppleInAppleList() {
        apples.add(Apple.generateNewApple());
    }

    private void generateAllyInAllyList() {
        allies.add(Ally.generateNewAlly(apples));
    }

    private void manageKeys() {
        // Moving Speed with different difficulties
        if (difficulty == Difficulty.Easy) {
            doMovementWithSpeed(1);
        }
        else if (difficulty == Difficulty.Normal) {
            doMovementWithSpeed(7);
        }
        else if (difficulty == Difficulty.Hard) {
            doMovementWithSpeed(13);
        }
    }

    private void activateMenu() {
        if(!isGamePaused())
        {
            gameState = GameState.PAUSE;
            Sound.music.stop();
            Sound.redtouch.stop();
            Sound.yellowbeep.stop();
            Shop.getInstance().setActive(false);
            optionButton.setActive(true);
            menuButton.setActive(true);
        }
        else
        {
            gameState = GameState.PLAY;
            Shop.getInstance().setActive(true);
            optionButton.setActive(false);
            menuButton.setActive(false);
            if(playMusic) {
                Sound.music.resumeLoop();
                Sound.redtouch.resume();
                Sound.yellowbeep.resume();
            }
        }
    }

    private void activateButtons() {
        Shop.getInstance().setActive(true);
    }

    private void deactivateButtons() {
        Shop.getInstance().setActive(false);
        optionButton.setActive(false);
        menuButton.setActive(false);
    }

    private void doMovementWithSpeed(float speed) {
        if (Keyboard.keyPressed(KeyEvent.VK_UP) || Keyboard.keyPressed(KeyEvent.VK_W)) {
            player.MoveYBy(-speed);
            if (playSounds)
                Sound.yellowbeep.play();
        }
        if (Keyboard.keyPressed(KeyEvent.VK_DOWN) || Keyboard.keyPressed(KeyEvent.VK_S)) {
            player.MoveYBy(speed);
            if (playSounds)
                Sound.yellowbeep.play();
        }
        if (Keyboard.keyPressed(KeyEvent.VK_LEFT) || Keyboard.keyPressed(KeyEvent.VK_A)) {
            player.MoveXBy(-speed);
            if (playSounds)
                Sound.yellowbeep.play();
        }
        if (Keyboard.keyPressed(KeyEvent.VK_RIGHT) || Keyboard.keyPressed(KeyEvent.VK_D)) {
            player.MoveXBy(speed);
            if (playSounds)
                Sound.yellowbeep.play();
        }


        // When Player goes off the map, returns other side
        if (Screen.getInstance().entityXIsOnRightSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(0f, player.getPosition().getY(), player.getPosition().getZ()));
        }

        else if (Screen.getInstance().entityXIsOnLeftSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(Screen.getInstance().getWidth() - player.getWidth(), player.getPosition().getY(), player.getPosition().getZ()));
        }

        else if (Screen.getInstance().entityYIsOnBottomSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(player.getPosition().getX(),0f, player.getPosition().getZ()));
        }

        else if (Screen.getInstance().entityYIsOnTopSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(player.getPosition().getX(), Screen.getInstance().getHeight() - player.getHeight(), player.getPosition().getZ())); ;
        }
    }

    private void StartGame() {
        gameState = GameState.PLAY;
        currentScore = 0;
        startingAmountOfTimer = 60000;
        player.setPosition(new Vector3<Float>(0f,0f,0f));
        if(playMusic)
            Sound.music.loop();
        generateAppleInAppleList();
    }

    private void EndGame() {
        SceneManager.setActiveScene(SceneManager.getSceneByName("EndScene"));
    }

    private void makeAlliesEatApples() {
        for (int i = 0; i < apples.size(); i++) {
            Apple currentApple = apples.get(i);
            if (player.ateApple(currentApple)) {
                apples.set(i, goodEntityTouchApple());
                currentApple.onAppleEaten();
            }
        }

        for (Ally ally : allies) {
            if (ally.ateTarget()) {
                apples.set(apples.indexOf(ally.getTarget()), goodEntityTouchApple());
                ally.getTarget().onAppleEaten();
            }
        }
    }

    private boolean isGamePaused() {
        return gameState == GameState.PAUSE;
    }

    private boolean canSubtractFromTimer() {
        return startingAmountOfTimer >= amountOfTimerToDecreasePerSecond;
    }

    private void performShopActs() {
        for(int i = 0; i < ShopActQueue.getLength(); i++) {
            ShopAct currentAct = ShopActQueue.pop();
            performShopAct(currentAct);
        }
    }

    private void performShopAct(ShopAct shopAct) {
        switch (shopAct) {
            case BUYALLY:
                generateAllyInAllyList();
                break;
            case BUYAPPLE:
                generateAppleInAppleList();
                break;
            case RESETAPPLE:
                regenerateAllApplesInList();
                break;
            case INCREMENTAPPLEVALUE:
                appleValue++;
                break;
            default:
                break;
        }
    }

    private void drawGame() {
        if(Shop.getInstance().finishedLoading())
            Shop.getInstance().draw();
        drawTimer();
        drawScore();
        player.draw();
        for (Apple apple : apples) {
            apple.draw();
        }
        for (Ally ally : allies) {
            ally.draw();
        }
        GraphicsManager.drawImage(scoreLabel, new Vector3<Float>(GameWindow.getWidth() - 634f, 0f, 0f));
    }

    private void drawScore() {
        AdvancedImage[] ScoreImages = GameFont.getSymbolsfromNumber(currentScore);
        GraphicsManager.drawImage(ScoreImages[2], new Vector3<Float>(GameWindow.getWidth() - 628f, 56f, 0f));
        GraphicsManager.drawImage(ScoreImages[1], new Vector3<Float>(GameWindow.getWidth() - 569f, 56f, 0f));
        GraphicsManager.drawImage(ScoreImages[0], new Vector3<Float>(GameWindow.getWidth() - 510f, 56f, 0f));

        if (currentScore >= 1000) {
            GraphicsManager.drawString(Color.WHITE, "" + (currentScore / 1000) + "", new Vector3<Float>((float)(374 + 59), 16f, 0f));
        }
    }

    private void drawTimer() {
        GraphicsManager.drawImage(timerLabel, new Vector3<Float>(GameWindow.getWidth() - 269f, 0f, 0f));
        int TimerNumber = startingAmountOfTimer / 200;
        AdvancedImage[] TimerImages = GameFont.getSymbolsfromNumber(TimerNumber);
        GraphicsManager.drawImage(TimerImages[2], new Vector3<Float>(GameWindow.getWidth() - 280f, 56f, 0f));
        GraphicsManager.drawImage(TimerImages[1], new Vector3<Float>(GameWindow.getWidth() - 221f, 56f, 0f));
        GraphicsManager.drawImage(TimerImages[0], new Vector3<Float>(GameWindow.getWidth() - 162f, 56f, 0f));
    }

    private void drawPauseScreen() {
        GraphicsManager.drawImage(pauseScreenBackground, new Vector3<Float>(0f, 0f,0f));
        menuButton.draw();
        optionButton.draw();
    }

    private ButtonListener buttonListener = new ButtonListener() {
        @Override
        public void onClick(Button button) {
            if(!isGamePaused()) return;
            if(button == menuButton) {
                SceneManager.SaveActiveSceneState();
                SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
            }

            if(button == optionButton) {
                SceneManager.SaveActiveSceneState();
                SceneManager.setSharedObject(OptionReturnSceneKey, "GameScene");
                SceneManager.setActiveScene(SceneManager.getSceneByName("OptionScene"));
            }
        }

        @Override
        public void onPress(Button button) {

        }

        @Override
        public void onRelease(Button button) {

        }

        @Override
        public void onHover(Button button) {

        }
    };

    private Keyboard.KeyboardListener keyboardListener = new Keyboard.KeyboardListener() {
        @Override
        public void onKeyPressed(int key) {
            if(!finishedLoading()) return;

            if (key == KeyEvent.VK_E) {
                difficulty = Difficulty.Easy;
            }
            if (key == KeyEvent.VK_N) {
                difficulty = Difficulty.Normal;
            }
            if (key == KeyEvent.VK_H) {
                difficulty = Difficulty.Hard;
            }

            // When player is playing
            if (!isGamePaused()) {
                if (key == KeyEvent.VK_M) {
                    gameState = GameState.PAUSE;
                    SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
                }
            }

            if(key == KeyEvent.VK_ESCAPE) {
                canActivateMenu = true;
            }
        }

        @Override
        public void onKeyReleased(int key) {
            if(!finishedLoading()) return;
            if(key == KeyEvent.VK_ESCAPE && canActivateMenu) {
                activateMenu();
                canActivateMenu = false;
            }
        }
    };
}
