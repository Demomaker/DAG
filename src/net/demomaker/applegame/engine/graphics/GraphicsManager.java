package net.demomaker.applegame.engine.graphics;

import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.*;

public class GraphicsManager {

  private static DrawInterface drawInterface;

  public static void init(DrawInterface drawInterface) {
    GraphicsManager.drawInterface = drawInterface;
  }

  public static void fillRectangle(Color color, int width, int height, Vector3<Float> position) {
    drawInterface.fillRectangle(color, width, height, position);
  }

  public static void outlineRectangle(Color color, int width, int height, Vector3<Float> position) {
    drawInterface.outlineRectangle(color, width, height, position);
  }

  public static void drawString(Color color, String string, Vector3<Float> position) {
    drawInterface.drawString(color, string, position);
  }

  public static void drawImage(AdvancedImage advancedImage, Vector3<Float> position) {
    drawInterface.drawImage(advancedImage, position);
  }

  public static void drawImage(AdvancedImage advancedImage, int width, int height, Vector3<Float> position) {
    drawInterface.drawImage(advancedImage, width, height, position);
  }

  public static void cleanup() {
    drawInterface.cleanup();
  }
}
