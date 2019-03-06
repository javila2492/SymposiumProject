package scenes;

import Rooms.Map;
import characters.Character;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
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

    private String[] commandList = {"move", "search", "inspect", "use", "ability", "take"};
    static Character mainCharacter;

    public void initialize()
    {
        mainCharacter = CharSelectionController.chosen;
        mainCharacter.changePos(1, 3);
        showtext.setText("Hello world. This is a test to see if things work properly. If not, I will CRY.");
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


    public void doAction()
    {
        String command = type.getText();
        String[] commandSplit = command.split(" ");
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
        if(direction.contains("north"))
        {
            if(Map.areaMap[mainCharacter.xPos][mainCharacter.yPos].canGoNorth)
                mainCharacter.changeY(mainCharacter.yPos - 1);
            else
                textFlow("A wall blocks your path.");
        }
    }

}
