package rooms;

import scenes.GUIController;

public class BrokenBottle extends UsableObject
{
    public BrokenBottle()
    {
        super("Broken Bottle");
        useMsg = "I managed to ward off the fiend with the bottle!";
    }

    public void use()
    {
        GUIController.enemy.x = (int) (Math.random() * 3);
        GUIController.enemy.y = (int) (Math.random() * 3);
        if(GUIController.getCurrentRoom().lit)
            GUIController.enemy.hp -= 15;
    }
}
