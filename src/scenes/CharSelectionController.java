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

    public void charInfoChangerSabrina(MouseEvent mouseEvent)
    {
        Sabrina sabrina = new Sabrina();
        statshower.setText("STATS: " + "\n" + sabrina.statBuilderShower());
        descshower.setText("DESCRIPTION: " + sabrina.getCharacterDesc());
        abilityshower.setText(sabrina.getAbilityDesc());
        contbutton.setStyle("visibility: visible");
    }

    public void charInfoChangerVin(MouseEvent mouseEvent)
    {
        Vin vin = new Vin();
        statshower.setText("STATS: " + "\n" + vin.statBuilderShower());
        descshower.setText("DESCRIPTION: " + vin.getCharacterDesc());
        abilityshower.setText(vin.getAbilityDesc());
        contbutton.setVisible(true);
    }

    public void charInfoChangerMikey(MouseEvent mouseEvent)
    {
        Mikey mikey = new Mikey();
        statshower.setText("STATS: " + "\n" + mikey.statBuilderShower());
        descshower.setText("DESCRIPTION: " + mikey.getCharacterDesc());
        abilityshower.setText(mikey.getAbilityDesc());
        contbutton.setVisible(true);
    }

    public void charInfoChangerJoey(MouseEvent mouseEvent)
    {
        Joey joey = new Joey();
        statshower.setText("STATS: " + "\n" + joey.statBuilderShower());
        descshower.setText("DESCRIPTION: " + joey.getCharacterDesc());
        abilityshower.setText(joey.getAbilityDesc());
        contbutton.setVisible(true);
    }

    public void charInfoChangerFred(MouseEvent mouseEvent)
    {
        Fred fred = new Fred();
        statshower.setText("STATS: " + "\n" + fred.statBuilderShower());
        descshower.setText("DESCRIPTION: " + fred.getCharacterDesc());
        abilityshower.setText(fred.getAbilityDesc());
        contbutton.setVisible(true);
    }
}
