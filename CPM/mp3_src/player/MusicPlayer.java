package player;

import java.io.File;

import javazoom.jlgui.basicplayer.*;

public class MusicPlayer {
	private BasicPlayer basicPlayer = null;
	private File song = null;

	public MusicPlayer() {
		basicPlayer = new BasicPlayer();
	}

	public void play(File file) {
		try {
			// if we are playing a song which is paused
			if (basicPlayer.getStatus() == BasicPlayer.PAUSED && song == file) {
				basicPlayer.resume();
			} else {
				basicPlayer.open(file);
				basicPlayer.play();
				song = file;
			}
		} catch (Exception e) {
		}
	}

	public void stop() {
		try {
			basicPlayer.stop();
		} catch (BasicPlayerException e) {
		}
	}

	public void pause() {
		try {
			basicPlayer.pause();
		} catch (BasicPlayerException e) {
		}
	}

	public void setVolume(double vol, double volMax) {
		try {

			basicPlayer.setGain(vol / volMax);
		} catch (BasicPlayerException e) {
		}
	}

	public boolean isPaused() {
		return basicPlayer.getStatus() == BasicPlayer.PAUSED;
	}

}
