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

    /* Buttons */
    private Button menuButton;
    private Button optionButton;

    private boolean Option = false;
    private int FullTimer = 3000;
    private int amountOfTimerToDecrease = 5;
    private int Highscore = 0;
    private int MoneyPerApple = 1;
    private int Score = 0;

    private final AdvancedImage scored = AssetRetreiver.getImageFromPath("/resources/NewScore.png");
    private final AdvancedImage Grid = AssetRetreiver.getImageFromPath("/resources/Grid.png");
    private final AdvancedImage Menu = AssetRetreiver.getImageFromPath("/resources/Menu.png");
    private final AdvancedImage Time = AssetRetreiver.getImageFromPath("/resources/Timer.png");
    private final AdvancedImage Escap = AssetRetreiver.getImageFromPath("/resources/Escape.png");
    private final AdvancedImage Options = AssetRetreiver.getImageFromPath("/resources/Options.png");
    private int movementKey;
    private boolean canActivateButtons = false;

    private void initGameButtons() {
        menuButton = new Button();
        optionButton = new Button();
        menuButton.setPosition(new Vector3<>((float)(GameWindow.getWidth() - (GameWindow.getWidth() / 2) - 128 / 2),(float)(GameWindow.getHeight() - (GameWindow.getHeight() / 2) - 72 / 2),0f));
        menuButton.setButtonNormalStateImage(Menu);
        menuButton.setSize(new Vector3<>(128f, 72f,0f));
        optionButton.setPosition(new Vector3<>(menuButton.getPosition().getX(), menuButton.getPosition().getY() - 144f, 0f));
        optionButton.setButtonNormalStateImage(Options);
        optionButton.setSize(new Vector3<>(128f, 72f,0f));
    }
    private Apple goodEntityTouchApple() {
        Score++;
        if (playSounds) {
            Sound.redtouch.play();
        }
        Shop.getInstance().addMoney(MoneyPerApple);
        return generateNewApple();
    }

    private Apple generateNewApple() {
        Apple apple = new Apple();
        int appleX = random.nextInt(GameWindow.getWidth() - 10);
        int appleY = random.nextInt(GameWindow.getHeight() - 30);
        apple.setPosition(new Vector3<Float>(appleX * 1.0f, appleY * 1.0f, 0f));
        return apple;
    }

    private Ally generateNewAlly() {
        Ally ally = new Ally();
        int allyX = random.nextInt(GameWindow.getWidth() - 10);
        int allyY = random.nextInt(GameWindow.getHeight() - 30);
        ally.setPosition(new Vector3<Float>(allyX * 1.0f,allyY * 1.0f,0f));
        ally.SetTarget(getNewTarget());
        return ally;
    }

    private Apple getNewTarget() {
        int targetIndex = random.nextInt(apples.size());
        return apples.get(targetIndex);
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
        apples.add(generateNewApple());
    }

    private void generateAllyInAllyList() {
        allies.add(generateNewAlly());
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

    public void activateMenu() {
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

    public void activateButtons() {
        Shop.getInstance().setActive(true);
    }

    public void deactivateButtons() {
        Shop.getInstance().setActive(false);
        optionButton.setActive(false);
        menuButton.setActive(false);
    }

    public void doMovementWithSpeed(float speed) {
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
        Score = 0;
        FullTimer = 60000;
        player.setPosition(new Vector3<Float>(0f,0f,0f));
        if(playMusic)
            Sound.music.loop();
        generateAppleInAppleList();
    }

    private void EndGame() {
        SceneManager.setActiveScene(SceneManager.getSceneByName("EndScene"));
    }

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
        generateAppleInAppleList();
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

        List<Apple> eatenApples = new ArrayList<>();
        for (int i = 0; i < apples.size(); i++) {
            Apple currentApple = apples.get(i);
            if (player.ateApple(currentApple)) {
                apples.set(i, goodEntityTouchApple());
            }
            for (Ally ally : allies) {
                if(eatenApples.contains(ally.GetTarget())) {
                    ally.SetTarget(getNewTarget());
                }
                if (ally.ateTarget()) {
                    eatenApples.add(ally.GetTarget());
                    ally.SetTarget(goodEntityTouchApple());
                    apples.set(i, ally.GetTarget());
                }
            }
        }

        for (int i = 0; i < apples.size(); i++) {
            Apple currentApple = apples.get(i);
            if(!Screen.getInstance().entityIsWithin(currentApple)) {
                regenerateAppleInList(currentApple);
            }
        }

        if (canSubtractFromTimer() && !isGamePaused()) {
            FullTimer -= amountOfTimerToDecrease;
        }

        // When time runs out, net.demomaker.applegame.game finishes
        if (FullTimer == 0) {
            EndGame();
        }

        if (isGamePaused()) {
            interactWithPauseScreen();
        }
    }

    private boolean isGamePaused() {
        return gameState == GameState.PAUSE;
    }

    private boolean canSubtractFromTimer() {
        return FullTimer >= amountOfTimerToDecrease;
    }

    public void performShopActs() {
        for(int i = 0; i < ShopActQueue.getLength(); i++) {
            ShopAct currentAct = ShopActQueue.pop();
            performShopAct(currentAct);
        }
    }

    public void performShopAct(ShopAct shopAct) {
        switch (shopAct) {
            case BUYALLY:
                generateAllyInAllyList();
                break;
            case BUYAPPLE:
                generateAppleInAppleList();
                break;
            case RESETAPPLE:
                regenerateAllApplesInList();
                //regenerateAppleInList(apples.get(0));
                break;
            case INCREMENTAPPLEVALUE:
                MoneyPerApple++;
                break;
            default:
                break;
        }
    }

    private void interactWithPauseScreen() {

    }

    @Override
    public void draw() {
        GraphicsManager.drawImage(Grid, new Vector3<Float>(0f, 0f,0f));

        if(!isGamePaused()) {
            drawGame();
        }

        if (isGamePaused()) {
            drawPauseScreen();
        }
    }

    private void drawGame() {
        if(Shop.getInstance().finishedLoading())
            Shop.getInstance().draw();
        GraphicsManager.drawImage(Time, new Vector3<Float>(GameWindow.getWidth() - 269f, 0f, 0f));
        int TimerNumber = FullTimer / 200;
        AdvancedImage[] TimerImages = GameFont.getSymbolsfromNumber(TimerNumber);
        GraphicsManager.drawImage(TimerImages[2], new Vector3<Float>(GameWindow.getWidth() - 280f, 56f, 0f));
        GraphicsManager.drawImage(TimerImages[1], new Vector3<Float>(GameWindow.getWidth() - 221f, 56f, 0f));
        GraphicsManager.drawImage(TimerImages[0], new Vector3<Float>(GameWindow.getWidth() - 162f, 56f, 0f));

        AdvancedImage[] ScoreImages = GameFont.getSymbolsfromNumber(Score);
        GraphicsManager.drawImage(ScoreImages[2], new Vector3<Float>(GameWindow.getWidth() - 628f, 56f, 0f));
        GraphicsManager.drawImage(ScoreImages[1], new Vector3<Float>(GameWindow.getWidth() - 569f, 56f, 0f));
        GraphicsManager.drawImage(ScoreImages[0], new Vector3<Float>(GameWindow.getWidth() - 510f, 56f, 0f));

        if (Score >= 1000) {
            GraphicsManager.drawString(Color.WHITE, "" + (Score / 1000) + "", new Vector3<Float>((float)(374 + 59), 16f, 0f));
        }
        player.draw();
        for (Apple apple : apples) {
            apple.draw();
        }
        for (Ally ally : allies) {
            ally.draw();
        }
        GraphicsManager.drawImage(scored, new Vector3<Float>(GameWindow.getWidth() - 634f, 0f, 0f));
    }

    public void drawPauseScreen() {
        GraphicsManager.drawImage(Escap, new Vector3<Float>(0f, 0f,0f));
        menuButton.draw();
        optionButton.draw();
    }

    @Override
    public void cleanup() {
        gameState = GameState.PAUSE;
        allies.clear();
        apples.clear();
        if (Score > Highscore) {
            Highscore = Score;
        }
        Score = 0;
        SceneManager.setSharedObject(HighscoreKey, Highscore);
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
            if (Option) {
                if (key == KeyEvent.VK_E) {
                    difficulty = Difficulty.Easy;
                }
                if (key == KeyEvent.VK_N) {
                    difficulty = Difficulty.Normal;
                }
                if (key == KeyEvent.VK_H) {
                    difficulty = Difficulty.Hard;
                }
            }

            // When player is playing
            if (!isGamePaused()) {
                if (key == KeyEvent.VK_M) {
                    gameState = GameState.PAUSE;
                    SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
                }
            }

            if(key == KeyEvent.VK_ESCAPE) {
                canActivateButtons = true;
            }
        }

        @Override
        public void onKeyReleased(int key) {
            if(!finishedLoading()) return;
            if(key == KeyEvent.VK_ESCAPE && canActivateButtons) {
                activateMenu();
                canActivateButtons = false;
            }
        }
    };
}
