package logic;

public class Box {
    private int innerBorderX = 0;
    private int innerBorderY = 0;
    private int outerBorderX = 0;
    private int outerBorderY = 0;
    public Box() {
        this(0,0,0,0);
    }

    public Box(int innerBorderX, int innerBorderY, int outerBorderX, int outerBorderY) {
        this.innerBorderX = innerBorderX;
        this.innerBorderY = innerBorderY;
        this.outerBorderX = outerBorderX;
        this.outerBorderY = outerBorderY;
    }

    public int getInnerBorderX() {
        return innerBorderX;
    }

    public void setInnerBorderX(int innerBorderX) {
        this.innerBorderX = innerBorderX;
    }

    public int getInnerBorderY() {
        return innerBorderY;
    }

    public void setInnerBorderY(int innerBorderY) {
        this.innerBorderY = innerBorderY;
    }

    public int getOuterBorderX() {
        return outerBorderX;
    }

    public void setOuterBorderX(int outerBorderX) {
        this.outerBorderX = outerBorderX;
    }

    public int getOuterBorderY() {
        return outerBorderY;
    }

    public void setOuterBorderY(int outerBorderY) {
        this.outerBorderY = outerBorderY;
    }

    public Vector3<Float> getTopLeftCorner() {
        return new Vector3<Float>(innerBorderX * 1.0f, innerBorderY * 1.0f, 0f);
    }

    public Vector3<Float> getTopRightCorner() {
        return new Vector3<Float>(outerBorderX * 1.0f, innerBorderY * 1.0f, 0f);
    }

    public Vector3<Float> getBottomLeftCorner() {
        return new Vector3<>(innerBorderX * 1.0f, outerBorderY * 1.0f, 0f);
    }

    public Vector3<Float> getBottomRightCorner() {
        return new Vector3<>(outerBorderX * 1.0f, outerBorderY * 1.0f, 0f);
    }
}
