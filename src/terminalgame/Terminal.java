package terminalgame;

import java.awt.Graphics2D;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;

public class Terminal implements Updatable, Renderable
{

    private ArrayList<Object> screen, commands;
    private boolean display, nextStep, loopOperation;
    private boolean[] flags;
    private String name;
    private float lineSpacing;
    private int textX, textY, index;
    private final int SCREEN_HEIGHT = 600;

    Terminal()
    {
        screen = new ArrayList<>();
        commands = new ArrayList<>();
        name = "<JAMES>";
        display = true;
        nextStep = true;
        loopOperation = true;
        flags = new boolean[20];
        textY = 0;
        textX = 0;
        index = 0;
        lineSpacing = 18 * 1.5f;
    }

    @Override
    public void update(InputHandler inputHandler)
    {
        try
        {
            Operations operations = new Operations(name, flags);
            // Copies Operations onto ArrayList commands
            if (loopOperation)
            {
                commands.addAll(Arrays.asList(operations.getOperations()));
                loopOperation = false;
            }
            
            // Remove first screen item when screen full, kinda brokey
            if (textY > SCREEN_HEIGHT)
            {
                screen.remove(0);
            }
            
            if (index < commands.size())
            {
                // Adds next command to screen
                if (nextStep)
                {
                    screen.add(commands.get(index));
                    nextStep = false;
                }
                // Updates current command
                switch (commands.get(index).getClass().getSimpleName())
                {
                    case "Message":
                        ((Message) commands.get(index)).update(inputHandler);
                        if (((Message) commands.get(index)).getDone())
                        {
                            index++;
                            nextStep = true;
                        }
                        break;
                    case "Sequence":
                        for (Object o : ((Sequence) commands.get(index)).getActions())
                        {
                            commands.add(index + ((Sequence) commands.get(index)).getIndex(), o);
                            ((Sequence) commands.get(index)).incrementIndex(1);
                        }
                        index++;
                        nextStep = true;
                        break;
                    case "OptionMenu":
                        ((OptionMenu) commands.get(index)).update(inputHandler);
                        if (((OptionMenu) commands.get(index)).getSelected())
                        {
                            commands.add(index + 1, ((OptionMenu) commands.get(index)).getActionSelected());
                            index++;
                            nextStep = true;
                        }
                        break;
                    case "CheckFlag":
                        if(((CheckFlag) commands.get(index)).getFlag())
                        {
                            commands.add(index + 1, ((CheckFlag) commands.get(index)).getActions());
                        }
                        index++;
                        nextStep = true;
                        break;
                    case "GoTo":
                        commands.add(index + 1, operations.getOperations()[((GoTo) commands.get(index)).getStep()]);
                        nextStep = true;
                        index++;
                        break;
                    case "Exit":
                        ((Exit) commands.get(index)).update(inputHandler);
                        break;
                    case "SetFlag":
                        flags[((SetFlag) commands.get(index)).getFlag()] = true;
                        index++;
                        nextStep = true;
                        break;
                    case "ChangeName":
                        name = "<LIAM>";
                        index++;
                        nextStep = true;
                        break;
                    case "Pong":
                        ((Pong) commands.get(index)).update(inputHandler);
                        if(((Pong) commands.get(index)).getNext())
                        {
                            index++;
                            nextStep = true;
                        }
                        break;
                    default:
                        index++;
                        nextStep = true;
                        
                }
            }
            // Skip ahead using Z key Developmental purposes only
            /*if(inputHandler.getZ())
            {
                index++;
                nextStep = true;
                inputHandler.setZ(false);
            }*/
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
        // Rendering shit might need to redo this
        textY = 18;
        if (display)
        {
            for (Object o : screen)
            {
                if (o.getClass().equals(Message.class))
                {
                    ((Message) o).setXPos(textX);
                    ((Message) o).setYPos(textY);
                    ((Message) o).render(graphics, interpolation);
                    textY += lineSpacing * ((Message) o).getIndex();
                }
                else if (o.getClass().equals(OptionMenu.class))
                {
                    ((OptionMenu) o).setXPos(textX);
                    ((OptionMenu) o).setYPos(textY);
                    ((OptionMenu) o).render(graphics, interpolation);
                    textY += lineSpacing;
                }
                else if (o.getClass().equals(Image.class))
                {
                    ((Image) o).setX(textX);
                    ((Image) o).setY(textY);
                    ((Image) o).render(graphics, interpolation);
                    textY += ((Image) o).getHeight() + lineSpacing;
                }
                else if(o.getClass().equals(Pong.class))
                {
                    ((Pong) o).render(graphics, interpolation);
                    textY += ((Pong) o).getHeight() - lineSpacing;
                }
            }
        }
    }
}
