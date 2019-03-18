import javafx.scene.image.Image;
import scenes.GUIController;

import java.util.concurrent.TimeUnit;

public class Fiend
{
    int atk;
    int hp = 100;
    int x;
    int y;
    Image sprite;

    public Fiend(int x, int y)
    {
        atk = 20;
        this.x = x;
        this.y = y;
        Image sprite = new Image("images/fiendsprite.png");
    }

    public void fiendTelepathy()
    {
        if(x == GUIController.mainCharacter.xPos && y == GUIController.mainCharacter.yPos)
        {
            try { TimeUnit.SECONDS.wait(5); } catch (InterruptedException e) { e.printStackTrace(); }
            if(x == GUIController.mainCharacter.xPos && y == GUIController.mainCharacter.yPos)
                GUIController.dealDamage();
        }
    }

    public void fiendChase()
    {
        if(x == GUIController.mainCharacter.xPos && y == GUIController.mainCharacter.yPos)
        {
            try { TimeUnit.SECONDS.wait(5); } catch (InterruptedException e) { e.printStackTrace(); }
            if(!(x == GUIController.mainCharacter.xPos && y == GUIController.mainCharacter.yPos))

        }
    }
}
