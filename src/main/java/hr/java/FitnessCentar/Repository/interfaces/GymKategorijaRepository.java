package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.Kategorija;

import java.util.List;

public interface GymKategorijaRepository {
    void addKategorijaToGym(int KategorijaID, int GymID);
    void removeKategorijaFromGym(int KategorijaID, int GymID);

    List<Kategorija> getKategorijaByGym(int GymID);
}
