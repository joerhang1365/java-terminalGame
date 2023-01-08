
package terminalgame;

import java.awt.FontFormatException;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Main
{
    private static Container container;
    private static Terminal terminal;
    
    public static void main(String[] args) throws IOException, FontFormatException, UnsupportedAudioFileException, LineUnavailableException
    {
        container = new Container();
        terminal = new Terminal();
        
        container.addUpdatable(terminal);
        container.addRenderable(terminal);
        
        container.start();
    }
}
