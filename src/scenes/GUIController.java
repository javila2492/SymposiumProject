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

    private String[] commandList = {"move", "search", "inspect", "use", "ability", "take"};
    static Character mainCharacter;
    Map temp = new Map();
    boolean rage = false;
    public Room[][] aMap = temp.areaMap;

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
        type.setText("Please type in a valid command.");

    }


    public void doAction()
    {
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

        if(currCmd.contains("move"))
            moveTo(sec);

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
            else
            {
                textFlow("A wall blocks your path.");
                return;
            }
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

}
