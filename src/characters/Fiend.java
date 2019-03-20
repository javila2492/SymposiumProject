package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

import java.util.concurrent.TimeUnit;

public class Fiend
{
    int atk;
    int hp = 100;
    public int x;
    public int y;
    Image sprite;

    public Fiend(int x, int y)
    {
        atk = 20;
        this.x = x;
        this.y = y;
        Image sprite = new Image("images/fiendsprite.png");
    }

}
