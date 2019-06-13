package scenes;

import characters.Character;
import characters.Fiend;
import javafx.animation.*;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.util.Duration;
import rooms.Map;
import rooms.OperatableObject;
import rooms.Room;
import rooms.UsableObject;

import java.lang.reflect.Field;
import java.util.ArrayList;


public class GUIController
{
    @FXML private Pane background;
    @FXML private ImageView icon;
    @FXML private TextField type;
    @FXML private Label showtext;
    @FXML ImageView mapimg;
    @FXML Label roomname;
    @FXML Label ragetext;
    @FXML public ProgressBar ragemeter;
    @FXML Label invtext;
    @FXML ImageView dmgfx;
    @FXML Pane lights;
    @FXML Label objective;
    @FXML Label health;
    @FXML Label syntaxshower;
    @FXML Button enterbutton;
    @FXML ImageView finalscreen;
    @FXML ImageView mapicon;

    public static Character mainCharacter;
    public static double rageCount = 0;
    private boolean win = false;
    private int breakCount = 0;
    private int spdCount;
    private static Map temp = new Map();
    public static Fiend enemy = new Fiend(2, 0);
    private boolean rage = false;
    public static Room[][] aMap = temp.areaMap;
    private static String[] objectives = {"Get backstage and turn on the power.", "Find a key backstage.", "Find a way to open the supply closet.", "Get backstage and turn on the power.", "Kill the fiend."};
    private static String[][] cmdSyntax = {{"move", "Syntax: move (north, east, south, west)"}, {"search", "Syntax: search room"}, {"take", "Syntax: take [object that exists in room]"}, {"use", "Syntax: use [object in inventory]"}, {"operate", "Syntax: operate [non takeable object in room]"}, {"attack", "Syntax: attack"}};
    public static boolean hazy = false;
    private String invTextOut = "";
    private boolean firstTime = true;
    private boolean firstDTime = true;
    private boolean alive = true;

    /**
     * Initializes the program by setting the characters position and activating the fiend. If the character is Mikey, the rage meter activates.
     */
    public void initialize()
    {
        mainCharacter = CharSelectionController.chosen;
        mainCharacter.changePos(1, 3);
        spdCount = mainCharacter.getSpd() * 1000;
        icon.setImage(mainCharacter.getMainImg());
        if(mainCharacter.name.contains("Mikey"))
        {
            rageActive();
        }
        Tooltip tp = new Tooltip();
        Tooltip tp2 = new Tooltip();
        tp.setText("Commands: move, search, take, use, ability, operate, attack");
        hackTooltipStartTiming(tp);
        tp2.setText("Click on me after typing a commands for syntax help.");
        syntaxshower.setTooltip(tp);
        enterbutton.setTooltip(tp2);
        objective.setText(objectives[0]);
        moveRoom(1, 3);
        fiendChecker fiendCheck = new fiendChecker();
        fiendCheck.main();
        fiendMover fiendMove = new fiendMover();
        fiendMove.main();
        fiendDamage fiendDam = new fiendDamage();
        fiendDam.main();
    }

    private static void hackTooltipStartTiming(Tooltip tooltip)
    {
        try
        {
            Field fieldBehavior = tooltip.getClass().getDeclaredField("BEHAVIOR");
            fieldBehavior.setAccessible(true);
            Object objBehavior = fieldBehavior.get(tooltip);

            Field fieldTimer = objBehavior.getClass().getDeclaredField("activationTimer");
            fieldTimer.setAccessible(true);
            Timeline objTimer = (Timeline) fieldTimer.get(objBehavior);

            objTimer.getKeyFrames().clear();
            objTimer.getKeyFrames().add(new KeyFrame(new Duration(1)));
        } catch (Exception e)
        {
            e.printStackTrace();
        }
    }

