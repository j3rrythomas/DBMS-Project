package EMeter;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.Button;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.Connection;

public class Main extends Application {

    protected static Connection con;
    @Override
    public void start(Stage stage) {
        JDBC jdbc = new JDBC();
        con = jdbc.getConnection();
        try {
            Parent root = FXMLLoader.load(getClass().getResource("Login.fxml"));
            stage.setTitle("E-Meter");
            stage.setScene(new Scene(root,640,480));
            stage.show();
        }catch(IOException e){
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) {
        launch();
    }

}
