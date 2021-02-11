package sample.main;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

import java.io.IOException;


public class Controller {
    @FXML
    private Label playerScore;
    @FXML
    private Label computerScore;
    @FXML
    private Button rockBtn, paperBtn, scissorsBtn;

    public void playGame(ActionEvent actionEvent) throws IOException {
        // Hide current window
        ((Node) actionEvent.getSource()).getScene().getWindow().hide();

        // Create a new wondow
        Parent root = FXMLLoader.load(getClass().getResource("../game/Game.fxml"));
        Stage gameStage = new Stage();
        gameStage.setTitle("You vs Computer");
        gameStage.getIcons().add(new Image("sample/images/icon.png")); // set icon
        gameStage.setResizable(false);
        gameStage.setScene(new Scene(root));
        gameStage.show();
    }
}
