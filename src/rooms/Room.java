package rooms;

import com.sun.istack.internal.NotNull;

import java.util.ArrayList;

public class Room
{
    public String roomName;
    public boolean canGoNorth;
    public boolean canGoEast;
    public boolean canGoSouth;
    public boolean canGoWest;
    public boolean lit;
    public String image;
    public ArrayList<String> items = new ArrayList<String>();
    public boolean hasPlants;

    public Room(String roomName, boolean canGoNorth, boolean canGoEast, boolean canGoSouth, boolean canGoWest, boolean lit, String image, ArrayList<String> newItems, boolean hasPlants, int x, int y)
    {
        this.roomName = roomName;
        this.canGoNorth = canGoNorth;
        this.canGoEast = canGoEast;
        this.canGoSouth = canGoSouth;
        this.canGoWest = canGoWest;
        this.lit = lit;
        this.image = image;
        if(newItems != null)
        {
            System.out.println(newItems);
            items.addAll(newItems);
        }
        this.hasPlants = hasPlants;
    }

    public String itemTaken(int idx)
    {
        return items.remove(idx);
    }
}
