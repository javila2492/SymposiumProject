package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

import static scenes.GUIController.aMap;

public class Joey extends Character
{
    public Joey()
    {
        name = "Joey";
        atk = 12;
        def = 14;
        spd = 10;
        vis = 14;
        imgName = "images/joey";
        mainImg = new Image("images/joey.png", 100, 100, true, true);

        specialDialog = new String[][]{{"entrance", "God I hope I survive. Why did I say that?"}, {"main area", "Being part of the crowd in the wrong body was..." +
                "something..."}, {"stage", "I'm so sad I wasn't here for our last show in this area."}, {"backstage", "This is where it all went down..."},
                {"east wing", "Why didn't Vin choose a more sanitary place to play in?"}, {"men's bathroom", "I want to get out of here as soon as possible."},
                {"women's bathroom", "Oh dear. I don't think I should be here."}, {"supply closet", "If there isn't anything useful here I will cry."}, {"stairwell", "No no no no" +
                " no no no no no no no no no no."}, {"west wing a", "Too empty...it's too empty..."}, {"west wing b", "I see it in the distance..."}, {"wall", "There uh...seems to be a wall here."}, {"fiend", "Oh god! Run!"}};

        characterDesc = "Joey is the guitarist of the group, with a shy soul but strong will. Even after so long, his encounter with the fiend " +
                "gives him nightmares. He's tough, but he'd rather bail out if things get messy.";
        abilityDesc = "Hazy: Joey can turn himself into mist in order to evade attacks and quickly move to other rooms regardless of proximity." +
                " He can’t attack or pick up items in this state, and drops everything in his inventory upon use, forcing him to go back and pick it up later on.";
    }

    public String useAbility()
    {
        if(GUIController.hazy)
        {
            GUIController.hazy = false;
            return "And back to normal...";
        }
        aMap[xPos][yPos].items.addAll(inventory);
        inventory.clear();
        GUIController.hazy = true;
        return "Hazy! Clouds may cry but can never die...";
    }
}
