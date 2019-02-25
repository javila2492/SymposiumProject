package Rooms;

public class Map
{
    public static Room[][] mapOut = null;
    mapOut = new Room[4][4];

    Room entrance = new Room("Entrance", true, false, true, false, false, "entrance.png", null, true);
    mapOut[1][3] = entrance;
}
