package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.TrenerRepository;
import hr.java.FitnessCentar.model.entity.Trener;

import java.util.List;
import java.util.Optional;

public class TrenerService extends BaseService<Trener, Integer>{

    public TrenerService(TrenerRepository Trepo) {
        super(Trepo);
    }

    @Override
    public void save(Trener t) throws Exception {
        validate(t);
        super.save(t);
    }

    @Override
    public void update(Integer id, Trener t) throws Exception {
        validate(t);
        super.update(id, t);
    }

    @Override
    public void delete(Integer id) throws Exception {
        super.delete(id);
    }



    Optional<Trener> getById(Integer id) throws Exception {
        return findById(id);
    }

    List<Trener> getByNaziv(String naziv) throws Exception {
        return findAll();
    }


    private void validate(Trener tr){
        if(tr == null){
            throw new IllegalStateException("Trener ne postoji u null!");
        }
        if(tr.getIme() == null || tr.getIme().isBlank()){
            throw new IllegalArgumentException("Trener Ime nepostoji");
        }
        if(tr.GetPrezime()==null || tr.GetPrezime().isBlank()){
            throw new IllegalArgumentException("Trener Prezime nepostoji");
        }
        if(tr.GetCertifikacije()==null || tr.GetCertifikacije().isBlank()){
            throw new IllegalArgumentException("Trener Certifikacije nepostoji");
        }
        if(tr.GetSpecijalizacije()==null || tr.GetSpecijalizacije().isBlank()) {
            throw new IllegalArgumentException("Trener Specijalizacije nepostoji");
        }
        if(tr.GetPhotoPath()==null || tr.GetPhotoPath().isBlank()){
            throw new IllegalArgumentException("Trener PhotoPath nepostoji");
        }
    }
}
