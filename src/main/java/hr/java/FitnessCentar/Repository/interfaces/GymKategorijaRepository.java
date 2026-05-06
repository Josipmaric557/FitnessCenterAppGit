package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.Kategorija;

import java.util.List;

public interface GymKategorijaRepository {
    void addKategorijaToGym(int GymID,int KategorijaID) throws Exception;
    void removeKategorijaFromGym(int GymID ,int KategorijaID) throws Exception;

    List<Kategorija> getKategorijaByGym(int GymID) throws Exception;
}
