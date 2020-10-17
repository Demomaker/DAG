package controls;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

public class Mouseboard implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseButton = -1;
	public static int mouseHold = -1;
	public static boolean mouseRelease = false;
	public static boolean mouseReleas = false;

	public static int getX() {
		return mouseX;
	}

	public static int getY() {
		return mouseY;
	}

	public static int getButton() {
		return mouseButton;
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		// mouseButton = 0;
		mouseHold = 0;
	}

	@Override
	public void mousePressed(MouseEvent e) {

		mouseButton = e.getButton();
		mouseHold = 0;
		mouseReleas = false;

		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		mouseButton = -1;
		mouseRelease = true;
		mouseReleas = true;
		mouseHold = 0;

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		// mouseButton = 0;
		mouseHold = 0;
		mouseReleas = false;
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseHold = 0;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		// mouseButton = 0;
		mouseHold = 0;
		mouseReleas = false;
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();

	}

}
