package net.demomaker.applegame.engine.scene;

import java.awt.*;

public interface Scene {
    int WIDTH = 900;
    int HEIGHT = WIDTH / 16 * 9;
    int winWIDTH = WIDTH;
    int winHEIGHT = HEIGHT;
    void init();
    void update(float deltaTime);
    void draw(Graphics g);
    void cleanup();
    void onResume();
}
