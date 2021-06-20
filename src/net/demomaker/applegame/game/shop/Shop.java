package net.demomaker.applegame.game.shop;

import net.demomaker.applegame.engine.graphics.GameWindow;
import net.demomaker.applegame.engine.graphics.GraphicsManager;
import net.demomaker.applegame.engine.ui.UIElement;
import net.demomaker.applegame.engine.ui.UIImage;
import net.demomaker.applegame.engine.ui.button.Button;
import net.demomaker.applegame.engine.ui.button.ButtonListener;
import net.demomaker.applegame.engine.ui.button.CheckBox;
import net.demomaker.applegame.engine.ui.button.MovingButton;
import net.demomaker.applegame.engine.util.Vector3;
import net.demomaker.applegame.game.entity.Entity;
import net.demomaker.applegame.engine.util.AdvancedImage;
import net.demomaker.applegame.engine.util.AssetRetreiver;

import java.awt.*;

public class Shop extends Entity {
  private static final int NUMBER_OF_BUTTON_COLUMNS = 3;
  private static final int NUMBER_OF_BUTTON_ROWS = 3;
  private static final float DEFAULT_BUTTON_WIDTH = 200f;
  private static final float DEFAULT_BUTTON_HEIGHT = 50f;
  private int Money = 2000;
  private boolean shopIsGoingUp = true;
  private boolean isActive = true;
  private int SHOP_BUTTON_Y_UPPER_LIMIT = 247;
  private int SHOP_BUTTON_Y_LOWER_LIMIT;
  private boolean finishedLoading = false;
  private Vector3[][] buttonInitialPositions = new Vector3[NUMBER_OF_BUTTON_COLUMNS][NUMBER_OF_BUTTON_ROWS];
  private int oldHeight = 0;

  private MovingButton shopButton;
  private Button appleBuyButton;
  private Button allyBuyButton;
  private Button moneyIncreaseButton;
  private Button resetRedButton;
  private CheckBox openShop;
  private CheckBox closeShop;
  private UIElement shop;

  private UIImage shopBackgroundUIImage = new UIImage();
  private UIImage resetAppleUIImage = new UIImage();
  private UIImage appleBuyImage = new UIImage();
  private UIImage moneyIncrementImage = new UIImage();
  private UIImage allyBuyImage = new UIImage();

  private final AdvancedImage ShopButton = AssetRetreiver.getImageFromPath("/resources/ShopButton.png");
  private final AdvancedImage ShopBackground = AssetRetreiver.getImageFromPath("/resources/ShopBackground.png");
  private final AdvancedImage RedDoteImage = AssetRetreiver.getImageFromPath("/resources/RedDoteImage.png");
  private final AdvancedImage RedDoteBuy = AssetRetreiver.getImageFromPath("/resources/RedDoteBuy.png");
  private final AdvancedImage RedDoteBuyHover = AssetRetreiver.getImageFromPath("/resources/RedDoteBuyHover.png");
  private final AdvancedImage RedDoteBuyAction = AssetRetreiver.getImageFromPath("/resources/RedDoteBuyAction.png");
  private final AdvancedImage MoneyAddImage = AssetRetreiver.getImageFromPath("/resources/MoneyAddImage.png");
  private final AdvancedImage MoneyAddBuy = AssetRetreiver.getImageFromPath("/resources/MoneyAddBuy.png");
  private final AdvancedImage MoneyAddBuyAction = AssetRetreiver.getImageFromPath("/resources/MoneyAddBuyAction.png");
  private final AdvancedImage MoneyAddBuyHover = AssetRetreiver.getImageFromPath("/resources/MoneyAddBuyHover.png");
  private final AdvancedImage YellowAutoImage = AssetRetreiver.getImageFromPath("/resources/YellowAutoImage.png");
  private final AdvancedImage YellowAddBuy = AssetRetreiver.getImageFromPath("/resources/YellowAddBuy.png");
  private final AdvancedImage YellowAddBuyAction = AssetRetreiver.getImageFromPath("/resources/YellowAddBuyAction.png");
  private final AdvancedImage YellowAddBuyHover = AssetRetreiver.getImageFromPath("/resources/YellowAddBuyHover.png");
  private final AdvancedImage Checkbox = AssetRetreiver.getImageFromPath("/resources/Checkbox.png");
  private final AdvancedImage Checker = AssetRetreiver.getImageFromPath("/resources/Checker.png");
  private final AdvancedImage RedReset = AssetRetreiver.getImageFromPath("/resources/RedReset.png");

  private static Shop instance = null;
  private Shop() {}

  public static Shop getInstance() {
    if(instance == null)
      instance = new Shop();
    return instance;
  }

  public boolean finishedLoading() {
    return finishedLoading;
  }

