package scenes;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;

public class CharSelectionController
{
    @FXML
    private Label statshower;
    @FXML
    private Label descshower;
    @FXML
    private Label abilityshower;

    public void charInfoChangerSabrina(MouseEvent mouseEvent)
    {
        statshower.setText("STATS: " + "\n" + characters.Sabrina.statBuilderShower());
        descshower.setText("DESCRIPTION: " + characters.Sabrina.getCharacterDesc());
        abilityshower.setText(characters.Sabrina.getAbilityDesc());
    }

    public void charInfoChangerVin(MouseEvent mouseEvent)
    {
        statshower.setText("STATS: " + "\n" + characters.Vin.statBuilderShower());
        descshower.setText("DESCRIPTION: " + characters.Vin.getCharacterDesc());
        abilityshower.setText(characters.Vin.getAbilityDesc());
    }

    public void charInfoChangerMikey(MouseEvent mouseEvent)
    {
        statshower.setText("STATS: " + "\n" + characters.Mikey.statBuilderShower());
        descshower.setText("DESCRIPTION: " + characters.Mikey.getCharacterDesc());
        abilityshower.setText(characters.Mikey.getAbilityDesc());
    }

    public void charInfoChangerJoey(MouseEvent mouseEvent)
    {
        statshower.setText("STATS: " + "\n" + characters.Joey.statBuilderShower());
        descshower.setText("DESCRIPTION: " + characters.Joey.getCharacterDesc());
        abilityshower.setText(characters.Joey.getAbilityDesc());
    }

    public void charInfoChangerFred(MouseEvent mouseEvent)
    {
        statshower.setText("STATS: " + "\n" + characters.Fred.statBuilderShower());
        descshower.setText("DESCRIPTION: " + characters.Fred.getCharacterDesc());
        abilityshower.setText(characters.Fred.getAbilityDesc());
    }
}
