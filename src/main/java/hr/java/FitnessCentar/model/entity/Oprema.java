package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.OpremaE;

public class Oprema {
    private int id;
    private String Naziv;
    private String Proizvodac;
    private int Kolicina;
    Gym gym_ID;
    Kategorija kategorija_id;
    OpremaE opremaE;

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
}
