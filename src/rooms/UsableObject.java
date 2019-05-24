package rooms;

public abstract class UsableObject
{
    public String objName;
    public boolean unlocker = false;
    public String useMsg;

    public UsableObject(String a)
    {
        objName = a;
    }

    public abstract void use();
}
