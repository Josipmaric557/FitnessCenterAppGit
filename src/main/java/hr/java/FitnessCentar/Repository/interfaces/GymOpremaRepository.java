package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.Oprema;

import java.util.List;

public interface GymOpremaRepository {
    void addOpremaToGym(int OpremaID, int GymID);
    void removeOpremaFromGym(int OpremaID, int GymID);

    List<Oprema> getOpremaByGym(int GymID);
}
