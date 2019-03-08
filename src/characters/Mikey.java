package characters;

import javafx.scene.image.Image;

public class Mikey extends Character
{
    public Mikey()
    {
        atk = 15;
        def = 16;
        spd = 9;
        vis = 10;
        mainImg = new Image("images/mikey.png", 100, 100, true, true);
        characterDesc = "Mikey, drummer and best friend of Vin. Gruff and tough, this guy has quite a bit of bulk to him." +
                " Unfortunately, his rage can be quite dangerous if left unchecked. Careful! Though he’s angry, he’s got a soft heart.";
        abilityDesc = "Roid Rage: A meter will display, increasing for every failed action. Once full, it can be used to demolish heavy objects or damage the enemy in return for half the meter." +
                "If the meter isn’t used when full by three actions, a random object will be broken.";
        int rage = 0;
     }

    public void useAbility()
    {

    }
}
