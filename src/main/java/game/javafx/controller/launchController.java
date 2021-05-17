package game.javafx.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class launchController {

    @FXML
    private TextField playerNameTextField;
    @FXML
    private TextField playerNameTextField1;

    @FXML
    private Button startButton;

    @FXML
    private Label errorLabel;

    @FXML
    private ImageView kep;

    @FXML
    private ImageView kep2;

    @SuppressWarnings("checkstyle:MissingJavadocMethod")
    public void startAction(ActionEvent actionEvent) throws IOException {

        if (playerNameTextField.getText().isEmpty() || playerNameTextField1.getText().isEmpty()) {
            errorLabel.setText("Enter player names!");
        }
        else if (playerNameTextField.getText().length()>=10 || playerNameTextField1.getText().length()>=10){
            errorLabel.setText("Player names' length must not exceed 10!");
        }
        else if (playerNameTextField.getText().equals(playerNameTextField1.getText())){
            errorLabel.setText("Choose different names!");
        }
        else {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/fxml/game.fxml"));
            Parent root = loader.load();
            GameController gameController = loader.getController();
            gameController.setPlayersName(playerNameTextField.getText(), playerNameTextField1.getText());
            Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
            log.info("The names has been set to {} and {}, loading game scene", playerNameTextField.getText(), playerNameTextField1.getText());
        }

    }
    @FXML
    private void initialize(){
        kep.setImage(new Image(getClass().getResource("/pictures/stonegamepic.png").toExternalForm()));
        kep2.setImage(new Image(getClass().getResource("/pictures/stonegamepic2.png").toExternalForm()));

    }
    public void exitGame(ActionEvent actionEvent) {
        log.debug("{} button has been pressed.", ((Button)actionEvent.getSource()).getText());
        Platform.exit();
        log.info("Exit..");
    }
}

