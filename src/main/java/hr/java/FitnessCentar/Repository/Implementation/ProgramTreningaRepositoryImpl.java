package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.ProgramTreningaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.OpremaE;
import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Oprema;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.sql.*;
import java.util.List;
import java.util.Optional;

public class ProgramTreningaRepositoryImpl implements ProgramTreningaRepository {

    private Connection conn;

    public ProgramTreningaRepositoryImpl() throws SQLException {
        DB_Connection.getInstance();
    }


    @Override
    public void save(programTreninga pt) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt =  conn.prepareCall("call insert_program_treninga(?,?,?,?,?)")){

            stmt.setString(1, pt.GetNaziv());
            stmt.setInt(2, toIdProgramTreninga(pt.GetProgramTreningaE()));
            stmt.setString(3, pt.GetOpis());
            stmt.setInt(4, pt.GetTrajanjeMin());
            stmt.setInt(5, toIdTezinaTreninga(pt.GetTezinaTreningaE()));

            stmt.executeUpdate();
        }
    }

    @Override
    public void update(Integer id, programTreninga pt) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt =  conn.prepareCall("call update_program_treninga(?,?,?,?,?,?)")){

            stmt.setInt(1, id);
            stmt.setString(2, pt.GetNaziv());
            stmt.setInt(3, toIdProgramTreninga(pt.GetProgramTreningaE()));
            stmt.setString(4, pt.GetOpis());
            stmt.setInt(5, pt.GetTrajanjeMin());
            stmt.setInt(6, toIdTezinaTreninga(pt.GetTezinaTreningaE()));

            stmt.executeUpdate();

        }
    }

    @Override
    public void delete(Integer id) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt =  conn.prepareCall("call delete_program_treninga(?)")){

            stmt.setInt(1, id);

            stmt.executeUpdate();

        }
    }

    @Override
    public Optional<programTreninga> findById(Integer integer) throws Exception {
        try (Connection conn = DB_Connection.getInstance();
             PreparedStatement stmt = conn.prepareStatement("select * from get_program_treninga_by_id(?)")) {

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
    public List<programTreninga> findAll() throws Exception {
        try (Connection conn = DB_Connection.getInstance();
             PreparedStatement stmt = conn.prepareStatement("select * from get_all_program_treninga()")) {

        List<programTreninga> pt = new java.util.ArrayList<>();


        ResultSet rs = stmt.executeQuery();

        while(rs.next()){
            pt.add(map(rs));
        }
        return pt;
        }
    }



    public int toIdProgramTreninga(programTreningaE a) {
        switch (a) {
            case CrossFit:
                return 1;
            case Bodybuilding:
                return 2;
            case FunkcionalniTrening:
                return 3;
            case GrupniFitness:
                return 4;
                case BorilackaVjestina:
                    return 5;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + a);
        }
    }



    public int toIdTezinaTreninga(TezinaTreningaE a) {
        switch (a) {
            case slabo:
                return 1;
            case srednje:
                return 2;
            case tesko:
                return 3;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + a);
        }
    }

        private programTreninga map(ResultSet rs) throws Exception {
            return new programTreninga(
                    rs.getInt("id"),
                    rs.getString("naziv"),
                    programTreningaE.fromId(rs.getInt("program_treninga_e_id")),
                    rs.getString("opis"),
                    rs.getInt("trajanje_min"),
                    TezinaTreningaE.fromId(rs.getInt("tezina_treninga_e_id"))
            );
        }
}
