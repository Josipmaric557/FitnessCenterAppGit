package hr.java.FitnessCentar.Util;

import java.io.InputStream;
import java.sql.*;
import java.util.Properties;

public class DB_Connection {
    private static Connection con;

    private DB_Connection(){}

    public static Connection getInstance() throws SQLException{
        if(con == null || con.isClosed()){
            try(InputStream input = DB_Connection.class.getClassLoader().getResourceAsStream("db.properties")){
                Properties prop = new Properties();
                prop.load(input);

                con = DriverManager.getConnection(
                        prop.getProperty("db.url"),
                        prop.getProperty("db.user"),
                        prop.getProperty("db.password")
                );

            }
            catch(Exception e){
                throw new SQLException("DB connection error", e);
            }

        }
        return con;
    }
}
