package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.Oprema;

import java.util.List;

public interface GymOpremaRepository {
    void addOpremaToGym(int GymID,int OpremaID) throws Exception;
    void removeOpremaFromGym(int GymID,int OpremaID) throws Exception;

    List<Oprema> getOpremaByGym(int GymID) throws Exception;
}
