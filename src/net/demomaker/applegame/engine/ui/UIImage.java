package net.demomaker.applegame.engine.ui;

import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.ImageObserver;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;

public class UIImage extends UIElement {
    private AdvancedImage image;
    public void setImage(AdvancedImage image) {
        this.image = image;
        this.setSize(new Vector3<>( (float) image.getWidth(ImageObserver.getImageObserver()), (float) image.getHeight(ImageObserver.getImageObserver()),0f));
    }
    public void draw() {
        GraphicsManager.drawImage(image, getPosition());
    }
}
