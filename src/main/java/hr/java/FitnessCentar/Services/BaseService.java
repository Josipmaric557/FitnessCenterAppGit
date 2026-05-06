package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.Repository;
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.Kategorija;
import hr.java.FitnessCentar.model.entity.Korisnik;

import java.util.List;
import java.util.Optional;

public abstract class BaseService<T, ID> {

    protected Repository<T, ID> repository;

    public BaseService(Repository<T, ID> repository) {
        this.repository = repository;
    }

    public void save(T entity) throws Exception {

        repository.save(entity);
    }

    public void update(ID id, T entity) throws Exception {
        repository.update(id, entity);
    }

    public void delete(ID id) throws Exception {
        repository.delete(id);
    }

    public Optional<T> findById(ID id) throws Exception {
        return repository.findById(id);
    }

    public List<T> findAll() throws Exception {
        return repository.findAll();
    }
}
