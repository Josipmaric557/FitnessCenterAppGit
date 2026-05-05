package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.GymRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.entity.Gym;

import java.sql.*;
import java.util.List;
import java.util.Optional;


public class GymRepositoryImpl implements GymRepository {

    private final Connection conn;

    public GymRepositoryImpl() throws Exception{
        conn = DB_Connection.getInstance();
    }

    @Override
    public void save(Gym gym) throws Exception {
        try(Connection con = DB_Connection.getInstance();
            CallableStatement stmt = con.prepareCall("{CALL insert_gym(?,?,?,?,?,?,?)}")){

            stmt.setString(1, gym.GetNaziv());
            stmt.setString(2, gym.GetAdresa());
            stmt.setString(3, gym.GetGrad());
            stmt.setString(4, gym.GetRadnoVrijeme());
            stmt.setBigDecimal(5, gym.GetCijenaClanarine());
            stmt.setInt(6, gym.GetUkupnaPovrsina());
            stmt.setString(7, gym.GetFotoPath());

            stmt.executeUpdate();
        }
    }



    @Override
    public void update(Integer id, Gym gym) throws Exception {
        try(Connection con = DB_Connection.getInstance();
            CallableStatement stmt = con.prepareCall("CALL update_gym(?,?,?,?,?,?,?,?)")){

            stmt.setInt(1, id);
            stmt.setString(2, gym.GetNaziv());
            stmt.setString(3, gym.GetAdresa());
            stmt.setString(4, gym.GetGrad());
            stmt.setString(5, gym.GetRadnoVrijeme());
            stmt.setBigDecimal(6, gym.GetCijenaClanarine());
            stmt.setInt(7, gym.GetUkupnaPovrsina());
            stmt.setString(8, gym.GetFotoPath());


            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(Connection con = DB_Connection.getInstance();
            CallableStatement stmt = con.prepareCall("CALL delete_gym(?)")){

            stmt.setInt(1,id);
            stmt.executeUpdate();
        }
    }



    @Override
    public Optional<Gym> findById(Integer id) throws Exception {
        try(Connection con = DB_Connection.getInstance();
             PreparedStatement stmt = con.prepareStatement("SELECT * FROM get_gym_by_id(?)")){

            stmt.setInt(1, id);

            try(ResultSet rs = stmt.executeQuery()) {
                if(rs.next()){
                    return Optional.of(map(rs));
                }
            }
        }
        return Optional.empty();
    }

    @Override
    public List<Gym> findAll() throws Exception {
        List<Gym> gyms = new java.util.ArrayList<>();

        try(Connection con = DB_Connection.getInstance();
        PreparedStatement stmt = con.prepareStatement("SELECT * FROM get_all_gyms()")){
            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                gyms.add(map(rs));
            }
        }

        return gyms;
    }


    private Gym map(ResultSet rs) throws SQLException {
        return new Gym(
                rs.getInt("id"),
                rs.getString("naziv"),
                rs.getString("adresa"),
                rs.getString("grad"),
                rs.getString("radno_vrijeme"),
                rs.getBigDecimal("cijena_clanarine"),
                rs.getInt("ukupna_povrsina"),
                rs.getString("foto_path")
        );
    }

}
