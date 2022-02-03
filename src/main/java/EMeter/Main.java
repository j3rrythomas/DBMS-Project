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

public class Main extends Application {

    @Override
    public void start(Stage stage) {
//        String javaVersion = System.getProperty("java.version");
//        String javafxVersion = System.getProperty("javafx.version");
//        Label l = new Label("Hello, user");
////        Button b = new Button("Login");
//        Scene scene = new Scene(new StackPane(l), 640, 480);
//        stage.setScene(scene);
//        stage.show();
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
