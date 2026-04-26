package hr.java.FitnessCentar.model.entity;

import java.util.List;

public class Gym {
    private int id;
    private String naziv;
    private String adresa;
    private String radnoVrijeme;
    private double cijenaClanarine;
    private int ukupnaPovrsina;
    private String fotoPath;
    Kategorija kategorija;

    List<Trener> trener;
    List<Oprema> oprema;
    List<Kategorija> kategorija1;
    List<programTreninga> programTreninga;

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

    public String GetRadnoVrijeme(){
        return radnoVrijeme;
    }

    public void SetRadnoVrijeme(String radnoVrijeme){
        this.radnoVrijeme = radnoVrijeme;
    }

    public double GetCijenaClanarine(){
        return cijenaClanarine;
    }

    public void SetCijenaClanarine(double cijenaClanarine){
        this.cijenaClanarine = cijenaClanarine;
    }

    public int GetUkupnaPovrsina(){
        return ukupnaPovrsina;
    }

    public void SetUkupnaPovrsina(int ukupnaPovrsina){
        this.ukupnaPovrsina = ukupnaPovrsina;
    }

    private String GetFotoPath(){
        return fotoPath;
    }

    public void SetFotoPath(String fotoPath){
        this.fotoPath = fotoPath;
    }

    public Kategorija GetKategorija(){
        return kategorija;
    }

    private void SetKategorija(Kategorija kategorija){
        this.kategorija = kategorija;
    }

    public List<Trener> GetTrener(){
        return trener;
    }

    public void SetTrener(List<Trener> trener){
        this.trener = trener;
    }

    public List<Oprema> GetOprema(){
        return oprema;
    }

    public void SetOprema(List<Oprema> oprema){
        this.oprema = oprema;
    }

    public List<Kategorija> Getkategorija(){
        return kategorija1;
    }

    public void SetKategorija(List<Kategorija> kategorija){
        this.kategorija1 = kategorija;
    }

    public List<programTreninga> GetProgramTreninga(){
        return programTreninga;
    }

    public void SetProgramTreninga(List<programTreninga> programTreninga){
        this.programTreninga = programTreninga;
    }
}
