package EMeter;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBC {
    private Connection connection;
    public Connection getConnection(){
        String dbName="EMeter";
        String dbUrl="jdbc:mysql://localhost:3306/"+dbName;
        String userName="root";
        String password="";
        try {
            connection = DriverManager.getConnection(dbUrl, userName, password);
        }
        catch(SQLException e){
            e.printStackTrace();
        }
        return connection;
    }
}
