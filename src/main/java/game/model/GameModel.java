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
    public boolean isEmptyField(int x) {//melyik elemre akarsz lépni
        boolean isEmpty = false;
        if (grid[x] == 0) {
            if (x == 0) {
                if (grid[x+1] == 0) {
                    isEmpty = true;
                }
            }
            else {
                if (x == 31) {
                    if(grid[x-1] == 0) {
                        isEmpty = true;
                    }
                }
            else {
                    if (grid[x-1] == 0 && grid[x+1] == 0) {
                        isEmpty = true;
                    }
                }
            }
        }
        else{
            isEmpty=false;
        }
        if(isEmpty){
            return true;
        }
        else {
            if (isloganabel = true) {
                //log.info(" nem üres mező");
            }
            return false;

        }

    }


    /**
     * Method that check that game has ended with a winner
     * @return true if the game is over and there is a winner. Otherwise return false.
     */
    public boolean isGameOver() {
        isloganabel = false;
        int count = 0;
        for (int i = 0; i < grid.length; i++) {
            isloganabel = false;
            if (isEmptyField(i)) {
                count++;
            }
        }
        isloganabel = true;
        if (count == 0) {
            log.info(" game over..");
            return true;
        }
        return false;
    }

}
