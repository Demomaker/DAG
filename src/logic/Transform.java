package logic;

public class Transform {
    private Vector3<Float> position;
    private Vector3<Float> size;
    private Vector3<Float> origin;

    public Transform() {
        this(new Vector3<>(0f,0f,0f), new Vector3<>(0f,0f,0f), new Vector3<>(0f,0f,0f));
    }

    public Transform(Vector3<Float> position, Vector3<Float> size, Vector3<Float> origin) {
        this.position = position;
        this.size = size;
        this.origin = origin;
    }

    public Vector3<Float> getPosition() {
        return position;
    }

    public void setPosition(Vector3<Float> position) {
        this.position = position;
    }

    public Vector3<Float> getSize() {
        return size;
    }

    public void setSize(Vector3<Float> size) {
        this.size = size;
    }

    public Vector3<Float> getOrigin() {
        return origin;
    }

    public void setOrigin(Vector3<Float> origin) {
        this.origin = origin;
    }
}
