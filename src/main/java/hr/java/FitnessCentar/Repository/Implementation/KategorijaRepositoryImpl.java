package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.KategorijaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Kategorija;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class KategorijaRepositoryImpl implements KategorijaRepository {

    private Connection conn;

    public KategorijaRepositoryImpl() throws Exception{
        DB_Connection.getInstance();
    }

    @Override
    public void save(Kategorija kat) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("CALL insert_kategorija(?,?,?)")){

            stmt.setString(1, kat.GetNaziv());
            stmt.setString(2, kat.GetOpis());
            stmt.setInt(3, toId(kat.GetVrstaGymaE()));

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Integer id, Kategorija kat) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
        CallableStatement stmt = conn.prepareCall("CALL update_kategorija(?,?,?,?)")){

            stmt.setInt(1, id);
            stmt.setString(2, kat.GetNaziv());
            stmt.setString(3, kat.GetOpis());
            stmt.setInt(4, toId(kat.GetVrstaGymaE()));

            stmt.executeUpdate();

        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("CALL delete_kategorija(?)")){

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public Optional<Kategorija> findById(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM get_kategorija_by_id(?)")){

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
    public List<Kategorija> findAll() throws Exception {
        List<Kategorija> kat = new java.util.ArrayList<>();

        try(Connection conn = DB_Connection.getInstance();
        PreparedStatement stmt = conn.prepareStatement("SELECT * FROM get_all_kategorija()")){

            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                kat.add(map(rs));
            }
            return kat;
        }
    }

    public int toId(vrstaGymaE v) {
        switch (v) {
            case KomercijalniGym:
                return 1;
            case PowerliftingGym:
                return 2;
            case CrossFitGym:
                return 3;
            case CombatGym:
                return 4;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + v);
        }
    }

    private Kategorija map(ResultSet rs) throws SQLException {
        return new Kategorija(
                rs.getInt("id"),
                rs.getString("naziv"),
                rs.getString("opis"),
                vrstaGymaE.valueOf(rs.getString("vrsta"))

        );
    }
}
