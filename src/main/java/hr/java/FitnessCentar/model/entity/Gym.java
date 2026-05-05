package hr.java.FitnessCentar.model.entity;

import java.math.BigDecimal;
import java.util.Objects;

public class Gym implements Comparable<Gym>{
    private int id;
    private String naziv;
    private String adresa;
    private String grad;
    private String radnoVrijeme;
    private BigDecimal cijenaClanarine;
    private int ukupnaPovrsina;
    private String fotoPath;



    public Gym(String naziv, String adresa, String grad, String radnoVrijeme, BigDecimal cijenaClanarine, int ukupnaPovrsina, String fotoPath){
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
        this.radnoVrijeme = radnoVrijeme;
        this.cijenaClanarine = cijenaClanarine;
        this.ukupnaPovrsina = ukupnaPovrsina;
        this.fotoPath = fotoPath;
    }

    public Gym(int id, String naziv, String adresa, String grad, String radnoVrijeme, BigDecimal cijenaClanarine, int ukupnaPovrsina, String fotoPath){

        this.id = id;
        this.naziv = naziv;
        this.adresa = adresa;
        this.grad = grad;
        this.radnoVrijeme = radnoVrijeme;
        this.cijenaClanarine = cijenaClanarine;
        this.ukupnaPovrsina = ukupnaPovrsina;
        this.fotoPath = fotoPath;

    }

    public int GetId(){
        return id;
    };

    public void SetID(int id){
        this.id = id;
    }

    public String GetNaziv(){
        return naziv;
    }

    public void SetNaziv(String naziv){
        this.naziv = naziv;
    }

    public String GetAdresa(){
        return adresa;
    }

    public void SetAdresa(String adresa){
        this.adresa = adresa;
    }

    public String GetGrad(){
        return grad;
    }

    public void SetGrad(String grad){
        this.grad = grad;
    }

    public String GetRadnoVrijeme(){
        return radnoVrijeme;
    }

    public void SetRadnoVrijeme(String radnoVrijeme){
        this.radnoVrijeme = radnoVrijeme;
    }

    public BigDecimal GetCijenaClanarine(){
        return cijenaClanarine;
    }

    public void SetCijenaClanarine(BigDecimal cijenaClanarine){
        this.cijenaClanarine = cijenaClanarine;
    }

    public int GetUkupnaPovrsina(){
        return ukupnaPovrsina;
    }

    public void SetUkupnaPovrsina(int ukupnaPovrsina){
        this.ukupnaPovrsina = ukupnaPovrsina;
    }

    public String GetFotoPath(){
        return fotoPath;
    }

    public void SetFotoPath(String fotoPath){
        this.fotoPath = fotoPath;
    }




    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Gym gym = (Gym) o;
        return id == gym.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Gym{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", adresa='" + adresa + '\'' +
                ", grad='" + grad + '\'' +
                ", radnoVrijeme='" + radnoVrijeme + '\'' +
                ", cijenaClanarine=" + cijenaClanarine +
                ", ukupnaPovrsina=" + ukupnaPovrsina +
                ", fotoPath='" + fotoPath + '\'' +
                '}';
    }

    @Override
    public int compareTo(Gym o) {
        return this.id - o.id;
    }
}
