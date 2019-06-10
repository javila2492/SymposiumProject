package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

public class Danny extends Character
{
    public Danny()
    {
        name = "Danny";
        atk = 10;
        def = 12;
        spd = 17;
        vis = 11;
        imgName = "images/vin";
        mainImg = new Image("images/vin.png", 100, 100, true, true);

        specialDialog = new String[][]{{"entrance", "Jeez. Sweet sweet nostalgia huh. This was the place I did my first show. Now, it's nothing but ruin."},
                {"main area", "These halls used to echo with the sound of our music. It feels so...hopeless...looking at it now."}, {"stage", "There's still marks here from when we used to play here." +
                "I can still remember exactly where each of us stood."}, {"backstage", "This is where it happened. This is where I became a prisoner of that...thing..."}, {"east wing", "This place" +
                " reeks! Like if something died in here...something probably did."}, {"men's bathroom", "The sticky floor is a telltale sign that men in fact can not aim."}, {"women's bathroom", "I" +
                " don't feel comfortable being here..."}, {"supply closet", "Oh...I'm going to be sick..."}, {"stairwell", "I...I...oh god " +
                "I...I don't like being here..."}, {"west wing a", "This is the worst part for me. Something about the stairwell in the distance...scares me..."}, {"west wing b", "It's getting hard to see..."},
                {"wall", "Can't go that way."}, {"fiend", "The pain...it's all coming back..."}};

        characterDesc = "Danny, the lead singer of the band, with a silent yet strong nature. He’s been through the most, from being possessed and hurting his friends to messy breakups. " +
                "This poor man has been through a lot, but his experience with life comes in handy.";
        abilityDesc = "In The Garden: Allows Danny to control plants. Rooms with plants in them can be used to scour for objects or set up traps against the fiend.";
     }
    public String useAbility()
    {
        if(!GUIController.getCurrentRoom().hasPlants)
        {
            return "No plants for me to use.";
        }
        if(GUIController.inSameRoom())
        {
            GUIController.enemy.x = (int) (Math.random() * 3);
            GUIController.enemy.y = (int) (Math.random() * 3);
            if(GUIController.getCurrentRoom().lit)
                GUIController.enemy.hp -= (int) (Math.random() * 40);
            return "OSHAAAA! Blasted that creep far away from me.";
        }
        GUIController.getCurrentRoom().hasPlants = false;
        return "In The Garden! " + GUIController.visSearch(50, xPos, yPos);
    }

}
