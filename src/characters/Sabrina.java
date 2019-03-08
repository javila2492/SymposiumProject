package characters;

import javafx.scene.image.Image;

public class Sabrina extends Character
{
    public Sabrina()
    {
        atk = 11;
        def = 11;
        spd = 14;
        vis = 14;
        mainImg = new Image("images/sabrina.png", 100, 100, true, true);
        characterDesc = "Sabrina, the unfortunate highschooler that got tied into this mess. Though she'd rather be studying, she's willing " +
                "to take a hit to her attendance if it means nobody ever has to go through what she did ever again.";
        abilityDesc = "Another Light: Sabrina can create and manipulate light to make items easier to find. " +
                "She can also focus the light into a powerful laser that can disorient enemies and burn through walls.";
    }

    public void useAbility()
    {

    }
}
