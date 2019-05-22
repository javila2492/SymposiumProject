package scenes;

import javafx.application.Platform;
import javafx.scene.control.ProgressBar;
import javafx.scene.layout.Pane;
import rooms.Map;
import rooms.OperatableObject;
import rooms.Room;
import characters.Fiend;
import characters.Character;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import java.util.ArrayList;


public class GUIController
{
    @FXML
    private ImageView icon;
    @FXML
    private TextField type;
    @FXML
    private Label showtext;
    @FXML
    ImageView mapimg;
    @FXML
    Label roomname;
    @FXML
    Label ragetext;
    @FXML
    public ProgressBar ragemeter;
    @FXML
    Label invtext;
    @FXML
    Label damage;
    @FXML
    ImageView dmgfx;
    @FXML
    Pane lights;

    private Character mainCharacter;
    private static Map temp = new Map();
    public Fiend enemy = new Fiend(2, 2);
    private boolean rage = false;
    public static Room[][] aMap = temp.areaMap;
    private static String[][] cmdSyntax = {{"move", "Syntax: move (north, east, south, west)"}, {"search", "Syntax: search room"}, {"take", "Syntax: take [object that exists in room]"}, {"use", "Syntax: use [object in inventory"}, {"operate", "Syntax: operate [non takeable object in room]"}};
    public static boolean hazy = false;
    private String invTextOut = "";
    private boolean firstTime = true;
    private boolean alive = true;

    /**
     * Initializes the program by setting the characters position and activating the fiend. If the character is Mikey, the rage meter activates.
     */
    public void initialize()
    {
        mainCharacter = CharSelectionController.chosen;
        mainCharacter.changePos(1, 3);
        icon.setImage(mainCharacter.getMainImg());
        if(mainCharacter.name.contains("Mikey"))
        {
            rageActive();
        }
        moveRoom(1, 3);
        fiendChecker fiendCheck = new fiendChecker();
        fiendCheck.main();
        fiendMover fiendMove = new fiendMover();
        fiendMove.main();
    }

    /**
     * Deals damage to the player and checks to see if they died. If the character is Joey and Hazy is active, no damage is dealt.
     */
    public void dealDamage()
    {
        if(hazy)
        {
            textFlow("I am impervious to all damage while Hazy is active!");
            return;
        }
        dmgfx.setVisible(true);
        mainCharacter.takeDamage(20);
        if (mainCharacter.hp == 0)
        {
            death();
            return;
        }

        icon.setImage(mainCharacter.getCurrentHealthIndicator());

        dmgfx.setVisible(false);
    }

    /**
     * Method originally meant to create text in a typewriter style. For now it's just an easy call.
     * @param text Text to be displayed.
     */
    private void textFlow(String text)
    {
        /*
        String splitext = "";
        String[] textArr = text.split("");
        for(int i = 0; i < textArr.length; i++)
        {
            showtext.setText(splitext);
            splitext += textArr[i];
        }
        */
        showtext.setText(text);
    }

    /**
     * Tells the player the correct syntax for a command. Called whenever a command fails via code word and displays error message based on that.
     * @param cmd Code word sent by invalid command.
     */
    private void invalidCommand(String cmd)
    {
        if(rage)
            ragemeter.setProgress(ragemeter.getProgress() + .1);
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
            type.setText("");
            return;
        }
        if(currCmd.contains("search"))
        {
            searchThing(sec);
            type.setText("");
            return;
        }
        if(currCmd.contains("take"))
        {
            takeThing(sec + " " + tre);
            type.setText("");
            return;
        }
        if(currCmd.contains("use"))
        {
            useThing(sec + " " + tre);
            type.setText("");
            return;
        }
        if(currCmd.contains("ability"))
        {
            mainCharacter.useAbility();
            if(hazy)
            {
                invTextOut = "";
                invtext.setText(invTextOut);
            }
            type.setText("");
            return;
        }
        if(currCmd.contains("operate"))
        {
            mainCharacter.useAbility();
            if(hazy)
            {
                invTextOut = "";
                invtext.setText(invTextOut);
            }
            type.setText("");
            return;
        }
        invalidCommand("");
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
        if(!aMap[mainCharacter.xPos][mainCharacter.yPos].lit)
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

    private void invDisplay()
    {
        invTextOut = "";
        for(String a : mainCharacter.inventory)
            invTextOut += a + "\n";
        invtext.setText(invTextOut);
        System.out.println(invTextOut);
    }

    private void moveRoom(int x, int y)
    {
        firstTime = true;
        Image tempimg = new Image("images/" + aMap[x][y].image, 800, 350, true, true);
        mapimg.setImage(tempimg);
        roomname.setText(aMap[x][y].roomName);
        showtext.setText(getRoomText(x, y));
    }

    private String getRoomText(int x, int y)
    {
        for(String[] i : mainCharacter.specialDialog)
        {
            if(aMap[x][y].roomName.toLowerCase().equals(i[0]))
                return i[1];
        }
        return "";
    }

