package net.demomaker.applegame.game.entity;

import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.shop.ShopActQueue;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class Apple extends Entity {
    private final static Vector3<Float> DEFAULT_POSITION = new Vector3<>(0f, 0f, 0f);
    private final static int DEFAULT_WIDTH = 10;
    private final static int DEFAULT_HEIGHT = 10;
    private final static Color DEFAULT_COLOR = Color.RED;
    private static final Random random = new Random();
    private static ArrayList<AppleListener> appleListenerList = new ArrayList<>();

    public Apple() {
        super();
        setPosition(DEFAULT_POSITION);
        setSize(DEFAULT_WIDTH, DEFAULT_HEIGHT);
        setColor(DEFAULT_COLOR);
    }

    @Override
    public void draw() {
        super.draw();
        GraphicsManager.fillRectangle(getColor(), Math.round(getWidth()), Math.round(getHeight()), new Vector3<Float>(getPosition().getX(), getPosition().getY(), getPosition().getZ()));
    }

    public static Apple generateNewApple() {
        Apple apple = new Apple();
        int appleX = random.nextInt(GameWindow.getWidth() - 10);
        int appleY = random.nextInt(GameWindow.getHeight() - 30);
        apple.setPosition(new Vector3<Float>(appleX * 1.0f, appleY * 1.0f, 0f));
        return apple;
    }

    public void onAppleEaten() {
        for(AppleListener appleListener : appleListenerList) {
            appleListener.onAppleEaten(this);
        }
    }

    public abstract static class AppleListener {
        public AppleListener() { Apple.addAppleListener(this); }
        abstract void onAppleEaten(Apple apple);
    }

    private static void addAppleListener(AppleListener appleListener) {
        Apple.appleListenerList.add(appleListener);
    }

}
