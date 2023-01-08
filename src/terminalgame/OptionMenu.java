package terminalgame;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.IOException;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class OptionMenu implements Updatable, Renderable
{

    private String from, message;
    private String[] options;
    private Object[] actions;
    private boolean selected;
    private float xPos, yPos, lineSpacing;
    private int selection, boxWidth, boxHeight;
    private final int MARGIN = 30, OFFSET = 5;

    public OptionMenu(String from, String message, String[] option, Object[] action) throws UnsupportedAudioFileException, IOException, LineUnavailableException
    {
        this.from = from;
        this.message = from + " " + message;
        this.options = option;
        this.actions = action;
        selected = false;
        selection = 0;
        boxWidth = 0;
        boxHeight = 0;
        xPos = 0;
        yPos = 0;
        lineSpacing = 1.5f;
    }

    public boolean getSelected()
    {
        return selected;
    }

    public Object getActionSelected()
    {
        return actions[selection];
    }

    public void setXPos(float x)
    {
        this.xPos = x;
    }

    public float getYPos()
    {
        return yPos;
    }

    public void setYPos(float y)
    {
        this.yPos = y;
    }

    @Override
    public void update(InputHandler inputHandler)
    {
        try
        {
            AudioHandler audioHandler = new AudioHandler("src/audio/beep.wav");
            if (!selected)
            {
                // Left Arrow
                if (inputHandler.getArrow()[1] && selection > 0)
                {
                    selection--;
                    inputHandler.setArrow(1, false);
                    audioHandler.start();
                }
                // Right Arrow
                else if (inputHandler.getArrow()[2] && selection < options.length - 1)
                {
                    selection++;
                    inputHandler.setArrow(2, false);
                    audioHandler.start();
                }
                // Enter
                else if (inputHandler.getEnter())
                {
                    selected = true;
                    inputHandler.setEnter(false);
                    audioHandler.start();
                }
            }
        }
        catch (UnsupportedAudioFileException ex)
        {
        }
        catch (IOException ex)
        {
        }
        catch (LineUnavailableException ex)
        {
        }

    }

    @Override
    public void render(Graphics2D graphics, float interpolation)
    {
        // How the hell does this work
        boxWidth = graphics.getFontMetrics().stringWidth(options[selection] + OFFSET);
        boxHeight = graphics.getFontMetrics().getHeight();
        graphics.drawString(message, xPos, yPos);
        yPos += graphics.getFontMetrics().getHeight() * lineSpacing;
        xPos += MARGIN;
        if (!selected)
        {
            for (int i = 0; i < options.length; i++)
            {
                if (i == selection)
                {
                    graphics.fillRect((int) xPos - OFFSET, (int) yPos - boxHeight + OFFSET, boxWidth, boxHeight);
                    graphics.setColor(Color.BLACK);
                    graphics.drawString(options[i], xPos, yPos);
                    graphics.setColor(Color.GREEN.darker());
                }
                else
                {
                    graphics.drawString(options[i], xPos, yPos);
                }
                xPos += graphics.getFontMetrics().stringWidth(options[i] + MARGIN);
            }
        }
    }
}
