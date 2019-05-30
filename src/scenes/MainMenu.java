package scenes;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

import java.awt.*;

public class MainMenu
{
    @FXML
    Button startbutton;
    @FXML
    Button helpbutton;
    @FXML
    Button lorebutton;
    @FXML
    Label textview;
    @FXML
    ImageView img;

    public void initialize()
    {
        img.setImage(new Image("images/fiendicon.png"));
    }

    private String[] help = {
            "Use the terminal in the bottom left of the game to enter commands to play.",
            "Syntax: move (north, east, south, west)",
            "Syntax: search room",
            "Syntax: take [object that exists in room]",
            "Syntax: use [object in inventory]",
            "Syntax: operate [non takeable object in room]",
            "Syntax: attack"};
    private String lore = "It's been two years since the events of the first flayer encounter. Sabrina and the band have been living peacefully..." +
            "until now. One day, everyone woke up with strange new abilities. Something within them knew that the dreaded Fiend was back. Equipped with their new abilities" +
            " and previous experience, they return to the old venue. In order to avoid confusion, they choose to send only one person in to fight. Who will it be? \n" +
            "The Fiend: A telepathic demon capable of possession, telekinesis, and memory wiping. It tends to attack its victims with psychic blows from the inside" +
            "of their head. It is not as fast as a human, but it doesn't need to get close to kill you. In the dark, it is immune to all damage. Only in light will it be weak" +
            " enough to be killed.";

    public void help()
    {
        String end = "";
        for(String a : help)
        {
            end += a;
            end += "\n";
        }
        textview.setText(end);
    }

    public void lore()
    {
        textview.setText(lore);
    }

    public void start(ActionEvent event) throws Exception
    {
        Parent root = FXMLLoader.load(getClass().getResource("CharSelection.fxml"));
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
