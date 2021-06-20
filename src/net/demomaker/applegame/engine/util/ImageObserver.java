package net.demomaker.applegame.engine.util;

public class ImageObserver {
  private static java.awt.image.ImageObserver imageObserver = null;

  public static java.awt.image.ImageObserver getImageObserver() {
    return imageObserver;
  }

  public static void setImageObserver(java.awt.image.ImageObserver imageObserver) {
    ImageObserver.imageObserver = imageObserver;
  }
}
