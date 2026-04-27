package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.UlogaE;

import java.util.List;
import java.util.Objects;

public class Korisnik implements Comparable<Korisnik> {
    private int id;
    private String korisnikIme;
    private String email;
    private String lozinka;
    UlogaE uloga;
    //mozemo napraviti i sucelje za trenera ako budem imao vremena kada se on ulogira da zna koje korisnike ima danas u kolko sati koji trening
    //da si moze radit plan

    public Korisnik(int id, String KorisnikIme, String email, String lozinka, UlogaE uloga){
        this.id = id;
        this.korisnikIme = KorisnikIme;
        this.email = email;
        this.lozinka = lozinka;
        this.uloga = uloga;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Korisnik korisnik = (Korisnik) o;
        return id == korisnik.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Korisnik{" +
                "id=" + id +
                ", korisnikIme='" + korisnikIme + '\'' +
                ", email='" + email + '\'' +
                ", lozinka='" + lozinka + '\'' +
                ", uloga=" + uloga +
                '}';
    }

    @Override
    public int compareTo(Korisnik o) {
        return this.id - o.id;
    }
}
