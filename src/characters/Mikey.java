package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

public class Mikey extends Character
{
    public Mikey()
    {
        name = "Mikey";
        atk = 15;
        def = 16;
        spd = 9;
        vis = 10;
        imgName = "images/mikey";
        mainImg = new Image("images/mikey.png", 100, 100, true, true);

        specialDialog = new String[][]{{"entrance", "God this place is a mess! Nobody could be bothered to clean around here?"}, {"main area", "This is the " +
                "place, no mistaking it. I know since PEOPLE ALWAYS LEAVE THEIR TRASH EVERYWHERE."}, {"stage", "Owner never fixed this place so that even now you see our nasty footprints!"},
                {"backstage", "Three stabbings happened here. I caused two of em."}, {"east wing", "Do I really gotta be here?"}, {"men's bathroom", "No monster is scarier than the mess men leave in these bathrooms."},
                {"women's bathroom", "Now this is where it's at! Wait that doesn't sound right."}, {"supply closet", "So much dust! I'll sneeze at any moment."}, {"stairwell", "No way " +
                "in HELL am I going there."}, {"west wing", "Finally some peace and quiet."}, {"wall", "OW! There's a stupid wall here!"}, {"fiend", "That telekinetic bastard has arrived..."}};

        characterDesc = "Mikey, drummer and best friend of Vin. Gruff and tough, this guy has quite a bit of bulk to him." +
                " Unfortunately, his rage can be quite dangerous if left unchecked. Careful! Though he’s angry, he’s got a soft heart.";
        abilityDesc = "Roid Rage: A meter will display, increasing for every failed action. Once full, it can be used to demolish heavy objects or damage the enemy in return for half the meter." +
                "If the meter isn’t used when full by three actions, a random object will be broken.";
     }

    public void useAbility()
    {

    }
}
