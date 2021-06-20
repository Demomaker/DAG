package net.demomaker.applegame.engine.ui.button;

public abstract class ButtonListener {

  public ButtonListener() {
    Button.addButtonListener(this);
  }

  public abstract void onClick(Button button);

  public abstract void onPress(Button button);

  public abstract void onRelease(Button button);

  public abstract void onHover(Button button);
}
