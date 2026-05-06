package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.Trener;

import java.util.List;

public interface GymTrenerRepository {
    void addTrenerToGym(int GymID, int TrenerID) throws Exception;
    void removeTrenerFromGym(int GymID, int TrenerID) throws Exception;

    List<Trener> getTrenersByGym(int GymID) throws Exception;
}
