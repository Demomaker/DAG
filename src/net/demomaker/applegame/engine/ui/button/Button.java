package net.demomaker.applegame.engine.ui.button;
import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.input.Mouseboard;
import net.demomaker.applegame.engine.ui.UIElement;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.ImageObserver;

import java.awt.*;
import java.awt.event.MouseEvent;
import java.util.ArrayList;

public class Button extends UIElement {
    private static ArrayList<ButtonListener> buttonListenerList = new ArrayList();
    protected ButtonState buttonState = ButtonState.NORMAL;
    protected AdvancedImage buttonNormalStateImage = null;
    protected AdvancedImage buttonPressedStateImage = null;
    protected AdvancedImage buttonReleasedStateImage = null;
    protected final static long TIME_IN_MILLISECONDS_BEFORE_END_OF_INTERACTION = 100;
    protected long timeOfLastInteractionInMilliseconds = 0;
    protected AdvancedImage imageToShow;
    protected boolean show = true;
    protected boolean isActive = true;
    private int lastMouseX = 0;
    private int lastMouseY = 0;
    private Button button = this;

    protected void click() {
        this.buttonState = ButtonState.CLICKED;
        this.timeOfLastInteractionInMilliseconds = System.currentTimeMillis();
    }

    protected void press() {
        this.buttonState = ButtonState.PRESSED;
    }

    protected void normal() {
        this.buttonState = ButtonState.NORMAL;
    }

    protected void release() {
        this.buttonState = ButtonState.RELEASED;
        this.timeOfLastInteractionInMilliseconds = System.currentTimeMillis();
    }

    protected void hover() {
        this.buttonState = ButtonState.HOVERED;
    }

    @Override
    public void draw(){
        selectImage();
        if(this.isActive)
            this.update();
        if(this.imageToShow != null && this.show)
            GraphicsManager.drawImage(this.imageToShow, this.getSize().getX().intValue(), this.getSize().getY().intValue(), new Vector3<Float>(this.getPosition().getX(), this.getPosition().getY(), 0f));
    }

    @Override
    public void update() {
        if(this.isClicked() && System.currentTimeMillis() - this.timeOfLastInteractionInMilliseconds > TIME_IN_MILLISECONDS_BEFORE_END_OF_INTERACTION) {
            this.release();
        }
        else if(this.isReleased() && System.currentTimeMillis() - this.timeOfLastInteractionInMilliseconds > TIME_IN_MILLISECONDS_BEFORE_END_OF_INTERACTION) {
            this.normal();
        }
        else if(this.isHovered() && !this.isPressed()) {
            if(!this.touchesObjectAt(new Vector3<>(lastMouseX * 1.0f, lastMouseY * 1.0f, 0f))) {
                this.release();
            }
        }
    }

    public boolean isClicked() { return this.buttonState == ButtonState.CLICKED; }
    public boolean isPressed() {
        return this.buttonState == ButtonState.PRESSED;
    }
    public boolean isHovered() { return this.buttonState == ButtonState.HOVERED; }
    public boolean isReleased() { return this.buttonState == ButtonState.RELEASED; }
    public boolean isInteracted() { return !(this.buttonState == ButtonState.NORMAL); }
    public boolean isShown() { return imageToShow != null && show; }

    public void setButtonNormalStateImage(AdvancedImage image) {
        buttonNormalStateImage = image;
        this.setSize(new Vector3<>( (float) image.getWidth(ImageObserver.getImageObserver()), (float) image.getHeight(ImageObserver.getImageObserver()),0f));
    }

    public void setButtonPressedStateImage(AdvancedImage image) {
        buttonPressedStateImage = image;
        this.setSize(new Vector3<>( (float) image.getWidth(ImageObserver.getImageObserver()), (float) image.getHeight(ImageObserver.getImageObserver()),0f));
    }

    public void setButtonReleasedStateImage(AdvancedImage image){
        buttonReleasedStateImage = image;
        this.setSize(new Vector3<>( (float) image.getWidth(ImageObserver.getImageObserver()), (float) image.getHeight(ImageObserver.getImageObserver()),0f));
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
    public void setActive(boolean isActive) { this.isActive = isActive;}
    public ButtonState getButtonState() { return buttonState; }

    private void selectImage() {
        if(!this.isInteracted() && this.buttonNormalStateImage != null) {
            this.imageToShow = this.buttonNormalStateImage;
        }
        else if(this.isReleased() && this.buttonReleasedStateImage != null) {
            this.imageToShow = this.buttonReleasedStateImage;
        }
        else if((this.isPressed() || this.isClicked()) && this.buttonPressedStateImage != null) {
            this.imageToShow = this.buttonPressedStateImage;
        }
    }

    private Mouseboard.MouseboardListener mouseboardListener = new Mouseboard.MouseboardListener() {
        @Override
        public void onMouseMoved(MouseEvent mouse) {
            lastMouseX = mouse.getX();
            lastMouseY = mouse.getY();
            if(!button.isActive || !button.touchesObjectAt(new Vector3<>(mouse.getX() * 1.0f, mouse.getY() * 1.0f, 0f))) {
                return;
            }

            if(button.isInteracted() && !button.isHovered()) return;

            button.hover();
            for(ButtonListener buttonListener : buttonListenerList) {
                buttonListener.onHover(button);
            }
        }

        @Override
        public void onMouseClicked(MouseEvent mouse) {
            if(!button.isActive || !button.touchesObjectAt(new Vector3<>(mouse.getX() * 1.0f, mouse.getY() * 1.0f, 0f))) return;
            if(button.isPressed()) return;
            button.click();
            for(ButtonListener buttonListener : buttonListenerList) {
                buttonListener.onClick(button);
            }
        }

        @Override
        public void onMousePressed(MouseEvent mouse) {
            if(!button.isActive || !button.touchesObjectAt(new Vector3<>(mouse.getX() * 1.0f, mouse.getY() * 1.0f, 0f))) return;
            button.press();
            for(ButtonListener buttonListener : buttonListenerList) {
                buttonListener.onPress(button);
            }
        }

        @Override
        public void onMouseReleased(MouseEvent mouse) {
            if(!button.isActive || button.isReleased()) return;
            if(!button.isInteracted()) return;
            button.release();
            for(ButtonListener buttonListener : buttonListenerList) {
                buttonListener.onRelease(button);
            }
        }
    };



    protected static void addButtonListener(ButtonListener buttonListener) {
        buttonListenerList.add(buttonListener);
    }
}

