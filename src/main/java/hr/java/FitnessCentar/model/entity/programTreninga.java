package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;

public class programTreninga {
    private int id;
    private String naziv;
    programTreningaE programTreningaE;
    private String opis;
    private int trajanjeMin;
    TezinaTreningaE  tezinaTreninga;

    public int Getid(){
        return id;
    }

    public void SetId(int id){
        this.id = id;
    }

    public String GetNaziv(){
        return naziv;
    }

    public void Setnaziv(String naziv){
        this.naziv = naziv;
    }

    public programTreningaE GetProgramTreningaE(){
        return programTreningaE;
    }

    public void SetProgramTreningaE(programTreningaE programTreningaE){
        this.programTreningaE = programTreningaE;
    }

    public String GetOpis(){
        return opis;
    }

    public void SetOpis(String opis){
        this.opis = opis;
    }

    public int GetTrajanjeMin(){
        return trajanjeMin;
    }

    public void SettrajanjeMin(int trajanjeMin){
        this.trajanjeMin = trajanjeMin;
    }

    public TezinaTreningaE GetTezinaTreningaE(){
        return tezinaTreninga;
    }

    public void SetTezinaTreningaE(TezinaTreningaE tezinaTreninga){
        this.tezinaTreninga = tezinaTreninga;
    }
}
