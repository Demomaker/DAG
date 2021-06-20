package net.demomaker.applegame.engine.graphics;

import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;
import java.util.Vector;

public interface DrawInterface {
  void fillRectangle(Color color, int width, int height, Vector3<Float> position);
  void outlineRectangle(Color color, int width, int height, Vector3<Float> position);
  void drawImage(AdvancedImage advancedImage, Vector3<Float> position);
  void drawImage(AdvancedImage advancedImage, int width, int height, Vector3<Float> position);
  void drawString(Color color, String string, Vector3<Float> position);
  void cleanup();

}
