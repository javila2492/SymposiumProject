package rooms;

import scenes.GUIController;

public class GlassShard extends UsableObject
{
    public GlassShard()
    {
        super("Glass Shard");
        useMsg = "Ah! I managed to ward off the fiend but cut myself in the process.";
    }

    public void use()
    {
        GUIController.enemy.x = (int) (Math.random() * 3);
        GUIController.enemy.y = (int) (Math.random() * 3);
        GUIController.mainCharacter.takeDamage(10);
        GUIController.enemy.hp -= 20;
    }
}
