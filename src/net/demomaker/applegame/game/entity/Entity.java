package net.demomaker.applegame.game.entity;

import net.demomaker.applegame.engine.object.Body;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;

public class Entity {
    private Body entityBody = new Body();

    public Color getColor() {
        return entityBody.getColor();
    }

    public void setColor(Color color) {
        entityBody.setColor(color);
    }

    public void setPosition(Vector3<Float> position) {
        this.entityBody.getTransform().setPosition(position);
    }
    public void setSize(float width, float height) {
        this.entityBody.getTransform().setSize(new Vector3<>(width, height, 0f));
        this.entityBody.getBox().setOuterBorderX((int) width);
        this.entityBody.getBox().setOuterBorderY((int) height);
    }
    public float getWidth() {
        return this.entityBody.getTransform().getSize().getX();
    }
    public float getHeight() {
        return this.entityBody.getTransform().getSize().getY();
    }
    public Vector3<Float> getPosition() {
        return this.entityBody.getTransform().getPosition();
    }
    public Vector3<Float> getTopLeftCorner() { return entityBody.getBox().getTopLeftCorner(); }
    public Vector3<Float> getBottomRightCorner() { return entityBody.getBox().getBottomRightCorner(); }

    public void draw() {

    }

    public void MoveXBy(float xMovement) {
        setPosition(new Vector3<Float>(getPosition().getX() + xMovement, getPosition().getY(), getPosition().getZ()));
    }

    public void MoveYBy(float yMovement) {
        setPosition(new Vector3<Float>(getPosition().getX(), getPosition().getY() + yMovement, getPosition().getZ()));
    }

    public void update() {

    }

    public void onCollisionWith(Entity other) {

    }

    public boolean collidedWith(Entity other) {
        return inOther(other) || otherInThis(other);
    }

    public boolean exactlyIn(Entity other) {
        return getWidth() == other.getWidth() && getHeight() == other.getHeight() && getPosition() == other.getPosition();
    }

    private boolean inOther(Entity other) {
        return entityInOtherEntity(this, other);
    }

    private boolean otherInThis(Entity other) {
        return entityInOtherEntity(other, this);
    }

    private static boolean entityInOtherEntity(Entity firstEntity, Entity secondEntity) {
        Vector3<Float> firstEntityPosition = firstEntity.getPosition();
        float firstEntityWidth = firstEntity.getWidth();
        float firstEntityHeight = firstEntity.getHeight();
        Vector3<Float> firstCornerPosition = firstEntityPosition;
        Vector3<Float> secondCornerPosition = new Vector3<>(firstEntityPosition.getX() + firstEntityWidth, firstCornerPosition.getY(), firstEntityPosition.getZ());
        Vector3<Float> thirdCornerPosition = new Vector3<>(firstEntityPosition.getX() + firstEntityWidth, firstCornerPosition.getY() + firstEntityHeight, firstEntityPosition.getZ());
        Vector3<Float> fourthCornerPosition = new Vector3<>(firstEntityPosition.getX(), firstCornerPosition.getY() + firstEntityHeight, firstEntityPosition.getZ());
        return collideInCorner(firstCornerPosition, secondEntity) || collideInCorner(secondCornerPosition, secondEntity) || collideInCorner(thirdCornerPosition, secondEntity) || collideInCorner(fourthCornerPosition, secondEntity);
    }

    private static boolean collideInCorner(Vector3<Float> position, Entity secondEntity) {
        return position.getX() > secondEntity.getPosition().getX() && position.getX() < position.getX() + secondEntity.getWidth()
                && position.getY() > secondEntity.getPosition().getY() && position.getY() < position.getY() + secondEntity.getHeight();
    }

}
