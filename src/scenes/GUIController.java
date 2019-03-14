package scenes;

import javafx.scene.control.ProgressBar;
import rooms.Map;
import rooms.Room;
import characters.Character;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
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
    ProgressBar ragemeter;
    @FXML
    Label invtext;

    private String[] commandList = {"move", "search", "inspect", "use", "ability", "take"};
    static Character mainCharacter;
    public static Map temp = new Map();
    boolean rage = false;
    public static Room[][] aMap = temp.areaMap;
    String invTextOut = "";

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
    }

    public void updateIcon()
    {
        mainCharacter.takeDamage(20);
        icon.setImage(mainCharacter.getCurrentHealthIndicator());
    }


    public void textFlow(String text)
    {
        String splitext = "";
        String[] textArr = text.split("");
        for(int i = 0; i < textArr.length; i++)
        {
            showtext.setText(splitext);
            splitext += textArr[i];
        }
    }

    public void invalidCommand()
    {
        type.setText("");
        type.setPromptText("Please type in a valid command.");
    }


    public void doAction()
    {
        type.setPromptText("_");
        String command = type.getText();
        command = command.toLowerCase();
        String[] commandSplit = command.split(" ");
        if(commandSplit.length < 2)
        {
            invalidCommand();
            return;
        }
        String currCmd = commandSplit[0];
        String sec = commandSplit[1];
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
        invalidCommand();
    }

    public void rageActive()
    {
        rage = true;
        ragetext.setVisible(true);
        ragemeter.setVisible(true);
    }


    public void moveTo(String direction)
    {
        int currX = mainCharacter.xPos;
        int currY = mainCharacter.yPos;

        if(direction.contains("north"))
        {
            if(aMap[currX][currY].canGoNorth)
            {
                mainCharacter.changeY(currY - 1);
                moveRoom(currX, currY - 1);
                type.setText("");
                return;
            }
            else
            {
                textFlow("A wall blocks your path.");
                return;
            }
        }
        if(direction.contains("east"))
        {
            if(aMap[currX][currY].canGoEast)
            {
                mainCharacter.changeX(currX + 1);
                moveRoom(currX + 1, currY);
                type.setText("");
                return;
            }
            else
            {
                textFlow("A wall blocks your path.");
                return;
            }
        }
        if(direction.contains("south"))
        {
            if(aMap[currX][currY].canGoSouth)
            {
                mainCharacter.changeY(currY + 1);
                moveRoom(currX, currY + 1);
                type.setText("");
                return;
            }
            else
            {
                textFlow("A wall blocks your path.");
                return;
            }
        }
        if(direction.contains("west"))
        {
            if(aMap[currX][currY].canGoWest)
            {
                mainCharacter.changeX(currX - 1);
                moveRoom(currX - 1, currY);
                type.setText("");
                return;
            }
            textFlow("A wall blocks your path.");
        }
    }

    public void moveRoom(int x, int y)
    {
        Image tempimg = new Image("images/" + aMap[x][y].image);
        mapimg.setImage(tempimg);
        roomname.setText(aMap[x][y].roomName);
        showtext.setText(getRoomText(x, y));
    }

    public String getRoomText(int x, int y)
    {
        for(String[] i : mainCharacter.specialDialog)
        {
            if(i[0].contains(aMap[x][y].roomName.toLowerCase()))
                return i[1];
        }
        return "";
    }

    public void searchThing(String a)
    {
        if(a.contains("room"))
        {
            showtext.setText(visSearch(mainCharacter.getVis(), mainCharacter.xPos, mainCharacter.yPos));
        }
    }

    public String visSearch(int vision, int x, int y)
    {
        int tem;
        String it = "";
        ArrayList potential = aMap[x][y].items;
        if(vision > 11)
        {
            if(potential.isEmpty())
                return "There isn't anything useful here.";
            tem = (int) (Math.random() * 7);
            if(vision > (20 - tem))
                return "I can't see anything useful for now.";
            for(int i = 0; i < potential.size(); i++)
            {
                it += potential.get(i);
                it += ", ";
            }
            return "I see a " + it + "and nothing else.";
        }

        if(vision <= 11)
        {
            if(potential.isEmpty())
                return "I can't see anything useful for now.";
            tem = (int) (Math.random() * 7);
            if(vision < (12 - tem))
                return "I can barely see anything.";
            for(int i = 0; i < potential.size(); i++)
            {
                it += potential.get(i);
                it += ", ";
            }
            return "I see a " + it + "and nothing else.";
        }
        return "";
    }

    public void takeThing(String item)
    {
        System.out.println(item);
        ArrayList<String> tem = aMap[mainCharacter.xPos][mainCharacter.yPos].items;
        for(int i = 0; i < tem.size(); i++)
        {
            if(item.contains(tem.get(i).toLowerCase()))
            {
                mainCharacter.inventory.add(tem.remove(i));
                invTextOut += mainCharacter.inventory.get(mainCharacter.inventory.size() - 1) + "\n";
                invtext.setText(invTextOut);
                return;
            }
        }
    }
}
