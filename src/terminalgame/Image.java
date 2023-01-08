
package terminalgame;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;

public class Image implements Renderable
{
    private BufferedImage image;
    private String url;
    private int x, y, width, height;
    
    public Image(int width, int height, String url)
    {
        this.width = width;
        this.height = height;
        this.url = url;
        
        try
        {
            image = ImageIO.read(new File(url));
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
    
    public void setX(int x)
    {
        this.x = x;
    }
    
    public void setY(int y)
    {
        this.y = y;
    }
    
    public int getHeight()
    {
        return height;
    }

    @Override
    public void render(Graphics2D graphics, float interpolation)
    {
        graphics.drawImage(image, x, y, width, height, null);
    }
}
