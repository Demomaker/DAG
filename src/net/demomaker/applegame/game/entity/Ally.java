package net.demomaker.applegame.game.entity;

import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.util.Vector3;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Ally extends GoodEntity {
    private static final Random random = new Random();
    private Apple target = null;
    public void SetRandomPositionIn(Vector3<Float> topLeftLimit, Vector3<Float> bottomRightLimit) {
        Float x = random.nextInt(Math.round(bottomRightLimit.getX())) + topLeftLimit.getX();
        Float y = random.nextInt(Math.round(bottomRightLimit.getY())) + topLeftLimit.getY();
        setPosition(new Vector3<>(x, y, 0f));
    }

    public void SetTarget(Apple apple) {
        target = apple;
    }

    public Apple GetTarget() {
        return target;
    }

    public void MoveToTarget() {
        if (getPosition().getX() < target.getPosition().getX()) {
            MoveXBy(1);
        }
        if (getPosition().getX() > target.getPosition().getX()) {
            MoveXBy(-1);
        }
        if (getPosition().getY() < target.getPosition().getY()) {
            MoveYBy(1);
        }
        if (getPosition().getY() > target.getPosition().getY()) {
            MoveYBy(-1);
        }
    }

    public static Ally generateNewAlly() {
        Ally ally = new Ally();
        int allyX = random.nextInt(GameWindow.getWidth() - 10);
        int allyY = random.nextInt(GameWindow.getHeight() - 30);
        ally.setPosition(new Vector3<>(allyX * 1.0f,allyY * 1.0f,0f));
        ally.SetTarget(ally.getNewTarget());
        return ally;
    }

    public Apple getNewTarget() {
        int targetIndex = random.nextInt(Apple.getList().size());
        return Apple.getList().get(targetIndex);
    }

    public Apple getTarget() {
        return target;
    }

    public void setNewTarget() {
        SetTarget(getNewTarget());
    }

    public boolean ateTarget() {
        return ateApple(target);
    }

    private Apple.AppleListener appleListener = new Apple.AppleListener(){

        @Override
        void onAppleEaten(Apple apple) {
            if(apple == target) {
                setNewTarget();
            }
        }
    };
}