    /**
     * Deals damage to the player and checks to see if they died. If the character is Joseph and Hazy is active, no damage is dealt.
     */
    public void dealDamage()
    {
        if(hazy)
        {
            textFlow("I am impervious to all damage while Hazy is active!");
            return;
        }
        background.setStyle("-fx-background-color: red;");
        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(.5), new KeyValue(mapimg.opacityProperty(), 0.0));
        KeyFrame endFadeIn = new KeyFrame(Duration.seconds(.6), new KeyValue(mapimg.opacityProperty(), 1.0));
        KeyFrame revertBlack = new KeyFrame(Duration.seconds(.7), new KeyValue(background.styleProperty(), "-fx-background-color: black;"));
        Timeline timelineOn = new Timeline(startFadeOut, endFadeIn, revertBlack);
        timelineOn.play();
        mainCharacter.takeDamage(20);
        health.setText(String.valueOf(Character.hp));
        if (Character.hp == 0)
        {
            death();
            return;
        }
        icon.setImage(mainCharacter.getCurrentHealthIndicator());
        background.setStyle("-fx-background-color: black;");
    }

    /**
     * Method to create text in a typewriter style.
     * @param text Text to be displayed.
     */

    public void textFlow(String text)
    {
        final Animation animation = new Transition()
        {
            {
                setCycleDuration(Duration.millis(1500));
            }
            protected void interpolate(double frac) {
                final int length = text.length();
                final int n = Math.round(length * (float) frac);
                showtext.setText(text.substring(0, n));
            }
        };
        animation.play();
    }

    /**
     * Tells the player the correct syntax for a command. Called whenever a command fails via code word and displays error message based on that.
     * @param cmd Code word sent by invalid command.
     */
    private void invalidCommand(String cmd)
    {
        if(rage)
        {
            rageCount += .2;
            ragemeter.setProgress(rageCount);
        }
        type.setText("");
        for(String[] i : cmdSyntax)
        {
            if(cmd.contains(i[0]))
            {
                type.setPromptText(i[1]);
                return;
            }
        }
        type.setPromptText("Please type in a valid command.");
    }

    /**
     * Takes in player input upon the enter key being hit.
     */
    @FXML
    public void onEnter()
    {
        doAction();
    }

    /**
     * Takes in input from player terminal and executes command if one is found.
     */
    public void doAction()
    {
        if(!alive)
            return;
        type.setPromptText("_");
        String command = type.getText();
        command = command.toLowerCase();
        String[] commandSplit = command.split(" ");

        lightUpdate();

        String currCmd = commandSplit[0];
        String sec = "";
        if(commandSplit.length != 1)
            sec = commandSplit[1];
        String tre = "";
        if(commandSplit.length > 2)
            tre = commandSplit[2];

        if(currCmd.contains("move"))
        {
            moveTo(sec);
            rageCheck();
            type.setText("");
            return;
        }
        if(currCmd.contains("search"))
        {
            searchThing();
            rageCheck();
            type.setText("");
            return;
        }
        if(currCmd.contains("take"))
        {
            takeThing(sec + " " + tre);
            rageCheck();
            type.setText("");
            return;
        }
        if(currCmd.contains("use"))
        {
            useThing(sec + " " + tre);
            rageCheck();
            type.setText("");
            return;
        }
        if(currCmd.contains("ability"))
        {
            textFlow(mainCharacter.useAbility());
            if(hazy)
            {
                invTextOut = "";
                invtext.setText(invTextOut);
            }
            rageCheck();
            type.setText("");
            return;
        }
        if(currCmd.contains("operate"))
        {
            operate(sec + " " + tre);
            rageCheck();
            type.setText("");
            return;
        }
        if(currCmd.contains("attack"))
        {
            attack();
            rageCheck();
            type.setText("");
            return;
        }
        invalidCommand("");
    }

    private void rageCheck()
    {
        if(rageCount >= 1)
        {
            if(breakCount >= 2)
            {
                textFlow(mainCharacter.useAbility());
                breakCount = 0;
            }
            else
                breakCount++;
        }
        ragemeter.setProgress(rageCount);
    }

    /**
     * Activates rage meter. Invalid actions cause the meter to rise.
     */
    private void rageActive()
    {
        rage = true;
        ragetext.setVisible(true);
        ragemeter.setVisible(true);
    }

    /**
     * Updates lighting via room boolean
     */
    private void lightUpdate()
    {
        if(!getCurrentRoom().lit)
            lights.setVisible(true);
        else
            lights.setVisible(false);
    }

    /**
     * Moves character in the direction they enter
     * @param direction Direction in the form of north, east, south, or west. If the player can't move that direction, they state that a wall block their way.
     */
    private void moveTo(String direction)
    {
        int currX = mainCharacter.xPos;
        int currY = mainCharacter.yPos;

        if(direction.contains("north"))
        {
            if((currX == 1 && currY == 1) && aMap[currX][currY - 1].locked)
                objective.setText(objectives[1]);
            if(aMap[currX][currY].canGoNorth)
            {
                if(aMap[currX][currY - 1].locked)
                {
                    textFlow(aMap[currX][currY - 1].lockedDesc);
                    return;
                }
                mainCharacter.changeY(currY - 1);
                moveRoom(currX, currY - 1);
                type.setText("");
                return;
            }
            else
            {
                textFlow(getReactionText("wall"));
                return;
            }
        }
        if(direction.contains("east"))
        {
            if((currX == 2 && currY == 2) && aMap[currX + 1][currY].locked)
                objective.setText(objectives[2]);
            if(aMap[currX][currY].canGoEast)
            {
                if(aMap[currX + 1][currY].locked)
                {
                    textFlow(aMap[currX + 1][currY].lockedDesc);
                    return;
                }
                mainCharacter.changeX(currX + 1);
                moveRoom(currX + 1, currY);
                type.setText("");
                return;
            }
            else
            {
                textFlow(getReactionText("wall"));
                return;
            }
        }
        if(direction.contains("south"))
        {
            if(aMap[currX][currY].canGoSouth)
            {
                if(aMap[currX][currY + 1].locked)
                {
                    textFlow(aMap[currX][currY + 1].lockedDesc);
                    return;
                }
                mainCharacter.changeY(currY + 1);
                moveRoom(currX, currY + 1);
                type.setText("");
                return;
            }
            else
            {
                textFlow(getReactionText("wall"));
                return;
            }
        }
        if(direction.contains("west"))
        {
            if(aMap[currX][currY].canGoWest)
            {
                if(aMap[currX - 1][currY].locked)
                {
                    textFlow(aMap[currX - 1][currY].lockedDesc);
                    return;
                }
                mainCharacter.changeX(currX - 1);
                moveRoom(currX - 1, currY);
                type.setText("");
                return;
            }
            textFlow(getReactionText("wall"));
        }
        invalidCommand("move");
    }

    /**
     * Changes inventory display to players current inventory.
     */
    private void invDisplay()
    {
        invTextOut = "";
        for(UsableObject a : mainCharacter.inventory)
            invTextOut += a.objName + "\n";
        invtext.setText(invTextOut);
    }

    /**
     * Moves the player to designated room and changes scene and text to match it. Resets "first time" (used for fiend)
     * @param x x position of room player is moving to
     * @param y y position of room player is moving to
     */
    private void moveRoom(int x, int y)
    {
        firstTime = true;
        firstDTime = true;
        double spdC = (double) (20000 - mainCharacter.getSpd() * 1000) / (10000 + mainCharacter.getSpd() * 100);
        Image imgB = new Image("images/" + aMap[x][y].image, 800, 350, true, true);
        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(spdC), new KeyValue(mapimg.opacityProperty(), 0.0));
        KeyFrame keyFrameOn = new KeyFrame(Duration.seconds(spdC), new KeyValue(mapimg.imageProperty(), imgB));
        KeyFrame endFadeIn = new KeyFrame(Duration.seconds(spdC + .6), new KeyValue(mapimg.opacityProperty(), 1.0));
        Timeline timelineOn = new Timeline(startFadeOut, keyFrameOn, endFadeIn);
        timelineOn.play();
        mapicon.setImage(new Image("images/" + aMap[x][y].truName + "icon.png"));
        if(getCurrentRoom().lit)
            lights.setVisible(false);
        else
            lights.setVisible(true);
        roomname.setText(aMap[x][y].roomName);
        textFlow(getRoomText(x, y));
    }

    /**
     * Returns text for players current room
     * @param x x position of room player is moving to
     * @param y y position of room player is moving to
     * @return String of the characters reaction to the current room
     */
    private String getRoomText(int x, int y)
    {
        for(String[] i : mainCharacter.specialDialog)
        {
            if(aMap[x][y].roomName.toLowerCase().equals(i[0]))
                return i[1];
        }
        return "";
    }

    /**
     * Grabs appropriate reaction by using an indicator string
     * @param text What the player is reacting too (wall, fiend, etc)
     * @return Characters reaction text
     */
    private String getReactionText(String text)
    {
        for(String[] i : mainCharacter.specialDialog)
        {
            if(i[0].contains(text))
                return i[1];
        }
        return "";
    }

    /**
     * Searches room for items.
     */
    private void searchThing()
    {
        String b = "";
        b += visSearch(mainCharacter.getVis(), mainCharacter.xPos, mainCharacter.yPos);
        if(getCurrentRoom().operatable != null)
            b += " There also seems to be a " + getCurrentRoom().operatable.objName + " that I can operate.";
        textFlow(b);
    }

    /**
     * Search method for rooms
     * @param vision Characters vision stat
     * @param x x position of room player is in
     * @param y y position of room player is in
     * @return All items the player can see (will not always be all the items in the room)
     */
    public static String visSearch(int vision, int x, int y)
    {
        int tem = 0;
        if(aMap[x][y].lit)
            tem = -5;
        String it = "";
        ArrayList<UsableObject> potential = aMap[x][y].items;
        if(potential.isEmpty())
            return "There isn't anything useful here.";
        tem += (int) (Math.random() * 7);
        int a;
        for (UsableObject aPotential : potential)
        {
            a = (int) (Math.random() * 7);
            tem += a;
            if(tem < (vision / 2))
            {
                it += aPotential.objName;
                it += ", ";
            }
            tem -= a;
        }
        if(it.equals(""))
            return "I can barely see anything...";
        it = it.substring(0, it.length() - 2);
        return "I see a " + it + ".";
    }

    /**
     * Adds item from room to inventory
     * @param item Item in room the player is taking
     */
    private void takeThing(String item)
    {
        if(hazy)
        {
            textFlow("I can't pick up items while Hazy is active.");
            return;
        }
        if(item.contains("key"))
            objective.setText(objectives[3]);
        ArrayList<UsableObject> tem = getCurrentRoom().items;
        for(int i = 0; i < tem.size(); i++)
        {
            if(item.contains(tem.get(i).objName.toLowerCase()))
            {
                mainCharacter.inventory.add(tem.remove(i));
                invDisplay();
                item = item.trim();
                textFlow("I now have a " + item + " in my inventory.");
                return;
            }
        }
        invalidCommand("take");
    }

    /**
     * Operates a non takeable object in a room.
     * @param op Name of object player is operating
     */
    private void operate(String op)
    {
        OperatableObject a = getCurrentRoom().operatable;
        if(a != null && op.contains(a.objName.toLowerCase()))
        {
            textFlow(getCurrentRoom().operatable.operate());
            if(op.contains("fuse box"))
                objective.setText(objectives[4]);
            return;
        }
        textFlow("I can't operate what doesn't exist.");
    }

    /**
     * Uses thing from inventory and removes it. If the item is used to unlock a door, unlockThing is called.
     * @param item Item being used
     */
    private void useThing(String item)
    {
        ArrayList<UsableObject> tem = mainCharacter.inventory;
        for (UsableObject aTem : tem)
        {
            if (item.contains(aTem.objName.toLowerCase()))
            {
                if(aTem.unlocker)
                {
                    if(unlockThing(aTem))
                        mainCharacter.inventory.remove(aTem);
                    else
                        textFlow("I think this should go somewhere else.");
                    invDisplay();
                    return;
                }
                else
                {
                    aTem.use();
                    textFlow(aTem.useMsg);
                    mainCharacter.inventory.remove(aTem);
                    invDisplay();
                    return;
                }
            }
        }
        invalidCommand("use");
    }

    /**
     * Checks if a room perpendicular to the player can be unlocked by the item used and unlocks it if found.
     * @param a Item being used to unlock.
     */
    public boolean unlockThing(UsableObject a)
    {
        String item = a.objName.trim().toLowerCase();
        int x = mainCharacter.xPos;
        int y = mainCharacter.yPos;
        if (perpRoomNeedsItem(x, y))
        {
            if ((aMap[x][y - 1] != null && aMap[x][y - 1].neededThing != null) && aMap[x][y - 1].neededThing.contains(item))
                unlock(x, y - 1);
            if ((aMap[x][y + 1] != null && aMap[x][y + 1].neededThing != null) && aMap[x][y + 1].neededThing.contains(item))
                unlock(x, y + 1);
            if ((aMap[x - 1][y] != null && aMap[x - 1][y].neededThing != null) && aMap[x - 1][y].neededThing.contains(item))
                unlock(x - 1, y);
            if ((aMap[x + 1][y] != null && aMap[x + 1][y].neededThing != null) && aMap[x + 1][y].neededThing.contains(item))
                unlock(x + 1, y);
            textFlow(a.useMsg);
            return true;
        }
        return false;
    }

    /**
     * Unlocks room
     * @param x x position of room being unlocked
     * @param y y position of room being unlocked
     */
    private void unlock(int x, int y)
    {
        aMap[x][y].neededThing = null;
        aMap[x][y].locked = false;
    }

    /**
     * Checks rooms around player to see if any can be unlocked
     * @param x x position of room being unlocked
     * @param y y position of room being unlocked
     * @return True if any rooms around can be unlocked (function acts as pre condition to prevent errors)
     */
    private boolean perpRoomNeedsItem(int x, int y)
    {
        if(aMap[x][y - 1] != null && aMap[x][y - 1].neededThing != null)
            return true;
        if(aMap[x][y + 1] != null && aMap[x][y + 1].neededThing != null)
            return true;
        if(aMap[x - 1][y] != null && aMap[x - 1][y].neededThing != null)
            return true;
        return aMap[x + 1][y] != null && aMap[x + 1][y].neededThing != null;
    }

    /**
     * Kills the player by immobilizing them and displaying a death screen
     */
    private void death()
    {
        alive = false;
        lose();
    }

    public void attack()
    {
        if(hazy)
        {
            textFlow("I can't attack while Hazy is active.");
            return;
        }
        if(!inSameRoom())
        {
            textFlow("There's nothing to attack here.");
            return;
        }
        if(!getCurrentRoom().lit)
        {
            textFlow("The fiend is invincible in the dark!");
            return;
        }
        int a = mainCharacter.getAtk() + (int) (Math.random() * (20 - mainCharacter.getAtk()) + 1);
        if(a >= 15)
        {
            enemy.hp -= (a);
            textFlow("Aha! Take that!");
            if(enemy.hp <= 0)
                win();
        }
        else
            textFlow("My attack was too weak!");
    }

    public static boolean inSameRoom()
    {
        return mainCharacter.xPos == enemy.x && mainCharacter.yPos == enemy.y;
    }

    public static Room getCurrentRoom()
    {
        return aMap[mainCharacter.xPos][mainCharacter.yPos];
    }

    private void win()
    {
        enemy.x = 0;
        enemy.y = 0;
        win = true;
        finalscreen.setVisible(true);
        finalscreen.setImage(new Image("images/win.png", 800, 550, true, true));
    }

    private void lose()
    {
        enemy.x = 0;
        enemy.y = 0;
        win = false;
        finalscreen.setVisible(true);
        finalscreen.setImage(new Image("images/lose.png", 800, 550, true, true));
    }





    public class fiendChecker
    {

        private void main()
        {
            bgFiend thread = new bgFiend();
            thread.setDaemon(true);
            thread.start();

            java.awt.EventQueue.invokeLater(() -> {

            });
        }
        public class bgFiend extends Thread
        {

            @Override
            public void run()
            {
                while(alive && !win)
                {
                    if(inSameRoom() && firstTime)
                    {
                        firstTime = false;
                        Platform.runLater(() -> textFlow(getReactionText("fiend")));
                        Image tempimg = new Image("images/" + getCurrentRoom().truName + "_fiend.png", 800, 350, true, true);
                        KeyFrame startFadeOut = new KeyFrame(Duration.seconds(.5), new KeyValue(mapimg.opacityProperty(), 0.0));
                        KeyFrame fiendImg = new KeyFrame(Duration.seconds(.6), new KeyValue(mapimg.imageProperty(), tempimg));
                        KeyFrame endFadeIn = new KeyFrame(Duration.seconds(.7), new KeyValue(mapimg.opacityProperty(), 1.0));
                        Timeline timelineOn = new Timeline(startFadeOut, fiendImg, endFadeIn);
                        timelineOn.play();
                    }
                    else
                    {
                        if(!inSameRoom())
                            mapimg.setImage(new Image("images/" + getCurrentRoom().image, 800, 350, true, true));
                    }
                    try
                    {
                        Thread.sleep(1000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }
        }
    }

    public class fiendDamage
    {

        private void main()
        {
            bgFiend thread = new bgFiend();
            thread.setDaemon(true);
            thread.start();

            java.awt.EventQueue.invokeLater(() -> {

            });
        }
        public class bgFiend extends Thread
        {

            @Override
            public void run()
            {
                while(alive && !win)
                {
                    if(inSameRoom())
                    {
                        if(firstDTime)
                            firstDTime = false;
                        else
                            Platform.runLater(() -> dealDamage());
                    }
                    try
                    {
                        Thread.sleep(6000);
                    } catch (InterruptedException e)
                    {
                        e.printStackTrace();
                    }
                }
            }

        }

    }

    private class fiendMover
    {

        private void main()
        {

            bgFiendM thread = new bgFiendM();
            thread.setDaemon(true);
            thread.start();
        }
    }
    public class bgFiendM extends Thread
    {
        @Override
        public void run()
        {
            while(alive && !win)
            {
                if(mainCharacter.xPos == enemy.x && mainCharacter.yPos > enemy.y)
                    enemy.y++;
                if(mainCharacter.xPos == enemy.x && mainCharacter.yPos < enemy.y)
                    enemy.y--;
                if(mainCharacter.xPos > enemy.x && mainCharacter.yPos == enemy.y)
                    enemy.x++;
                if(mainCharacter.xPos < enemy.x && mainCharacter.yPos == enemy.y)
                    enemy.x--;
                try
                {
                    Thread.sleep(spdCount);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

