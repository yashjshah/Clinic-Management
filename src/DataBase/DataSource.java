package DataBase;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by mohamed on 3/25/2017.
 */

public class DataSource {

    private  String url="jdbc:mysql://localhost:3306";
    private  String login="root";
    private  String password="root";
    private Connection connection;

    private  DataSource datasource;

    public DataSource() {
        try{
            connection = DriverManager.getConnection(url,login,password);
        } catch(SQLException e){
            e.printStackTrace();
        }
    }

    public Connection getConnection() {
        return connection;
    }


    public  DataSource getInstance() {
        if(datasource==null) {
            datasource = new DataSource();
        }
        return datasource;

    }



}
