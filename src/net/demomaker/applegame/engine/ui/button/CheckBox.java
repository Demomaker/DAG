package net.demomaker.applegame.engine.ui.button;

import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.ImageObserver;
import net.demomaker.applegame.engine.util.Vector3;

public class CheckBox extends Button {
    private boolean isChecked = false;
    private AdvancedImage checkedImage = null;
    private AdvancedImage uncheckedImage = null;
    private boolean initialStateDone = false;

    public void check() {
        isChecked = true;
        imageToShow = checkedImage;
    }
    public void uncheck() {
        isChecked = false;
        imageToShow = uncheckedImage;
    }
    public boolean isChecked() {
        return isChecked;
    }
    public void setChecked(boolean checked) { if(checked) check(); else uncheck();}

    @Override
    protected void normal() {
        super.normal();
        //Do nothing
    }

    @Override
    protected void click() {
        super.click();
        if(isChecked) uncheck();
        else check();
    }

    @Override
    protected void release() {
        super.release();
        //Do nothing
    }

    @Override
    public void update() {
        super.update();
        //Do nothing
    }

    public void setCheckedImage(AdvancedImage checkedImage) {
        this.checkedImage = checkedImage;
        this.setSize(new Vector3<>( (float) checkedImage.getWidth(ImageObserver.getImageObserver()), (float) checkedImage.getHeight(ImageObserver.getImageObserver()),0f));
    }

    public void setUncheckedImage(AdvancedImage uncheckedImage) {
        this.uncheckedImage = uncheckedImage;
        this.setSize(new Vector3<>( (float) uncheckedImage.getWidth(ImageObserver.getImageObserver()), (float) uncheckedImage.getHeight(ImageObserver.getImageObserver()),0f));
        if(!initialStateDone) {
            initialStateDone = true;
            uncheck();
        }
    }
}
