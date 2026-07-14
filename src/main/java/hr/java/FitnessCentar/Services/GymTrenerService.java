package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.GymTrenerRepository;
import hr.java.FitnessCentar.model.entity.Trener;

import java.util.List;

public class GymTrenerService {

    private final GymTrenerRepository repo;

    public GymTrenerService(GymTrenerRepository repo) {
        this.repo = repo;
    }

    public void addTrenerToGym(int GymID, int TrenerID) throws Exception {
        repo.addTrenerToGym(GymID, TrenerID);
    }

    public void removeTrenerFromGym(int GymID, int TrenerID) throws Exception {
        repo.removeTrenerFromGym(GymID, TrenerID);
    }
    public List<Trener> getTreneriByGymId(int GymID) throws Exception {
        return repo.getTrenersByGym(GymID);
    }
}
