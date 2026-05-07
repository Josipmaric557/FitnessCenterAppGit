package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.TrenerProgramTreningaRepository;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.util.List;

public class TrenerProgramTreningaService {
 // napraviti validation kad bude exceptione radili

    //
     //
    private final TrenerProgramTreningaRepository repo;

    public TrenerProgramTreningaService(TrenerProgramTreningaRepository repo) {

        this.repo = repo;
    }

    public void addProgramTreningaToTrainer(int TrenerID, int ProgramTreningaID) throws Exception {
        repo.addProgramTreningaToTrener(TrenerID, ProgramTreningaID);
    }

    public void removeProgramTreningaFromTrainer(int TrenerID, int ProgramTreningaID) throws Exception {
        repo.removeProgramTreningaFromTrener(TrenerID, ProgramTreningaID);
    }

    public List<programTreninga> getProgramTreningaByTrener(int TrenerID) throws Exception {
       return repo.getprogramTreningaByTrener(TrenerID);
    }



}
