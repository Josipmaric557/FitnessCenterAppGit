package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.Trener;

import java.util.List;

public interface GymTrenerRepository {
    void addTrenerToGym(int TrenerID,int GymID);
    void removeTrenerFromGym(int TrenerID, int GymID);

    List<Trener> getTrenersByGym(int GymID);
}
