package hr.java.FitnessCentar;

import hr.java.FitnessCentar.Repository.Implementation.GymRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.KategorijaRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.KorisnikRepositoryImpl;
import hr.java.FitnessCentar.Repository.Implementation.OpremaRepositoryImpl;
import hr.java.FitnessCentar.Repository.interfaces.GymRepository;
import hr.java.FitnessCentar.Repository.interfaces.KategorijaRepository;
import hr.java.FitnessCentar.Repository.interfaces.KorisnikRepository;
import hr.java.FitnessCentar.Repository.interfaces.OpremaRepository;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.OpremaE;
import hr.java.FitnessCentar.model.Enum.UlogaE;
import hr.java.FitnessCentar.model.Enum.vrstaGymaE;
import hr.java.FitnessCentar.model.entity.Gym;
import hr.java.FitnessCentar.model.entity.Kategorija;
import hr.java.FitnessCentar.model.entity.Korisnik;
import hr.java.FitnessCentar.model.entity.Oprema;

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

     OpremaRepository oprema = new OpremaRepositoryImpl();

     Oprema opr = new Oprema("sdfd", "sdfsdfsd", 5, vrstaGymaE.CrossFitGym, OpremaE.CalisthenicsOprema);

     oprema.save(opr);

        Oprema kor2 = new Oprema(
                "noviUsername123",   // ✔ mora biti drugačiji od postojećih
                "novi@mail.com",
                7,
                vrstaGymaE.KomercijalniGym,
                OpremaE.CalisthenicsOprema
        );

        // ID korisnika koji želiš update-at (mora postojati u bazi)
        int id = 2;

        oprema.update(id, kor2);



        try{
            Optional<Oprema> gymopt = oprema.findById(3);
            if(gymopt.isPresent()){
                Oprema gym2 = gymopt.get();
                System.out.println(gym2.GetNaziv());
            }else{
                System.out.println("Gym ne postoji");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try{

            oprema.delete(3);

        }catch(Exception e){
            System.out.println(e);
        }

        try {
            List<Oprema> gyms = oprema.findAll();

            if (gyms.isEmpty()) {
                System.out.println("No gyms found in database.");
            } else {

                for (Oprema gym3 : gyms) {
                    System.out.println("ID: " + gym3.GetId());
                    System.out.println("Naziv: " + gym3.toString());
                    System.out.println("----------------------");
                }
            }

        } catch (Exception e) {
            System.err.println("Error while fetching gyms:");
            e.printStackTrace();
        }


    }
}
        /*
        try{
            Optional<Oprema> gymopt = oprema.findById(2);
            if(gymopt.isPresent()){
                Oprema gym2 = gymopt.get();
                System.out.println(gym2.GetNaziv());
            }else{
                System.out.println("Gym ne postoji");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        KorisnikRepository repo = new KorisnikRepositoryImpl();


        Korisnik kor = new Korisnik("johsigpgbh", "josibbhp@gmail.com", "1234b5", UlogaE.Korisnik);

        repo.save(kor);


        Korisnik kor2 = new Korisnik(
                "noviUsername123",   // ✔ mora biti drugačiji od postojećih
                "novi@mail.com",
                "novaLozinka",
                UlogaE.Admin
        );

        // ID korisnika koji želiš update-at (mora postojati u bazi)
        int id = 1;

        repo.update(id, kor2);


        try{
            Optional<Korisnik> gymopt = repo.findById(5);
            if(gymopt.isPresent()){
                Korisnik gym2 = gymopt.get();
                System.out.println(gym2.GetKorisnikIme());
            }else{
                System.out.println("Gym ne postoji");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        try {
            List<Korisnik> gyms = repo.findAll();

            if (gyms.isEmpty()) {
                System.out.println("No gyms found in database.");
            } else {

                for (Korisnik gym3 : gyms) {
                    System.out.println("ID: " + gym3.GetId());
                    System.out.println("Naziv: " + gym3.GetKorisnikIme());
                    System.out.println("----------------------");
                }
            }

        } catch (Exception e) {
            System.err.println("Error while fetching gyms:");
            e.printStackTrace();
        }

         */
