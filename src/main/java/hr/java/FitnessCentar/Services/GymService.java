package hr.java.FitnessCentar.Services;

import hr.java.FitnessCentar.Repository.interfaces.GymRepository;
import hr.java.FitnessCentar.model.entity.*;

import java.math.BigDecimal;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;

public class GymService extends BaseService<Gym, Integer> {


    public GymService(GymRepository gymRepository){
        super(gymRepository);
    }

    @Override
    public void save(Gym gym) throws Exception {
        validate(gym);
        super.save(gym);
    }

    @Override
    public void update(Integer id, Gym gym) throws Exception {
        validate(gym);
        super.update(id,gym);
    }

    @Override
    public void delete(Integer id) throws Exception {
        super.delete(id);
    }

    public Gym getGymByIdOrError(Integer id) throws Exception {
        return findById(id)
                .orElseThrow(() -> new Exception("Gym nepostoji"));
    }




    private void validate(Gym gym){
        if(gym == null){
            throw new IllegalArgumentException("Gym nesmije biti prazan");
        }

        if(gym.GetNaziv() == null || gym.GetNaziv().isBlank()){
            throw new IllegalArgumentException("Gym naziv nesmije biti prazan");
        }

        if(gym.GetAdresa() == null || gym.GetAdresa().isBlank()){
            throw new IllegalArgumentException("Gym adresa nesmije biti prazan");
        }

        if(gym.GetGrad() == null || gym.GetGrad().isBlank()){
            throw new IllegalArgumentException("Gym grad nesmije biti prazan");
        }

        if(gym.GetRadnoVrijeme() == null || gym.GetRadnoVrijeme().isBlank()){
            throw new IllegalArgumentException("Gym radno vrijeme nesmije biti prazan");
        }

        if(gym.GetCijenaClanarine() == null || gym.GetCijenaClanarine().compareTo(BigDecimal.ZERO) < 0){
            throw new IllegalArgumentException("Gym cijena nesmije biti prazan");
        }

        if(gym.GetUkupnaPovrsina() <= 0){
            throw new IllegalArgumentException("Gym ukupna povrsina nesmije biti prazan");
        }

        if(gym.GetFotoPath() == null || gym.GetFotoPath().isBlank()){
            throw new IllegalArgumentException("Gym foto nesmije biti prazan");
        }
    }

    public List<Gym> sortByPrice() throws Exception{
        return findAll().stream()
                .sorted(Comparator.comparing(Gym::GetCijenaClanarine))
                .toList();
    }

    public List<Gym> sortByName() throws Exception{
        return findAll().stream()
                .sorted(Comparator.comparing(Gym::GetNaziv))
                .toList();
    }

    public List<Gym> filterByCity(String city) throws Exception{
        return findAll().stream()
                .filter(g -> g.GetGrad().equalsIgnoreCase(city))
                .toList();
    }

    public List<Gym> filterByBiggestPrice(BigDecimal biggestPrice) throws Exception{
        return findAll().stream()
                .filter(g ->g.GetCijenaClanarine().compareTo(biggestPrice) <= 0)
                .toList();
    }

    public boolean isMoreExpensiveGymExists(BigDecimal price) throws Exception{
        return findAll().stream()
                .anyMatch(g -> g.GetCijenaClanarine().compareTo(price) > 0);
    }

    public Optional<Gym> findFirstCheapGym(BigDecimal price) throws Exception{
        return findAll().stream()
                .filter(g->g.GetCijenaClanarine().compareTo(price) <= 0)
                .findFirst();
    }


    private void assignTrainerToGym(Gym gym, Trener trener){
        gym.getTreneri().add(trener);
    }

    private void removeTrainerFromGym(Gym gym, Trener trener){
        gym.getTreneri().remove(trener);
    }

    private void assignProgramToGym(Gym gym, programTreninga program){
        gym.getProgrami().add(program);
    }

    private void removeProgramFromGym(Gym gym, programTreninga program){
        gym.getProgrami().remove(program);
    }

    private void assignOpremaToGym(Gym gym, Oprema oprema){
        gym.getOprema().add(oprema);
    }

    private void removeOpremaFromGym(Gym gym, Oprema oprema){
        gym.getOprema().remove(oprema);
    }

    private void assignKategorijaToGym(Gym gym, Kategorija kategorija){
        gym.setKategorija(kategorija);
    }





}
