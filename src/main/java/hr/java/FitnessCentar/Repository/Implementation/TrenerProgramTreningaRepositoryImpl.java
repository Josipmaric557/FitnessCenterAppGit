package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.TrenerProgramTreningaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;
import hr.java.FitnessCentar.model.entity.Trener;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrenerProgramTreningaRepositoryImpl implements TrenerProgramTreningaRepository {

    private final Connection conn;

    public TrenerProgramTreningaRepositoryImpl() throws Exception{
        conn = DB_Connection.getInstance();
    }

    @Override
    public void addProgramTreningaToTrener(int TrenerID, int ProgramTreningaID) throws Exception{
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("CALL insert_program_treninga_to_trener(?,?)")){

            stmt.setInt(1, TrenerID);
            stmt.setInt(2, ProgramTreningaID);

            stmt.executeUpdate();

        }
    }

    @Override
    public void removeProgramTreningaFromTrener(int TrenerID, int ProgramTreningaID) throws Exception{
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("CALL delete_program_treninga_from_trener(?,?)")){

            stmt.setInt(1, TrenerID);
            stmt.setInt(2, ProgramTreningaID);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<programTreninga> getprogramTreningaByTrener(int TrenerID) throws Exception{

        List<programTreninga> trening = new ArrayList<programTreninga>();

        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_program_treninga_by_trener(?) ")){

            stmt.setInt(1, TrenerID);

            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    trening.add(map(rs));
                }
            }

        }
        return trening;
    }

    private programTreninga map(ResultSet rs) throws SQLException {
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
