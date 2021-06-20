package net.demomaker.applegame.engine.graphics;

import net.demomaker.applegame.engine.scene.SceneManager;

public class GameWindow {
  private static int width = 0;
  private static int height = 0;

  public static void setWidth(int width) {
    GameWindow.width = width;
    if(SceneManager.getActiveScene() != null)
    SceneManager.getActiveScene().onWindowResize();
  }

  public static void setHeight(int height) {
    GameWindow.height = height;
    if(SceneManager.getActiveScene() != null)
    SceneManager.getActiveScene().onWindowResize();
  }

  public static int getWidth() {
    return width;
  }

  public static int getHeight() {
    return height;
  }
}
