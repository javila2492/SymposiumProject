package rooms;

import static scenes.GUIController.aMap;

public class FuseBox extends OperatableObject
{
    public FuseBox()
    {
        super("Fuse Box");
    }

    public String operate()
    {
        if(!functional)
            return "Looks broken...";
        for(Room[] a : aMap)
            for(Room b : a)
                if(b != null)
                    b.lit = true;
        super.turnOff();
        return "Success! The lights are on now.";
    }
}
