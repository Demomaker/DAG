package net.demomaker.applegame.game.scene;

import net.demomaker.applegame.engine.scene.Scene;
import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.engine.ui.UIImage;
import net.demomaker.applegame.engine.ui.button.ButtonState;
import net.demomaker.applegame.engine.ui.button.CheckBox;
import net.demomaker.applegame.engine.ui.button.MovingButton;
import net.demomaker.applegame.engine.util.GameState;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.controller.DemomakerGame;
import net.demomaker.applegame.engine.input.Keyboard;
import net.demomaker.applegame.engine.input.Mouseboard;
import net.demomaker.applegame.engine.ui.button.Button;
import net.demomaker.applegame.game.entity.Ally;
import net.demomaker.applegame.game.entity.Apple;
import net.demomaker.applegame.game.entity.Player;
import net.demomaker.applegame.game.logic.Screen;
import net.demomaker.applegame.game.logic.*;
import net.demomaker.applegame.game.sound.Sound;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;
import java.net.URL;
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

    /* Buttons */
    private MovingButton shopButton;
    private Button appleBuyButton;
    private Button allyBuyButton;
    private Button moneyIncreaseButton;
    private Button resetRedButton;
    private Button menuButton;
    private Button optionButton;
    private CheckBox checkBox1;
    private CheckBox checkBox2;

    private boolean Option = false;
    private int FullTimer = 3000;
    private int amountOfTimerToDecrease = 5;
    private int Highscore = 0;
    private int MoneyPerApple = 1;

    // Buffering Variables
    private final BufferedImage image = new BufferedImage(winWIDTH, winHEIGHT, BufferedImage.TYPE_INT_RGB);

    // variables
    private Screen screen;
    public JFrame frame;
    private Keyboard key;
    int Score = 0;
    int ShopButtonX;
    int ShopButtonY = HEIGHT - 69;
    int Money = 2000;
    int MenuX = winWIDTH - (winWIDTH / 2) - 128 / 2;
    int MenuY = winHEIGHT - (winHEIGHT / 2) - 72 / 2;
    boolean ShopOpen = true;
    private final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
    private UIImage shopBackgroundUIImage = new UIImage();
    private UIImage resetAppleUIImage = new UIImage();
    private UIImage appleBuyImage = new UIImage();
    private UIImage moneyIncrementImage = new UIImage();
    private UIImage allyBuyImage = new UIImage();
    // Audio

    // Image URLs
    // net.demomaker.applegame.game.controller Extras URLs
    URL cored = resource("/resources/NewScore.png");
    URL grid = resource("/resources/Grid.png");
    URL menu = resource("/resources/Menu.png");
    URL time = resource("/resources/Timer.png");
    URL escape = resource("/resources/Escape.png");
    URL options = resource("/resources/Options.png");

    // Shop URLs
    URL shopbutton = resource("/resources/ShopButton.png");
    URL shopbackground = resource("/resources/ShopBackground.png");
    URL reddoteimage = resource("/resources/RedDoteImage.png");
    URL reddotebuy = resource("/resources/RedDoteBuy.png");
    URL reddotebuyhover = resource("/resources/RedDoteBuyHover.png");
    URL reddotebuyaction = resource("/resources/RedDoteBuyAction.png");
    URL moneyaddimage = resource("/resources/MoneyAddImage.png");
    URL moneyaddbuy = resource("/resources/MoneyAddBuy.png");
    URL moneyaddbuyaction = resource("/resources/MoneyAddBuyAction.png");
    URL moneyaddbuyhover = resource("/resources/MoneyAddBuyHover.png");
    URL yellowautoimage = resource("/resources/YellowAutoImage.png");
    URL yellowaddbuy = resource("/resources/YellowAddBuy.png");
    URL yellowaddbuyaction = resource("/resources/YellowAddBuyAction.png");
    URL yellowaddbuyhover = resource("/resources/YellowAddBuyHover.png");
    URL checkbox = resource("/resources/Checkbox.png");
    URL checker = resource("/resources/Checker.png");
    URL redreset = resource("/resources/RedReset.png");

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

    // net.demomaker.applegame.game.controller Extras Images
    public Image scored = image(cored);
    public Image Grid = image(grid);
    private final Image Menu = image(menu);
    private final Image Time = image(time);
    private final Image Escap = image(escape);
    private final Image Options = image(options);

    // Shop Images
    private final Image ShopButton = image(shopbutton);
    private final Image ShopBackground = image(shopbackground);
    private final Image RedDoteImage = image(reddoteimage);
    private final Image RedDoteBuy = image(reddotebuy);
    private final Image RedDoteBuyHover = image(reddotebuyhover);
    private final Image RedDoteBuyAction = image(reddotebuyaction);
    private final Image MoneyAddImage = image(moneyaddimage);
    private final Image MoneyAddBuy = image(moneyaddbuy);
    private final Image MoneyAddBuyAction = image(moneyaddbuyaction);
    private final Image MoneyAddBuyHover = image(moneyaddbuyhover);
    private final Image YellowAutoImage = image(yellowautoimage);
    private final Image YellowAddBuy = image(yellowaddbuy);
    private final Image YellowAddBuyAction = image(yellowaddbuyaction);
    private final Image YellowAddBuyHover = image(yellowaddbuyhover);

    private final Image Checkbox = image(checkbox);
    private final Image Checker = image(checker);
    private final Image RedReset = image(redreset);

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

    private void initGameButtons() {
        allyBuyButton = new Button();
        appleBuyButton = new Button();
        moneyIncreaseButton = new Button();
        resetRedButton = new Button();
        shopButton = new MovingButton();
        checkBox1 = new CheckBox();
        checkBox2 = new CheckBox();
        menuButton = new Button();
        optionButton = new Button();
        shopBackgroundUIImage.setImage(ShopBackground);
        shopBackgroundUIImage.setPosition(new Vector3<>(-785f, 50f, 0f));
        shopBackgroundUIImage.getTransform().setParent(shopButton.getTransform());
        appleBuyImage.setImage(RedDoteImage);
        appleBuyImage.setPosition(new Vector3<>(-695f, 40f, 0f));
        appleBuyImage.getTransform().setParent(shopButton.getTransform());
        moneyIncrementImage.setImage(MoneyAddImage);
        moneyIncrementImage.setPosition(new Vector3<>(-695f, 100f, 0f));
        moneyIncrementImage.getTransform().setParent(shopButton.getTransform());
        allyBuyImage.setImage(YellowAutoImage);
        allyBuyImage.setPosition(new Vector3<>(-695f, 160f, 0f));
        allyBuyImage.getTransform().setParent(shopButton.getTransform());
        resetRedButton.setButtonReleasedStateImage(RedReset);
        resetRedButton.setButtonPressedStateImage(RedReset);
        resetRedButton.setButtonNormalStateImage(RedReset);
        resetRedButton.setPosition(new Vector3<>(-25f, 40f, 0f));
        resetRedButton.setSize(new Vector3<>(50f, 50f, 0f));
        resetRedButton.getTransform().setParent(shopButton.getTransform());
        allyBuyButton.setPosition(new Vector3<>(-500f, 160f, 0f));
        allyBuyButton.setSize(new Vector3<>(150f, 50f, 0f));
        allyBuyButton.setButtonReleasedStateImage(YellowAddBuyHover);
        allyBuyButton.setButtonPressedStateImage(YellowAddBuyAction);
        allyBuyButton.setButtonNormalStateImage(YellowAddBuy);
        appleBuyButton.setPosition(new Vector3<>(-500f, 40f, 0f));
        appleBuyButton.setSize(new Vector3<>(150f, 50f, 0f));
        appleBuyButton.setButtonReleasedStateImage(RedDoteBuyHover);
        appleBuyButton.setButtonPressedStateImage(RedDoteBuyAction);
        appleBuyButton.setButtonNormalStateImage(RedDoteBuy);
        moneyIncreaseButton.setPosition(new Vector3<>(-500f, 100f, 0f));
        moneyIncreaseButton.setSize(new Vector3<>(150f, 50f, 0f));
        moneyIncreaseButton.setButtonReleasedStateImage(MoneyAddBuyHover);
        moneyIncreaseButton.setButtonPressedStateImage(MoneyAddBuyAction);
        moneyIncreaseButton.setButtonNormalStateImage(MoneyAddBuy);
        shopButton.setPosition(new Vector3<>(ShopButtonX * 1.0f, HEIGHT - 69f, 0f));
        shopButton.setImage(ShopButton);
        shopButton.setSize(new Vector3<>(100f, 50f, 0f));
        checkBox1.setCheckedImage(Checker);
        checkBox1.setUncheckedImage(Checkbox);
        checkBox1.setPosition(new Vector3<>(winWIDTH - 35f, winHEIGHT / 2 - 60f, 0f));
        checkBox1.setSize(new Vector3<>(10f, 10f, 0f));
        checkBox2.setCheckedImage(Checker);
        checkBox2.setUncheckedImage(Checkbox);
        checkBox2.setPosition(new Vector3<>((winWIDTH - 50f), (winHEIGHT / 2 - 60f),0f));
        checkBox2.setSize(new Vector3<>(10f, 10f, 0f));
        menuButton.setPosition(new Vector3<>((float)(winWIDTH - (winWIDTH / 2) - 128 / 2),(float)(winHEIGHT - (winHEIGHT / 2) - 72 / 2),0f));
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
        Money += MoneyPerApple;
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
        ally.SetTarget(getNewTarget());
        return ally;
    }

    private Apple getNewTarget() {
        int targetIndex = random.nextInt(apples.size());
        return apples.get(targetIndex);
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
    private void manageKeys()
    {

        // Difficulty selecting if net.demomaker.applegame.game isnt running
        if (Option) {
            if (Keyboard.keyPressed(KeyEvent.VK_E)) {
                difficulty = Difficulty.Easy;
            }
            if (Keyboard.keyPressed(KeyEvent.VK_N)) {
                difficulty = Difficulty.Normal;
            }
            if (Keyboard.keyPressed(KeyEvent.VK_H)) {
                difficulty = Difficulty.Hard;
            }
        }

        // When player is playing
        if (gameState != GameState.PAUSE) {
            if (Keyboard.keyPressed(KeyEvent.VK_M)) {
                gameState = GameState.PAUSE;
                SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
            }
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

        if (Keyboard.keyReleased(KeyEvent.VK_ESCAPE)) {
            if(gameState != GameState.PAUSE)
            {
                gameState = GameState.PAUSE;
                Sound.music.stop();
                Sound.redtouch.stop();
                Sound.yellowbeep.stop();
            }
            else
            {
                gameState = GameState.PLAY;
            }
        }
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
        if (screen.entityXIsOnRightSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(0f, player.getPosition().getY(), player.getPosition().getZ()));
        }

        else if (screen.entityXIsOnLeftSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(screen.getWidth() - player.getWidth(), player.getPosition().getY(), player.getPosition().getZ()));
        }

        else if (screen.entityYIsOnBottomSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(player.getPosition().getX(),0f, player.getPosition().getZ()));
        }

        else if (screen.entityYIsOnTopSideOfBounds(player)) {
            player.setPosition(new Vector3<Float>(player.getPosition().getX(), screen.getHeight() - player.getHeight(), player.getPosition().getZ())); ;
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
    public void init() {
        System.out.println("init called");
        key = (Keyboard) SceneManager.getSharedObject(KeyboardKey);
        screen = (Screen) SceneManager.getSharedObject(ScreenKey);
        frame = (JFrame) SceneManager.getSharedObject(FrameKey);
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        playMusic = (Boolean) SceneManager.getSharedObject(PlayMusicKey);
        difficulty =  Difficulty.values()[((Integer) SceneManager.getSharedObject(DifficultyKey))];
        player = new Player();
        apples.clear();
        allies.clear();
        generateAppleInAppleList();
        Sound.music.stop();
        initGameButtons();
        ShopButtonX = frame.getWidth() - 116;
        shopButton.setX(ShopButtonX);
        StartGame();
    }

    @Override
    public void update(float deltaTime) {
        manageKeys();
        for (Ally ally : allies) {
            ally.MoveToTarget();
        }

        List<Apple> eatenApples = new ArrayList<>();
        for (int i = 0; i < apples.size(); i++) {
            Apple currentApple = apples.get(i);
            if (currentApple.getPosition().getX().equals(player.getPosition().getX())) {
                if (currentApple.getPosition().getY().equals(player.getPosition().getY())) {
                    apples.set(i, goodEntityTouchApple());
                }
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
            if (currentApple.getPosition().getX() >= 250 && currentApple.getPosition().getX() <= 325) {
                if (currentApple.getPosition().getY() >= 0 && currentApple.getPosition().getY() <= 56) {
                    regenerateAppleInList(currentApple);
                }
            }
        }

        for (int i = 0; i < apples.size(); i++) {
            Apple currentApple = apples.get(i);
            if (currentApple.getPosition().getX() <= 0 || currentApple.getPosition().getX() >= frame.getWidth()) {
                regenerateAppleInList(currentApple);
            } else if (currentApple.getPosition().getY() <= 0 || currentApple.getPosition().getY() >= frame.getHeight()) {
                regenerateAppleInList(currentApple);
            }
        }

        if (FullTimer >= amountOfTimerToDecrease && gameState != GameState.PAUSE) {
            FullTimer -= amountOfTimerToDecrease;
        }

        // When time runs out, net.demomaker.applegame.game finishes
        if (FullTimer == 0) {
            EndGame();
        }

        if (Mouseboard.mousePressedUIElement(moneyIncreaseButton) && gameState != GameState.PAUSE) {
            if (Money >= 20) {
                Money = Money - 20;
                MoneyPerApple++;
            }
        }
        if (Mouseboard.mousePressedUIElement(appleBuyButton) && gameState != GameState.PAUSE) {
            if (Money >= 10) {
                Money = Money - 10;
                generateAppleInAppleList();
            }
        }
        if (Mouseboard.mousePressedUIElement(allyBuyButton) && Mouseboard.mouseRelease && gameState != GameState.PAUSE) {
            if (Money >= 50) {
                Money = Money - 50;
                generateAllyInAllyList();
            }
        }

        if (Mouseboard.mousePressedUIElement(resetRedButton) && gameState != GameState.PAUSE) {
            resetRedButton.press();
        }

        if(resetRedButton.getButtonState() == ButtonState.RELEASED) {
            regenerateAppleInList(apples.get(0));
        }

        if (Mouseboard.mousePressedUIElement(checkBox1) && gameState != GameState.PAUSE) {
            checkBox1.press();
        }

        if (Mouseboard.mousePressedUIElement(checkBox2)
                && gameState != GameState.PAUSE) {
            checkBox2.press();
        }

        // Opening and Closing Shop
        if (Mouseboard.mouseTouchesUIElement(shopButton) && !checkBox1.isChecked() && gameState != GameState.PAUSE) {

            if (shopButton.getPosition().getY() >= 247 && ShopOpen) {
                shopButton.MoveYBy(-1);
            }
            if (shopButton.getPosition().getY() <= 247 || !ShopOpen) {
                shopButton.MoveYBy(1);
                ShopOpen = false;
            }
            if (shopButton.getPosition().getY() >= frame.getHeight() - 69 && !ShopOpen) {
                ShopOpen = true;
            }
            ShopButtonY = shopButton.getPosition().getY().intValue();
        }

        if (checkBox1.isChecked()) {
            shopButton.setY(247f);
        }

        if (checkBox2.isChecked()) {
            shopButton.setY(frame.getHeight() - 69f);
        }

        if (gameState == GameState.PAUSE) {
            interactWithPauseScreen();
        }
    }

    private void interactWithPauseScreen() {

        if(Mouseboard.mouseUpUIElement(menuButton)) {
            SceneManager.SaveActiveSceneState();
            SceneManager.setActiveScene(SceneManager.getSceneByName("TitleScene"));
            Mouseboard.mouseRelease = false;
        }

        if(Mouseboard.mouseUpUIElement(optionButton)) {
            SceneManager.SaveActiveSceneState();
            SceneManager.setSharedObject(OptionReturnSceneKey, "GameScene");
            SceneManager.setActiveScene(SceneManager.getSceneByName("OptionScene"));
            Mouseboard.mouseRelease = false;
        }
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(image, 0, 0, winWIDTH, winHEIGHT, null);
        g.drawImage(Grid, 0, 0, winWIDTH, winHEIGHT, null);
        shopBackgroundUIImage.draw(g);
        shopButton.draw(g);
        appleBuyButton.draw(g);
        moneyIncreaseButton.draw(g);
        allyBuyButton.draw(g);
        checkBox1.draw(g);
        checkBox2.draw(g);
        appleBuyImage.draw(g);
        moneyIncrementImage.draw(g);
        allyBuyImage.draw(g);
        resetRedButton.draw(g);


        if (gameState == GameState.PAUSE) {
            drawPauseScreen(g);
        }

        // Money Showing
        if(gameState != GameState.PAUSE) {
            g.setColor(Color.YELLOW);
            g.drawString("Money: " + Money, shopButton.getPosition().getX().intValue() + 25, shopButton.getPosition().getY().intValue());
        }

        g.drawImage(Time, 615, 0, null);
        int TimerNumber = FullTimer / 200;
        Image[] TimerImages = TransformNumbers(TimerNumber);
        g.drawImage(TimerImages[2], 604, 56, null);
        g.drawImage(TimerImages[1], 663, 56, null);
        g.drawImage(TimerImages[0], 722, 56, null);

        Image[] ScoreImages = TransformNumbers(Score);
        g.drawImage(ScoreImages[2], 256, 56, null);
        g.drawImage(ScoreImages[1], 315, 56, null);
        g.drawImage(ScoreImages[0], 374, 56, null);

        if (Score >= 1000) {
            g.setColor(Color.WHITE);
            g.drawString("" + (Score / 1000) + "", (374 + 59), 16);
        }

        if (gameState != GameState.PAUSE) {
            player.draw(g);
            for (Apple apple : apples) {
                apple.draw(g);
            }
            for (Ally ally : allies) {
                ally.draw(g);
            }
        }
        g.drawImage(scored, 250, 0, null);
    }

    public void drawPauseScreen(Graphics g) {
        g.drawImage(Escap, 0, 0, winWIDTH, winHEIGHT, null);
        menuButton.draw(g);
        optionButton.draw(g);
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
    }

    @Override
    public void onResume() {
        playSounds = (Boolean) SceneManager.getSharedObject(PlaySoundKey);
        playMusic = (Boolean) SceneManager.getSharedObject(PlayMusicKey);
        difficulty = Difficulty.values()[((Integer) SceneManager.getSharedObject(DifficultyKey))];

        if(playMusic)
            Sound.music.loop();
    }

    private class MouseListener extends Mouseboard.MouseboardListener {

        @Override
        public void onMousePressed(int mouseButton) {

        }

        @Override
        public void onMouseReleased(int mouseButton) {

        }
    }
}
