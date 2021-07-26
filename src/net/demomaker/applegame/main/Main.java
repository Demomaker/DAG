package net.demomaker.applegame.main;

import net.demomaker.applegame.engine.graphics.GameWindow;

public class Main {
  public static void main(String[] args) {
    if(args.length > 0) {
      try {
        String gameClass = args[0];
        Class.forName(gameClass).getDeclaredConstructor().newInstance();
        GameWindow.start();
      }
      catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
