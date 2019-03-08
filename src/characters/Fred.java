package characters;

import javafx.scene.image.Image;

public class Fred extends Character
{
    public Fred()
    {
        atk = 20;
        def = 20;
        spd = 5;
        vis = 5;
        mainImg = new Image("images/fred.png", 100, 100, true, true);
        characterDesc = "Fred is a synth player as well as the bulkiest man you'll meet. His \nextreme toughness makes up for his rather naive nature, " +
                "but make sure to never say that around him!";
        abilityDesc = "Memento Mori: Fred can fetch the memories of objects and rooms in order to create \ntemporary duplicates and see where missing pieces might be hidden.";
    }
    public void useAbility()
    {

    }
}
