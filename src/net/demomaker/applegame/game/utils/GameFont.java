package net.demomaker.applegame.game.utils;

import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.AssetRetreiver;

import java.awt.*;

public class GameFont {
  public static final AdvancedImage ZERO = AssetRetreiver.getImageFromPath("/resources/Zero.png");
  public static final AdvancedImage ONE = AssetRetreiver.getImageFromPath("/resources/One.png");
  public static final AdvancedImage TWO = AssetRetreiver.getImageFromPath("/resources/Two.png");
  public static final AdvancedImage THREE = AssetRetreiver.getImageFromPath("/resources/Three.png");
  public static final AdvancedImage FOUR = AssetRetreiver.getImageFromPath("/resources/Four.png");
  public static final AdvancedImage FIVE = AssetRetreiver.getImageFromPath("/resources/Five.png");
  public static final AdvancedImage SIX = AssetRetreiver.getImageFromPath("/resources/Six.png");
  public static final AdvancedImage SEVEN = AssetRetreiver.getImageFromPath("/resources/Seven.png");
  public static final AdvancedImage EIGHT = AssetRetreiver.getImageFromPath("/resources/Eight.png");
  public static final AdvancedImage NINE = AssetRetreiver.getImageFromPath("/resources/Nine.png");

  public static AdvancedImage getSymbolFromCharacter(char character) {
    return switch (character) {
      case '1' -> GameFont.ONE;
      case '2' -> GameFont.TWO;
      case '3' -> GameFont.THREE;
      case '4' -> GameFont.FOUR;
      case '5' -> GameFont.FIVE;
      case '6' -> GameFont.SIX;
      case '7' -> GameFont.SEVEN;
      case '8' -> GameFont.EIGHT;
      case '9' -> GameFont.NINE;
      default -> GameFont.ZERO;
    };
  }

  // Transformer les nombres en images
  public static AdvancedImage[] getSymbolsfromNumber(int number) {
    int numCharactersNeeded = 3;
    char[] numberToast;
    String numberInString = String.valueOf(number);
    numberToast = numberInString.toCharArray();
    char[] numberCharacters = new char[numCharactersNeeded];
    for (int i = 0; i < numberCharacters.length; i++) {
      if (numberToast.length > i) {
        numberCharacters[i] = numberToast[numberToast.length - 1 - i];
      }
    }
    AdvancedImage[] numberImages;
    if (number != 0) {
      numberImages = new AdvancedImage[numberCharacters.length];
    } else {
      numberImages = new AdvancedImage[numCharactersNeeded];
    }

    for (int i = 0; i < numberCharacters.length; i++) {
      numberImages[i] = getSymbolFromCharacter(numberCharacters[i]);
    }

    if (numCharactersNeeded > numberToast.length) {
      for (int i = numberToast.length; i < numCharactersNeeded; i++) {
        numberImages[i] = ZERO;
      }
    }
    if (number == 0) {
      for (int i = 0; i < numCharactersNeeded; i++) {
        numberImages[i] = ZERO;
      }
    }
    return numberImages;

  }
}
