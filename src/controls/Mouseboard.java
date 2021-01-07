package controls;

import logic.Button;
import logic.Vector3;

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
	public static boolean mousePressed = false;
	public static boolean mouseLeftButtonPressed = false;
	public static boolean mouseRightButtonPressed = false;

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
		mousePressed = true;
		if(e.getButton() == MouseEvent.BUTTON1) {
			mouseLeftButtonPressed = true;
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			mouseRightButtonPressed = true;
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {

		mouseButton = e.getButton();
		mouseHold = 0;
		mouseReleas = false;
		if(e.getButton() == MouseEvent.BUTTON1) {
			mouseLeftButtonPressed = true;
		}
		if(e.getButton() == MouseEvent.BUTTON2) {
			mouseRightButtonPressed = true;
		}

		// TODO Auto-generated method stub
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

		mouseButton = -1;
		mouseRelease = true;
		mouseReleas = true;
		mouseHold = 0;
		mousePressed = false;
		mouseLeftButtonPressed = false;
		mouseRightButtonPressed = false;

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

	public static boolean mousePressedButton(Button button) {
		return mouseLeftButtonPressed && button.touchesObjectAt(new Vector3<>(Mouseboard.getX() * 1.0f, Mouseboard.getY() * 1.0f, 0f));
	}

}
