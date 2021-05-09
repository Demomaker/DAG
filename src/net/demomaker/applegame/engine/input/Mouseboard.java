package net.demomaker.applegame.engine.input;

import net.demomaker.applegame.engine.ui.UIElement;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Mouseboard implements MouseListener, MouseMotionListener {

	private static int mouseX = -1;
	private static int mouseY = -1;
	private static int mouseButton = -1;
	public static boolean mouseRelease = false;
	public static boolean mouseReleas = false;
	public static boolean mousePressed = false;
	public static boolean mouseLeftButtonPressed = false;
	public static boolean mouseRightButtonPressed = false;
	private static List<MouseboardListener> mouseboardListeners = new ArrayList<>();
	private static boolean mouseLeftButtonReleased = false;
	private static boolean mouseRightButtonReleased = false;

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
		mousePressed = true;
		mouseX = e.getX();
		mouseY = e.getY();
		mouseButton = e.getButton();
		for(MouseboardListener mouseboardListener : mouseboardListeners) {
			mouseboardListener.onMousePressed(e.getButton());
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
		mouseButton = e.getButton();
		mouseReleas = false;
		mouseLeftButtonReleased = false;
		mouseRightButtonReleased = false;
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
		mousePressed = false;
		if(mouseLeftButtonPressed)
			mouseLeftButtonReleased = true;
		if(mouseRightButtonPressed)
			mouseRightButtonReleased = true;
		mouseLeftButtonPressed = false;
		mouseRightButtonPressed = false;


		for(MouseboardListener mouseboardListener : mouseboardListeners) {
			mouseboardListener.onMouseReleased(e.getButton());
		}

	}

	public static void detectRelease() {
		mouseLeftButtonReleased = false;
		mouseRightButtonReleased = false;
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseLeftButtonPressed = false;
		mouseRightButtonPressed = false;
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		mouseX = e.getX();
		mouseY = e.getY();

	}

	public static boolean mouseTouchesUIElement(UIElement uiElement) {
		return uiElement.touchesObjectAt(new Vector3<>(Mouseboard.getX() * 1.0f, Mouseboard.getY() * 1.0f, 0f));
	}

	public static boolean mousePressedUIElement(UIElement uiElement) {
		return mouseLeftButtonPressed && mouseTouchesUIElement(uiElement);
	}

	public static boolean mouseUpUIElement(UIElement uiElement) {
		boolean condition = mouseLeftButtonReleased && mouseTouchesUIElement(uiElement);
		detectRelease();
		return condition;
	}

	public static void addMouseboardListener(MouseboardListener mouseboardListener) {
		mouseboardListeners.add(mouseboardListener);
	}

	public static void removeMouseboardListener(MouseboardListener mouseboardListener) {
		mouseboardListeners.remove(mouseboardListener);
	}

	public static abstract class MouseboardListener {
		public MouseboardListener(){
			Mouseboard.addMouseboardListener(this);
		}
		public abstract void onMousePressed(int mouseButton);
		public abstract void onMouseReleased(int mouseButton);
	}

}
