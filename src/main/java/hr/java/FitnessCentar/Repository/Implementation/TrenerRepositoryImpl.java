package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.TrenerRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;
import hr.java.FitnessCentar.model.entity.Trener;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Optional;

public class TrenerRepositoryImpl implements TrenerRepository {

    private Connection connection;

    public TrenerRepositoryImpl() throws Exception {
        DB_Connection.getInstance();
    }


    @Override
    public void save(Trener tr) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt =  conn.prepareCall("call insert_trener(?,?,?,?,?)")){

            stmt.setString(1, tr.getIme());
            stmt.setString(2, tr.GetPrezime());
            stmt.setString(3, tr.GetCertifikacije());
            stmt.setString(4, tr.GetSpecijalizacije());
            stmt.setString(5, tr.GetPhotoPath());

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Integer id, Trener tr) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt =  conn.prepareCall("call update_trener(?,?,?,?,?,?)")) {

            stmt.setInt(1, id);
            stmt.setString(2, tr.getIme());
            stmt.setString(3, tr.GetPrezime());
            stmt.setString(4, tr.GetCertifikacije());
            stmt.setString(5, tr.GetSpecijalizacije());
            stmt.setString(6, tr.GetPhotoPath());

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt =  conn.prepareCall("call delete_trener(?)")) {

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Trener> findById(Integer integer) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt =  conn.prepareStatement("select * from get_trener_by_id(?)")) {

            stmt.setInt(1, integer);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }
            return Optional.empty();
        }
    }

    @Override
    public List<Trener> findAll() throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt =  conn.prepareStatement("select * from get_all_treneri()")) {

            List<Trener> tr = new java.util.ArrayList<>();


            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                tr.add(map(rs));
            }
            return tr;
        }
    }

    private Trener map(ResultSet rs) throws Exception {
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
