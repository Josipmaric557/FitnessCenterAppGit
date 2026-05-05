package hr.java.FitnessCentar.model.entity;

import java.util.Objects;

public class Trener implements Comparable<Trener>{
    private int id;
    private String ime;
    private String prezime;
    private String certifikacije;
    private String specijalizacije;
    private String PhotoPath;

    public Trener(){

    }

    public Trener(String ime, String prezime, String certifikacije, String specijalizacije, String PhotoPath) {
        this.ime = ime;
        this.prezime = prezime;
        this.certifikacije = certifikacije;
        this.specijalizacije = specijalizacije;
        this.PhotoPath = PhotoPath;
    }

    public Trener(int id, String ime, String prezime, String certifikacije, String specijalizacije, String PhotoPath) {
        this.id = id;
        this.ime = ime;
        this.prezime = prezime;
        this.certifikacije = certifikacije;
        this.specijalizacije = specijalizacije;
        this.PhotoPath = PhotoPath;
    }

    public int GetId(){
        return id;
    }

    public void SetId(int id){
        this.id = id;
    }

    public String getIme(){
        return ime;
    }

    public void SetIme(String ime){
        this.ime = ime;
    }

    public String GetPrezime(){
        return prezime;
    }

    public void SetPrezime(String prezime){
        this.prezime = prezime;
    }

    public String GetCertifikacije(){
        return certifikacije;
    }

    public void SetCertifikacije(String certifikacije){
        this.certifikacije = certifikacije;
    }

    public String GetSpecijalizacije(){
        return specijalizacije;
    }

    public void SetSpecijalizacije(String specijalizacije){
        this.specijalizacije = specijalizacije;
    }

    public String GetPhotoPath(){
        return PhotoPath;
    }

    public void SetPhotoPath(String PhotoPath){
        this.PhotoPath = PhotoPath;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Trener trener = (Trener) o;
        return id == trener.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return "Trener{" +
                "id=" + id +
                ", ime='" + ime + '\'' +
                ", prezime='" + prezime + '\'' +
                ", certifikacije='" + certifikacije + '\'' +
                ", specijalizacije='" + specijalizacije + '\'' +
                ", PhotoPath='" + PhotoPath + '\'' +
                '}';
    }

    @Override
    public int compareTo(Trener o) {
        return this.id-o.id;
    }

}
