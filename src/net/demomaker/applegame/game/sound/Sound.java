package net.demomaker.applegame.game.sound;

import java.applet.*;

public class Sound {

	public static final Sound yellowbeep = new Sound("/resources/YellowBeep.wav");
	public static final Sound redtouch = new Sound("/resources/RedTouch.wav");
	public static final Sound music = new Sound("/resources/Drum.wav");
	private AudioClip clip;

	public Sound(String filename) {
		try {
			clip = Applet.newAudioClip(Sound.class.getResource(filename));
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// Called things for playing, looping, etc.

	public void music() {

	}

	public void play() {
		try {
			new Thread() {
				public void run() {
					clip.play();
				}
			}.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void stop() {
		clip.stop();
	}

	public void loop() {
		try {
			new Thread() {
				public void run() {
					clip.loop();
				}
			}.start();

		} catch (Exception ex) {
			ex.printStackTrace();
		}

	}

}
