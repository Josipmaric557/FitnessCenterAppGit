package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.KategorijaRepository;
import hr.java.FitnessCentar.model.entity.Kategorija;

import java.util.List;
import java.util.Optional;

public class KategorijaService extends BaseService<Kategorija, Integer>{

    public KategorijaService(KategorijaRepository kategorija){
        super(kategorija);
    }

    @Override
    public void save(Kategorija kat) throws Exception{
        validate(kat);
        super.save(kat);
    }

    @Override
    public void update(Integer id, Kategorija kat) throws Exception{
        validate(kat);
        super.update(id, kat);
    }

    @Override
    public void delete(Integer id) throws Exception{
        super.delete(id);
    }

    public Optional<Kategorija> getById(Integer id) throws Exception {
        return findById(id);
    }

    public List<Kategorija> getAll() throws Exception {
        return findAll();
    }


    private void validate(Kategorija kat){

        if(kat == null){
            throw new IllegalArgumentException("kategorija je null");
        }

        if(kat.GetNaziv() == null || kat.GetNaziv().isBlank()){
            throw new IllegalArgumentException("kategorija naziv je null");
        }
    }
}
