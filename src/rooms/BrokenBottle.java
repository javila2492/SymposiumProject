package rooms;

import scenes.GUIController;

public class BrokenBottle extends UsableObject
{

    public BrokenBottle(String name)
    {
        super(name);
        useMsg = "I managed to ward off the fiend with the bottle!";
    }

    public void use()
    {
        GUIController.enemy.x = (int) (Math.random() * 3);
        GUIController.enemy.y = (int) (Math.random() * 3);
    }
}
