
package terminalgame;

import java.io.File;
import java.io.IOException;
import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class AudioHandler
{
    private Clip clip;
    private AudioInputStream audioInputStream;
    private String source;
    
    public AudioHandler(String source) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        this.source = source;
        audioInputStream = AudioSystem.getAudioInputStream(new File(source));
        clip = AudioSystem.getClip();
        clip.open(audioInputStream);
    }
    
    public void loop()
    {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }
    
    public void start()
    {
        clip.start();
    }
}
