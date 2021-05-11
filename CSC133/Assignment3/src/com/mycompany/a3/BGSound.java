package com.mycompany.a3;
import java.io.InputStream;

import com.codename1.media.Media;
import com.codename1.media.MediaManager;
import com.codename1.ui.Display;

public class BGSound implements Runnable{

	private Media m;
	
	
	public BGSound(String fileName) {
		try {
			InputStream inputStream = Display.getInstance().getResourceAsStream(getClass(), "/" + fileName);
		
			m = MediaManager.createMedia(inputStream,"audio/wav", this);
		}
		catch(Exception e){
			e.printStackTrace();
			System.out.println("Cannot Play Sound");
		}
	}
	public void pause() {
		m.pause();
	}
	public void play() {
		m.play();
	
	}
	
	
	
	/**
	 * @Override 
	 * Override Runnable run() , this method is invoke whenever media has finished playing.
	 */
	
	public void run() {
		
		m.setTime(0);
		m.play();
	}

}