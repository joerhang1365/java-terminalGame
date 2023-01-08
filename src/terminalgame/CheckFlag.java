
package terminalgame;

public class CheckFlag
{
    private Sequence actions;
    private boolean flag;
    
    public CheckFlag(boolean flag, Sequence actions)
    {
        this.actions = actions;
        this.flag = flag;
    }
    
    public boolean getFlag()
    {
        return flag;
    }
    
    public Sequence getActions()
    {
        return actions;
    }
}
