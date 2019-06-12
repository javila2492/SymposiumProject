package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

import java.util.ArrayList;

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
                {"women's bathroom", "Now this is where it's at! Wait that doesn't sound right."}, {"supply closet", "Oh dear lord!"}, {"stairwell", "No way " +
                "in HELL am I going there."}, {"west wing a", "Finally some peace and quiet."}, {"west wing b", "Doesn't look so peaceful no more."}, {"wall", "OW! There's a stupid wall here!"},
                {"fiend", "That telekinetic bastard has arrived..."}, {"fiend", "No! Get away!"}, {"empty space", "I don't wanna be here."}};

        characterDesc = "Mikey, drummer and best friend of Danny. Gruff and tough, this guy has quite a bit of bulk to him." +
                " Unfortunately, his rage can be quite dangerous if left unchecked. Careful! Though he’s angry, he’s got a soft heart.";
        abilityDesc = "Roid Rage: A meter will display, increasing for every failed action. Once full, it can be used to demolish walls or damage the enemy." +
                "If the meter isn’t used when full by three actions, a random object will be broken.";
     }

    public String useAbility()
    {
        if(GUIController.rageCount < 1)
            return "I'm not angry enough yet.";
        GUIController.rageCount = 0;
        if(GUIController.inSameRoom())
        {
            GUIController.enemy.x = (int) (Math.random() * 3);
            GUIController.enemy.y = (int) (Math.random() * 3);
            if(GUIController.getCurrentRoom().lit)
                GUIController.enemy.hp -= (int) (Math.random() * 40);
            return "SPEEYEAAAAAH! Gotcha! That bastard's gonna be feeling that for a while!";
        }
        int a = 0;
        boolean validBreak = false;
        ArrayList<Integer> invalidWalls = new ArrayList<>();
        if(yPos == 0)
            invalidWalls.add(0);
        if(xPos == 3)
            invalidWalls.add(1);
        if(yPos == 3)
            invalidWalls.add(2);
        if(xPos == 0)
            invalidWalls.add(3);

        while(!validBreak)
        {
            validBreak = true;
            a = (int) (Math.random() * 3);
            for(Integer x : invalidWalls)
                if(a == x)
                    validBreak = false;
            if(validBreak)
            {
                if(a == 0)
                {
                    GUIController.aMap[xPos][yPos].canGoNorth = true;
                    GUIController.aMap[xPos][yPos - 1].canGoSouth = true;
                    return "WRYYYYEAHH! I broke down the north wall!";
                }
                if(a == 1)
                {
                    GUIController.aMap[xPos][yPos].canGoEast = true;
                    GUIController.aMap[xPos + 1][yPos].canGoWest = true;
                    return "WRYYYYEAHH! I broke down the east wall!";
                }
                if(a == 2)
                {
                    GUIController.aMap[xPos][yPos].canGoSouth = true;
                    GUIController.aMap[xPos][yPos + 1].canGoNorth= true;
                    return "WRYYYYEAHH! I broke down the south wall!";
                }
                if(a == 3)
                {
                    GUIController.aMap[xPos][yPos].canGoWest = true;
                    GUIController.aMap[xPos - 1][yPos].canGoEast = true;
                    return "WRYYYYEAHH! I broke down the west wall!";
                }
            }
        }
        return "";
    }
}
