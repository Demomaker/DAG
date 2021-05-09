package net.demomaker.applegame.engine.ui.button;

import net.demomaker.applegame.game.controller.DemomakerGame;

import java.awt.*;

public class CheckBox extends Button {
    private boolean checked = false;
    private Image checkedImage = null;
    private Image uncheckedImage = null;
    private boolean initialStateDone = false;
    public CheckBox() {
        super();
    }
    public CheckBox(DemomakerGame demomakerGame) {
        super(demomakerGame);
    }
    public void Check() {
        checked = true;
        imageToShow = checkedImage;
    }
    public void Uncheck() {
        checked = false;
        imageToShow = uncheckedImage;
    }
    public boolean isChecked() {
        return checked;
    }

    @Override
    public void normal() {
        //Do nothing
    }

    @Override
    public void press() {
        if(checked) Uncheck();
        else Check();
    }

    @Override
    public void release() {
        //Do nothing
    }

    @Override
    public void update() {
        //Do nothing
    }

    public void setCheckedImage(Image checkedImage) {
        this.checkedImage = checkedImage;
    }

    public void setUncheckedImage(Image uncheckedImage) {
        this.uncheckedImage = uncheckedImage;
        if(!initialStateDone) {
            initialStateDone = true;
            Uncheck();
        }
    }
}
