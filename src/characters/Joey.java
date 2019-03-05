package characters;

public class Joey extends Character
{
    public Joey()
    {
        atk = 12;
        def = 14;
        spd = 10;
        vis = 14;
        characterDesc = "Joey is the guitarist of the group, with a shy soul but strong will. Even after \nso long, his encounter with the fiend " +
                "gives him nightmares. He's tough, but he'd rather bail \nout if things get messy.";
        abilityDesc = "Hazy: Joey can turn himself into mist in order to evade attacks and quickly move \nto other rooms regardless of proximity." +
                " He can’t attack or pick up items in this state, \nand drops everything in his inventory upon use, forcing him to go back and pick it up later on.";
    }

    public void useAbility()
    {

    }
}
