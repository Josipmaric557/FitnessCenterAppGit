package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.KorisnikRepository;
import hr.java.FitnessCentar.model.entity.Kategorija;
import hr.java.FitnessCentar.model.entity.Korisnik;

import java.util.List;
import java.util.Optional;

public class KorisnikService extends BaseService<Korisnik, Integer>{

    public KorisnikService(KorisnikRepository korRep) {
        super(korRep);
    }
    @Override
    public void save(Korisnik kor) throws Exception {
        validate(kor);
        super.save(kor);
    }

    @Override
    public void update(Integer id, Korisnik kor) throws Exception {
        validate(kor);
        super.update(id, kor);
    }

    @Override
    public void delete(Integer id) throws Exception {
        super.delete(id);
    }

    public Optional<Korisnik> getById(Integer id) throws Exception {
        return findById(id);
    }

    public List<Korisnik> getByKategorija(Kategorija kat) throws Exception {
        return findAll();
    }



    private void validate(Korisnik kor){
        if(kor == null){
            throw new IllegalArgumentException("Korisnik ne postoji");
        }

        if(kor.GetKorisnikIme() == null || kor.GetKorisnikIme().isBlank()){
            throw new IllegalArgumentException("Korisnik ime ne postoji");
        }

        if(kor.getEmail() == null || kor.getEmail().isBlank()){
            throw new IllegalArgumentException("Korisnik email ne postoji");
        }

        if(kor.GetUlogaE() == null){
            throw new IllegalArgumentException("Korisnik uloga ne postoji");
        }
    }
}
