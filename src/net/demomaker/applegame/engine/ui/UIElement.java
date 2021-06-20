package net.demomaker.applegame.engine.ui;

import net.demomaker.applegame.engine.object.Transform;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;

public class UIElement {
    protected Transform transform = new Transform();

    public void draw(){
    }

    public void update() {
    }

    public boolean touchesObjectAt(Vector3<Float> position) {
        return position.getX() >= this.getPosition().getX()
                && position.getX() <= this.getPosition().getX() + this.getSize().getX()
                && position.getY() >= this.getPosition().getY()
                && position.getY() <= this.getPosition().getY() + this.getSize().getY();
    }

    public void setPosition(Vector3<Float> position) {
        transform.setPosition(position);
    }

    public void setPositionRelativeToParentTransform(Vector3<Float> position) {
        transform.setPositionRelativeToParent(position);
    }

    public Vector3<Float> getPositionRelativeToParentTransform() {
        return transform.getPositionRelativeToParent();
    }

    public Vector3<Float> getPosition() {
        return transform.getPosition();
    }

    public void setSize(Vector3<Float> size) {
        transform.setSize(size);
    }

    public Vector3<Float> getSize() {
        return transform.getSize();
    }

    public void setX(float x) {
        setPosition(new Vector3<>(x, getPosition().getY(), getPosition().getZ()));
    }

    public void setY(float y) {
        setPosition(new Vector3<>(getPosition().getX(), y, getPosition().getZ()));
    }

    public void setZ(float z) {
        setPosition(new Vector3<>(getPosition().getX(), getPosition().getY(), z));
    }

    public void MoveXBy(float difference){
        setPosition(new Vector3<>(getPosition().getX() + difference, getPosition().getY(), getPosition().getZ()));
    }

    public void MoveYBy(float difference){
        setPosition(new Vector3<>(getPosition().getX(), getPosition().getY() + difference, getPosition().getZ()));
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }
}
