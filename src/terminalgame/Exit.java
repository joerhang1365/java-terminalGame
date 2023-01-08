
package terminalgame;

public class Exit implements Updatable
{
    @Override
    public void update(InputHandler inputHandler)
    {
        System.exit(0);
    }
}
