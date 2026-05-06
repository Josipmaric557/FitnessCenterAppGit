package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.GymOpremaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.OpremaE;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Kategorija;
import hr.java.FitnessCentar.model.entity.Oprema;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymOpremaRepositoryImpl implements GymOpremaRepository {
    private final Connection conn;

    public GymOpremaRepositoryImpl() throws Exception{
       conn = DB_Connection.getInstance();
    }

    @Override
    public void addOpremaToGym(int GymID,int OpremaID) throws Exception{
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("CALL insert_oprema_to_gym(?,?)")){

            stmt.setInt(1, GymID);
            stmt.setInt(2, OpremaID);


            stmt.execute();
        }
    }

    @Override
    public void removeOpremaFromGym(int GymID,int OpremaID) throws Exception{
        try(Connection conn = DB_Connection.getInstance();
        CallableStatement stmt = conn.prepareCall("CALL delete_oprema_to_gym(?,?)")){

            stmt.setInt(1, GymID);
            stmt.setInt(2, OpremaID);


            stmt.execute();

        }
    }

    @Override
    public List<Oprema> getOpremaByGym(int GymID) throws Exception{
        List<Oprema> oprema = new ArrayList<>();
        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_oprema_by_gym(?)")){

            stmt.setInt(1, GymID);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    oprema.add(map(rs));
                }
            }

        }
        return oprema;
    }

    private Oprema map(ResultSet rs) throws SQLException {
        return new Oprema(
                rs.getInt("id"),
                rs.getString("naziv"),
                rs.getString("proizvodac"),
                rs.getInt("kolicina"),
                vrstaGymaE.fromId(rs.getInt("vrsta_gyma_e_id")),
                OpremaE.fromId(rs.getInt("oprema_e_id"))
        );
    }
}
