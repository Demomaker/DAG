package net.demomaker.applegame.engine.scene;

import java.awt.*;

public interface Scene {
    boolean finishedLoading();
    void onWindowResize();
    void init();
    void update(float deltaTime);
    void draw();
    void cleanup();
    void onResume();
}
