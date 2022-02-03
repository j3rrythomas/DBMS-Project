package EMeter;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class LoginController {
    public TextField emailInput;
    public Button loginButton;
    public PasswordField passwordInput;
    public Label errorLabel;

    public void login(ActionEvent actionEvent) {
        JDBC jdbc = new JDBC();
        Connection con = jdbc.getConnection();

        try {
            String email = emailInput.getText();
            String password = passwordInput.getText();
            String getPasswordSql = "SELECT password FROM Users WHERE email='"+email+"'";
            Statement stmt = con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs = stmt.executeQuery(getPasswordSql);
            if(!rs.next()){
                errorLabel.setText("Email not found");
            }
            else{
                rs.first();
                String queriedPassword = rs.getString(1);
                if(password.equals(queriedPassword)){
                    errorLabel.setText("Logged In");
                }
                else{
                    errorLabel.setText("Invalid credentials");
                }
            }
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
