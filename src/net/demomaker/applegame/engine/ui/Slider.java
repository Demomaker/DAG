package net.demomaker.applegame.engine.ui;

import net.demomaker.applegame.engine.input.Mouseboard;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.engine.ui.button.MovingButton;

import java.awt.*;

public class Slider extends UIElement {
    private int sliderValue = 50;
    private final MovingButton movingButton = new MovingButton(null);
    private int minValue = 0;
    private int maxValue = 0;
    private Image sliderBackgroundImage = null;
    private Image sliderForegroundImage = null;

    public void setMinValue(int minValue) {
        this.minValue = minValue;
    }

    public void setMaxValue(int maxValue) {
        this.maxValue = maxValue;
    }

    public int getMinValue() {
        return minValue;
    }

    public int getMaxValue() {
        return maxValue;
    }

    public void setSliderValue(int value) {
        this.sliderValue = value;
        int xValue = (int)(value * getXByValue());
        if(0 <= xValue && xValue <= getSize().getX() - movingButton.getSize().getX())
            movingButton.getPosition().setX(getPosition().getX() + xValue);
    }

    public int getSliderValue() {
        return sliderValue;
    }

    public float getValueByX() {
        return (maxValue - minValue) / (getSize().getX() - movingButton.getSize().getX());
    }

    public float getXByValue() {
        return (getSize().getX() - movingButton.getSize().getX()) / (maxValue - minValue);
    }

    public float getDifferenceInXFromSliderCenter(int x) {
        float differenceInX = 0;
        if(isOnLeftSideOfSlider(x)) {
            differenceInX = movingButton.getSize().getX() / 2 - (x - (getPosition().getX() + movingButton.getPosition().getX()));
        }
        else if(isOnRightSideOfSlider(x)) {
            differenceInX = (x - (getPosition().getX() + movingButton.getPosition().getX() + movingButton.getSize().getX() / 2)) - movingButton.getSize().getX() / 2;
        }
        return differenceInX;
    }

    public void SlideToTheLeft(int amountOfMovement) {
        setSliderValue((int)(sliderValue - (getValueByX() * amountOfMovement)));
    }

    public void SlideToTheRight(int amountOfMovement) {
        setSliderValue((int)(sliderValue + (getValueByX() * amountOfMovement)));
    }

    public boolean isOnLeftSideOfSlider(int x) {
        return x <= movingButton.getPosition().getX() + movingButton.getSize().getX() / 2 &&
                x >= movingButton.getPosition().getX();
    }

    public boolean isOnRightSideOfSlider(int x) {
        return x >= movingButton.getPosition().getX() + movingButton.getSize().getX() / 2 &&
                x <= movingButton.getPosition().getX() + movingButton.getSize().getX();
    }

    public float getSliderX() {
        return movingButton.getPosition().getX();
    }

    public float getSliderY() {
        return movingButton.getPosition().getY();
    }

    public Vector3<Float> getSliderSize() {
        return movingButton.getSize();
    }

    public void setSliderImage(Image image) {
        movingButton.setImage(image);
    }

    public void setSliderBackgroundImage(Image image) {
        sliderBackgroundImage = image;
    }

    public boolean sliderPressed() {
        return Mouseboard.mousePressedUIElement(movingButton);
    }

    public void setSliderPosition(Vector3<Float> position) {
        movingButton.setPosition(position);
    }

    public void setSliderSize(Vector3<Float> size) {
        movingButton.setSize(size);
    }

    public void setSliderForegroundImage(Image image) {
        sliderForegroundImage = image;
    }

    @Override
    public void draw(Graphics g) {
        g.drawImage(sliderBackgroundImage, getPosition().getX().intValue(), getPosition().getY().intValue(), null);
        g.drawImage(sliderForegroundImage, movingButton.getPosition().getX().intValue(), movingButton.getPosition().getY().intValue(), null);
        movingButton.draw(g);
    }

    @Override
    public void update() {
    }
}
