import java.io.File;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class backgroundsound 
{
	static Clip[] clip = new Clip[2];
    static AudioInputStream[] ais = new  AudioInputStream[2];
    static AudioInputStream audioStream;
	public static void playmusic() 
	{
		try 
		{
			
			ais[0] = AudioSystem.getAudioInputStream(new File("bgm/bgm.wav"));
			
			//URL url = frm.getClass().getClassLoader().getResource("bgm.wav");	//老師的方法一定要經過Frame有點煩

			//ais[0] = AudioSystem.getAudioInputStream(audioStream);

			clip[0] = AudioSystem.getClip();

			clip[0].open(ais[0]);
			
			clip[0].setFramePosition(0);

			clip[0].loop(10); //0=play once 
		  
		} catch (Exception e) {e.printStackTrace();};
	}
	public void button_sound() 
	{
		try 
		{
			
			ais[1] = AudioSystem.getAudioInputStream(new File("bgm/btn_sd.wav"));
			
			//URL url = frm.getClass().getClassLoader().getResource("bgm.wav");	//老師的方法一定要經過Frame有點煩

			//ais[0] = AudioSystem.getAudioInputStream(audioStream);

			clip[1] = AudioSystem.getClip();

			clip[1].open(ais[1]);
			
			clip[1].setFramePosition(1);

			clip[1].loop(0); //0=play once 
		  
		} catch (Exception e) {e.printStackTrace();};
	}
}
