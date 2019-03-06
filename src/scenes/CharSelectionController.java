package scenes;

import characters.*;
import characters.Character;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;


public class CharSelectionController
{
    @FXML
    private Label statshower;
    @FXML
    private Label descshower;
    @FXML
    private Label abilityshower;

    boolean selected = false;
    public static Character chosen;

    public void charInfoChangerSabrina(MouseEvent mouseEvent)
    {
        chosen = new Sabrina();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerVin(MouseEvent mouseEvent)
    {
        chosen = new Vin();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerMikey(MouseEvent mouseEvent)
    {
        chosen = new Mikey();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerJoey(MouseEvent mouseEvent)
    {
        chosen = new Joey();
        infoBuilder(chosen);
        selected = true;
    }

    public void charInfoChangerFred(MouseEvent mouseEvent)
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
        Scene scene = new Scene(root);
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        stage.setScene(scene);
        stage.show();
    }
}
