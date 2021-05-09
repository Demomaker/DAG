package net.demomaker.applegame.game.entity;

import net.demomaker.applegame.engine.util.Vector3;

import java.util.Random;

public class Ally extends GoodEntity {
    private Random random = new Random();
    private Apple target = null;
    public void SetRandomPositionIn(Vector3<Float> topLeftLimit, Vector3<Float> bottomRightLimit) {
        Float x = random.nextInt(Math.round(bottomRightLimit.getX())) + topLeftLimit.getX();
        Float y = random.nextInt(Math.round(bottomRightLimit.getY())) + topLeftLimit.getY();
        setPosition(new Vector3<Float>(x, y, 0f));
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

    public boolean ateTarget() {
        Vector3<Integer> targetPositionInInt = new Vector3<>(target.getPosition().getX().intValue(), target.getPosition().getY().intValue(), target.getPosition().getZ().intValue());
        Vector3<Integer> thisPositionInInt = new Vector3<>(getPosition().getX().intValue(), getPosition().getY().intValue(), getPosition().getZ().intValue());
        return targetPositionInInt.equals(thisPositionInInt);
    }
}
