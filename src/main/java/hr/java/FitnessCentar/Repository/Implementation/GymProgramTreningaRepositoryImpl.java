package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.GymProgramTreningaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.OpremaE;
import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Oprema;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class GymProgramTreningaRepositoryImpl implements GymProgramTreningaRepository {

    private final Connection conn;

    public GymProgramTreningaRepositoryImpl() throws Exception{
        conn = DB_Connection.getInstance();
    }

    @Override
    public void addProgramTreningaToGym(int GymID,int ProgramTreningaID) throws Exception {
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("call insert_program_treninga_to_gym(?,?)")){

            stmt.setInt(1, GymID);
            stmt.setInt(2, ProgramTreningaID);

            stmt.executeUpdate();
        }
    }

    @Override
    public void removeProgramTreningaFromGym(int GymID, int ProgramTreningaID) throws Exception{
        try(Connection conn = DB_Connection.getInstance();
            CallableStatement stmt = conn.prepareCall("call remove_program_treninga_from_gym(?,?)")){


            stmt.setInt(1, GymID);
            stmt.setInt(2, ProgramTreningaID);

            stmt.executeUpdate();
        }
    }

    @Override
    public List<programTreninga> getProgramTreningaByGym(int GymID) throws Exception{
        List<programTreninga> programTreninga = new ArrayList<>();

        try(Connection conn = DB_Connection.getInstance();
            PreparedStatement stmt = conn.prepareStatement("select * from get_gym_programi_treninga(?)")){

            stmt.setInt(1, GymID);
            try(ResultSet rs = stmt.executeQuery()) {
                while(rs.next()){
                    programTreninga.add(map(rs));
                }
            }

        }
        return programTreninga;
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
