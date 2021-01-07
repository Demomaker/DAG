package controls;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class Keyboard implements KeyListener {

	private boolean[] keys = new boolean[65536];
	public boolean up, down, left, right, space, h, n, e, m, p, fthree, ffour, esc;
	public boolean kr = false;

	public void update() {
		up = keys[KeyEvent.VK_UP] || keys[KeyEvent.VK_W];
		down = keys[KeyEvent.VK_DOWN] || keys[KeyEvent.VK_S];
		left = keys[KeyEvent.VK_LEFT] || keys[KeyEvent.VK_A];
		right = keys[KeyEvent.VK_RIGHT] || keys[KeyEvent.VK_D];
		space = keys[KeyEvent.VK_SPACE];
		ffour = keys[KeyEvent.VK_F4];
		fthree = keys[KeyEvent.VK_F3];
		h = keys[KeyEvent.VK_H];
		n = keys[KeyEvent.VK_N];
		e = keys[KeyEvent.VK_E];
		m = keys[KeyEvent.VK_M];
		p = keys[KeyEvent.VK_P];
		esc = keys[KeyEvent.VK_ESCAPE];
	}

	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}

	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
		kr = true;
	}

	public void keyTyped(KeyEvent e) {

	}

}
