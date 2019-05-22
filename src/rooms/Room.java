package rooms;

import java.util.ArrayList;

public class Room
{
    public String roomName;
    public String truName;
    public boolean canGoNorth;
    public boolean canGoEast;
    public boolean canGoSouth;
    public boolean canGoWest;
    public boolean lit;
    public String image;
    public ArrayList<String> items = new ArrayList<String>();
    public boolean hasPlants;
    public String neededThing;
    public String lockedDesc;
    public boolean locked;
    public OperatableObject operatable;

    public Room(String roomName, boolean canGoNorth, boolean canGoEast, boolean canGoSouth, boolean canGoWest, boolean lit, String image, ArrayList<String> newItems, boolean hasPlants)
    {
        this.roomName = roomName;
        this.canGoNorth = canGoNorth;
        this.canGoEast = canGoEast;
        this.canGoSouth = canGoSouth;
        this.canGoWest = canGoWest;
        this.lit = lit;
        this.image = image;
        if(newItems != null)
            items.addAll(newItems);
        this.hasPlants = hasPlants;
        truName = roomName.toLowerCase().replaceAll("\\s", "");
    }

    public Room(String roomName, boolean canGoNorth, boolean canGoEast, boolean canGoSouth, boolean canGoWest, boolean lit, String image, ArrayList<String> newItems, boolean hasPlants, String neededThing, String lockedDesc, boolean locked)
    {
        this.roomName = roomName;
        this.canGoNorth = canGoNorth;
        this.canGoEast = canGoEast;
        this.canGoSouth = canGoSouth;
        this.canGoWest = canGoWest;
        this.lit = lit;
        this.image = image;
        if(newItems != null)
            items.addAll(newItems);
        this.hasPlants = hasPlants;
        this.neededThing = neededThing;
        this.lockedDesc = lockedDesc;
        this.locked = locked;
        truName = roomName.toLowerCase().replaceAll("\\s", "");
    }

    public Room(String roomName, boolean canGoNorth, boolean canGoEast, boolean canGoSouth, boolean canGoWest, boolean lit, String image, ArrayList<String> newItems, boolean hasPlants, String neededThing, String lockedDesc, boolean locked, OperatableObject operatable)
    {
        this.roomName = roomName;
        this.canGoNorth = canGoNorth;
        this.canGoEast = canGoEast;
        this.canGoSouth = canGoSouth;
        this.canGoWest = canGoWest;
        this.lit = lit;
        this.image = image;
        if(newItems != null)
            items.addAll(newItems);
        this.hasPlants = hasPlants;
        this.neededThing = neededThing;
        this.lockedDesc = lockedDesc;
        this.locked = locked;
        truName = roomName.toLowerCase().replaceAll("\\s", "");
        this.operatable = operatable;
    }
}
