package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.UlogaE;

import java.util.List;

public class Korisnik {
    private int id;
    private String korisnikIme;
    private String email;
    private String lozinka;
    UlogaE uloga;
    //mozemo napraviti i sucelje za trenera ako budem imao vremena kada se on ulogira da zna koje korisnike ima danas u kolko sati koji trening
    //da si moze radit plan

    public int GetId(){
        return id;
    }

    public void SetId(int id){
        this.id = id;
    }

    public String GetKorisnikIme(){
        return korisnikIme;
    }

    public void SetkorisnikIme(String korisnikIme){
        this.korisnikIme = korisnikIme;
    }

    public String getEmail(){
        return email;
    }

    public void SetEmail(String email){
        this.email = email;
    }

    public String getLozinka(){
        return lozinka;
    }

    public void SetLozinka(String lozinka){
        this.lozinka = lozinka;
    }

    public UlogaE GetUlogaE(){
        return uloga;
    }

    public void SetUlogaE(UlogaE uloga){
        this.uloga = uloga;
    }
}
