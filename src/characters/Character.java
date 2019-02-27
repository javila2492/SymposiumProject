package characters;

import javafx.scene.image.Image;

public abstract class Character
{
    private static int hp = 100;
    private static int atk;
    private static int def;
    private static int spd;
    private static int vis;
    private static String characterDesc;
    private static String abilityDesc;
    private static Image[] healthIndicator = new Image[5];
    private static Image mainImg;
    public int xPos;
    public int yPos;

    public static int getAtk() { return atk; }
    public static int getDef() { return def; }
    public static int getSpd() { return spd; }
    public static int getVis() { return vis; }
    public static Image getMainImg() { return mainImg;}
    public static String getCharacterDesc() { return characterDesc; }
    public static String getAbilityDesc() { return abilityDesc; }
    public static String statBuilderShower() { return "ATK: " + getAtk() + "\n" + "DEF: " + getDef() + "\n" + "SPD: " + getSpd() + "\n" + "VIS: " + getVis(); }

    public static Image getCurrentHealthIndicator()
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

    public static void takeDamage(int dmg)
    {
        int a = dmg;

    }

    public void changePos(int x, int y)
    {
        xPos = x;
        yPos = y;
    }

    public abstract void useAbility();
}
