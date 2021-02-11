package sample.game;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;

import java.io.IOException;

public class Game {
    @FXML private Pane scoreBoard;
    @FXML private Label userFinalScore;
    @FXML private Label compFinalScore;

    private String computerChoice;
    private String userChoice;
    private Integer userScore = 0;
    private Integer compScore = 0;

    /*
    * Function that generates a random choice for the computer
    * @return int : 1 => rock || 2 => paper || 3 => scissors
    * */
    public int generateRandomChoice() {
        return (int) (Math.random() * 3 + 1);
    }

    /*
     * Function that returns the computer choice
     * @return String :  rock || paper || scissors
     * */
    public void getComputerChoice(int number) {
        switch (number) {
            case 1:
                computerChoice = "rock";
                break;
            case 2:
                computerChoice = "paper";
                break;
            case 3:
                computerChoice = "scissors";
                break;
        }
    }

    public void gameScore(String userChoice) {
        switch(userChoice + "-" + computerChoice){
            case "rock-scissors":
            case "paper-rock":
            case "scissors-paper":
                scoreBoard.setStyle("-fx-background-color: #82C869;");
                userScore++;
                userFinalScore.setText(userScore.toString());
                System.out.println(userChoice + "-" + computerChoice + " Win");
                break;
            case "rock-rock":
            case "paper-paper":
            case "scissors-scissors":
                scoreBoard.setStyle("-fx-background-color: #FFDE79;");
                System.out.println(userChoice + "-" + computerChoice + " Draw");
                break;
            case "rock-paper":
            case "paper-scissors":
            case "scissors-rock":
                scoreBoard.setStyle("-fx-background-color: #ff1818;");
                compScore++;
                compFinalScore.setText(compScore.toString());
                System.out.println(userChoice + "-" + computerChoice + " Loss");
                break;

        }

    }

    public void handleUserAction(ActionEvent actionEvent) throws IOException {
        // Get current window
        Parent game = FXMLLoader.load(getClass().getResource("Game.fxml"));
        Stage gameStage = new Stage();
        gameStage.getIcons().add(new Image("sample/images/icon.png")); // set icon
        gameStage.setScene(new Scene(game));

        // Get user action
        String userAction = ((Button) actionEvent.getSource()).getText();
        switch (userAction) {
            case "Reset":
                System.out.println("Reset");
                scoreBoard.setStyle("-fx-background-color: transparent;");
                userScore = 0;
                compScore = 0;
                userFinalScore.setText("0");
                compFinalScore.setText("0");
                break;
            case "End game":
                try {
                    if (userScore > compScore){
                        System.out.println(userScore + " > " + compScore);
                        // Create win window
                        Parent win = FXMLLoader.load(getClass().getResource("../win/Win.fxml"));
                        Stage winStage = new Stage();
                        winStage.setTitle("Win");
                        winStage.getIcons().add(new Image("sample/images/icon.png")); // set icon
                        winStage.setScene(new Scene(win));
                        winStage.show();
                    } else if (userScore < compScore) {
                        System.out.println(userScore + " < " + compScore);
                        // Create lose window
                        Parent lose = FXMLLoader.load(getClass().getResource("../lose/Lose.fxml"));
                        Stage loseStage = new Stage();
                        loseStage.setTitle("Loss");
                        loseStage.getIcons().add(new Image("sample/images/icon.png")); // set icon
                        loseStage.setScene(new Scene(lose));
                        loseStage.show();
                    } else {
                        System.out.println(userScore + " == " + compScore);
                        // Create draw window
                        Parent draw = FXMLLoader.load(getClass().getResource("../draw/Draw.fxml"));
                        Stage drawStage = new Stage();
                        drawStage.setTitle("Draw");
                        drawStage.getIcons().add(new Image("sample/images/icon.png")); // set icon
                        drawStage.setScene(new Scene(draw));
                        drawStage.show();
                    }
                    ((Node) actionEvent.getSource()).getScene().getWindow().hide();
                } catch (Exception e) {
                    System.out.println(e);
                }
                break;
        }
    }

    public void startGame(ActionEvent actionEvent) {
        userChoice = ((Button) actionEvent.getSource()).getText();
        getComputerChoice(generateRandomChoice());
        gameScore(userChoice);
    }
}
