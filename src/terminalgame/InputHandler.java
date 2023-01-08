
package terminalgame;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class InputHandler implements KeyListener
{
    private boolean arrow[];
    private boolean enter;
    private boolean z;
    
    public InputHandler()
    {
        arrow = new boolean[4];
        enter = false;
        z = false;
    }
    
    public boolean[] getArrow()
    {
        return arrow;
    }
    
    public void setArrow(int i, boolean b)
    {
        arrow[i] = b;
    }
            
    public boolean getEnter()
    {
        return enter;
    }
    
    public boolean getZ()
    {
        return z;
    }
    
    public void setEnter(boolean b)
    {
        enter = b;
    }
    
    public void setZ(boolean b)
    {
        z = b;
    }
    @Override
    public void keyTyped(KeyEvent event)
    {
        // throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void keyPressed(KeyEvent event)
    {
        switch(event.getKeyCode())
        {
            case KeyEvent.VK_UP : arrow[0] = true; break;
            case KeyEvent.VK_LEFT : arrow[1] = true; break;
            case KeyEvent.VK_RIGHT : arrow[2] = true; break;
            case KeyEvent.VK_DOWN : arrow[3] = true; break;
            case KeyEvent.VK_ENTER : enter = true; break;
            case KeyEvent.VK_Z : z = true; break;
        }
    }

    @Override
    public void keyReleased(KeyEvent event)
    {
        switch(event.getKeyCode())
        {
            case KeyEvent.VK_UP : arrow[0] = false; break;
            case KeyEvent.VK_LEFT : arrow[1] = false; break;
            case KeyEvent.VK_RIGHT : arrow[2] = false; break;
            case KeyEvent.VK_DOWN : arrow[3] = false; break;
            case KeyEvent.VK_ENTER : enter = false; break;
            case KeyEvent.VK_Z : z = false; break;
        }
    }
    
}
