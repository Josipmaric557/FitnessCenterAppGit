package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.OpremaE;

import java.util.Objects;

public class Oprema {
    private int id;
    private String Naziv;
    private String Proizvodac;
    private int Kolicina;
    Gym gym_ID;
    Kategorija kategorija_id;
    OpremaE opremaE;

    public Oprema(){

    }

    public Oprema(int id, String Naziv, String Proizvodac, int Kolicina, Gym gym_ID, Kategorija kategorija_id, OpremaE opremaE){
        this.id = id;
        this.Naziv = Naziv;
        this.Proizvodac = Proizvodac;
        this.Kolicina = Kolicina;
        this.gym_ID = gym_ID;
        this.kategorija_id = kategorija_id;
        this.opremaE = opremaE;

    }

    public int GetId(){
        return id;
    }

    public void SetId(int id){
        this.id = id;
    }

    public String GetNaziv(){
        return Naziv;
    }

    public void Setnaziv(String Naziv){
        this.Naziv = Naziv;
    }

    public String GetProizvodac(){
        return Proizvodac;
    }

    public void SetProizvodac(String Proizvodac){
        this.Proizvodac = Proizvodac;
    }

    public int GetKolicina(){
        return Kolicina;
    }

    public void SetKolicina(int Kolicina){
        this.Kolicina = Kolicina;
    }

    public Gym GetGym(){
        return gym_ID;
    }

    public void SetGym(Gym gym_ID){
        this.gym_ID = gym_ID;
    }
    public Kategorija Getkategorija(){
        return kategorija_id;
    }

    public void Setkategorija(Kategorija kategorija_id){
        this.kategorija_id = kategorija_id;
    }

    public OpremaE GetOpremaE(){
        return opremaE;
    }

    public void SetOpremaE(OpremaE opremaE){
        this.opremaE = opremaE;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Oprema oprema = (Oprema) o;
        return id == oprema.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Oprema{" +
                "id=" + id +
                ", Naziv='" + Naziv + '\'' +
                ", Proizvodac='" + Proizvodac + '\'' +
                ", Kolicina=" + Kolicina +
                ", gym_ID=" + gym_ID +
                ", kategorija_id=" + kategorija_id +
                ", opremaE=" + opremaE +
                '}';
    }
}
