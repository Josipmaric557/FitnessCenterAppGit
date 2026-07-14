package hr.java.FitnessCentar.Repository.interfaces;

import hr.java.FitnessCentar.model.entity.Korisnik;

import java.util.Optional;

public interface KorisnikRepository extends Repository<Korisnik, Integer>{

    Optional<Korisnik> findByUsername(String username) throws Exception;
}
