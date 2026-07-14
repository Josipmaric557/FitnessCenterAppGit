package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.GymKategorijaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Kategorija;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymKategorijaRepositoryImpl implements GymKategorijaRepository {

    private final Connection conn;

    public GymKategorijaRepositoryImpl() throws Exception{
        conn = DB_Connection.getInstance();
    }


    @Override
    public void addKategorijaToGym(int GymID, int KategorijaID) throws Exception{
        try(Connection con = DB_Connection.getInstance();
            CallableStatement stmt = con.prepareCall("CALL insert_kategorija_to_gym(?,?)")){

            stmt.setInt(1, GymID);
            stmt.setInt(2, KategorijaID);


            stmt.executeUpdate();

        }
    }

    @Override
    public void removeKategorijaFromGym(int GymID, int KategorijaID) throws Exception{
        try(Connection con = DB_Connection.getInstance();
            CallableStatement stmt = con.prepareCall("CALL delete_kategorija_from_gym(?,?)")){

            stmt.setInt(1, GymID);
            stmt.setInt(2, KategorijaID);


            stmt.executeUpdate();
        }
    }


    @Override
    public List<Kategorija> getKategorijaByGym(int GymID) throws Exception{
        List<Kategorija> kategorije = new ArrayList<>();
        try(Connection con = DB_Connection.getInstance();
            PreparedStatement stmt = con.prepareStatement("select * from get_gym_kategorije(?)")){
            stmt.setInt(1, GymID);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    kategorije.add(map(rs));
                }
            }
        }
        return kategorije;
        }


    private Kategorija map(ResultSet rs) throws SQLException {
        return new Kategorija(
                rs.getInt("id"),
                rs.getString("naziv"),
                rs.getString("opis"),
                vrstaGymaE.fromId(rs.getInt("vrsta_gyma_e_id"))
        );
    }
}
