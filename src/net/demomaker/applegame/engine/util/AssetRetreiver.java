package net.demomaker.applegame.engine.util;

import net.demomaker.applegame.engine.util.AdvancedImage;

public class AssetRetreiver {
  public static AdvancedImage getImageFromPath(String path) {
    return AdvancedImage.createImageFromPath(path);
  }
}
