package hr.java.FitnessCentar.model.Enum;

public enum OpremaE {
    TreningSnageOprema,
    CardioOprema,
    CalisthenicsOprema,
    BorilackaOprema,
    OporavakOprema;

    public static OpremaE fromId(int id) {
        switch (id) {
            case 1:
                return TreningSnageOprema;
            case 2:
                return CardioOprema;
            case 3:
                return CalisthenicsOprema;
            case 4:
                return BorilackaOprema;
            case 5:
                return OporavakOprema;
            default:
                throw new IllegalArgumentException("Nepoznata vrijednost: " + id);
        }
    }
}
