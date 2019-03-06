package Rooms;

public class Map
{
    public static Room[][] areaMap = new Room[4][4];

    public Map()
    {

        Room entrance = new Room("Entrance", true, false, true, false, false, "entrance.png", null, true, 1, 3);
        areaMap[1][3] = entrance;

        Room mainArea = new Room("Main Area", true, true, true, true, false, "mainarea.png", null, true, 1, 2);
        areaMap[1][2] = mainArea;

        Room stage = new Room("Stage", true, false, true, true, false, "stage.png", null, false, 1, 1);
        areaMap[1][1] = stage;

        Room backstage = new Room("Backstage", false, false, true, false, false, "backstage.png", null, true, 1, 0);
        areaMap[1][0] = backstage;

        Room eastWing = new Room("East Wing", true, true, true, true, false, "eastwing.png", null, false, 2, 2);
        areaMap[2][2] = eastWing;

        Room mensRoom = new Room("Men's Bathroom", false, false, true, false, false, "mensroom.png", null, false, 2, 1);
        areaMap[2][1] = mensRoom;

        Room womensRoom = new Room("Women's Bathroom", true, false, true, true, false, "womensroom.png", null, false, 2, 3);
        areaMap[2][3] = womensRoom;

        Room supplyCloset = new Room("Supply Closet", false, false, false, true, false, "supplycloset.png", null, false, 3, 2);
        areaMap[3][2] = supplyCloset;

        Room backStairwell = new Room("Back Stairwell", true, false, true, true, false, "backstairwell.png", null, true, 0, 0);
        areaMap[0][0] = backStairwell;

        Room westWing = new Room("West Wing", true, false, false, true, false, "stage.png", null, true, 0, 1);
        areaMap[1][0] = westWing;
    }

}
