package scenes;

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
    private String[] commandExecute = {"moveTo", "search",}

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

        for(int i = 0; i < commandList.length; i++)
        {
            if(currCmd.contains(commandList[i]))
                stringToMethod(commandExecute[i], sec);

        }
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

    }
}
