package hr.java.FitnessCentar.Repository.Implementation;

import hr.java.FitnessCentar.Repository.interfaces.GymRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.entity.Gym;
import jdk.internal.classfile.impl.Util;

import java.sql.Connection;
import java.util.List;
import java.util.Optional;

public class GymRepositoryImpl implements GymRepository {

    private final Connection conn;

    public GymRepositoryImpl() throws Exception{
        conn = DB_Connection.getInstance();
    }

    @Override
    public void save(Gym entity) throws Exception {

    }

    @Override
    public void update(Gym entity) throws Exception {

    }

    @Override
    public void delete(Integer integer) throws Exception {

    }

    @Override
    public Optional<Gym> findById(Integer integer) throws Exception {
        return Optional.empty();
    }

    @Override
    public List<Gym> findAll() throws Exception {
        return List.of();
    }
}
