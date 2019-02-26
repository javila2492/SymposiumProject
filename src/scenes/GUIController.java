package scenes;

import Rooms.Map;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


public class GUIController
{
    @FXML
    private ImageView icon;
    private TextField type;


    private String[] commandList = {"move", "search", "inspect", "use", "ability", "take"};

    public void updateIcon()
    {
        icon.setImage(characters.Character.getCurrentHealthIndicator());
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
            if(Map.areaMap[])
        }
    }
}
