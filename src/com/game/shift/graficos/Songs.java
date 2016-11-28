package com.game.shift.graficos;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;

public class Songs {
	public static void menuSong(){
		AudioPlayer menuPlayer = AudioPlayer.player;
		AudioStream menuStream;
		AudioData MD;
		
		ContinuousAudioDataStream loop = null;
		
		try{
			InputStream is = new FileInputStream("res/audio/mainmenu.wav");
			menuStream = new AudioStream(is);
			AudioPlayer.player.start(menuStream);
		}catch(FileNotFoundException e){
			System.out.println(e.toString());
		}catch(IOException error){
			System.out.println(error.toString());
		}
		menuPlayer.start(loop);
		//menuPlayer.run();
		//menuPlayer.stop(loop);
	}
}
