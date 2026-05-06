package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.GymTrenerRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;
import hr.java.FitnessCentar.model.entity.Trener;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymTrenerRepositoryImpl implements GymTrenerRepository {

    private final Connection conn;

    public GymTrenerRepositoryImpl() throws Exception{
        conn = DB_Connection.getInstance();
    }

    @Override
    public void addTrenerToGym(int GymID, int TrenerID) throws Exception{
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("Call insert_trener_to_gym(?,?)")){

            stmt.setInt(1, GymID);
            stmt.setInt(2, TrenerID);

            stmt.executeUpdate();
        }
    }

    @Override
    public void removeTrenerFromGym(int GymID, int TrenerID) throws Exception{
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("Call delete_trener_from_gym(?,?)")){

            stmt.setInt(1, GymID);
            stmt.setInt(2, TrenerID);

            stmt.executeUpdate();
        }

    }

    @Override
    public List<Trener> getTrenersByGym(int GymID) throws Exception{

        List<Trener> treneri = new ArrayList<Trener>();

        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_gym_treneri(?)")){

            stmt.setInt(1, GymID);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    treneri.add(map(rs));
                }
            }
        }
        return treneri;
    }

    private Trener map(ResultSet rs) throws SQLException {
        return new Trener(
                rs.getInt("id"),
                rs.getString("ime"),
                rs.getString("prezime"),
                rs.getString("certifikacije"),
                rs.getString("specijalizacije"),
                rs.getString("photo_path")
        );
    }
}
