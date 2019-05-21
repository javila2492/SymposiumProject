package rooms;

import java.util.ArrayList;
import java.util.Arrays;

import static scenes.GUIController.aMap;

public class Map
{

    public Room[][] areaMap;

    public Map()
    {
        areaMap = new Room[4][4];
        ArrayList<String> mainAreaItems = new ArrayList<String>();
        mainAreaItems.add("Crowbar");
        mainAreaItems.add("Broken Bottle");

        ArrayList<String> backstageItems = new ArrayList<String>();
        backstageItems.add("Glass Shard");
        ArrayList<String> backstageOps = new ArrayList<String>();
        backstageOps.add("Fuse Box");

        ArrayList<String> supplyClosetItems = new ArrayList<String>();
        supplyClosetItems.add("Key");

        Room entrance = new Room("Entrance", true, false, false, false, false, "entrance.png", null, true);
        areaMap[1][3] = entrance;

        Room mainArea = new Room("Main Area", true, true, true, true, false, "mainarea.png", mainAreaItems, true);
        areaMap[1][2] = mainArea;

        Room stage = new Room("Stage", true, false, true, true, false, "stage.png", null, false);
        areaMap[1][1] = stage;

        Room backstage = new Room("Backstage", false, false, true, false, false, "backstage.png", backstageItems, true, "key", "Looks like I need a key to get in here.", true, backstageOps);
        areaMap[1][0] = backstage;

        Room eastWing = new Room("East Wing", true, true, true, true, false, "eastwing.png", null, false);
        areaMap[2][2] = eastWing;

        Room mensRoom = new Room("Men's Bathroom", false, false, true, false, false, "men'sbathroom.png", null, false);
        areaMap[2][1] = mensRoom;

        Room womensRoom = new Room("Women's Bathroom", true, false, true, true, false, "women'sbathroom.png", null, false);
        areaMap[2][3] = womensRoom;

        Room supplyCloset = new Room("Supply Closet", false, false, false, true, false, "supplycloset.png", supplyClosetItems, false,"crowbar", "The door's jammed shut. I'll need something to pry it open.", true);
        areaMap[3][2] = supplyCloset;

        Room stairwell = new Room("Stairwell", false, false, true, false, false, "stairwell.png", null, true);
        areaMap[0][0] = stairwell;

        Room westWingA = new Room("West Wing A", true, true, false, false, false, "westwinga.png", null, true);
        areaMap[0][2] = westWingA;

        Room westWingB = new Room("West Wing B", true, false, true, false, false, "westwingb.png", null, true);
        areaMap[0][1] = westWingB;
    }

    public void fuseUse()
    {
        for(Room[] a : areaMap)
            for(Room b : a)
                if(b != null)
                    b.lit = true;
    }

}
