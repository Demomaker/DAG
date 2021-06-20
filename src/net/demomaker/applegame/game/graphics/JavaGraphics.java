package net.demomaker.applegame.game.graphics;

import net.demomaker.applegame.engine.graphics.DrawInterface;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.ImageObserver;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;

public class JavaGraphics implements DrawInterface {
  private Graphics graphics = null;
  public void setGraphics(Graphics graphics) {
    this.graphics = graphics;
  }

  @Override
  public void fillRectangle(Color color, int width, int height, Vector3<Float> position) {
    graphics.setColor(color);
    graphics.fillRect(position.getX().intValue(), position.getY().intValue(), width, height);
  }

  @Override
  public void outlineRectangle(Color color, int width, int height, Vector3<Float> position) {

  }

  @Override
  public void drawImage(AdvancedImage advancedImage, Vector3<Float> position) {
    graphics.drawImage(advancedImage.getBufferedImage(), position.getX().intValue(), position.getY().intValue(), ImageObserver.getImageObserver());
  }

  @Override
  public void drawImage(AdvancedImage advancedImage, int width, int height, Vector3<Float> position) {
    graphics.drawImage(advancedImage.getBufferedImage(), position.getX().intValue(), position.getY().intValue(), width, height, ImageObserver.getImageObserver());
  }

  @Override
  public void drawString(Color color, String string, Vector3<Float> position) {
    graphics.setColor(color);
    graphics.drawString(string, position.getX().intValue(), position.getY().intValue());
  }

  @Override
  public void cleanup() {
    graphics.dispose();
  }
}
