package net.demomaker.applegame.engine.util;

import net.demomaker.applegame.engine.graphics.GameWindow;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.IOException;
import java.net.URL;

public class AdvancedImage extends Image {
  private static final Toolkit defaultToolkit = Toolkit.getDefaultToolkit();
  private URL url;
  private BufferedImage bufferedImage = null;
  private Image image = null;
  private String path = "";

  private AdvancedImage(URL url) {
    this.url = url;
    try {
      bufferedImage = ImageIO.read(url);
    } catch (IOException e) {
      e.printStackTrace();
    }
    this.image = defaultToolkit.createImage(url);
  }

  private AdvancedImage(int width, int height) {
    bufferedImage = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
    url = null;
  }

  private void setPath(String path) {
    this.path = path;
  }

  public String getPath() {
    return this.path;
  }

  public URL getUrl() {
    return this.url;
  }

  public BufferedImage getBufferedImage() {
    return this.bufferedImage;
  }

  public static AdvancedImage createImageFromPath(String path) {
    AdvancedImage advancedImage = new AdvancedImage(resource(path));
    advancedImage.setPath(path);
    return advancedImage;
  }

  public static AdvancedImage createImageFromWidthAndHeight(int width, int height) {
    AdvancedImage advancedImage = new AdvancedImage(width, height);
    advancedImage.setPath("");
    return advancedImage;
  }

  private static URL resource(String path) {
    return ResourceFinder.getResource(path);
  }

  @Override
  public int getWidth(ImageObserver observer) {
    return bufferedImage.getWidth();
  }

  @Override
  public int getHeight(ImageObserver observer) {
    return bufferedImage.getHeight();
  }

  @Override
  public ImageProducer getSource() {
    return image.getSource();
  }

  @Override
  public Graphics getGraphics() {
    return image.getGraphics();
  }

  @Override
  public Object getProperty(String name, ImageObserver observer) {
    return image.getProperty(name, observer);
  }
}
