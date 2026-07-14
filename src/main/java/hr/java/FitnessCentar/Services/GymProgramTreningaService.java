package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.GymProgramTreningaRepository;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.util.List;

public class GymProgramTreningaService {

    private final GymProgramTreningaRepository repo;

    public GymProgramTreningaService(GymProgramTreningaRepository repo) {
        this.repo = repo;
    }

    public void addProgramTreningaToGym(int GymID,int ProgramTreningaID) throws Exception {
        repo.addProgramTreningaToGym(GymID, ProgramTreningaID);
    }

    public void removeProgramTreningaFromGym(int GymID,int ProgramTreningaID) throws Exception {
        repo.removeProgramTreningaFromGym(GymID, ProgramTreningaID);
    }

    public List<programTreninga> getProgramTreningaByGymId(int GymID) throws Exception {
        return repo.getProgramTreningaByGym(GymID);
    }
}
