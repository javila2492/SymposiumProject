package scenes;

import characters.Character;
import characters.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import javafx.stage.Stage;


public class CharSelectionController
{
    static Character chosen;
    private boolean selected = false;
    @FXML private Label statshower;
    @FXML private Label descshower;
    @FXML private Label abilityshower;
    @FXML ImageView s;
    @FXML ImageView d;
    @FXML ImageView m;
    @FXML ImageView j;
    @FXML ImageView f;

    public void initialize()
    {
        s.setImage(new Image("images/sabrina.png", 100, 100, true, true));
        d.setImage(new Image("images/vin.png", 100, 100, true, true));
        m.setImage(new Image("images/mikey.png", 100, 100, true, true));
        j.setImage(new Image("images/joey.png", 100, 100, true, true));
        f.setImage(new Image("images/fred.png", 100, 100, true, true));
    }

    public void charInfoChangerSabrina()
    {
        chosen = new Sabrina();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerDanny()
    {
        chosen = new Danny();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerMikey()
    {
        chosen = new Mikey();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerJoseph()
    {
        chosen = new Joseph();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerFred()
    {
        chosen = new Fred();
        infoBuilder(chosen);
        selected = true;
    }

    private void infoBuilder(Character a)
    {
        statshower.setText("STATS: " + "\n" + a.statBuilderShower());
        descshower.setText("DESCRIPTION: " + a.getCharacterDesc());
        abilityshower.setText(a.getAbilityDesc());
    }

    public void selectionChosen(ActionEvent event) throws Exception
    {
        if(!selected)
            return;

        Parent root = FXMLLoader.load(getClass().getResource("GUI.fxml"));
        Scene scene = new Scene(root, Color.BLACK);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
