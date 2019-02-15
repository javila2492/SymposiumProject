package scenes;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

public class GUIController
{
    @FXML
    private Image icon;


    public void updateIcon()
    {
        Image a = characters.Character.getCurrentHealthIndicator();
    }
}
