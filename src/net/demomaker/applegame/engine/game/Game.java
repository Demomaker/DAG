package net.demomaker.applegame.engine.game;

import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.scene.SceneManager;

import java.awt.*;

public abstract class Game extends Canvas implements Runnable {
  private Thread thread;
  private boolean running = false;
  public abstract void initGame();
  public abstract void renderGame();
  public abstract void updateGame(float deltaTime);
  public void goFullScreen() {}
  public void updateFPS() {}
  public void updateUpdates() {}
  public void startGame() {}
  public void endGame() {}
  public void onException(Exception exception) {}
  private void runGame() {
    long Timer = System.currentTimeMillis();
    long lastTime = System.nanoTime();
    int frames = 0;
    int updates = 0;
    final double ns = 1000000000.0 / 60.0;
    double delta = 0;
    int oneSecond = 1000;

    while (running) {
      if (!SceneManager.activeSceneFinishedLoading()) continue;
      long now = System.nanoTime();
      delta += (now - lastTime) / ns;
      lastTime = now;
      while (delta >= 1) {
        updateGame((float) delta);
        updates++;
        delta--;
      }

      renderGame();
      frames++;
      if (System.currentTimeMillis() - Timer > oneSecond) {
        Timer += oneSecond;
        GameWindow.setFPS(frames);
        GameWindow.setUpdates(updates);
        updates = 0;
        frames = 0;
      }

    }
  }
  public void run() {
    try {
      initGame();
      runGame();
      stop();
    }
    catch (Exception e) {
      onException(e);
      e.printStackTrace();
    }
  }

  public synchronized void start() {
    running = true;
    thread = new Thread(this, "Display");
    thread.start();
  }

  public synchronized void stop() {
    running = false;
    try {
      thread.join();
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

  private GameWindow.GameWindowListener gameWindowListener = new GameWindow.GameWindowListener() {
    @Override
    public void onClose() {
      endGame();
    }

    @Override
    public void onStart() {
      startGame();
    }

    @Override
    public void onFullScreen() {
      goFullScreen();
    }

    @Override
    public void onFPSSet() {
      updateFPS();
    }

    @Override
    public void onUpdatesSet() {
      updateUpdates();
    }
  };
}
