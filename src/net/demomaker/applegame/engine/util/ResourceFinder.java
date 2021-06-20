package net.demomaker.applegame.engine.util;

import java.net.URL;

public class ResourceFinder {
  private static Object rootObject = null;
  public static void setRoot(Object object) {
    rootObject = object;
  }

  public static URL getResource(String path) {
    return rootObject.getClass().getResource(path);
  }
}
