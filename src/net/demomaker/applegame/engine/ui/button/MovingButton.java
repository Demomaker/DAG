package net.demomaker.applegame.engine.ui.button;

import net.demomaker.applegame.game.controller.DemomakerGame;

import java.awt.*;

public class MovingButton extends Button {
    public MovingButton() {
        super();
    }
    public MovingButton(DemomakerGame demomakerGame) {
        super(demomakerGame);
    }

    public void setImage(Image image) {
        imageToShow = image;
    }

    @Override
    public void press() {
        //Do nothing
    }

    @Override
    public void normal() {
        //Do nothing
    }

    @Override
    public void release() {
        //Do nothing
    }
}
