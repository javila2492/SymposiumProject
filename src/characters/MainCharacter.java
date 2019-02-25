package characters;

public class MainCharacter
{
    int atk;
    int def;
    int spd;
    int vis;

    public MainCharacter(Character choice)
    {
        atk = choice.getAtk();
        def = choice.getDef();
        spd = choice.getSpd();
        vis = choice.getVis();
    }
}
