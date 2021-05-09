package net.demomaker.applegame.engine.util;

public class Vector3<T> extends Object{
    private T x;
    private T y;
    private T z;
    public Vector3(T x, T y, T z) {
        this.x = x;
        this.y = y;
        this.z = z;
    }

    public T getX() {
        return x;
    }

    public void setX(T x) {
        this.x = x;
    }

    public T getY() {
        return y;
    }

    public void setY(T y) {
        this.y = y;
    }

    public T getZ() {
        return z;
    }

    public void setZ(T z) {
        this.z = z;
    }

    public static Vector3<Float> sum(Vector3<Float> first, Vector3<Float> second) {
        return new Vector3<>(first.getX() + second.getX(), first.getY() + second.getY(), first.getZ() + second.getZ());
    }

    public static Vector3<Float> sub(Vector3<Float> first, Vector3<Float> second) {
        return new Vector3<>(first.getX() - second.getX(), first.getY() - second.getY(), first.getZ() - second.getZ());
    }

    @Override
    public String toString() {
        return "(" + getX().toString() + "," + getY().toString() + "," + getZ().toString() + ")";
    }

    public boolean equals(Vector3<T> other)
    {
        return x.equals(other.x) && y.equals(other.y) && z.equals(other.z);
    }
}
