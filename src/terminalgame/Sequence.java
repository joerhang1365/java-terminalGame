
package terminalgame;

public class Sequence
{
    private Object[] actions;
    private int index;
    
    public Sequence(Object[] actions)
    {
        this.actions = actions;
        index = 1;
    }
    
    public Object[] getActions()
    {
        return actions;
    }
    
    public int getIndex()
    {
        return index;
    }
    
    public void incrementIndex(int i)
    {
        index += i;
    }
}
