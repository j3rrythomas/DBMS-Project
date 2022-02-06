package EMeter;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class LoginController {
    public TextField emailInput;
    public Button loginButton;
    public PasswordField passwordInput;
    public Label errorLabel;
    public static int userId;

    @FXML
    public void login(ActionEvent actionEvent) {
        try {
            String email = emailInput.getText();
            String password = passwordInput.getText();
            String getPasswordSql = "SELECT password,type,user_id FROM Users WHERE email='"+email+"'";
            Statement stmt = Main.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(getPasswordSql);
            if(!rs.next()){
                errorLabel.setText("Email not found");
            }
            else{
                rs.first();
                String queriedPassword = rs.getString(1);
                if(password.equals(queriedPassword)){
//                    errorLabel.setText("Logged In");
                    String userType = rs.getString(2);
                    userId = rs.getInt(3);
                    Parent root;
                    Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
                    if(userType.equals("CUSTOMER")){
                        root = FXMLLoader.load(getClass().getResource("CustomerDashboard.fxml"));

                    }
                    else{
                        root = FXMLLoader.load(getClass().getResource("AdminDashboard.fxml"));
                    }
                    appStage.setScene(new Scene(root,640,480));
                    appStage.show();
                }
                else{
                    errorLabel.setText("Invalid credentials");
                }
            }
        }
//        catch(SQLException e){
//            errorLabel.setText(String.valueOf(e));
//        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
