package game.javafx.controller;

import com.sun.javafx.logging.Logger;
import game.model.GameModel;
import game.results.GameResult;
import game.results.GameResultDao;
import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.time.DurationFormatUtils;

import java.time.Instant;
//import org.apache.commons.lang.time.DurationFormatUtils;


import java.io.IOException;
import java.time.Duration;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

@Slf4j
public class GameController {

    private Instant startTime;
    private Timeline stopWatchTimeline;
    @FXML
    private Text stopWatchLabel;

    @FXML
    private Pane outerPane;

    @FXML
    private Pane pane;

    @FXML
    private Label messageLabel;

    private String playerName1;

    private String playerName2;

    @FXML
    private TextField playerNameTextField;
    @FXML
    private TextField playerNameTextField1;

    private String p1name;

    private String p2name;

    @FXML
    private Text p1nameText;

    @FXML
    private Text p2nameText;

    private String currentPlayer;

    private GameModel gameModel;

    private GameResultDao gameResultDao;

    private StringProperty activePlayerName = new SimpleStringProperty();

    @FXML
    private Label errorLabel;

    public void initialize() {
        Platform.runLater(() -> {
            initGame();
           p1nameText.setText(p1name);
           p2nameText.setText(p2name);
        });
        initGame();
    }
    private void createStopWatch() {
        stopWatchTimeline = new Timeline(new KeyFrame(javafx.util.Duration.ZERO, e -> {
            long millisElapsed = startTime.until(Instant.now(), ChronoUnit.MILLIS);
            stopWatchLabel.setText(DurationFormatUtils.formatDuration(millisElapsed, "HH:mm:ss"));
        }), new KeyFrame(javafx.util.Duration.seconds(1)));
        stopWatchTimeline.setCycleCount(Animation.INDEFINITE);
        stopWatchTimeline.play();
    }
    public void initGame() {
        messageLabel.setText("");
        gameModel = new GameModel();
        gameModel.setP1name(p1name);
        gameModel.setP2name(p2name);
        currentPlayer = p1name;
        startTime = Instant.now();
        createStopWatch();
        for (int i = 0; i < 1024; i += 1024/32) {
            for (int j = 0; j < 100; j += 100) {
                Rectangle r = new Rectangle(i, j, 1024/32, 1024/32);
                r.setFill(Color.SANDYBROWN);
                r.setStroke(Color.BLACK);
                pane.getChildren().addAll(r); //hozzáadja a dolgokat amiket ki kell rajzolni
                r.setOnMousePressed(mouseEvent -> mousePressed(mouseEvent, r));
            }
        }
        log.info("Initializing game...");
    }


    private void mousePressed(MouseEvent mouseEvent, Rectangle r) {
        errorLabel.setText("");
        System.out.println((int)r.getX() / 32); //elosztom 32-vel mert ennyi a tábla
        if(gameModel.isEmptyField((int)r.getX() / 32)) {
            if (currentPlayer.equals(gameModel.getP1name())) {

                gameModel.placeStone(currentPlayer, (int)r.getX() / 32);
               //kör kirajzolás
                Circle c = new Circle();
                double radius = 32 / 3.0;
                int x = 32 / 2 + 32 * (0+(int)r.getX()/32);
                int y = 32 / 2 + 32 * (0+(int)r.getY()/32);

                c.setRadius(radius);
                c.setTranslateX(x);
                c.setTranslateY(y);
                c.setFill(Color.SADDLEBROWN);
                pane.getChildren().addAll(c);
            }
            else {
                gameModel.placeStone(currentPlayer, (int) r.getX() / 32);
                Circle c = new Circle();
                double radius = 32 / 3.0;
                int x = 32 / 2 + 32 * (0+(int)r.getX()/32);
                int y = 32 / 2 + 32 * (0+(int)r.getY()/32);

                c.setRadius(radius);
                c.setTranslateX(x);
                c.setTranslateY(y);
                c.setFill(Color.BROWN);
                pane.getChildren().addAll(c);
            }
            if(gameModel.isGameOver()) {
                System.out.println(currentPlayer+ " won the game!");
                messageLabel.setText("Gratulálok, " + currentPlayer + " nyertél!");

                gameModel.setWinnerName(currentPlayer);

                gameResultDao = new GameResultDao();
                gameResultDao.persist(createGameResult());
                log.info("{} is the winner of the game.", currentPlayer);
            }

            if (currentPlayer.equals(gameModel.getP1name())) {
                this.currentPlayer = gameModel.getP2name();
            }
            else {
                this.currentPlayer = gameModel.getP1name();
            }
        }
        else{
            errorLabel.setText("You can't do that!");
        }

    }

    public GameResult createGameResult(){
        String loser = "";
        if(gameModel.getWinnerName().equals(gameModel.getP1name())){
            loser = gameModel.getP2name();
        }
        else {
            loser = gameModel.getP1name();
        }
        return GameResult.builder().name(gameModel.getWinnerName())
                .duration(Duration.between(startTime,Instant.now()))
                .opponentName(loser).build();
    }
    public void exitGame(ActionEvent actionEvent) {
        log.debug("{} button has been pressed.", ((Button)actionEvent.getSource()).getText());
        Platform.exit();
        log.info("Exit..");
    }

    public void resetGame(ActionEvent actionEvent) {
        log.debug("{} button has been pressed.", ((Button)actionEvent.getSource()).getText());
        initGame();
        log.info("Reset game..");
    }
    public void setPlayersName(String p1name, String p2name) {
        this.p1name = p1name;
        this.p2name = p2name;
        log.info("Setting player name");
    }

    public void handleHighScoreButton(ActionEvent actionEvent) throws IOException {
        log.debug("{} button has been pressed.", ((Button)actionEvent.getSource()).getText());
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/fxml/highscores.fxml"));
        Parent root = fxmlLoader.load();
        Stage stage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        stage.setScene(new Scene(root));
        stage.show();
        log.info("Loading highscores..");
    }
}
