package characters;

import javafx.scene.image.Image;

public abstract class Character
{
    static int hp = 100;
    int atk;
    int def;
    int spd;
    int vis;
    String characterDesc;
    String abilityDesc;
    Image[] healthIndicator = new Image[5];
    Image mainImg;
    public String[][] specialDialog;
    public int xPos;
    public int yPos;

    public int getAtk() { return atk; }
    public int getDef() { return def; }
    public int getSpd() { return spd; }
    public int getVis() { return vis; }
    public Image getMainImg() { return mainImg;}
    public String getCharacterDesc() { return characterDesc; }
    public String getAbilityDesc() { return abilityDesc; }
    public String statBuilderShower() { return "ATK: " + getAtk() + "\n" + "DEF: " + getDef() + "\n" + "SPD: " + getSpd() + "\n" + "VIS: " + getVis(); }
    public void changeX(int newx) { xPos = newx; }
    public void changeY(int newy) { yPos = newy; }

    public Image getCurrentHealthIndicator()
    {
        if(hp < 20)
            return healthIndicator[4];
        if(hp < 40)
            return healthIndicator[3];
        if(hp < 60)
            return healthIndicator[2];
        if(hp < 80)
            return healthIndicator[1];
        return healthIndicator[0];
    }

    public void takeDamage(int dmg)
    {
        int a = dmg;
        hp -= a - ((Math.random() * getDef()) + (getDef() / (Math.random() * 3)));
    }

    public void changePos(int x, int y)
    {
        xPos = x;
        yPos = y;
    }

    public abstract void useAbility();
}
