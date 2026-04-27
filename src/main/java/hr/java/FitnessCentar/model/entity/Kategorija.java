package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.vrstaGymaE;

import java.util.Objects;

public class Kategorija {
    private int id;
    private String naziv;
    private String opis;
    vrstaGymaE vrsta;

    public Kategorija(){

    }

    public Kategorija(int id, String naziv, String opis, vrstaGymaE vrsta){
        this.id = id;
        this.naziv = naziv;
        this.opis = opis;
        this.vrsta = vrsta;
    }

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

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Kategorija that = (Kategorija) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "Kategorija{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", opis='" + opis + '\'' +
                ", vrsta=" + vrsta +
                '}';
    }
}
