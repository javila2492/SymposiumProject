package rooms;

public class Key extends UsableObject
{

    public Key()
    {
        super("Key");
        unlocker = true;
        useMsg = "Click! The door's unlocked.";
    }

    public void use()
    {
        return;
    }
}
