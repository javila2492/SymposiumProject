package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

public class Vin extends Character
{
    public Vin()
    {
        atk = 10;
        def = 12;
        spd = 17;
        vis = 11;
        mainImg = new Image("images/vin.png", 100, 100, true, true);
        specialDialog = new String[][]{{"entrance", "Jeez. Sweet sweet nostalgia huh. This was the place I did my first show. Now, it's nothing but ruin."},
                {"main area", "These halls used to echo with the sound of our music. It feels so...hopeless...looking at it now."}, {"stage", "There's still marks here from when we used to play here." +
                "I can still remember exactly where each of us stood."}, {"backstage", "This is where it happened. This is where I became a prisoner of that...thing..."}};

        characterDesc = "Vin, the lead singer of the band, with a silent yet strong nature. He’s been through the most, from being possessed and hurting his friends to messy breakups. " +
                "This poor man has been through a lot, but his experience with life comes in handy.";
        abilityDesc = "In The Garden: Allows Vin to control plants. Rooms with plants in them can be used to scour for objects or set up traps against the fiend.";
     }
    public void useAbility()
    {
        /*
        if(!GUIController.aMap[xPos][yPos].hasPlants)
        {

        }
        */
    }

}
