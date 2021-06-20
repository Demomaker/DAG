package net.demomaker.applegame.engine.input;

import net.demomaker.applegame.engine.ui.UIElement;
import net.demomaker.applegame.engine.util.Vector3;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.util.ArrayList;
import java.util.List;

public class Mouseboard implements MouseListener, MouseMotionListener {
	private static List<MouseboardListener> mouseboardListeners = new ArrayList<>();

	@Override
	public void mouseClicked(MouseEvent e) {
		for(MouseboardListener mouseboardListener : mouseboardListeners) {
			mouseboardListener.onMouseClicked(e);
		}
	}

	@Override
	public void mousePressed(MouseEvent e) {
		for(MouseboardListener mouseboardListener : mouseboardListeners) {
			mouseboardListener.onMousePressed(e);
		}
	}



	@Override
	public void mouseReleased(MouseEvent e) {
		for(MouseboardListener mouseboardListener : mouseboardListeners) {
			mouseboardListener.onMouseReleased(e);
		}

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MouseboardListener mouseboardListener : mouseboardListeners) {
			mouseboardListener.onMouseMoved(e);
		}
	}

	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		for(MouseboardListener mouseboardListener : mouseboardListeners) {
			mouseboardListener.onMouseMoved(e);
		}
	}

	public static void addMouseboardListener(MouseboardListener mouseboardListener) {
		mouseboardListeners.add(mouseboardListener);
	}

	public static abstract class MouseboardListener {
		public MouseboardListener(){
			Mouseboard.addMouseboardListener(this);
		}
		public abstract void onMouseMoved(MouseEvent mouse);
		public abstract void onMouseClicked(MouseEvent mouse);
		public abstract void onMousePressed(MouseEvent mouse);
		public abstract void onMouseReleased(MouseEvent mouse);
	}

}
