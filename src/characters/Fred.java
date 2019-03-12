package characters;

import javafx.scene.image.Image;

public class Fred extends Character
{
    public Fred()
    {
        name = "Fred";
        atk = 20;
        def = 20;
        spd = 5;
        vis = 5;
        mainImg = new Image("images/fred.png", 100, 100, true, true);
        specialDialog = new String[][]{{"entrance", "Yeah! This place again!"}, {"main area", "So big! Like me!"}, {"stage", "The place I played has the deepest marks!"},
                {"backstage", "I stabbed someone here once! I also almost died here twice!"}, {"east wing", "Smells like Kentucky!"},
                {"men's bathroom", "Garbage...truly a reminder to the futility of our existence!"}, {"women's bathroom", "Ma, if you're watching from above, please don't judge!"},
                {"supply closet", "Can barely fit in here!"}, {"stairwell", "Don't like that..."}, {"west wing", "This place could use company!"}};

        characterDesc = "Fred is a synth player as well as the bulkiest man you'll meet. His extreme toughness makes up for his rather naive nature, " +
                "but make sure to never say that around him!";
        abilityDesc = "Memento Mori: Fred can fetch the memories of objects and rooms in order to create temporary duplicates and see where missing pieces might be hidden.";
    }
    public void useAbility()
    {

    }
}
