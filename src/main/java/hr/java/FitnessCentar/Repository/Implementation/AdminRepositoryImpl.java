package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.AdminRepository;
import hr.java.FitnessCentar.Util.DB_Connection;

import java.sql.CallableStatement;
import java.sql.Connection;

public class AdminRepositoryImpl implements AdminRepository{

        @Override
        public void resetDatabase() throws Exception {

            try(Connection conn = DB_Connection.getInstance();
                CallableStatement stmt =
                        conn.prepareCall("CALL reset_database()")) {

                stmt.execute();
            }
        }
    }

