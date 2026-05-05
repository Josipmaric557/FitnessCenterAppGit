package hr.java.FitnessCentar;

import hr.java.FitnessCentar.Repository.Implementation.GymRepositoryImpl;
import hr.java.FitnessCentar.Repository.interfaces.GymRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.entity.Gym;

import java.math.BigDecimal;
import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;
import java.util.Optional;

public class Main {
    public static void main(String[] args) throws Exception {
        System.out.println("Hello");

        GymRepository repo = new GymRepositoryImpl();
        Gym gym = new Gym("test", "Adresa", "Zagreb", "08-22", new BigDecimal("10.67"), 30, "slika.jpg");

        repo.save(gym);

        Optional<Gym> g = repo.findById(1);

        if(g.isPresent()){
            Gym gm = g.get();

            gm.SetNaziv("hello");
            gm.SetAdresa("Avenija Dubrovnik");
            gm.SetGrad("Zagreb");
            gm.SetRadnoVrijeme("8");
            gm.SetCijenaClanarine(new BigDecimal("10.56"));
            gm.SetUkupnaPovrsina(15);
            gm.SetFotoPath("xcv");

            repo.update(gm.GetId(), gm);

        }else{
            System.out.println("Gym ne postoji");

        }

        int id = 1;

        try{
            repo.delete(id);


        }catch(Exception e){
            e.printStackTrace();
        }

        try{
            Optional<Gym> gymopt = repo.findById(5);
            if(gymopt.isPresent()){
                Gym gym2 = gymopt.get();
                System.out.println(gym2.GetNaziv());
            }else{
                System.out.println("Gym ne postoji");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            List<Gym> gyms = repo.findAll();

            if (gyms.isEmpty()) {
                System.out.println("No gyms found in database.");
            } else {

                for (Gym gym3 : gyms) {
                    System.out.println("ID: " + gym3.GetId());
                    System.out.println("Naziv: " + gym3.GetNaziv());
                    System.out.println("----------------------");
                }
            }

        } catch (Exception e) {
            System.err.println("Error while fetching gyms:");
            e.printStackTrace();
        }
    }

}