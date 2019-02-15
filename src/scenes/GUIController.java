package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;

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

        if(commandSplit)
    }

    public void move(String direction)
    {

    }
}
