package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.programTreninga;

import java.util.List;

public interface GymProgramTreningaRepository {
    void addProgramTreningaToGym(int ProgramTreningaID, int GymID);
    void removeProgramTreningaFromGym(int GymID);

    List<programTreninga> getProgramTreningaByGym(int GymID);

}
