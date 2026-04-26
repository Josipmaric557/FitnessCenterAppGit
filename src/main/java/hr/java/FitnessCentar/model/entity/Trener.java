package hr.java.FitnessCentar.model.entity;

public class Trener {
    private int id;
    private String ime;
    private String prezime;
    private String certifikacije;
    private String specijalizacije;
    private String PhotoPath;

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
}
