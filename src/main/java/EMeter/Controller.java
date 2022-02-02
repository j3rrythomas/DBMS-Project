package EMeter;

import javafx.event.ActionEvent;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Controller {
    public Label outputLabel;
    public TextField textField;
    public Button button;

    public void button(ActionEvent actionEvent) {
        JDBC jdbc = new JDBC();
        Connection con = jdbc.getConnection();

        try {
            String insertSql = "INSERT INTO User VALUES ('"+textField.getText()+"')";
            Statement stmt = con.createStatement();
            stmt.executeUpdate(insertSql);
            String selectSql = "SELECT * FROM User";
            StringBuilder userNames = new StringBuilder();
            ResultSet rs = stmt.executeQuery(selectSql);
            while(rs.next()){
                userNames.append(rs.getString(1));
                userNames.append(" ");
            }
            outputLabel.setText(new String(userNames));
        }
        catch(Exception e){
            e.printStackTrace();
        }

    }
}
