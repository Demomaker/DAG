package logic;


import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class Shop {
    private Transform transform;
    private Box box;
    private Image backgroundImage;
    private MovingButton shopOpeningButton;
    private List<Button> buttons = new ArrayList<>();
    private Vector3<Float> startingPosition = new Vector3<>(0f,0f,0f);

    public Shop() {
        this(null, null);
    }

    public Shop(MovingButton shopOpeningButton, List<Button> buttons) {
        this.shopOpeningButton = shopOpeningButton;
        this.buttons = buttons;
        MoveTo(startingPosition);
    }

    public void MoveTo(Vector3<Float> position) {
        transform.setPosition(position);
        for(Button button : buttons) {
            button.setPosition(position);
        }
    }
}
