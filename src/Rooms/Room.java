package Rooms;

import java.util.ArrayList;

public class Room
{
    String roomName;
    boolean canGoNorth;
    boolean canGoEast;
    boolean canGoSouth;
    boolean canGoWest;
    boolean lit;
    String image;
    ArrayList<String> items;
    boolean hasPlants;

    public Room(String roomName, boolean canGoNorth, boolean canGoEast, boolean canGoSouth, boolean canGoWest, boolean lit, String image, ArrayList<String> newItems, boolean hasPlants)
    {
        this.roomName = roomName;
        this.canGoNorth = canGoNorth;
        this.canGoEast = canGoEast;
        this.canGoSouth = canGoSouth;
        this.canGoWest = canGoWest;
        this.lit = lit;
        this.image = image;
        items.addAll(newItems);
        this.hasPlants = hasPlants;
    }

    public String itemTaken(int idx)
    {
        return items.remove(idx);

    }
}
