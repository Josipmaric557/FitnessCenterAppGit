package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.OpremaRepository;
import hr.java.FitnessCentar.model.entity.Kategorija;
import hr.java.FitnessCentar.model.entity.Oprema;

import java.util.List;
import java.util.Optional;

public class OpremaService extends BaseService<Oprema, Integer>{

        public OpremaService(OpremaRepository repo) {
            super(repo);
        }

        @Override
        public void save(Oprema opr) throws Exception {
            validate(opr);
            super.save(opr);
        }

        public void update(Integer id, Oprema opr) throws Exception {
            validate(opr);
            super.update(id, opr);
        }

        @Override
        public void delete(Integer id) throws Exception {
            super.delete(id);
        }

        public Optional<Oprema> getById(Integer id) throws Exception {
            return findById(id);
        }

        public List<Oprema> getByKategorija(Kategorija kat) throws Exception {
            return findAll();
        }

        private void validate(Oprema op){
            if(op == null){
                throw new IllegalArgumentException("Oprema je null");
            }
            if(op.GetNaziv()==null || op.GetNaziv().isBlank()){
                throw new IllegalArgumentException("Oprema naziv nepostoji");
            }

            if(op.GetProizvodac()==null || op.GetProizvodac().isBlank()){
                throw new IllegalArgumentException("Oprema proizvodac nepostoji");
            }
            if(op.GetKolicina() < 0){
                throw new IllegalArgumentException("Oprema kolicina nepostoji");
            }

            if(op.GetOpremaE() == null){
                throw new IllegalArgumentException("Oprema oprema nepostoji");
            }
            if(op.GetVrstaGyma() == null){
                throw new IllegalArgumentException("Oprema vrsta gyma nepostoji");
            }
        }
}
