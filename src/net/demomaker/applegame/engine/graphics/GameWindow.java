package net.demomaker.applegame.engine.graphics;

import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.game.shop.ShopActQueue;

import java.util.ArrayList;

public class GameWindow {
  private static int width = 0;
  private static int height = 0;
  private static int fps = 60;
  private static ArrayList<GameWindowListener> windowListenerList = new ArrayList<>();

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

  public static void setFPS(int frames) {
    fps = frames;
  }

  public static int getFPS() {
    return fps;
  }

  public static void close() {
    for (GameWindowListener windowListener : windowListenerList) {
      windowListener.onClose();
    }
  }

  public static abstract class GameWindowListener {
    public GameWindowListener() { addWindowListener(this); }
    public abstract void onClose();
  }

  public static void addWindowListener(GameWindowListener windowListener) {
    GameWindow.windowListenerList.add(windowListener);
  }
}
