package logic;

import java.awt.*;

public interface Scene {
    void init();
    void update(float deltaTime);
    void draw(Graphics g);
    void cleanup();
    void onResume();
}
