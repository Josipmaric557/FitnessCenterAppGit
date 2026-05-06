package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.programTreninga;

import java.util.List;

public interface TrenerProgramTreningaRepository {
    void addProgramTreningaToTrener(int TrenerID, int ProgramTreningaID) throws Exception;
    void removeProgramTreningaFromTrener(int TrenerID, int ProgramTreningaID) throws Exception;

    List<programTreninga> getprogramTreningaByTrener(int TrenerID) throws Exception;
}
