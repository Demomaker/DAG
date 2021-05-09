package net.demomaker.applegame.engine.ui.button;

import net.demomaker.applegame.engine.ui.UIElement;
import net.demomaker.applegame.game.controller.DemomakerGame;

import java.awt.*;

public class Button extends UIElement {
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
    }

    public void press() {
        buttonState = ButtonState.PRESSED;
        timeOfLastInteractionInMilliseconds = System.currentTimeMillis();
    }

    public void normal() {
        buttonState = ButtonState.NORMAL;
    }

    public void release() {
        buttonState = ButtonState.RELEASED;
        timeOfLastInteractionInMilliseconds = System.currentTimeMillis();
    }

    @Override
    public void draw(Graphics g){
        updateImage();
        if(imageToShow != null && show)
            g.drawImage(imageToShow, getPosition().getX().intValue(), getPosition().getY().intValue(), getSize().getX().intValue(), getSize().getY().intValue(), demomakerGame);
    }

    @Override
    public void update() {
        if(buttonState == ButtonState.PRESSED && System.currentTimeMillis() - timeOfLastInteractionInMilliseconds > TIME_IN_MILLISECONDS_BEFORE_END_OF_INTERACTION) {
            release();
        }

        else if(buttonState == ButtonState.RELEASED && System.currentTimeMillis() - timeOfLastInteractionInMilliseconds > TIME_IN_MILLISECONDS_BEFORE_END_OF_INTERACTION) {
            normal();
        }

        else {
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


    public void show() {
        show = true;
    }
    public void hide() {
        show = false;
    }
    public boolean isVisible() {
        return show;
    }
    public ButtonState getButtonState() { return buttonState; }

    private void updateImage() {
        if(buttonState == ButtonState.NORMAL && buttonNormalStateImage != null) {
            imageToShow = buttonNormalStateImage;
        }
        else if(buttonState == ButtonState.RELEASED && buttonReleasedStateImage != null) {
            imageToShow = buttonReleasedStateImage;
        }
        else if(buttonState == ButtonState.PRESSED && buttonPressedStateImage != null) {
            imageToShow = buttonPressedStateImage;
        }
    }
}
