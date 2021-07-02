package net.demomaker.applegame.engine.graphics;

import net.demomaker.applegame.engine.scene.SceneManager;
import net.demomaker.applegame.game.shop.ShopActQueue;

import java.util.ArrayList;

public class GameWindow {
  private static int width = 0;
  private static int height = 0;
  private static int fps = 60;
  private static int updates = 60;
  private static String name = "";
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
    for(GameWindowListener windowListener : windowListenerList) {
      windowListener.onFPSSet();
    }
  }

  public static int getFPS() {
    return fps;
  }

  public static void setUpdates(int updates) {
    GameWindow.updates = updates;
    for(GameWindowListener windowListener : windowListenerList) {
      windowListener.onUpdatesSet();
    }
  }

  public static int getUpdates() {
    return updates;
  }

  public static void close() {
    for (GameWindowListener windowListener : windowListenerList) {
      windowListener.onClose();
    }
  }

  public static void start() {
    for (GameWindowListener windowListener : windowListenerList) {
      windowListener.onStart();
    }
  }

  public static void fullscreen() {
    for (GameWindowListener windowListener : windowListenerList) {
      windowListener.onFullScreen();
    }
  }

  public static void setName(String name) {
    GameWindow.name = name;
  }

  public static String getName() {
    return GameWindow.name;
  }

  public static abstract class GameWindowListener {
    public GameWindowListener() { addWindowListener(this); }
    public abstract void onClose();
    public abstract void onStart();
    public abstract void onFullScreen();
    public abstract void onFPSSet();
    public abstract void onUpdatesSet();
  }

  public static void addWindowListener(GameWindowListener windowListener) {
    GameWindow.windowListenerList.add(windowListener);
  }
}
