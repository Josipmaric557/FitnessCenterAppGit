package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.programTreninga;

import java.util.List;

public interface GymProgramTreningaRepository {
    void addProgramTreningaToGym(int GymID,int ProgramTreningaID) throws Exception;
    void removeProgramTreningaFromGym(int GymID,int ProgramTreningaID) throws Exception;

    List<programTreninga> getProgramTreningaByGym(int GymID) throws Exception;

}
