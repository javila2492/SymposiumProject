package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

public class Fred extends Character
{
    public Fred()
    {
        name = "Fred";
        atk = 20;
        def = 20;
        spd = 5;
        vis = 5;
        imgName = "images/fred";
        mainImg = new Image("images/fred.png", 100, 100, true, true);

        specialDialog = new String[][]{{"entrance", "Yeah! This place again!"}, {"main area", "So big! Like me!"}, {"stage", "The place I played has the deepest marks!"},
                {"backstage", "I stabbed someone here once! I also almost died here twice!"}, {"east wing", "Smells like Ohio!"},
                {"men's bathroom", "Garbage...truly a reminder of the futility of our existence!"}, {"women's bathroom", "Ma, if you're watching from above, please don't judge!"},
                {"supply closet", "Can barely fit in here!"}, {"stairwell", "Don't like that..."}, {"empty space", "I think it's better if I can't see."}, {"west wing a", "This place could use company!"}, {"west wing b", "..."},
                {"wall", "Ouch! There's this large flat surface blocking me. I think it's called a \"wall\"!"}, {"fiend", "AAAH! THE FIEND! IT'S HERE!"}};

        characterDesc = "Fred is a synth player as well as the bulkiest man you'll meet. His extreme toughness makes up for his rather naive nature, " +
                "but make sure to never say that around him!";
        abilityDesc = "Memento Mori: Fred can fetch the memories of objects and rooms in order to see where missing pieces might be hidden and what items may be needed.";
    }
    public String useAbility()
    {
        String needed = "";
        if(yPos > 0 && (GUIController.aMap[xPos][yPos - 1] != null && GUIController.aMap[xPos][yPos - 1].neededThing != null))
            needed = GUIController.aMap[xPos][yPos - 1].neededThing;
        if(yPos < 3 && (GUIController.aMap[xPos][yPos + 1] != null && GUIController.aMap[xPos][yPos + 1].neededThing != null))
            needed = GUIController.aMap[xPos][yPos + 1].neededThing;
        if(xPos > 0 && (GUIController.aMap[xPos - 1][yPos] != null && GUIController.aMap[xPos - 1][yPos].neededThing != null))
            needed = GUIController.aMap[xPos - 1][yPos].neededThing;
        if(xPos < 3 && (GUIController.aMap[xPos - 1][yPos] != null && GUIController.aMap[xPos + 1][yPos].neededThing != null))
            needed = GUIController.aMap[xPos + 1][yPos].neededThing;
        if(!needed.equals(""))
            return "I'm going to need a " + needed + ". I know for a fact that important things are strewn about the main area and supply closet.";
        return "Looks like nothing is needed for now.";
    }
}
