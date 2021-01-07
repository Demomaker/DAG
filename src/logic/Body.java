package logic;

import java.awt.*;

public class Body {
    private Transform transform;
    private Box box;
    private Color color;

    public Body() {
        this(new Transform(), new Box(), Color.WHITE);
    }

    public Body(Transform transform, Box box, Color color) {
        this.transform = transform;
        this.box = box;
        this.color = color;
    }

    public Transform getTransform() {
        return transform;
    }

    public void setTransform(Transform transform) {
        this.transform = transform;
    }

    public Box getBox() {
        return box;
    }

    public void setBox(Box box) {
        this.box = box;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
