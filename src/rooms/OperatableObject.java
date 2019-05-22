package rooms;

public abstract class OperatableObject
{
    public String objName;
    boolean functional = true;

    public OperatableObject(String a)
    {
        objName = a;
    }

    public abstract String operate();

    public void turnOff()
    {
        functional = false;
    }
}
