package terminalgame;

import java.awt.Graphics2D;

public class Message implements Updatable, Renderable
{

    private String from, message;
    private String[] stringArray;
    private boolean done;
    private double time;
    private float delay;
    private int xPos, yPos, index;
    private final int TICKS = 30;

    public Message(String from, String message, float delay)
    {
        this.from = from;
        this.message = from + " " + message;
        this.delay = delay;
        done = false;
        time = 0;
        index = 0;
    }

    public int getXPos()
    {
        return xPos;
    }

    public void setXPos(int x)
    {
        this.xPos = x;
    }

    public int getYPos()
    {
        return yPos;
    }

    public void setYPos(int y)
    {
        this.yPos = y;
    }

    public boolean getDone()
    {
        return done;
    }
    
    public int getIndex()
    {
        return index;
    }

    @Override
    public void update(InputHandler inputHandler)
    {
        // Delays next command making text appear longer
        time += (double) 1 / TICKS;
        if (time > delay)
        {
            done = true;
            time = 0;
        }
        
        // Splits da message at \n
        // I show try to make this automated and split string when line reaches
        // edge but dont care rn.
        stringArray = message.split("\n");
    }

    @Override
    public void render(Graphics2D graphics, float interpolation)
    {
        // Wraps sentences that are too long
        //if(graphics.getFontMetrics().stringWidth(message) > 800)
        index = 0;
        for (String s : stringArray)
        {
            graphics.drawString(s, xPos, yPos + index * graphics.getFontMetrics().getHeight());
            index++;
        }
    }

}
