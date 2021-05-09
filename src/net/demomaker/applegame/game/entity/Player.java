package net.demomaker.applegame.game.entity;

import net.demomaker.applegame.engine.util.Vector3;

public class Player extends GoodEntity {
    private final static Vector3<Float> DEFAULT_POSITION = new Vector3<>(0f, 0f, 0f);
    public Player() {
        super();
        setPosition(DEFAULT_POSITION);
    }
}
