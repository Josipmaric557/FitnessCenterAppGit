package hr.java.FitnessCentar;

import hr.java.FitnessCentar.Util.DB_Connection;

import java.sql.SQLException;

public class Main {
    public static void main(String[] args) {
        try {
            System.out.println(DB_Connection.getInstance());
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}