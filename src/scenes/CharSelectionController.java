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
}
