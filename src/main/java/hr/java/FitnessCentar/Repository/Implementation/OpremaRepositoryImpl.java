package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.OpremaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.OpremaE;
import hr.java.FitnessCentar.model.Enum.UlogaE;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.Korisnik;
import hr.java.FitnessCentar.model.entity.Oprema;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class OpremaRepositoryImpl implements OpremaRepository {
    private Connection conn;

    public OpremaRepositoryImpl() throws Exception{
        DB_Connection.getInstance();
    }

    @Override
    public void save(Oprema opr) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("CALL insert_oprema(?,?,?,?,?)")){

            stmt.setString(1, opr.GetNaziv());
            stmt.setString(2, opr.GetProizvodac());
            stmt.setInt(3, opr.GetKolicina());
            stmt.setInt(4, toIdGym(opr.GetVrstaGyma()));
            stmt.setInt(5, toIdOprema(opr.GetOpremaE()));

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Integer id, Oprema opr) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("call update_oprema(?,?,?,?,?,?)")){

            stmt.setInt(1, id);
            stmt.setString(2, opr.GetNaziv());
            stmt.setString(3, opr.GetProizvodac());
            stmt.setInt(4, opr.GetKolicina());
            stmt.setInt(5, toIdGym(opr.GetVrstaGyma()));
            stmt.setInt(6, toIdOprema(opr.GetOpremaE()));

            stmt.executeUpdate();
        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("call delete_oprema(?)")){

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public Optional<Oprema> findById(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_oprema_by_id(?)")){

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
    public List<Oprema> findAll() throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_all_oprema()")){

            List<Oprema> opr = new java.util.ArrayList<>();


            ResultSet rs = stmt.executeQuery();

            while(rs.next()){
                opr.add(map(rs));
            }
            return opr;
        }
    }

    public int toIdGym(vrstaGymaE a) {
        switch (a) {
            case KomercijalniGym:
                return 1;
            case PowerliftingGym:
                return 2;
            case CrossFitGym:
                return 3;
            case CombatGym:
                return 4;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + a);
        }
    }




    public int toIdOprema(OpremaE a) {
        switch (a) {
            case TreningSnageOprema:
                return 1;
            case CardioOprema:
                return 2;
            case  CalisthenicsOprema:
                return 3;
            case BorilackaOprema:
                return 4;
              case OporavakOprema:
                  return 5;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + a);
        }
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
