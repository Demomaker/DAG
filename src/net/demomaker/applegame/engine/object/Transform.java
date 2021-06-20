package net.demomaker.applegame.engine.object;

import net.demomaker.applegame.engine.util.Vector3;

import java.util.ArrayList;
import java.util.List;

import static net.demomaker.applegame.engine.util.Vector3.sum;

public class Transform {
    private List<Transform> children = new ArrayList<>();
    private Transform parent;
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

    public Vector3<Float> getPositionRelativeToParent() {
        return Vector3.sub(this.getPosition(), parent.getPosition());
    }

    public void setPosition(Vector3<Float> position) {
        Vector3<Float> oldPosition = this.position;
        this.position = position;
        if(!children.isEmpty()) {
            for (Transform child : children) {
                Vector3<Float> childPreviousPosition = Vector3.sub(child.getPosition(), oldPosition);
                child.setPosition(sum(getPosition(), childPreviousPosition));
            }
        }
    }

    public void setPositionRelativeToParent(Vector3<Float> position) {
        if(parent != null) {
            this.setPosition(sum(parent.getPosition(), position));
        }
        else {
            this.setPosition(position);
        }
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

    public void setParent(Transform otherTransform) {
        this.parent = otherTransform;
        otherTransform.children.add(this);
    }

    public Transform getParent() {
        return parent;
    }
}
