package hr.java.FitnessCentar;

import hr.java.FitnessCentar.Repository.Implementation.*;
import hr.java.FitnessCentar.Repository.interfaces.*;
import hr.java.FitnessCentar.Util.DB_Connection;
import hr.java.FitnessCentar.model.Enum.*;
import hr.java.FitnessCentar.model.entity.*;

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

        TrenerRepository oprema = new TrenerRepositoryImpl();

        Trener opr = new Trener("josip", "maric", "asdfgdfg", "sdfasg", "sdsdg");

        oprema.save(opr);


        Trener kor2 = new Trener("JOSIP", "maric", "asdfgdfg", "sdfasg", "sdsdg");


        // ID korisnika koji želiš update-at (mora postojati u bazi)
        int id = 1;

        oprema.update(id, kor2);

        try{

            oprema.delete(1);

        }catch(Exception e){
            System.out.println(e);
        }

        try{
            Optional<Trener> gymopt = oprema.findById(9);
            if(gymopt.isPresent()){
                Trener gym2 = gymopt.get();
                System.out.println(gym2.getIme());
            }else{
                System.out.println("Gym ne postoji");
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }


        try {
            List<Trener> gyms = oprema.findAll();

            if (gyms.isEmpty()) {
                System.out.println("No gyms found in database.");
            } else {

                for (Trener gym3 : gyms) {
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










        -----------------------------------------------------------------------------------------------
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
