package characters;

public class Mikey extends Character
{
    private int atk = 15;
    private int def = 16;
    private int spd = 9;
    private int vis = 10;
    private String characterDesc = "Drummer and best friend of Vin. Gruff and tough, this guy has quite a bit of bulk to him. " +
            "Unfortunately, his rage can be quite dangerous if left unchecked. Careful! Though he’s angry, he’s got a soft heart.";
    private String abilityDesc = "Roid Rage: A meter will stay near the hotbar, increasing for every failed action. Once full, it can be used to demolish heavy objects in return for half the meter. " +
            "If the meter isn’t used when full by three actions, a random object in the room or inventory will be demolished. There is a chance he will break the screen too. This ability can be used against the enemy.";
    private static String[] healthIndicator = {"Koala.jpg"};
    public int rage = 0;

    public void useAbility()
    {

    }
}
