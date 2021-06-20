package net.demomaker.applegame.game.entity;

import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;

public class GoodEntity extends Entity{
    private final static Vector3<Float> DEFAULT_POSITION = new Vector3<>(0f, 0f, 0f);
    private final static int DEFAULT_WIDTH = 10;
    private final static int DEFAULT_HEIGHT = 10;
    private final static Color DEFAULT_COLOR = Color.YELLOW;
    public GoodEntity() {
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

    public boolean ateApple(Apple apple) {
        Vector3<Integer> targetPositionInInt = new Vector3<>(apple.getPosition().getX().intValue(), apple.getPosition().getY().intValue(), apple.getPosition().getZ().intValue());
        Vector3<Integer> thisPositionInInt = new Vector3<>(getPosition().getX().intValue(), getPosition().getY().intValue(), getPosition().getZ().intValue());
        return targetPositionInInt.equals(thisPositionInInt);
    }
}