  public void init() {
    for(int i = 0; i < NUMBER_OF_BUTTON_COLUMNS; i++) {
      for(int j = 0; j < NUMBER_OF_BUTTON_ROWS; j++) {
        buttonInitialPositions[i][j] = new Vector3<>(10f + i * (10f + DEFAULT_BUTTON_WIDTH), 40f + j * (10 + DEFAULT_BUTTON_HEIGHT), 0f);
      }
    }
    allyBuyButton = new Button();
    appleBuyButton = new Button();
    moneyIncreaseButton = new Button();
    resetRedButton = new Button();
    shopButton = new MovingButton();
    openShop = new CheckBox();
    closeShop = new CheckBox();
    shop = new UIElement();
    shop.setSize(new Vector3<>((float)GameWindow.getWidth(), (float)GameWindow.getHeight(), 0f));
    shop.setPosition(new Vector3<>(0f, GameWindow.getHeight() - 69f, 0f));
    shopButton.setImage(ShopButton);
    shopButton.getTransform().setParent(shop.getTransform());
    shopButton.setPositionRelativeToParentTransform(new Vector3<>((GameWindow.getWidth() - 116) * 1.0f, 0f, 0f));
    shopBackgroundUIImage.setImage(ShopBackground);
    shopBackgroundUIImage.getTransform().setParent(shop.getTransform());
    shopBackgroundUIImage.setPositionRelativeToParentTransform(new Vector3<>(0f, shopButton.getSize().getY(), 0f));
    resetRedButton.setButtonReleasedStateImage(RedReset);
    resetRedButton.setButtonPressedStateImage(RedReset);
    resetRedButton.setButtonNormalStateImage(RedReset);
    resetRedButton.setSize(new Vector3<>(50f, 50f, 0f));
    resetRedButton.getTransform().setParent(shop.getTransform());
    resetRedButton.setPositionRelativeToParentTransform(new Vector3<>(765f, 40f, 0f));
    allyBuyButton.setButtonReleasedStateImage(YellowAddBuyHover);
    allyBuyButton.setButtonPressedStateImage(YellowAddBuyAction);
    allyBuyButton.setButtonNormalStateImage(YellowAddBuy);
    allyBuyButton.setSize(new Vector3<>(150f, 50f, 0f));
    allyBuyButton.getTransform().setParent(shop.getTransform());
    allyBuyButton.setPositionRelativeToParentTransform(buttonInitialPositions[0][2]);
    appleBuyButton.setButtonReleasedStateImage(RedDoteBuyHover);
    appleBuyButton.setButtonPressedStateImage(RedDoteBuyAction);
    appleBuyButton.setButtonNormalStateImage(RedDoteBuy);
    appleBuyButton.setSize(new Vector3<>(150f, 50f, 0f));
    appleBuyButton.getTransform().setParent(shop.getTransform());
    appleBuyButton.setPositionRelativeToParentTransform(buttonInitialPositions[0][0]);
    moneyIncreaseButton.setButtonReleasedStateImage(MoneyAddBuyHover);
    moneyIncreaseButton.setButtonPressedStateImage(MoneyAddBuyAction);
    moneyIncreaseButton.setButtonNormalStateImage(MoneyAddBuy);
    moneyIncreaseButton.setSize(new Vector3<>(150f, 50f, 0f));
    moneyIncreaseButton.getTransform().setParent(shop.getTransform());
    moneyIncreaseButton.setPositionRelativeToParentTransform(buttonInitialPositions[0][1]);
    appleBuyImage.setImage(RedDoteImage);
    appleBuyImage.getTransform().setParent(shop.getTransform());
    appleBuyImage.setPositionRelativeToParentTransform(new Vector3<>(appleBuyButton.getPositionRelativeToParentTransform().getX() + appleBuyButton.getSize().getX(), appleBuyButton.getPositionRelativeToParentTransform().getY(), 0f));
    moneyIncrementImage.setImage(MoneyAddImage);
    moneyIncrementImage.getTransform().setParent(shop.getTransform());
    moneyIncrementImage.setPositionRelativeToParentTransform(new Vector3<>(moneyIncreaseButton.getPositionRelativeToParentTransform().getX() + moneyIncreaseButton.getSize().getX(), moneyIncreaseButton.getPositionRelativeToParentTransform().getY(), 0f));
    allyBuyImage.setImage(YellowAutoImage);
    allyBuyImage.getTransform().setParent(shop.getTransform());
    allyBuyImage.setPositionRelativeToParentTransform(new Vector3<>(allyBuyButton.getPositionRelativeToParentTransform().getX() + allyBuyButton.getSize().getX(), allyBuyButton.getPositionRelativeToParentTransform().getY(), 0f));
    openShop.setCheckedImage(Checker);
    openShop.setUncheckedImage(Checkbox);
    openShop.setPosition(new Vector3<>(GameWindow.getWidth() - 35f, GameWindow.getHeight() / 2 - 60f, 0f));
    openShop.setSize(new Vector3<>(10f, 10f, 0f));
    closeShop.setCheckedImage(Checker);
    closeShop.setUncheckedImage(Checkbox);
    closeShop.setPosition(new Vector3<>((GameWindow.getWidth() - 50f), (GameWindow.getHeight() / 2 - 60f),0f));
    closeShop.setSize(new Vector3<>(10f, 10f, 0f));
    finishedLoading = true;
  }

