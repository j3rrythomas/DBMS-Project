package EMeter;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.io.IOException;

public class AdminDashboardController {
    public Label nameLabel;
    public Button logoutButton;

    public void logout(ActionEvent actionEvent) throws IOException {
        Parent root;
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        appStage.setScene(new Scene(root,640,480));
        appStage.show();
    }

    public void addUser(ActionEvent actionEvent) {
    }
}
