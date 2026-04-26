package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.vrstaGymaE;

public class Kategorija {
    private int id;
    private String naziv;
    private String opis;
    vrstaGymaE vrsta;


    public int GetId(){
        return id;
    }

    public void SetId(int id){
        this.id = id;
    }

    public String GetNaziv(){
        return naziv;
    }

    public void SetNaziv(String naziv){
        this.naziv = naziv;
    }

    public String GetOpis(){
        return opis;
    }

    public void SetOpis(String opis){
        this.opis = opis;
    }

    public vrstaGymaE GetVrstaGymaE(){
        return vrsta;
    }

    public void SetVrstagymaE(vrstaGymaE vrsta){
        this.vrsta = vrsta;
    }
}
