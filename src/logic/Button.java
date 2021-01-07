package logic;

import controller.DemomakerGame;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Button {
    private static List<Button> buttons = new ArrayList<>();
    protected Vector3<Float> position;
    protected Vector3<Float> size;
    protected ButtonState buttonState = ButtonState.NORMAL;
    protected Image buttonNormalStateImage = null;
    protected Image buttonPressedStateImage = null;
    protected Image buttonReleasedStateImage = null;
    protected final static long TIME_IN_MILLISECONDS_BEFORE_END_OF_INTERACTION = 100;
    protected long timeOfLastInteractionInMilliseconds = 0;
    protected DemomakerGame demomakerGame;
    protected Image imageToShow;
    protected boolean show = true;

    public Button() { this(null); }
    public Button(DemomakerGame demomakerGame) {
        this.demomakerGame = demomakerGame;
        addToList();
    }

    public boolean isShown() { return show; }
    public void addToList() { buttons.add(this); }
    public void removeFromList() { buttons.remove(this); }
    public static List<Button> getAllButtons() { return buttons; }

    public boolean touchesObjectAt(Vector3<Float> position) {
        return position.getX() >= this.position.getX()
                && position.getX() <= this.position.getX() + this.size.getX()
                && position.getY() >= this.position.getY()
                && position.getY() <= this.position.getY() + this.size.getY();
    }

    public void press() {
        imageToShow = buttonPressedStateImage;
        buttonState = ButtonState.PRESSED;
        timeOfLastInteractionInMilliseconds = System.currentTimeMillis();
    }

    public void normal() {
        imageToShow = buttonNormalStateImage;
        buttonState = ButtonState.NORMAL;
    }

    public void release() {
        imageToShow = buttonReleasedStateImage;
        buttonState = ButtonState.RELEASED;
        timeOfLastInteractionInMilliseconds = System.currentTimeMillis();
    }

    public void draw(Graphics g){
        if(imageToShow != null && show)
            g.drawImage(imageToShow, position.getX().intValue(), position.getY().intValue(), size.getX().intValue(), size.getY().intValue(), demomakerGame);
    }

    public void update() {
        if(buttonState != ButtonState.NORMAL && System.currentTimeMillis() - timeOfLastInteractionInMilliseconds > TIME_IN_MILLISECONDS_BEFORE_END_OF_INTERACTION) {
            normal();
        }
    }

    public void setButtonNormalStateImage(Image image) {
        buttonNormalStateImage = image;
    }

    public void setButtonPressedStateImage(Image image) {
        buttonPressedStateImage = image;
    }

    public void setButtonReleasedStateImage(Image image){
        buttonReleasedStateImage = image;
    }

    public void setPosition(Vector3<Float> position) {
        this.position = position;
    }

    public Vector3<Float> getPosition() {
        return position;
    }

    public void setSize(Vector3<Float> size) {
        this.size = size;
    }

    public Vector3<Float> getSize() {
        return size;
    }

    public void setX(float x) {
        setPosition(new Vector3<>(x, position.getY(), position.getZ()));
    }

    public void setY(float y) {
        setPosition(new Vector3<>(position.getX(), y, position.getZ()));
    }

    public void setZ(float z) {
        setPosition(new Vector3<>(position.getX(), position.getY(), z));
    }

    public void MoveXBy(float difference){
        setPosition(new Vector3<>(position.getX() + difference, position.getY(), position.getZ()));
    }

    public void MoveYBy(float difference){
        setPosition(new Vector3<>(position.getX(), position.getY() + difference, position.getZ()));
    }

    public void show() {
        show = true;
    }
    public void hide() {
        show = false;
    }
    public ButtonState getButtonState() { return buttonState; }
}
