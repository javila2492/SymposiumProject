package characters;

import javafx.scene.image.Image;
import scenes.GUIController;

import static scenes.GUIController.aMap;

public class Sabrina extends Character
{
    public Sabrina()
    {
        name = "Sabrina";
        atk = 11;
        def = 11;
        spd = 14;
        vis = 14;
        imgName = "images/sabrina";
        mainImg = new Image("images/sabrina.png", 100, 100, true, true);

        specialDialog = new String[][]{{"entrance", "What a dump! Not even the cafeteria's attempt at tacos smell this bad!"}, {"main area", "I played here once! Granted it " +
                "wasn't my body...but I still had fun!"}, {"stage", "Scuffs, huh? I think I stood on the right? The trauma kinda clouds that memory out."},
                {"backstage", "Oh man...this place. I don't like this place."}, {"east wing", "Oh god! I can smell the bathrooms from here! Gross!"},
                {"men's bathroom", "Is this the so called \"juul room\"? No...this place smells like they've smoked worse!"}, {"women's bathroom", "I'd go right now if it weren't for the fiend chasing me right now!"},
                {"supply closet", "I really hope those bodies aren't real."}, {"stairwell", "Nope. Not going down there."}, {"west wing a", "Feels like what walking through the empty halls after school feels like."},
                {"west wing b", "Don't like that mist."}, {"empty space", "....I need to get out."}, {"wall", "No door here!"}, {"fiend", "Those eyes...I'm getting flashbacks."}};

        characterDesc = "Sabrina, the unfortunate highschooler that got tied into this mess. Though she'd rather be studying, she's willing " +
                "to take a hit to her attendance if it means nobody ever has to go through what she did ever again.";
        abilityDesc = "Another Light: Sabrina can create and manipulate light to make items easier to find as well as making the fiend vulnerable to damage.";
    }

    public String useAbility()
    {
        if(aMap[xPos][yPos].lit)
            vis += 5;
        aMap[xPos][yPos].lit = true;
        return "Another Light! Make this room bright and bright!";
    }
}
