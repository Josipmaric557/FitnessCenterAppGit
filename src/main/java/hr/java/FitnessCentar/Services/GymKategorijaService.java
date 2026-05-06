package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.GymKategorijaRepository;
import hr.java.FitnessCentar.model.entity.Kategorija;

import java.util.List;

public class GymKategorijaService {

    private final GymKategorijaRepository repo;

    public GymKategorijaService(GymKategorijaRepository repo) {
        this.repo = repo;
    }

    public void assignKategorijaToGym(int gymId,int kategorijaId) throws Exception {
        repo.addKategorijaToGym(gymId, kategorijaId);
    }

    public void removeKategorijaFromGym(int gymId,int kategorijaId) throws Exception {
        repo.removeKategorijaFromGym(gymId, kategorijaId);
    }


    public List<Kategorija> getKategorijaByGymId(int gymId) throws Exception {
        return repo.getKategorijaByGym(gymId);
    }
}
