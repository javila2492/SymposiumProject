package scenes;

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

    private String[] commandList = {"move", "search", "inspect", "use", "ability", "take"};
    static Character mainCharacter;
    Map temp = new Map();
    public Room[][] aMap = temp.areaMap;

    public void initialize()
    {
        mainCharacter = CharSelectionController.chosen;
        mainCharacter.changePos(1, 3);
        icon.setImage(mainCharacter.getMainImg());
        showtext.setText("Hello world. This is a test to see if things work properly. If not, I will CRY.");
        Image img = new Image("images/entrance.png");
        mapimg.setImage(img);
        roomname.setText("Entrance");
    }

    public void updateIcon()
    {
        icon.setImage(characters.Character.getCurrentHealthIndicator());
    }


    public void textFlow(String text)
    {
        String splitext = "";
        String[] textArr = text.split("");
        for(int i = 0; i < textArr.length; i++)
        {
            showtext.setText(splitext);
            splitext += textArr[i];
            try{ Thread.sleep(500); } catch(InterruptedException ex){ Thread.currentThread().interrupt();}
        }
    }

    public void invalidCommand()
    {
        type.setText("Please type in a valid command.");

    }


    public void doAction()
    {
        String command = type.getText();
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


    public void stringToMethod(String func, String param)
    {
        Method m = null;
        try
        {
            m = GUIController.class.getMethod(func, String.class);
            Object returnValue = m.invoke(null, param);
        }
        catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e)
        {
            e.printStackTrace();
        }
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
            }
            else
                textFlow("A wall blocks your path.");
        }
        if(direction.contains("east"))
        {
            if(aMap[currX][currY].canGoEast)
            {
                mainCharacter.changeX(currX + 1);
                moveRoom(currX + 1, currY);
                type.setText("");
            }
            else
                textFlow("A wall blocks your path.");
        }
        if(direction.contains("south"))
        {
            if(aMap[currX][currY].canGoSouth)
            {
                mainCharacter.changeY(currY + 1);
                moveRoom(currX, currY + 1);
                type.setText("");
            }
            else
                textFlow("A wall blocks your path.");
        }
        if(direction.contains("west"))
        {
            if(aMap[currX][currY].canGoWest)
            {
                mainCharacter.changeX(currX - 1);
                moveRoom(currX - 1, currY);
                type.setText("");
            }
            else
                textFlow("A wall blocks your path.");
        }
    }

    public void moveRoom(int x, int y)
    {
        /*
        Image tempimg = new Image(aMap[x][y].image);
        mapimg.setImage(tempimg);
        */
        roomname.setText(aMap[x][y].roomName);
    }

}
