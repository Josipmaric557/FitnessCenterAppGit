package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.ProgramTreningaRepository;
import hr.java.FitnessCentar.model.entity.programTreninga;

import java.util.List;
import java.util.Optional;

public class ProgramTreningaService extends BaseService<programTreninga, Integer>{

    public ProgramTreningaService(ProgramTreningaRepository repo) {
        super(repo);
    }

    @Override
    public void save(programTreninga pt) throws Exception {
        validate(pt);
        super.save(pt);
    }

    @Override
    public void update(Integer id, programTreninga pt) throws Exception {
        validate(pt);
        super.update(id, pt);
    }

    @Override
    public void delete(Integer id) throws Exception {
        super.delete(id);
    }

    Optional<programTreninga> getById(Integer id) throws Exception {
        return findById(id);
    }

    List<programTreninga> getByNaziv(String naziv) throws Exception {
        return findAll();
    }

    private void validate(programTreninga pt){
        if(pt == null){
            throw new IllegalArgumentException("Program treninga je null");
        }
        if(pt.GetNaziv()==null || pt.GetNaziv().isBlank()){
            throw new IllegalArgumentException("Naziv program treninga je null");
        }
        if(pt.getProgramTreningaE() == null){
            throw new IllegalArgumentException("Program treningaE je null");
        }
        if(pt.GetOpis()==null || pt.GetOpis().isBlank()){
            throw new IllegalArgumentException("Opis program treninga je null");
        }
        if(pt.GetTrajanjeMin() <= 0){
            throw new IllegalArgumentException("Trajanje minimum program treninga je 0");
        }
        if(pt.getTezinaTreningaE()==null){
            throw new IllegalArgumentException("Tezina program treninga je null");
        }
    }
}
