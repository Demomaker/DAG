package net.demomaker.applegame.game.entity;

import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;

public class Apple extends Entity {
    private final static Vector3<Float> DEFAULT_POSITION = new Vector3<>(0f, 0f, 0f);
    private final static int DEFAULT_WIDTH = 10;
    private final static int DEFAULT_HEIGHT = 10;
    private final static Color DEFAULT_COLOR = Color.RED;
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
}
