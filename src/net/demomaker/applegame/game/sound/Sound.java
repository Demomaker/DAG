package net.demomaker.applegame.game.sound;

import net.demomaker.applegame.engine.util.AssetRetreiver;
import net.demomaker.applegame.engine.util.ResourceFinder;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.applet.*;

public class Sound {

	public static final Sound yellowbeep = new Sound("/resources/YellowBeep.wav");
	public static final Sound redtouch = new Sound("/resources/RedTouch.wav");
	public static final Sound music = new Sound("/resources/Drum.wav");
	private Clip clip;
	private AudioInputStream audioInputStream = null;
	private String filename = "";
	private int lastFramePosition = 0;

	public Sound(String filename) {
		try {
			this.filename = filename;
			audioInputStream = AudioSystem.getAudioInputStream(ResourceFinder.getResource(filename));
			clip = AudioSystem.getClip();
			clip.open(audioInputStream);
		} catch (Exception e) {
			e.printStackTrace();
			clip.close();
		}
	}

	// Called things for playing, looping, etc.

	public void play() {
		try {
			clip.start();
			clip.setFramePosition(0);

		} catch (Exception ex) {
			ex.printStackTrace();
			clip.close();
		}
	}

	public void stop() {
		try {
			lastFramePosition = clip.getFramePosition();
			clip.stop();
		}
		catch (Exception ex) {
			ex.printStackTrace();
			clip.close();
		}
	}

	public void loop() {
		try {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.setFramePosition(0);
		} catch (Exception ex) {
			ex.printStackTrace();
			clip.close();
		}
	}

	public void resume() {
		try {
			clip.start();
			clip.setFramePosition(lastFramePosition);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public void resumeLoop() {
		try {
			clip.loop(Clip.LOOP_CONTINUOUSLY);
			clip.setFramePosition(lastFramePosition);
		}
		catch (Exception ex) {
			ex.printStackTrace();
		}
	}
}
