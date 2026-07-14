package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.KorisnikRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.UlogaE;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Kategorija;
import hr.java.FitnessCentar.model.entity.Korisnik;

import java.sql.*;
import java.util.List;
import java.util.Optional;

import static hr.java.FitnessCentar.model.Enum.vrstaGymaE.KomercijalniGym;

public class KorisnikRepositoryImpl implements KorisnikRepository {

    private Connection conn;

    public KorisnikRepositoryImpl() throws Exception {
        DB_Connection.getInstance();
    }


    @Override
    public void save(Korisnik kor) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("call insert_korisnik(?,?,?,?)")){

            stmt.setString(1, kor.GetKorisnikIme());
            stmt.setString(2, kor.getEmail());
            stmt.setString(3, kor.getLozinka());
            stmt.setInt(4, toId(kor.GetUlogaE()));

            stmt.executeUpdate();

        }
    }

    @Override
    public void update(Integer id, Korisnik kor) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
        CallableStatement stmt = conn.prepareCall("call update_korisnik(?,?,?,?,?)")){

            stmt.setInt(1, id);
            stmt.setString(2, kor.GetKorisnikIme());
            stmt.setString(3, kor.getEmail());
            stmt.setString(4, kor.getLozinka());
            stmt.setInt(5, toId(kor.GetUlogaE()));

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("call delete_korisnik(?)")){

            stmt.setInt(1, id);

            stmt.executeUpdate();
        }
    }

    @Override
    public Optional<Korisnik> findById(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_korisnik_by_id(?)")){

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
    public Optional<Korisnik> findByUsername(String username) throws Exception {

        try (Connection conn = DB_Connection.getInstance();
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT * FROM get_korisnik_by_username(?)")) {

            stmt.setString(1, username);

            try (ResultSet rs = stmt.executeQuery()) {

                if (rs.next()) {
                    return Optional.of(map(rs));
                }
            }

        }

        return Optional.empty();
    }

    @Override
    public List<Korisnik> findAll() throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_all_korisnik()")){

            List<Korisnik> kor = new java.util.ArrayList<>();


            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                kor.add(map(rs));
            }
            return kor;
        }
    }

    public int toId(UlogaE u) {
        switch (u) {
            case Korisnik:
                return 1;
            case Admin:
                return 2;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + u);
        }
    }

    private Korisnik map(ResultSet rs) throws SQLException {
        return new Korisnik(
                rs.getInt("id"),
                rs.getString("korisnik_ime"),
                rs.getString("email"),
                rs.getString("lozinka"),
                UlogaE.valueOf(rs.getString("uloga"))
        );
    }
}
