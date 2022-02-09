package EMeter;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.ResourceBundle;

public class CustomerDashboardController implements Initializable {
    public Label nameLabel;
    public TableView<MeterTable> meterTable;
    public TableColumn<MeterTable,Integer> meterNumber;
    public TableColumn<MeterTable,Double> usage;
    public TableColumn<MeterTable,String> month;
    public TableColumn<MeterTable,Integer> year;
    public TableColumn<MeterTable,Double> dues;

    private ArrayList<Integer> meterIds;

    ObservableList<MeterTable> obtab = FXCollections.observableArrayList();

    @FXML
    private void logout(ActionEvent actionEvent) throws IOException {
        Parent root;
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("Login.fxml"));
        appStage.setScene(new Scene(root,640,480));
        appStage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resources){
        try {
            String getUserSql = "select name from Users where user_id='" + LoginController.userId + "';";
            Statement stmt = Main.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
            ResultSet rs= stmt.executeQuery(getUserSql);
            if(rs.next()) {
                nameLabel.setText(rs.getString(1));
            }

            this.getTableData();
        }
//        catch(SQLException e){
//                e.printStackTrace();
//        }
        catch(Exception e){
            e.printStackTrace();
        }


    }

    public void getTableData() throws SQLException{
        Statement stmt = Main.con.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
        String getMetersSql = "select m.meterid,current,month,year from Meter m,Users,CurrUsage u where u.meterid=m.meterid and customerid=user_id and user_id='" + LoginController.userId + "';";
        ResultSet rs= stmt.executeQuery(getMetersSql);
        meterNumber.setCellValueFactory(new PropertyValueFactory<>("meterNumber"));
        usage.setCellValueFactory(new PropertyValueFactory<>("usage"));
        month.setCellValueFactory(new PropertyValueFactory<>("month"));
        year.setCellValueFactory(new PropertyValueFactory<>("year"));
        dues.setCellValueFactory(new PropertyValueFactory<>("dues"));
            while (rs.next()) {
                obtab.add(new MeterTable(rs.getString("month"),rs.getInt("meterid"),rs.getDouble("current"),rs.getInt("year"),getDues(rs.getDouble("current"))));
            }
            meterTable.setItems(obtab);
                }

    private double getDues(double usage){
        double dues;
        if(usage<=50){
            dues= 2.8*usage;
        }
        else if(usage<=100){
            dues=140+3.2*(usage-50);
        }
        else if(usage<=250){
            dues = 300+7.5*(usage-100);
        }
        else{
            dues=1425+10*(usage-250);
        }
        return dues;
    }

    public void generateBill(ActionEvent actionEvent) throws IOException {
        Parent root;
        Stage appStage = (Stage) ((Node) actionEvent.getSource()).getScene().getWindow();
        root = FXMLLoader.load(getClass().getResource("GenerateBill.fxml"));
        appStage.setScene(new Scene(root,640,480));
        appStage.show();
    }
}
