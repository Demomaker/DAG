package net.demomaker.applegame.engine.input;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.time.LocalDateTime;
import java.time.ZonedDateTime;
import java.util.ArrayList;
import java.util.List;

public class Keyboard implements KeyListener {

	public static final int NUMBER_OF_KEYS_IN_UTF8 = 65536;
	private static KeyState[] keys = generateKeyStates();
	private static List<KeyboardListener> keyboardListeners = new ArrayList<>();
	private static long timeInMillisBeforeBackToNormalKeyState = 2000;

	private static KeyState[] generateKeyStates() {
		KeyState[] keys = new KeyState[NUMBER_OF_KEYS_IN_UTF8];
		for(int i = 0; i < NUMBER_OF_KEYS_IN_UTF8; i++) {
			keys[i] = KeyState.NORMAL;
		}
		return keys;
	}

	public void keyPressed(KeyEvent e) {
		for(KeyboardListener keyboardListener : keyboardListeners) {
			keyboardListener.onKeyPressed(e.getKeyCode());
		}
		keys[e.getKeyCode()] = KeyState.PRESSED;
	}

	public void keyReleased(KeyEvent e) {
		for(KeyboardListener keyboardListener : keyboardListeners) {
			keyboardListener.onKeyReleased(e.getKeyCode());
		}
		keys[e.getKeyCode()] = KeyState.RELEASED;
	}

	public static void setBackToNormal(int keycode) {
		long startingTimeInMilli = ZonedDateTime.now().toInstant().toEpochMilli();
		while(ZonedDateTime.now().toInstant().toEpochMilli() - startingTimeInMilli > timeInMillisBeforeBackToNormalKeyState) {
			//Do nothing
		}
		keys[keycode] = KeyState.NORMAL;
	}

	public void keyTyped(KeyEvent e) {
		//Do nothing on purpose
	}

	public static boolean keyPressed(int key) {
		return keys[key] == KeyState.PRESSED;
	}

	public static boolean keyReleased(int key) {
		boolean condition = keys[key] == KeyState.RELEASED;
		setBackToNormal(key);
		return condition;
	}

	public static void addKeyboardListener(KeyboardListener keyboardListener) {
		keyboardListeners.add(keyboardListener);
	}

	public static void removeKeyboardListener(KeyboardListener keyboardListener) {
		keyboardListeners.remove(keyboardListener);
	}

	public static abstract class KeyboardListener {
		public KeyboardListener(){
			Keyboard.addKeyboardListener(this);
		}
		public abstract void onKeyPressed(int key);
		public abstract void onKeyReleased(int key);
	}

	public enum KeyState {
		NORMAL,
		PRESSED,
		RELEASED
	}

}