    private String getReactionText(String text)
    {
        for(String[] i : mainCharacter.specialDialog)
        {
            if(i[0].contains(text))
                return i[1];
        }
        return "";
    }

    private void searchThing(String a)
    {
        String b = "";
        if(a.contains("room"))
        {
            b += visSearch(mainCharacter.getVis(), mainCharacter.xPos, mainCharacter.yPos);
            if(aMap[mainCharacter.xPos][mainCharacter.yPos].operatable != null)
                b += " There also seems to be a " + aMap[mainCharacter.xPos][mainCharacter.yPos].operatable.objName + " that I can use.";
            textFlow(b);
            return;
        }
        invalidCommand("search");
    }

    public static String visSearch(int vision, int x, int y)
    {
        int tem = 0;
        if(aMap[x][y].lit)
            tem = -5;
        String it = "";
        ArrayList potential = aMap[x][y].items;
        if(potential.isEmpty())
            return "There isn't anything useful here.";
        tem += (int) (Math.random() * 7);
        int a;
        for (Object aPotential : potential)
        {
            a = (int) (Math.random() * 7);
            tem += a;
            if(tem > (vision / 2))
            {
                it += aPotential;
                it += ", ";
            }
            tem -= a;
        }
        if(it.equals(""))
            return "I can barely see anything...";
        it = it.substring(0, it.length() - 2);
        return "I see a " + it + ".";
    }

    private void takeThing(String item)
    {
        if(hazy)
        {
            textFlow("I can't pick up items while Hazy is active.");
            return;
        }
        ArrayList<String> tem = aMap[mainCharacter.xPos][mainCharacter.yPos].items;
        for(int i = 0; i < tem.size(); i++)
        {
            if(item.contains(tem.get(i).toLowerCase()))
            {
                mainCharacter.inventory.add(tem.remove(i));
                invDisplay();
                textFlow("I now have a " + item + "in my inventory.");
                return;
            }
        }
        invalidCommand("take");
    }

    private void operate(String op)
    {
        OperatableObject a = aMap[mainCharacter.xPos][mainCharacter.yPos].operatable;
        if(op.contains(a.objName.toLowerCase()))
        {
            textFlow(aMap[mainCharacter.xPos][mainCharacter.yPos].operatable.operate());
            return;
        }
        textFlow("I can't operate what doesn't exist.");
    }

    private void useThing(String item)
    {
        item = item.trim();
        int x = mainCharacter.xPos;
        int y = mainCharacter.yPos;
        ArrayList<String> tem = mainCharacter.inventory;
        for (String aTem : tem)
        {
            if (item.contains(aTem.toLowerCase()))
            {
                if (perpRoomNeedsItem(x, y))
                {
                    if ((aMap[x][y - 1] != null && aMap[x][y - 1].neededThing != null) && aMap[x][y - 1].neededThing.equals(item))
                        unlock(x, y - 1, item);
                    if ((aMap[x][y + 1] != null && aMap[x][y + 1].neededThing != null) && aMap[x][y + 1].neededThing.equals(item))
                        unlock(x, y + 1, item);
                    if ((aMap[x - 1][y] != null && aMap[x - 1][y].neededThing != null) && aMap[x - 1][y].neededThing.equals(item))
                        unlock(x - 1, y, item);
                    if ((aMap[x + 1][y] != null && aMap[x + 1][y].neededThing != null) && aMap[x + 1][y].neededThing.equals(item))
                        unlock(x + 1, y, item);
                }

            }
        }
        invalidCommand("use");
    }

    private void unlock(int x, int y, String item)
    {
        aMap[x][y].neededThing = null;
        aMap[x][y].locked = false;
        mainCharacter.inventory.remove(item);
        invDisplay();
    }

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

    private void death()
    {
        alive = false;
        damage.setVisible(true);
        dmgfx.setVisible(true);
        damage.setStyle("-fx-background-color: black;");
        damage.setStyle("-fx-opacity: 1.0;");
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
                while(alive)
                {
                    if(mainCharacter.xPos == enemy.x && mainCharacter.yPos == enemy.y)
                    {
                        Image tempimg = new Image("images/" + aMap[mainCharacter.xPos][mainCharacter.yPos].truName + "_fiend.png");
                        mapimg.setImage(tempimg);
                        if(firstTime)
                        {
                            mapimg.setImage(tempimg);
                            Platform.runLater(() -> showtext.setText(getReactionText("fiend")));
                        }
                        else
                            dealDamage();
                        firstTime = false;
                    }
                    try
                    {
                        Thread.sleep(4000);
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

            java.awt.EventQueue.invokeLater(() -> {

            });
        }
    }
    public class bgFiendM extends Thread
    {
        @Override
        public void run()
        {
            while (alive)
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
                    Thread.sleep(70000);
                } catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
            }
        }
    }
}

