package scenes;

import characters.*;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;

import java.lang.Character;

public class CharSelectionController
{
    @FXML
    private Label statshower;
    @FXML
    private Label descshower;
    @FXML
    private Label abilityshower;
    @FXML
    Button contbutton;

    boolean selected = false;
    Character chosen;

    public void charInfoChangerSabrina(MouseEvent mouseEvent)
    {
        Sabrina sabrina = new Sabrina();
        statshower.setText("STATS: " + "\n" + sabrina.statBuilderShower());
        descshower.setText("DESCRIPTION: " + sabrina.getCharacterDesc());
        abilityshower.setText(sabrina.getAbilityDesc());
        selected = true;
    }

    public void charInfoChangerVin(MouseEvent mouseEvent)
    {
        Vin vin = new Vin();
        statshower.setText("STATS: " + "\n" + vin.statBuilderShower());
        descshower.setText("DESCRIPTION: " + vin.getCharacterDesc());
        abilityshower.setText(vin.getAbilityDesc());
        selected = true;
    }

    public void charInfoChangerMikey(MouseEvent mouseEvent)
    {
        Mikey mikey = new Mikey();
        statshower.setText("STATS: " + "\n" + mikey.statBuilderShower());
        descshower.setText("DESCRIPTION: " + mikey.getCharacterDesc());
        abilityshower.setText(mikey.getAbilityDesc());
        selected = true;
    }

    public void charInfoChangerJoey(MouseEvent mouseEvent)
    {
        Joey joey = new Joey();
        statshower.setText("STATS: " + "\n" + joey.statBuilderShower());
        descshower.setText("DESCRIPTION: " + joey.getCharacterDesc());
        abilityshower.setText(joey.getAbilityDesc());
        selected = true;
    }

    public void charInfoChangerFred(MouseEvent mouseEvent)
    {
        Fred fred = new Fred();
        statshower.setText("STATS: " + "\n" + fred.statBuilderShower());
        descshower.setText("DESCRIPTION: " + fred.getCharacterDesc());
        abilityshower.setText(fred.getAbilityDesc());
        selected = true;
    }

    public void selectionChosen()
    {
        if(!selected)
            return;

    }
}
