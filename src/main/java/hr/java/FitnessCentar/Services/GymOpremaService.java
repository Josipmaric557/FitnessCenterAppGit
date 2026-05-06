package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.GymOpremaRepository;
import hr.java.FitnessCentar.model.entity.Oprema;

import java.util.List;

public class GymOpremaService {

    private final GymOpremaRepository repo;

    public GymOpremaService(GymOpremaRepository repo) throws Exception{
        this.repo = repo;
    }

    public void assignOpremaToGym(int gymID, int opremaId) throws Exception {
        repo.addOpremaToGym(gymID, opremaId);
    }

    public void removeOpremaFromGym(int gymID, int opremaId) throws Exception {
        repo.removeOpremaFromGym(gymID, opremaId);
    }

    public List<Oprema> getOpremaByGymId(int gymId) throws Exception {
        return repo.getOpremaByGym(gymId);
    }
}
