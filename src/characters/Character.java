package characters;

import javafx.scene.image.Image;
import rooms.UsableObject;

import java.util.ArrayList;

public abstract class Character
{
    public static int hp = 100;
    public String name;
    int atk;
    int def;
    int spd;
    int vis;
    String characterDesc;
    String abilityDesc;
    public ArrayList<UsableObject> inventory = new ArrayList<UsableObject>();
    Image mainImg;
    public String[][] specialDialog;
    public int xPos;
    public int yPos;

    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getSpd() { return spd; }
    public int getVis() { return vis; }
    public String imgName;
    public Image getMainImg() { return mainImg;}
    public String getCharacterDesc() { return characterDesc; }
    public String getAbilityDesc() { return abilityDesc; }
    public String statBuilderShower() { return "ATK: " + getAtk() + "\n" + "DEF: " + getDef() + "\n" + "SPD: " + getSpd() + "\n" + "VIS: " + getVis(); }
    public void changeX(int newx) { xPos = newx; }
    public void changeY(int newy) { yPos = newy; }

    public Image getCurrentHealthIndicator()
    {
        if(hp < 20)
            return new Image(imgName + "_hurt_4.png",100, 100, true, true);
        if(hp < 40)
            return new Image(imgName + "_hurt_3.png",100, 100, true, true);
        if(hp < 60)
            return new Image(imgName + "_hurt_2.png",100, 100, true, true);
        if(hp < 80)
            return new Image(imgName + "_hurt_1.png",100, 100, true, true);
        return mainImg;
    }

    public void takeDamage(int dmg)
    {
        double a = dmg * ((double) 20 / (20 + getDef()));
        if(a <= 0)
            a = 1;
        hp -= a;
        if(hp < 0)
            hp = 0;
    }

    public void changePos(int x, int y)
    {
        xPos = x;
        yPos = y;
    }

    public abstract String useAbility();
}
