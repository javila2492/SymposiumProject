package rooms;

public class Crowbar extends UsableObject
{
    public Crowbar()
    {
        super("Crowbar");
        unlocker = true;
        useMsg = "I managed to pry the door open.";
    }

    public void use()
    {
        return;
    }
}