  @Override
  public void draw() {
    super.draw();
    shopBackgroundUIImage.draw();
    shopButton.draw();
    appleBuyButton.draw();
    moneyIncreaseButton.draw();
    allyBuyButton.draw();
    openShop.draw();
    closeShop.draw();
    appleBuyImage.draw();
    moneyIncrementImage.draw();
    allyBuyImage.draw();
    resetRedButton.draw();
    GraphicsManager.drawString(Color.YELLOW,"Money: " + Money, new Vector3<Float>(shopButton.getPosition().getX() + 25f, shopButton.getPosition().getY(), shopButton.getPosition().getZ()));
  }

  @Override
  public void update() {
    super.update();

    if (openShop.isChecked()) {
      shop.setY(SHOP_BUTTON_Y_UPPER_LIMIT);
      shopIsGoingUp = false;
    }

    if (closeShop.isChecked()) {
      shop.setY(SHOP_BUTTON_Y_LOWER_LIMIT);
      shopIsGoingUp = true;
    }
  }

  public void setActive(boolean isActive) {
    this.isActive = isActive;
    if(!finishedLoading()) init();
    if(isActive) {
      activateShop();
    }
    else {
      deactivateShop();
    }
  }

  public void addMoney(int amount) {
    Money += amount;
  }

  private void activateShop() {
    resetRedButton.setActive(true);
    openShop.setActive(true);
    closeShop.setActive(true);
    moneyIncreaseButton.setActive(true);
    appleBuyButton.setActive(true);
    allyBuyButton.setActive(true);
    shopButton.setActive(true);
  }

  private void deactivateShop() {
    resetRedButton.setActive(false);
    openShop.setActive(false);
    closeShop.setActive(false);
    moneyIncreaseButton.setActive(false);
    appleBuyButton.setActive(false);
    allyBuyButton.setActive(false);
    shopButton.setActive(false);
  }

  private void buy(int price, ShopAct item) {
    if (Money >= price) {
      Money -= price;
      ShopActQueue.add(item);
    }
  }

  public void onWindowResize() {
    updatePositions();
  }

  private void updatePositions() {
    shop.setPosition(new Vector3<>(0f, (GameWindow.getHeight() / oldHeight) * shop.getPosition().getY(), 0f));
    shopButton.setPositionRelativeToParentTransform(new Vector3<>((GameWindow.getWidth() - 116) * 1.0f, shopButton.getPositionRelativeToParentTransform().getY(), 0f));
    openShop.setPosition(new Vector3<>(GameWindow.getWidth() - 35f, GameWindow.getHeight() / 2 - 60f, 0f));
    closeShop.setPosition(new Vector3<>((GameWindow.getWidth() - 50f), (GameWindow.getHeight() / 2 - 60f),0f));
  }

  private ButtonListener buttonListener = new ButtonListener() {
    @Override
    public void onClick(Button button) {
      if(button == moneyIncreaseButton) {
        buy(20, ShopAct.INCREMENTAPPLEVALUE);
      }
      if(button == resetRedButton) {
        ShopActQueue.add(ShopAct.RESETAPPLE);
      }
      if(button == appleBuyButton) {
        buy(10, ShopAct.BUYAPPLE);
      }
      if(button == allyBuyButton) {
        buy(50, ShopAct.BUYALLY);
      }
    }

    @Override
    public void onPress(Button button) {

    }

    @Override
    public void onRelease(Button button) {

    }

    @Override
    public void onHover(Button button) {
      if(button == shopButton) {
        oldHeight = GameWindow.getHeight();
        SHOP_BUTTON_Y_UPPER_LIMIT = GameWindow.getHeight() - 260;
        SHOP_BUTTON_Y_LOWER_LIMIT = GameWindow.getHeight() - 69;
        if (!openShop.isChecked() && !closeShop.isChecked()) {
          if (shop.getPosition().getY() >= SHOP_BUTTON_Y_UPPER_LIMIT && shopIsGoingUp) {
            shop.MoveYBy(-1);
          }
          if (shop.getPosition().getY() <= SHOP_BUTTON_Y_UPPER_LIMIT || !shopIsGoingUp) {
            shop.MoveYBy(1);
            shopIsGoingUp = false;
          }
          if (shop.getPosition().getY() >= SHOP_BUTTON_Y_LOWER_LIMIT && !shopIsGoingUp) {
            shopIsGoingUp = true;
          }
        }
      }
    }
  };
}
