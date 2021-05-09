package net.demomaker.applegame.engine.ui;

import java.awt.*;

public class UIImage extends UIElement {
    private Image image;
    public void setImage(Image image) { this.image = image;}
    public void draw(Graphics g) {
        g.drawImage(image, getPosition().getX().intValue(), getPosition().getY().intValue(), null);
    }
}
