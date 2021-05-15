package game.model;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.text.Text;
import lombok.Data;
import lombok.Generated;
import lombok.extern.slf4j.Slf4j;

@Generated
@Slf4j
@Data
public class GameModel {

    private String p1name;

    private String p2name;
    /**
     * The arrey representing the configuration of the game grid.
     */
    private int[] grid = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};

    private String winnerName;
    private String winner;
    private String loser;
    private boolean isloganabel = true;
    public GameModel(){}

    @FXML
    private Label messageLabel;

    public GameModel(int[] grid) {
        this.grid = grid;
    }

    /**
     * Method that checks the given field of the grid is empty or not.
     *
     */
}
