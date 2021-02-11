package sample.lose;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.stage.Stage;

import java.io.IOException;

public class Lose {
    public void replay() throws IOException {
        Parent game = FXMLLoader.load(getClass().getResource("../game/Game.fxml"));
        Stage gameStage = new Stage();
        gameStage.getIcons().add(new Image("sample/images/icon.png")); // set icon
        gameStage.setScene(new Scene(game));
        gameStage.show();
    }

    /*
    * Exit from the (even if there are many active windows)
    * */
    public void quit(ActionEvent event) {
        Platform.exit();

        Thread start = new Thread(new Runnable() {
            @Override
            public void run() {
                //TODO Auto-generated method stub
                System.exit(0);
            }
        });
    }
}
