
package terminalgame;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.image.BufferStrategy;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.imageio.ImageIO;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Container
{
    private JFrame window;
    private JLabel border;
    private BufferedImage reflection;
    private Canvas canvas;
    private InputHandler inputHandler;
    private AudioHandler audioHandler;
    private Font font;
    private final String TITLE = "Terminal";
    private final float FONT_SIZE = 24f;
    private final int SCREEN_WIDTH = 800, SCREEN_HEIGHT = 600, BORDER_SIZE = 100;
    private final int TICKS_PER_SECOND = 30, TIME_PER_TICK = 1000 / TICKS_PER_SECOND, MAX_FRAMESKIPS = 5;
    private boolean running = false;
    
    private ArrayList<Updatable> updatables = new ArrayList<>();
    private ArrayList<Renderable> renderables = new ArrayList<>();
    
    public void addUpdatable(Updatable u)
    {
        updatables.add(u);
    }
    
    public void removeUpdatable(Updatable u)
    {
        updatables.remove(u);
    }
    
    public void addRenderable(Renderable r)
    {
        renderables.add(r);
    }
    
    public void removeRenderable(Renderable r)
    {
        renderables.remove(r);
    }
    
    public void start() throws FontFormatException, IOException, UnsupportedAudioFileException, LineUnavailableException 
    {
        inputHandler = new InputHandler();
        audioHandler = new AudioHandler("src/audio/computernoises.wav");
        audioHandler.loop();
        font = Font.createFont(Font.TRUETYPE_FONT, new File("src/fonts/VT323-Regular.ttf")).deriveFont(FONT_SIZE);
        window = new JFrame(TITLE);
        canvas = new Canvas();
        border = new JLabel(new ImageIcon("src/images/monitor.png"));
        reflection = ImageIO.read(new File("src/images/reflection.png"));
        window.setSize(new Dimension(SCREEN_WIDTH + BORDER_SIZE * 2, SCREEN_HEIGHT + BORDER_SIZE * 2 + 25));
        border.setSize(new Dimension(SCREEN_WIDTH + BORDER_SIZE * 2, SCREEN_HEIGHT + BORDER_SIZE * 2 ));
        canvas.setSize(new Dimension(SCREEN_WIDTH, SCREEN_HEIGHT));
        canvas.setBackground(Color.BLACK);
        canvas.setLocation(100, 100);
        window.setResizable(false);
        window.setLocationRelativeTo(null);
        window.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        window.setLayout(null);
        window.add(canvas);
        window.add(border);
        window.setVisible(true);
        canvas.setFocusable(false);
        window.requestFocus();
        window.addKeyListener(inputHandler);
        
        long nextGameTick = System.currentTimeMillis();
        long timeAtLastFPSCheck = 0;
        float interpolation = 0;
        int loops = 0;
        int ticks = 0;
        
        running = true;
        while(running)
        {
            loops = 0;
            while(System.currentTimeMillis() > nextGameTick && loops < MAX_FRAMESKIPS)
            {
                update();
                ticks++;
                
                nextGameTick += TIME_PER_TICK;
                loops++;
            }
            
            interpolation = (float) (System.currentTimeMillis() + TIME_PER_TICK - nextGameTick)
                          / (float) TIME_PER_TICK;
            
            render(interpolation);
            
            // FPS Check
            if(System.currentTimeMillis() - timeAtLastFPSCheck >= 1000)
            {
                //System.out.println("FPS: " + ticks);
                //window.setTitle(TITLE + " - FPS: " + ticks);
                ticks = 0;
                timeAtLastFPSCheck = System.currentTimeMillis();
            }
        }
    }
    
    private void update()
    {
        for(Updatable u : updatables)
        {
            u.update(inputHandler);
        }
    }
    
    private void render(float interpolation)
    {
        // Don't know what's happening. Looked up tutorial
        canvas.createBufferStrategy(2);
        BufferStrategy buffer = canvas.getBufferStrategy();
        
        if (buffer == null)
        {
            canvas.createBufferStrategy(2);
            return;
        }
        
        Graphics2D graphics = (Graphics2D) buffer.getDrawGraphics();
        
        // Settings I have for rendering
        graphics.setColor(Color.GREEN.darker());
        graphics.setFont(font);
        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_FRACTIONALMETRICS, RenderingHints.VALUE_FRACTIONALMETRICS_OFF);
        graphics.setRenderingHint(RenderingHints.KEY_INTERPOLATION, RenderingHints.VALUE_INTERPOLATION_NEAREST_NEIGHBOR);
        
        // Clears the screen so the last frame does not show
        graphics.clearRect(0, 0, canvas.getWidth(), canvas.getHeight());
        
        for(Renderable r : renderables)
            r.render(graphics, interpolation);
        
        // Screen Reflection
        graphics.drawImage(reflection, 0, 0, null);
        
        graphics.dispose();
        buffer.show();
    }
}
