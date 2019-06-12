package rooms;

import java.util.ArrayList;

public class Map
{

    public Room[][] areaMap;

    public Map()
    {
        areaMap = new Room[4][4];
        ArrayList<UsableObject> mainAreaItems = new ArrayList<>();
        mainAreaItems.add(new Crowbar());
        mainAreaItems.add(new BrokenBottle());

        ArrayList<UsableObject> backstageItems = new ArrayList<>();
        mainAreaItems.add(new GlassShard());

        ArrayList<UsableObject> wBathroomItems = new ArrayList<>();
        wBathroomItems.add(new GlassShard());
        wBathroomItems.add(new GlassShard());

        ArrayList<UsableObject> mBathroomItems = new ArrayList<>();
        mBathroomItems.add(new GlassShard());
        mBathroomItems.add(new BrokenBottle());

        ArrayList<UsableObject> supplyClosetItems = new ArrayList<>();
        supplyClosetItems.add(new Key());

        ArrayList<UsableObject> hiddenItems = new ArrayList<>();
        hiddenItems.add(new GlassShard());

        ArrayList<UsableObject> westItems = new ArrayList<>();
        westItems.add(new GlassShard());

        Room entrance = new Room("Entrance", true, false, false, false, false, "entrance.png", null, true);
        areaMap[1][3] = entrance;

        Room mainArea = new Room("Main Area", true, true, true, true, false, "mainarea.png", mainAreaItems, true);
        areaMap[1][2] = mainArea;

        Room stage = new Room("Stage", true, false, true, true, false, "stage.png", null, false);
        areaMap[1][1] = stage;

        Room backstage = new Room("Backstage", false, false, true, false, false, "backstage.png", backstageItems, true, "key", "Looks like I need a key to get in here.", true, new FuseBox());
        areaMap[1][0] = backstage;

        Room eastWing = new Room("East Wing", true, true, true, true, false, "eastwing.png", null, false);
        areaMap[2][2] = eastWing;

        Room mensRoom = new Room("Men's Bathroom", true, false, true, false, false, "men'sbathroom.png", mBathroomItems, false);
        areaMap[2][1] = mensRoom;

        Room womensRoom = new Room("Women's Bathroom", true, false, true, false, false, "women'sbathroom.png", wBathroomItems, false);
        areaMap[2][3] = womensRoom;

        Room supplyCloset = new Room("Supply Closet", false, false, false, true, false, "supplycloset.png", supplyClosetItems, false,"crowbar", "The door's jammed shut. I'll need something to pry it open.", true);
        areaMap[3][2] = supplyCloset;

        Room stairwell = new Room("Stairwell", false, false, true, false, false, "stairwell.png", null, true);
        areaMap[0][0] = stairwell;

        Room westWingA = new Room("West Wing A", true, true, false, false, false, "westwinga.png", westItems, true, new Hook());
        areaMap[0][2] = westWingA;

        Room westWingB = new Room("West Wing B", true, false, true, false, false, "westwingb.png", null, true);
        areaMap[0][1] = westWingB;

        for(int i = 0; i < areaMap.length; i++)
            for(int j = 0; j < areaMap[i].length; j++)
                if(areaMap[i][j] == null)
                    areaMap[i][j]  = new Room("Empty Space", true, true, true, true, false, "emptyspace.png", hiddenItems, false);
        areaMap[0][3].canGoSouth = false;
        areaMap[0][3].canGoWest = false;
        areaMap[3][3].canGoSouth = false;
        areaMap[3][3].canGoEast = false;
        areaMap[3][0].canGoNorth = false;
        areaMap[3][0].canGoEast = false;
    }


}
