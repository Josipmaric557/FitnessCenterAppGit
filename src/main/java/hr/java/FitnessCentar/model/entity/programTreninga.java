package hr.java.FitnessCentar.model.entity;

import hr.java.FitnessCentar.model.Enum.TezinaTreningaE;
import hr.java.FitnessCentar.model.Enum.programTreningaE;

import java.util.Objects;

public class programTreninga {
    private int id;
    private String naziv;
    programTreningaE program;
    private String opis;
    private int trajanjeMin;
    TezinaTreningaE  tezina;


    public programTreninga(){

    }

    public programTreninga(String naziv, programTreningaE program, String opis, int trajanjeMin, TezinaTreningaE tezina){
        this.naziv = naziv;
        this.program = program;
        this.opis = opis;
        this.trajanjeMin = trajanjeMin;
        this.tezina = tezina;
    }

    public programTreninga(int id, String naziv, programTreningaE program, String opis, int trajanjeMin, TezinaTreningaE tezina) {
        this.id = id;
        this.naziv = naziv;
        this.program = program;
        this.opis = opis;
        this.trajanjeMin = trajanjeMin;
        this.tezina = tezina;
    }


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
        return program;
    }

    public void SetProgramTreningaE(programTreningaE program){
        this.program = program;
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
        return tezina;
    }

    public void SetTezinaTreningaE(TezinaTreningaE tezinaTreninga){
        this.tezina = tezina;
    }

    public programTreningaE getProgramTreningaE() {
        return program;
    }

    public void setProgramTreningaE(programTreningaE program) {
        this.program = program;
    }

    public TezinaTreningaE getTezinaTreningaE() {
        return tezina;
    }

    public void setTezinaTreningaE(TezinaTreningaE tezina) {
        this.tezina = tezina;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        programTreninga that = (programTreninga) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(id);
    }

    @Override
    public String toString() {
        return "programTreninga{" +
                "id=" + id +
                ", naziv='" + naziv + '\'' +
                ", programTreningaE=" + program +
                ", opis='" + opis + '\'' +
                ", trajanjeMin=" + trajanjeMin +
                ", tezinaTreninga=" + tezina +
                '}';
    }
}
